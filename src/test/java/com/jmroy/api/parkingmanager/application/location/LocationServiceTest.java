package com.jmroy.api.parkingmanager.application.location;

import com.jmroy.api.parkingmanager.domain.location.Location;
import com.jmroy.api.parkingmanager.domain.location.LocationName;
import com.jmroy.api.parkingmanager.domain.location.LocationRepository;
import com.jmroy.api.parkingmanager.domain.owner.Owner;
import com.jmroy.api.parkingmanager.domain.vehicule.Vehicule;
import com.jmroy.api.parkingmanager.domain.vehicule.VehiculeType;
import com.jmroy.api.parkingmanager.exception.location.CapacityExceededException;
import com.jmroy.api.parkingmanager.exception.location.LocationNameAlreadyExistsException;
import com.jmroy.api.parkingmanager.exception.location.LocationNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LocationServiceTest {

    private static final long DEFAULT_LOCATION_ID = 1L;
    private static final int DEFAULT_LOCATION_CAPACITY = 10;
    private static final int DEFAULT_LOCATION_FORM_CAPACITY = 5;
    private static final long CREATED_LOCATION_ID = 2;
    private static final String OWNER_EMAIL = "test@example.com";
    private static final String OWNER_FIRST_NAME = "John";
    private static final String OWNER_LAST_NAME = "Doe";
    private static final String OWNER_PHONE_NUMBER = "123-456-7890";
    private static final String OWNER_NOTE = "Some notes";
    private static final String VEHICULE_BRAND = "brand";
    private static final String VEHICULE_MODEL = "model";
    private static final String VEHICULE_NOTE = "note";
    private static final int VEHICULE_EQ_YEAR = 2023;
    private static final String VEHICULE_COLOR = "color";
    private static final String VEHICULE_LICENCE_PLATE = "AA-123-AA";

    @Mock
    private LocationRepository locationRepository;

    @InjectMocks
    private LocationService locationService;

    private Location location;
    private LocationForm locationForm;

    private Owner owner;

    @BeforeEach
    void setUp() {
        location = new Location(DEFAULT_LOCATION_CAPACITY, LocationName.SOUTH_FIELD, Set.of(VehiculeType.CAR),
                new HashSet<>());
        location.setId(DEFAULT_LOCATION_ID);

        locationForm = new LocationForm();
        locationForm.setCapacity(DEFAULT_LOCATION_FORM_CAPACITY);
        locationForm.setLocationName(LocationName.SOUTH_FIELD);
        locationForm.setAllowedVehiculeTypes(Set.of(VehiculeType.MOTORCYCLE));
        locationForm.setVehicules(null);

        owner = new Owner(OWNER_EMAIL, OWNER_FIRST_NAME, OWNER_LAST_NAME, OWNER_PHONE_NUMBER, OWNER_NOTE,
                new HashSet<>());

    }

    @Test
    void getAllLocations_shouldReturnListOfLocations() {
        // Arrange
        List<Location> locations = new ArrayList<>();
        locations.add(location);
        locations.add(new Location(DEFAULT_LOCATION_CAPACITY, LocationName.SOUTH_FIELD, Set.of(VehiculeType.TRUCK),
                new HashSet<>()));

        when(locationRepository.findAll()).thenReturn(locations);

        // Act
        List<Location> result = locationService.getAllLocations();

        // Assert
        assertEquals(2, result.size());
        assertEquals(locations, result);
    }

    @Test
    void getLocationById_shouldReturnLocation_whenLocationExists() {
        // Arrange
        when(locationRepository.findById(DEFAULT_LOCATION_ID)).thenReturn(Optional.of(location));

        // Act
        Location result = locationService.getLocationById(DEFAULT_LOCATION_ID);

        // Assert
        assertNotNull(result);
        assertEquals(location, result);
    }

    @Test
    void getLocationById_shouldThrowLocationNotFoundException_whenLocationDoesNotExist() {
        // Arrange
        when(locationRepository.findById(DEFAULT_LOCATION_ID)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(LocationNotFoundException.class, () -> locationService.getLocationById(DEFAULT_LOCATION_ID));
    }

    @Test
    void getLocationVehicules_shouldReturnVehicules_whenLocationExists() {
        // Arrange
        Set<Vehicule> vehicules = new HashSet<>();
        vehicules.add(
                new Vehicule(VEHICULE_BRAND, VEHICULE_MODEL, VEHICULE_NOTE, VEHICULE_EQ_YEAR, VehiculeType.CAR,
                        location, VEHICULE_COLOR,
                        VEHICULE_LICENCE_PLATE, owner));
        location.setVehicules(vehicules);
        when(locationRepository.findById(DEFAULT_LOCATION_ID)).thenReturn(Optional.of(location));

        // Act
        Set<Vehicule> result = locationService.getLocationVehicules(DEFAULT_LOCATION_ID);
        // Assert
        assertEquals(vehicules, result);
    }

    @Test
    void getLocationVehicules_shouldThrowLocationNotFoundException_whenLocationDoesNotExist() {
        // Arrange
        when(locationRepository.findById(DEFAULT_LOCATION_ID)).thenReturn(Optional.empty());
        // Act & Assert
        assertThrows(LocationNotFoundException.class, () -> locationService.getLocationVehicules(DEFAULT_LOCATION_ID));
    }

    @Test
    void createLocation_shouldCreateLocation_whenValidData() {
        // Arrange
        Location createdLocation = new Location(DEFAULT_LOCATION_FORM_CAPACITY, LocationName.SOUTH_FIELD,
                Set.of(VehiculeType.MOTORCYCLE),
                new HashSet<>());
        createdLocation.setId(CREATED_LOCATION_ID);
        when(locationRepository.save(any(Location.class))).thenReturn(createdLocation);

        // Act
        Location result = locationService.createLocation(locationForm);

        // Assert
        assertNotNull(result);
        assertEquals(locationForm.getCapacity(), result.getCapacity());
        assertEquals(locationForm.getLocationName(), result.getLocationName());
        assertEquals(locationForm.getAllowedVehiculeTypes(), result.getAllowedVehiculeTypes());
    }

    @Test
    void createLocation_shouldThrowCapacityExceededException_whenCapacityIsNegative() {
        // Arrange
        locationForm.setCapacity(-1);
        // Act & Assert
        assertThrows(CapacityExceededException.class, () -> locationService.createLocation(locationForm));
    }

    @Test
    void createLocation_shouldThrowLocationNameAlreadyExistsException_whenNameExists() {
        // Arrange
        when(locationRepository.save(any(Location.class))).thenThrow(DataIntegrityViolationException.class);
        // Act & Assert
        assertThrows(LocationNameAlreadyExistsException.class, () -> locationService.createLocation(locationForm));
    }

    @Test
    void updateLocation_shouldUpdateLocation_whenValidData() {
        // Arrange
        Location updatedLocation = new Location(locationForm.getCapacity(), locationForm.getLocationName(),
                locationForm.getAllowedVehiculeTypes(), location.getVehicules());
        updatedLocation.setId(DEFAULT_LOCATION_ID);
        when(locationRepository.findById(DEFAULT_LOCATION_ID)).thenReturn(Optional.of(location));
        when(locationRepository.save(any(Location.class))).thenReturn(updatedLocation);

        // Act
        Location result = locationService.updateLocation(DEFAULT_LOCATION_ID, locationForm);

        // Assert
        assertNotNull(result);
        assertEquals(locationForm.getCapacity(), result.getCapacity());
        assertEquals(locationForm.getLocationName(), result.getLocationName());
        assertEquals(locationForm.getAllowedVehiculeTypes(), result.getAllowedVehiculeTypes());
    }

    @Test
    void updateLocation_shouldThrowLocationNotFoundException_whenLocationDoesNotExist() {
        // Arrange
        when(locationRepository.findById(DEFAULT_LOCATION_ID)).thenReturn(Optional.empty());
        // Act & Assert
        assertThrows(LocationNotFoundException.class,
                () -> locationService.updateLocation(DEFAULT_LOCATION_ID, locationForm));
    }

    @Test
    void updateLocation_shouldThrowCapacityExceededException_whenCapacityExceeded() {
        // Arrange
        locationForm.setCapacity(0);
        Set<Vehicule> vehicules = new HashSet<>();
        vehicules.add(
                new Vehicule(VEHICULE_BRAND, VEHICULE_MODEL, VEHICULE_NOTE, VEHICULE_EQ_YEAR, VehiculeType.CAR,
                        location, VEHICULE_COLOR,
                        VEHICULE_LICENCE_PLATE, owner));
        location.setVehicules(vehicules);
        when(locationRepository.findById(DEFAULT_LOCATION_ID)).thenReturn(Optional.of(location));

        // Act & Assert
        assertThrows(CapacityExceededException.class,
                () -> locationService.updateLocation(DEFAULT_LOCATION_ID, locationForm));
    }

    @Test
    void updateLocation_shouldThrowLocationNameAlreadyExistsException_whenNameExists() {
        // Arrange
        when(locationRepository.findById(DEFAULT_LOCATION_ID)).thenReturn(Optional.of(location));
        when(locationRepository.save(any(Location.class))).thenThrow(DataIntegrityViolationException.class);
        // Act & Assert
        assertThrows(LocationNameAlreadyExistsException.class,
                () -> locationService.updateLocation(DEFAULT_LOCATION_ID, locationForm));
    }
}
