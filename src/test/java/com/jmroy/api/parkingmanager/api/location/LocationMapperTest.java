package com.jmroy.api.parkingmanager.api.location;

import com.jmroy.api.parkingmanager.api.vehicule.VehiculeResource;
import com.jmroy.api.parkingmanager.domain.location.Location;
import com.jmroy.api.parkingmanager.domain.location.LocationName;
import com.jmroy.api.parkingmanager.domain.vehicule.VehiculeType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class LocationMapperTest {

    // Constants for Location
    private static final long LOCATION_ID = 1L;
    private static final int LOCATION_CAPACITY = 10;
    private static final LocationName LOCATION_NAME = LocationName.SOUTH_FIELD;

    // Constants for Multiple Locations
    private static final long LOCATION_ID_1 = 2L;
    private static final int LOCATION_CAPACITY_1 = 5;
    private static final LocationName LOCATION_NAME_1 = LocationName.BARN;

    private static final long LOCATION_ID_2 = 3L;
    private static final int LOCATION_CAPACITY_2 = 15;
    private static final LocationName LOCATION_NAME_2 = LocationName.SOUTH_FIELD;

    // Constants for vehicule type.
    private static final VehiculeType VEHICULE_TYPE = VehiculeType.CAR;
    private static final VehiculeType VEHICULE_TYPE_1 = VehiculeType.MOTORCYCLE;
    @Autowired
    private LocationMapper locationMapper;

    @Test
    void toEntity_shouldMapCorrectly() {
        // Arrange
        LocationResource locationResource = new LocationResource();
        locationResource.setId(LOCATION_ID);
        locationResource.setCapacity(LOCATION_CAPACITY);
        locationResource.setLocationName(LOCATION_NAME);
        locationResource.setAllowedVehiculeTypes(Set.of(VEHICULE_TYPE));
        locationResource.setVehicules(Set.of(new VehiculeResource()));

        // Act
        Location location = locationMapper.toEntity(locationResource);

        // Assert
        assertNotNull(location);
        assertEquals(LOCATION_ID, location.getId());
        assertEquals(LOCATION_CAPACITY, location.getCapacity());
        assertEquals(LOCATION_NAME, location.getLocationName());
        assertEquals(Set.of(VEHICULE_TYPE), location.getAllowedVehiculeTypes());
    }

    @Test
    void toResource_shouldMapCorrectly() {
        // Arrange
        Location location = new Location(LOCATION_CAPACITY, LOCATION_NAME, Set.of(VEHICULE_TYPE), new HashSet<>());
        location.setId(LOCATION_ID);

        // Act
        LocationResource locationResource = locationMapper.toResource(location);

        // Assert
        assertNotNull(locationResource);
        assertEquals(LOCATION_ID, locationResource.getId());
        assertEquals(LOCATION_CAPACITY, locationResource.getCapacity());
        assertEquals(LOCATION_NAME, locationResource.getLocationName());
        assertEquals(Set.of(VEHICULE_TYPE), locationResource.getAllowedVehiculeTypes());
    }

    @Test
    void toResourceList_shouldMapCorrectly() {
        // Arrange
        List<Location> locations = new ArrayList<>();
        Location location1 = new Location(LOCATION_CAPACITY_1, LOCATION_NAME_1, Set.of(VEHICULE_TYPE), new HashSet<>());
        location1.setId(LOCATION_ID_1);
        Location location2 = new Location(LOCATION_CAPACITY_2, LOCATION_NAME_2, Set.of(VEHICULE_TYPE_1),
                new HashSet<>());
        location2.setId(LOCATION_ID_2);
        locations.add(location1);
        locations.add(location2);

        // Act
        List<LocationResource> locationResources = locationMapper.toResourceList(locations);

        // Assert
        assertNotNull(locationResources);
        assertEquals(2, locationResources.size());

        // Check first location
        assertEquals(LOCATION_ID_1, locationResources.get(0).getId());
        assertEquals(LOCATION_CAPACITY_1, locationResources.get(0).getCapacity());
        assertEquals(LOCATION_NAME_1, locationResources.get(0).getLocationName());
        assertEquals(Set.of(VEHICULE_TYPE), locationResources.get(0).getAllowedVehiculeTypes());

        // Check second location
        assertEquals(LOCATION_ID_2, locationResources.get(1).getId());
        assertEquals(LOCATION_CAPACITY_2, locationResources.get(1).getCapacity());
        assertEquals(LOCATION_NAME_2, locationResources.get(1).getLocationName());
        assertEquals(Set.of(VEHICULE_TYPE_1), locationResources.get(1).getAllowedVehiculeTypes());
    }
}
