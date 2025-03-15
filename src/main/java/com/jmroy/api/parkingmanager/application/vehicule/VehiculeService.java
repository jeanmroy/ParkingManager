package com.jmroy.api.parkingmanager.application.vehicule;

import com.jmroy.api.parkingmanager.application.location.LocationService;
import com.jmroy.api.parkingmanager.application.owner.OwnerService;
import com.jmroy.api.parkingmanager.domain.location.Location;
import com.jmroy.api.parkingmanager.domain.owner.Owner;
import com.jmroy.api.parkingmanager.domain.vehicule.Vehicule;
import com.jmroy.api.parkingmanager.domain.vehicule.VehiculeRepository;
import com.jmroy.api.parkingmanager.exception.location.CapacityExceededException;
import com.jmroy.api.parkingmanager.exception.location.LocationNotFoundException;
import com.jmroy.api.parkingmanager.exception.owner.OwnerNotFoundException;
import com.jmroy.api.parkingmanager.exception.vehicule.InvalidEqYearException;
import com.jmroy.api.parkingmanager.exception.vehicule.LicencePlateAlreadyExistsException;
import com.jmroy.api.parkingmanager.exception.vehicule.VehiculeNotFoundException;
import java.time.Year;
import java.util.Set;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VehiculeService {

  private final VehiculeRepository vehiculeRepository;
  private final LocationService locationService;
  private final OwnerService ownerService;

  /**
   * Retrieves a vehicle by its unique identifier.
   * 
   * This method fetches a specific vehicle from the database based on the provided ID.
   * 
   * @param id The unique identifier of the vehicle to retrieve. Must not be null.
   * @return The Vehicule entity with the specified ID.
   * @throws VehiculeNotFoundException if no vehicle is found with the given ID.
   */
  public Vehicule getVehiculeById(Long id) {
    return vehiculeRepository.findById(id).orElseThrow(() -> new VehiculeNotFoundException(id));
  }

  /**
   * Creates a new vehicle based on the provided VehiculeForm.
   * 
   * This method validates the input data to ensure data integrity. It performs the following checks:
   * - Verifies the existence of the specified owner.
   * - Ensures the license plate is unique within the system.
   * - Validates that the equipment year (eqYear) is within an acceptable range (1900 to the current year).
   * 
   * If a location is provided in the VehiculeForm, the method also verifies that the location exists.
   * After successful validation, a new Vehicule entity is created and persisted in the database.
   * 
   * @param vehiculeForm The form containing the vehicle's details. Must not be null.
   * @return The newly created and persisted Vehicule entity.
   * @throws OwnerNotFoundException if the specified owner does not exist.
   * @throws LicencePlateAlreadyExistsException if the license plate is already in use.
   * @throws InvalidEqYearException if the equipment year is outside the valid range.
   * @throws LocationNotFoundException if a location is provided, but does not exist.
   */
  @Transactional
  public Vehicule createVehicule(VehiculeForm vehiculeForm) {
    Owner vehiculeFormOwner = ownerService.getOwnerById(vehiculeForm.getOwner().getId());
    Location vehiculeFormLocation;

    if (vehiculeForm.getLocation() != null) {
      vehiculeFormLocation = validateVehiculeLocation(vehiculeForm);
      validateLocationCapacityForVehicule(vehiculeFormLocation);
    } else {
      vehiculeFormLocation = null;
    }
    validateLicencePlateUniqueness(vehiculeForm.getLicencePlate());
    validateEqYear(vehiculeForm.getEqYear());

    Vehicule vehicule = new Vehicule(
        vehiculeForm.getBrand(),
        vehiculeForm.getModel(),
        vehiculeForm.getNote(),
        vehiculeForm.getEqYear(),
        vehiculeForm.getType(),
        vehiculeFormLocation,
        vehiculeForm.getColor(),
        vehiculeForm.getLicencePlate(),
        vehiculeFormOwner);

    return vehiculeRepository.save(vehicule);
  }

  /**
   * Updates an existing vehicle with the details provided in VehiculeForm.
   * 
   * This method retrieves a vehicle based on its ID, updates its attributes with the new
   * information from the VehiculeForm, and then persists the updated vehicle.
   * 
   * This method will throw exceptions if the owner or location does not exist.
   * 
   * @param id The ID of the vehicle to update. Must not be null.
   * @param vehiculeForm The form containing the updated vehicle's details. Must not be null.
   * @return The updated Vehicule entity.
   * @throws VehiculeNotFoundException if no vehicle is found with the given ID.
   * @throws OwnerNotFoundException if the specified owner does not exist.
   * @throws LocationNotFoundException if a location is provided, but does not exist.
   */
  @Transactional
  public Vehicule updateVehicule(Long id, VehiculeForm vehiculeForm) {
    Owner vehiculeFormOwner = ownerService.getOwnerById(vehiculeForm.getOwner().getId());
    Vehicule vehicule = getVehiculeById(id);
    Location vehiculeFormLocation;

    if (vehiculeForm.getLocation() != null) {
      vehiculeFormLocation = validateVehiculeLocation(vehiculeForm);
    } else {
      vehiculeFormLocation = null;
    }

    if (vehiculeFormLocation != null && !vehiculeFormLocation.equals(vehicule.getLocation())) {
      validateLocationCapacityForVehicule(vehiculeFormLocation);
    }

    validateLicencePlateUniqueness(vehiculeForm.getLicencePlate());
    validateEqYear(vehiculeForm.getEqYear());

    vehicule.setOwner(vehiculeFormOwner);
    vehicule.setLocation(vehiculeFormLocation);
    vehicule.setBrand(vehiculeForm.getBrand());
    vehicule.setModel(vehiculeForm.getModel());
    vehicule.setNote(vehiculeForm.getNote());
    vehicule.setEqYear(vehiculeForm.getEqYear());
    vehicule.setColor(vehiculeForm.getColor());
    vehicule.setLicencePlate(vehiculeForm.getLicencePlate());
    vehicule.setType(vehiculeForm.getType());

    return vehiculeRepository.save(vehicule);
  }

  /**
   * Patches (updates) the owner of an existing vehicle.
   * 
   * This method retrieves a vehicle based on its ID, updates only the owner, and then persists the change.
   * 
   * @param id The ID of the vehicle to update. Must not be null.
   * @param owner The new owner to be assigned to the vehicle. Must not be null.
   * @return The updated Vehicule entity.
   * @throws VehiculeNotFoundException if no vehicle is found with the given ID.
   * @throws OwnerNotFoundException if the specified owner does not exist.
   */
  @Transactional
  public Vehicule patchOwner(Long id, Owner owner) {
    Vehicule vehicule = getVehiculeById(id);
    Owner vehiculeOwner = ownerService.getOwnerById(owner.getId());
    vehicule.setOwner(vehiculeOwner);
    return vehiculeRepository.save(vehicule);
  }

  /**
   * Patches (updates) the location of an existing vehicle.
   * 
   * This method retrieves a vehicle based on its ID, updates only the location,
   * and then persists the change.
   * 
   * @param id The ID of the vehicle to update. Must not be null.
   * @param location The new location to be assigned to the vehicle. Must not be null.
   * @return The updated Vehicule entity.
   * @throws VehiculeNotFoundException if no vehicle is found with the given ID.
   * @throws LocationNotFoundException if the specified location does not exist.
   */
  @Transactional
  public Vehicule patchLocation(Long id, Location location) {
    Vehicule vehicule = getVehiculeById(id);
    Location vehiculeLocation = validateVehiculeLocation(new VehiculeForm() {
      {
        setLocation(location);
      }
    });
    validateLocationCapacityForVehicule(vehiculeLocation);
    vehicule.setLocation(vehiculeLocation);
    return vehiculeRepository.save(vehicule);
  }

  // Private validation methods

  private void validateLicencePlateUniqueness(String licencePlate) {
    if (vehiculeRepository.existsByLicencePlate(licencePlate)) {
      throw new LicencePlateAlreadyExistsException(licencePlate);
    }
  }

  private void validateEqYear(int eqYear) {
    if (eqYear < 1900 || eqYear > Year.now().getValue()) {
      throw new InvalidEqYearException(eqYear);
    }
  }

  private Location validateVehiculeLocation(VehiculeForm vehiculeForm) {
    return locationService.getLocationById(vehiculeForm.getLocation().getId());
  }

  private void validateLocationCapacityForVehicule(Location location) {
    if (location != null) {
      Set<Vehicule> currentVehicules = locationService.getLocationVehicules(location.getId());
      if (currentVehicules.size() >= location.getCapacity()) {
        throw new CapacityExceededException(currentVehicules.size(), location.getCapacity());
      }
    }
  }
}
