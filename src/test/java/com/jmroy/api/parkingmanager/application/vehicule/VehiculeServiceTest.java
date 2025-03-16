package com.jmroy.api.parkingmanager.application.vehicule;

import com.jmroy.api.parkingmanager.application.location.LocationService;
import com.jmroy.api.parkingmanager.application.owner.OwnerService;
import com.jmroy.api.parkingmanager.domain.location.Location;
import com.jmroy.api.parkingmanager.domain.location.LocationName;
import com.jmroy.api.parkingmanager.domain.owner.Owner;
import com.jmroy.api.parkingmanager.domain.vehicule.Vehicule;
import com.jmroy.api.parkingmanager.domain.vehicule.VehiculeRepository;
import com.jmroy.api.parkingmanager.domain.vehicule.VehiculeType;
import com.jmroy.api.parkingmanager.exception.location.CapacityExceededException;
import com.jmroy.api.parkingmanager.exception.location.LocationNotFoundException;
import com.jmroy.api.parkingmanager.exception.owner.OwnerNotFoundException;
import com.jmroy.api.parkingmanager.exception.vehicule.InvalidEqYearException;
import com.jmroy.api.parkingmanager.exception.vehicule.LicencePlateAlreadyExistsException;
import com.jmroy.api.parkingmanager.exception.vehicule.VehiculeNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VehiculeServiceTest {
    private static final long DEFAULT_VEHICULE_ID = 1L;
    private static final String VEHICULE_BRAND = "brand";
    private static final String VEHICULE_MODEL = "model";
    private static final String VEHICULE_NOTE = "note";
    private static final int VEHICULE_EQ_YEAR = 2023;
    private static final String VEHICULE_COLOR = "color";
    private static final String VEHICULE_LICENCE_PLATE = "AA-123-AA";
    private static final long DEFAULT_OWNER_ID = 1L;
    private static final String DEFAULT_OWNER_EMAIL = "test@example.com";
    private static final String DEFAULT_OWNER_FIRST_NAME = "John";
    private static final String DEFAULT_OWNER_LAST_NAME = "Doe";
    private static final String DEFAULT_OWNER_PHONE_NUMBER = "123-456-7890";
    private static final String DEFAULT_OWNER_NOTE = "Some notes";
    private static final long DEFAULT_LOCATION_ID = 1L;
    private static final int DEFAULT_LOCATION_CAPACITY = 10;
    private static final long CREATED_VEHICULE_ID = 2L;
    private static final Long NEW_OWNER_ID = 2L;
    private static final Long NEW_LOCATION_ID = 2L;

    @Mock
    private VehiculeRepository vehiculeRepository;
    @Mock
    private LocationService locationService;
    @Mock
    private OwnerService ownerService;

    @InjectMocks
    private VehiculeService vehiculeService;

    private Owner owner;
    private Vehicule vehicule;
    private VehiculeForm vehiculeForm;
    private Location location;

    @BeforeEach
    void setUp() {
        owner = new Owner(DEFAULT_OWNER_EMAIL, DEFAULT_OWNER_FIRST_NAME, DEFAULT_OWNER_LAST_NAME,
                DEFAULT_OWNER_PHONE_NUMBER,
                DEFAULT_OWNER_NOTE, new HashSet<>());
        owner.setId(DEFAULT_OWNER_ID);
        location = new Location(DEFAULT_LOCATION_CAPACITY, LocationName.SOUTH_FIELD, Set.of(VehiculeType.CAR),
                new HashSet<>());
        location.setId(DEFAULT_LOCATION_ID);
        vehicule = new Vehicule(VEHICULE_BRAND, VEHICULE_MODEL, VEHICULE_NOTE, VEHICULE_EQ_YEAR, VehiculeType.CAR,
                location,
                VEHICULE_COLOR, VEHICULE_LICENCE_PLATE, owner);
        vehicule.setId(DEFAULT_VEHICULE_ID);
        vehiculeForm = new VehiculeForm();
        vehiculeForm.setBrand(VEHICULE_BRAND);
        vehiculeForm.setModel(VEHICULE_MODEL);
        vehiculeForm.setNote(VEHICULE_NOTE);
        vehiculeForm.setEqYear(VEHICULE_EQ_YEAR);
        vehiculeForm.setType(VehiculeType.CAR);
        vehiculeForm.setLocation(location);
        vehiculeForm.setColor(VEHICULE_COLOR);
        vehiculeForm.setLicencePlate(VEHICULE_LICENCE_PLATE);
        vehiculeForm.setOwner(owner);
    }

    @Test
    void getVehiculeById_shouldReturnVehicule_whenVehiculeExists() {
        // Arrange
        when(vehiculeRepository.findById(DEFAULT_VEHICULE_ID)).thenReturn(Optional.of(vehicule));

        // Act
        Vehicule result = vehiculeService.getVehiculeById(DEFAULT_VEHICULE_ID);

        // Assert
        assertNotNull(result);
        assertEquals(vehicule, result);
    }

    @Test
    void getVehiculeById_shouldThrowVehiculeNotFoundException_whenVehiculeDoesNotExist() {
        // Arrange
        when(vehiculeRepository.findById(DEFAULT_VEHICULE_ID)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(VehiculeNotFoundException.class, () -> vehiculeService.getVehiculeById(DEFAULT_VEHICULE_ID));
    }

    @Test
    void createVehicule_shouldCreateVehicule_whenValidData() {
        // Arrange
        Vehicule createdVehicule = new Vehicule(VEHICULE_BRAND, VEHICULE_MODEL, VEHICULE_NOTE, VEHICULE_EQ_YEAR,
                VehiculeType.CAR, location,
                VEHICULE_COLOR, VEHICULE_LICENCE_PLATE, owner);
        createdVehicule.setId(CREATED_VEHICULE_ID);
        when(ownerService.getOwnerById(DEFAULT_OWNER_ID)).thenReturn(owner);
        when(locationService.getLocationById(DEFAULT_LOCATION_ID)).thenReturn(location);
        when(vehiculeRepository.existsByLicencePlate(VEHICULE_LICENCE_PLATE)).thenReturn(false);
        when(vehiculeRepository.save(any(Vehicule.class))).thenReturn(createdVehicule);

        // Act
        Vehicule result = vehiculeService.createVehicule(vehiculeForm);

        // Assert
        assertNotNull(result);
        assertEquals(vehiculeForm.getBrand(), result.getBrand());
        assertEquals(vehiculeForm.getModel(), result.getModel());
        assertEquals(vehiculeForm.getNote(), result.getNote());
        assertEquals(vehiculeForm.getEqYear(), result.getEqYear());
        assertEquals(vehiculeForm.getType(), result.getType());
        assertEquals(vehiculeForm.getLocation(), result.getLocation());
        assertEquals(vehiculeForm.getColor(), result.getColor());
        assertEquals(vehiculeForm.getLicencePlate(), result.getLicencePlate());
        assertEquals(vehiculeForm.getOwner(), result.getOwner());
    }

    @Test
    void createVehicule_shouldThrowOwnerNotFoundException_whenOwnerDoesNotExist() {
        // Arrange
        when(ownerService.getOwnerById(DEFAULT_OWNER_ID)).thenThrow(OwnerNotFoundException.class);
        // Act & Assert
        assertThrows(OwnerNotFoundException.class, () -> vehiculeService.createVehicule(vehiculeForm));
    }

    @Test
    void createVehicule_shouldThrowLicencePlateAlreadyExistsException_whenLicencePlateExists() {
        // Arrange
        when(ownerService.getOwnerById(DEFAULT_OWNER_ID)).thenReturn(owner);
        when(vehiculeRepository.existsByLicencePlate(VEHICULE_LICENCE_PLATE)).thenReturn(true);
        // Act & Assert
        assertThrows(LicencePlateAlreadyExistsException.class, () -> vehiculeService.createVehicule(vehiculeForm));
    }

    @Test
    void createVehicule_shouldThrowInvalidEqYearException_whenEqYearIsInvalid() {
        // Arrange
        vehiculeForm.setEqYear(1899);
        when(ownerService.getOwnerById(DEFAULT_OWNER_ID)).thenReturn(owner);
        when(vehiculeRepository.existsByLicencePlate(VEHICULE_LICENCE_PLATE)).thenReturn(false);
        // Act & Assert
        assertThrows(InvalidEqYearException.class, () -> vehiculeService.createVehicule(vehiculeForm));
    }

    @Test
    void createVehicule_shouldThrowLocationNotFoundException_whenLocationDoesNotExist() {
        // Arrange
        when(ownerService.getOwnerById(DEFAULT_OWNER_ID)).thenReturn(owner);
        when(locationService.getLocationById(DEFAULT_LOCATION_ID)).thenThrow(LocationNotFoundException.class);
        // Act & Assert
        assertThrows(LocationNotFoundException.class, () -> vehiculeService.createVehicule(vehiculeForm));
    }

    @Test
    void createVehicule_shouldThrowCapacityExceededException_whenCapacityExceeded() {
        // Arrange
        when(ownerService.getOwnerById(DEFAULT_OWNER_ID)).thenReturn(owner);
        when(locationService.getLocationById(DEFAULT_LOCATION_ID)).thenReturn(location);
        when(locationService.getLocationVehicules(DEFAULT_LOCATION_ID))
                .thenReturn(Set.of(new Vehicule("other", "other", "other", 2023, VehiculeType.CAR, location,
                        "other", "other", owner)));
        location.setCapacity(0);

        // Act & Assert
        assertThrows(CapacityExceededException.class, () -> vehiculeService.createVehicule(vehiculeForm));
    }

    @Test
    void updateVehicule_shouldUpdateVehicule_whenValidData() {
        // Arrange
        Vehicule updatedVehicule = new Vehicule("updated", "updated", "updated", 2020, VehiculeType.MOTORCYCLE,
                location,
                "updated", "BB-456-BB", owner);
        updatedVehicule.setId(DEFAULT_VEHICULE_ID);
        VehiculeForm updateForm = new VehiculeForm();
        updateForm.setBrand("updated");
        updateForm.setModel("updated");
        updateForm.setNote("updated");
        updateForm.setEqYear(2020);
        updateForm.setType(VehiculeType.MOTORCYCLE);
        updateForm.setLocation(location);
        updateForm.setColor("updated");
        updateForm.setLicencePlate("BB-456-BB");
        updateForm.setOwner(owner);
        when(ownerService.getOwnerById(DEFAULT_OWNER_ID)).thenReturn(owner);
        when(locationService.getLocationById(DEFAULT_LOCATION_ID)).thenReturn(location);
        when(vehiculeRepository.existsByLicencePlate("BB-456-BB")).thenReturn(false);
        when(vehiculeRepository.findById(DEFAULT_VEHICULE_ID)).thenReturn(Optional.of(vehicule));
        when(vehiculeRepository.save(any(Vehicule.class))).thenReturn(updatedVehicule);
        // Act
        Vehicule result = vehiculeService.updateVehicule(DEFAULT_VEHICULE_ID, updateForm);
        // Assert
        assertNotNull(result);
        assertEquals(updateForm.getBrand(), result.getBrand());
        assertEquals(updateForm.getModel(), result.getModel());
        assertEquals(updateForm.getNote(), result.getNote());
        assertEquals(updateForm.getEqYear(), result.getEqYear());
        assertEquals(updateForm.getType(), result.getType());
        assertEquals(updateForm.getLocation(), result.getLocation());
        assertEquals(updateForm.getColor(), result.getColor());
        assertEquals(updateForm.getLicencePlate(), result.getLicencePlate());
        assertEquals(updateForm.getOwner(), result.getOwner());
    }

    @Test
    void updateVehicule_shouldThrowOwnerNotFoundException_whenOwnerDoesNotExist() {
        // Arrange
        when(ownerService.getOwnerById(DEFAULT_OWNER_ID)).thenThrow(OwnerNotFoundException.class);
        // Act & Assert
        assertThrows(OwnerNotFoundException.class,
                () -> vehiculeService.updateVehicule(DEFAULT_VEHICULE_ID, vehiculeForm));
    }

    @Test
    void updateVehicule_shouldThrowVehiculeNotFoundException_whenVehiculeDoesNotExist() {
        // Arrange
        when(vehiculeRepository.findById(DEFAULT_VEHICULE_ID)).thenReturn(Optional.empty());
        // Act & Assert
        assertThrows(VehiculeNotFoundException.class,
                () -> vehiculeService.updateVehicule(DEFAULT_VEHICULE_ID, vehiculeForm));
    }

    @Test
    void updateVehicule_shouldThrowLocationNotFoundException_whenLocationDoesNotExist() {
        // Arrange
        // Location fakeLocation = new Location();
        // fakeLocation.setId(2L);
        // vehiculeForm.setLocation(2L));
        when(ownerService.getOwnerById(DEFAULT_OWNER_ID)).thenReturn(owner);
        when(locationService.getLocationById(DEFAULT_LOCATION_ID)).thenThrow(LocationNotFoundException.class);
        when(vehiculeRepository.findById(DEFAULT_VEHICULE_ID)).thenReturn(Optional.of(vehicule));
        // Act & Assert
        assertThrows(LocationNotFoundException.class,
                () -> vehiculeService.updateVehicule(DEFAULT_VEHICULE_ID, vehiculeForm));
    }

    @Test
    void updateVehicule_shouldThrowLicencePlateAlreadyExistsException_whenLicencePlateExists() {
        // Arrange
        when(ownerService.getOwnerById(DEFAULT_OWNER_ID)).thenReturn(owner);
        when(locationService.getLocationById(DEFAULT_LOCATION_ID)).thenReturn(location);
        when(vehiculeRepository.existsByLicencePlate(VEHICULE_LICENCE_PLATE)).thenReturn(true);
        when(vehiculeRepository.findById(DEFAULT_VEHICULE_ID)).thenReturn(Optional.of(vehicule));
        // Act & Assert
        assertThrows(LicencePlateAlreadyExistsException.class,
                () -> vehiculeService.updateVehicule(DEFAULT_VEHICULE_ID, vehiculeForm));
    }

    @Test
    void updateVehicule_shouldThrowInvalidEqYearException_whenEqYearIsInvalid() {
        // Arrange
        vehiculeForm.setEqYear(1899);
        when(ownerService.getOwnerById(DEFAULT_OWNER_ID)).thenReturn(owner);
        when(locationService.getLocationById(DEFAULT_LOCATION_ID)).thenReturn(location);
        when(vehiculeRepository.existsByLicencePlate(VEHICULE_LICENCE_PLATE)).thenReturn(false);
        when(vehiculeRepository.findById(DEFAULT_VEHICULE_ID)).thenReturn(Optional.of(vehicule));
        // Act & Assert
        assertThrows(InvalidEqYearException.class,
                () -> vehiculeService.updateVehicule(DEFAULT_VEHICULE_ID, vehiculeForm));
    }

    @Test
    void updateVehicule_shouldThrowCapacityExceededException_whenCapacityExceeded() {
        // Arrange
        Location newLocation = new Location(0, LocationName.BARN, Set.of(VehiculeType.CAR), new HashSet<>());
        newLocation.setId(NEW_LOCATION_ID);
        VehiculeForm updatedVehiculeForm = new VehiculeForm();
        updatedVehiculeForm.setBrand(VEHICULE_BRAND);
        updatedVehiculeForm.setModel(VEHICULE_MODEL);
        updatedVehiculeForm.setNote(VEHICULE_NOTE);
        updatedVehiculeForm.setEqYear(VEHICULE_EQ_YEAR);
        updatedVehiculeForm.setType(VehiculeType.CAR);
        updatedVehiculeForm.setLocation(newLocation);
        updatedVehiculeForm.setColor(VEHICULE_COLOR);
        updatedVehiculeForm.setLicencePlate(VEHICULE_LICENCE_PLATE);
        updatedVehiculeForm.setOwner(owner);
        when(ownerService.getOwnerById(DEFAULT_OWNER_ID)).thenReturn(owner);
        when(locationService.getLocationById(NEW_LOCATION_ID)).thenReturn(newLocation);
        when(vehiculeRepository.findById(DEFAULT_VEHICULE_ID)).thenReturn(Optional.of(vehicule));
        when(locationService.getLocationVehicules(NEW_LOCATION_ID))
                .thenReturn(Set.of(new Vehicule("other", "other", "other", 2023, VehiculeType.CAR, newLocation,
                        "other", "other", owner)));
        // Act & Assert
        assertThrows(CapacityExceededException.class,
                () -> vehiculeService.updateVehicule(DEFAULT_VEHICULE_ID, updatedVehiculeForm));
    }

    @Test
    void patchOwner_shouldUpdateOwner_whenValidData() {
        // Arrange
        Owner newOwner = new Owner("newowner@example.com", "New", "Owner", "111-222-3333", "New note",
                new HashSet<>());
        newOwner.setId(NEW_OWNER_ID);
        when(vehiculeRepository.findById(DEFAULT_VEHICULE_ID)).thenReturn(Optional.of(vehicule));
        when(ownerService.getOwnerById(NEW_OWNER_ID)).thenReturn(newOwner);
        when(vehiculeRepository.save(any(Vehicule.class))).thenReturn(vehicule);

        // Act
        Vehicule result = vehiculeService.patchOwner(DEFAULT_VEHICULE_ID, newOwner);

        // Assert
        assertNotNull(result);
        assertEquals(newOwner, result.getOwner());
    }

    @Test
    void patchOwner_shouldThrowVehiculeNotFoundException_whenVehiculeDoesNotExist() {
        // Arrange
        Owner newOwner = new Owner("newowner@example.com", "New", "Owner", "111-222-3333", "New note",
                new HashSet<>());
        newOwner.setId(NEW_OWNER_ID);
        when(vehiculeRepository.findById(DEFAULT_VEHICULE_ID)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(VehiculeNotFoundException.class,
                () -> vehiculeService.patchOwner(DEFAULT_VEHICULE_ID, newOwner));
    }

    @Test
    void patchOwner_shouldThrowOwnerNotFoundException_whenOwnerDoesNotExist() {
        // Arrange
        Owner newOwner = new Owner("newowner@example.com", "New", "Owner", "111-222-3333", "New note",
                new HashSet<>());
        newOwner.setId(NEW_OWNER_ID);
        when(vehiculeRepository.findById(DEFAULT_VEHICULE_ID)).thenReturn(Optional.of(vehicule));
        when(ownerService.getOwnerById(NEW_OWNER_ID)).thenThrow(OwnerNotFoundException.class);

        // Act & Assert
        assertThrows(OwnerNotFoundException.class,
                () -> vehiculeService.patchOwner(DEFAULT_VEHICULE_ID, newOwner));
    }

    @Test
    void patchLocation_shouldUpdateLocation_whenValidData() {
        // Arrange
        Location newLocation = new Location(DEFAULT_LOCATION_CAPACITY, LocationName.SOUTH_FIELD,
                Set.of(VehiculeType.CAR), new HashSet<>());
        newLocation.setId(NEW_LOCATION_ID);
        when(vehiculeRepository.findById(DEFAULT_VEHICULE_ID)).thenReturn(Optional.of(vehicule));
        when(locationService.getLocationById(NEW_LOCATION_ID)).thenReturn(newLocation);
        when(vehiculeRepository.save(any(Vehicule.class))).thenReturn(vehicule);
        when(locationService.getLocationVehicules(NEW_LOCATION_ID)).thenReturn(new HashSet<>());

        // Act
        Vehicule result = vehiculeService.patchLocation(DEFAULT_VEHICULE_ID, newLocation);

        // Assert
        assertNotNull(result);
        assertEquals(newLocation, result.getLocation());
    }

    @Test
    void patchLocation_shouldThrowVehiculeNotFoundException_whenVehiculeDoesNotExist() {
        // Arrange
        Location newLocation = new Location(DEFAULT_LOCATION_CAPACITY, LocationName.SOUTH_FIELD,
                Set.of(VehiculeType.CAR), new HashSet<>());
        newLocation.setId(NEW_LOCATION_ID);
        when(vehiculeRepository.findById(DEFAULT_VEHICULE_ID)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(VehiculeNotFoundException.class,
                () -> vehiculeService.patchLocation(DEFAULT_VEHICULE_ID, newLocation));
    }

    @Test
    void patchLocation_shouldThrowLocationNotFoundException_whenLocationDoesNotExist() {
        // Arrange
        Location newLocation = new Location(DEFAULT_LOCATION_CAPACITY, LocationName.SOUTH_FIELD,
                Set.of(VehiculeType.CAR), new HashSet<>());
        newLocation.setId(NEW_LOCATION_ID);
        when(vehiculeRepository.findById(DEFAULT_VEHICULE_ID)).thenReturn(Optional.of(vehicule));
        when(locationService.getLocationById(NEW_LOCATION_ID)).thenThrow(LocationNotFoundException.class);

        // Act & Assert
        assertThrows(LocationNotFoundException.class,
                () -> vehiculeService.patchLocation(DEFAULT_VEHICULE_ID, newLocation));
    }

    @Test
    void patchLocation_shouldThrowCapacityExceededException_whenCapacityExceeded() {
        // Arrange
        Location newLocation = new Location(0, LocationName.SOUTH_FIELD, Set.of(VehiculeType.CAR), new HashSet<>());
        newLocation.setId(NEW_LOCATION_ID);
        when(vehiculeRepository.findById(DEFAULT_VEHICULE_ID)).thenReturn(Optional.of(vehicule));
        when(locationService.getLocationById(NEW_LOCATION_ID)).thenReturn(newLocation);
        when(locationService.getLocationVehicules(NEW_LOCATION_ID))
                .thenReturn(Set.of(new Vehicule("other", "other", "other", 2023, VehiculeType.CAR, newLocation,
                        "other", "other", owner)));
        // Act & Assert
        assertThrows(CapacityExceededException.class,
                () -> vehiculeService.patchLocation(DEFAULT_VEHICULE_ID, newLocation));
    }
}
