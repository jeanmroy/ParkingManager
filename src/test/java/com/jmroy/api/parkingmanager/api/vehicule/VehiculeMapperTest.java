package com.jmroy.api.parkingmanager.api.vehicule;

import com.jmroy.api.parkingmanager.api.location.LocationMapper;
import com.jmroy.api.parkingmanager.api.location.LocationResource;
import com.jmroy.api.parkingmanager.api.owner.OwnerMapper;
import com.jmroy.api.parkingmanager.api.owner.OwnerResource;
import com.jmroy.api.parkingmanager.domain.location.Location;
import com.jmroy.api.parkingmanager.domain.location.LocationName;
import com.jmroy.api.parkingmanager.domain.owner.Owner;
import com.jmroy.api.parkingmanager.domain.vehicule.Vehicule;
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
class VehiculeMapperTest {

        // Constants for Owner
        private static final long OWNER_ID = 1L;
        private static final String OWNER_EMAIL = "test@example.com";
        private static final String OWNER_FIRST_NAME = "John";
        private static final String OWNER_LAST_NAME = "Doe";
        private static final String OWNER_PHONE_NUMBER = "123-456-7890";
        private static final String OWNER_NOTE = "Some notes";

        // Constants for Location
        private static final long LOCATION_ID = 1L;
        private static final int LOCATION_CAPACITY = 10;
        private static final LocationName LOCATION_NAME = LocationName.SOUTH_FIELD;

        // Constants for Vehicule
        private static final String VEHICULE_BRAND = "TestBrand";
        private static final String VEHICULE_MODEL = "TestModel";
        private static final String VEHICULE_NOTE = "TestNote";
        private static final int VEHICULE_EQ_YEAR = 2023;
        private static final VehiculeType VEHICULE_TYPE = VehiculeType.CAR;
        private static final String VEHICULE_COLOR = "Red";
        private static final String VEHICULE_LICENCE_PLATE = "AA-123-AA";

        // Constants for Multiple Vehicules
        private static final String VEHICULE_BRAND_1 = "TestBrand1";
        private static final String VEHICULE_MODEL_1 = "TestModel1";
        private static final String VEHICULE_NOTE_1 = "TestNote1";
        private static final int VEHICULE_EQ_YEAR_1 = 2022;
        private static final VehiculeType VEHICULE_TYPE_1 = VehiculeType.CAR;
        private static final String VEHICULE_COLOR_1 = "Blue";
        private static final String VEHICULE_LICENCE_PLATE_1 = "BB-456-BB";

        private static final String VEHICULE_BRAND_2 = "TestBrand2";
        private static final String VEHICULE_MODEL_2 = "TestModel2";
        private static final String VEHICULE_NOTE_2 = "TestNote2";
        private static final int VEHICULE_EQ_YEAR_2 = 2023;
        private static final VehiculeType VEHICULE_TYPE_2 = VehiculeType.MOTORCYCLE;
        private static final String VEHICULE_COLOR_2 = "Green";
        private static final String VEHICULE_LICENCE_PLATE_2 = "CC-789-CC";

        @Autowired
        private VehiculeMapper vehiculeMapper;
        @Autowired
        private OwnerMapper ownerMapper;
        @Autowired
        private LocationMapper locationMapper;

        @Test
        void toEntity_shouldMapCorrectly() {
                // Arrange
                Owner owner = new Owner(OWNER_EMAIL, OWNER_FIRST_NAME, OWNER_LAST_NAME, OWNER_PHONE_NUMBER, OWNER_NOTE,
                                new HashSet<>());
                owner.setId(OWNER_ID);
                Location location = new Location(LOCATION_CAPACITY, LOCATION_NAME, Set.of(VEHICULE_TYPE),
                                new HashSet<>());
                location.setId(LOCATION_ID);
                OwnerResource ownerResource = ownerMapper.toResource(owner);

                LocationResource locationResource = locationMapper.toResource(location);

                VehiculeResource vehiculeResource = new VehiculeResource();
                vehiculeResource.setBrand(VEHICULE_BRAND);
                vehiculeResource.setModel(VEHICULE_MODEL);
                vehiculeResource.setNote(VEHICULE_NOTE);
                vehiculeResource.setEqYear(VEHICULE_EQ_YEAR);
                vehiculeResource.setType(VEHICULE_TYPE);
                vehiculeResource.setLocation(locationResource);
                vehiculeResource.setColor(VEHICULE_COLOR);
                vehiculeResource.setLicencePlate(VEHICULE_LICENCE_PLATE);
                vehiculeResource.setOwner(ownerResource);

                // Act
                Vehicule vehicule = vehiculeMapper.toEntity(vehiculeResource);

                // Assert
                assertNotNull(vehicule);
                assertEquals(VEHICULE_BRAND, vehicule.getBrand());
                assertEquals(VEHICULE_MODEL, vehicule.getModel());
                assertEquals(VEHICULE_NOTE, vehicule.getNote());
                assertEquals(VEHICULE_EQ_YEAR, vehicule.getEqYear());
                assertEquals(VEHICULE_TYPE, vehicule.getType());
                assertEquals(location.getId(), vehicule.getLocation().getId());
                assertEquals(location.getCapacity(), vehicule.getLocation().getCapacity());
                assertEquals(location.getLocationName(), vehicule.getLocation().getLocationName());
                assertEquals(VEHICULE_COLOR, vehicule.getColor());
                assertEquals(VEHICULE_LICENCE_PLATE, vehicule.getLicencePlate());
                assertEquals(owner.getId(), vehicule.getOwner().getId());
                assertEquals(owner.getEmail(), vehicule.getOwner().getEmail());
                assertEquals(owner.getFirstName(), vehicule.getOwner().getFirstName());
                assertEquals(owner.getLastName(), vehicule.getOwner().getLastName());
                assertEquals(owner.getPhoneNumber(), vehicule.getOwner().getPhoneNumber());
                assertEquals(owner.getNote(), vehicule.getOwner().getNote());
        }

        @Test
        void toResource_shouldMapCorrectly() {
                // Arrange
                Owner owner = new Owner(OWNER_EMAIL, OWNER_FIRST_NAME, OWNER_LAST_NAME, OWNER_PHONE_NUMBER, OWNER_NOTE,
                                new HashSet<>());
                owner.setId(OWNER_ID);
                Location location = new Location(LOCATION_CAPACITY, LOCATION_NAME, Set.of(VEHICULE_TYPE),
                                new HashSet<>());
                location.setId(LOCATION_ID);
                Vehicule vehicule = new Vehicule(VEHICULE_BRAND, VEHICULE_MODEL, VEHICULE_NOTE, VEHICULE_EQ_YEAR,
                                VEHICULE_TYPE,
                                location,
                                VEHICULE_COLOR, VEHICULE_LICENCE_PLATE, owner);

                // Act
                VehiculeResource vehiculeResource = vehiculeMapper.toResource(vehicule);

                // Assert
                assertNotNull(vehiculeResource);
                assertEquals(VEHICULE_BRAND, vehiculeResource.getBrand());
                assertEquals(VEHICULE_MODEL, vehiculeResource.getModel());
                assertEquals(VEHICULE_NOTE, vehiculeResource.getNote());
                assertEquals(VEHICULE_EQ_YEAR, vehiculeResource.getEqYear());
                assertEquals(VEHICULE_TYPE, vehiculeResource.getType());
                assertEquals(location.getId(), vehiculeResource.getLocation().getId());
                assertEquals(location.getCapacity(), vehiculeResource.getLocation().getCapacity());
                assertEquals(location.getLocationName(), vehiculeResource.getLocation().getLocationName());
                assertEquals(VEHICULE_COLOR, vehiculeResource.getColor());
                assertEquals(VEHICULE_LICENCE_PLATE, vehiculeResource.getLicencePlate());
                assertEquals(owner.getId(), vehiculeResource.getOwner().getId());
                assertEquals(owner.getEmail(), vehiculeResource.getOwner().getEmail());
                assertEquals(owner.getFirstName(), vehiculeResource.getOwner().getFirstName());
                assertEquals(owner.getLastName(), vehiculeResource.getOwner().getLastName());
                assertEquals(owner.getPhoneNumber(), vehiculeResource.getOwner().getPhoneNumber());
                assertEquals(owner.getNote(), vehiculeResource.getOwner().getNote());
        }

        @Test
        void toResourceList_shouldMapCorrectly() {
                // Arrange
                Owner owner = new Owner(OWNER_EMAIL, OWNER_FIRST_NAME, OWNER_LAST_NAME, OWNER_PHONE_NUMBER, OWNER_NOTE,
                                new HashSet<>());
                owner.setId(OWNER_ID);
                Location location = new Location(LOCATION_CAPACITY, LOCATION_NAME, Set.of(VEHICULE_TYPE),
                                new HashSet<>());
                location.setId(LOCATION_ID);
                List<Vehicule> vehicules = new ArrayList<>();
                vehicules.add(new Vehicule(VEHICULE_BRAND_1, VEHICULE_MODEL_1, VEHICULE_NOTE_1, VEHICULE_EQ_YEAR_1,
                                VEHICULE_TYPE_1, location,
                                VEHICULE_COLOR_1, VEHICULE_LICENCE_PLATE_1, owner));
                vehicules.add(new Vehicule(VEHICULE_BRAND_2, VEHICULE_MODEL_2, VEHICULE_NOTE_2, VEHICULE_EQ_YEAR_2,
                                VEHICULE_TYPE_2, location,
                                VEHICULE_COLOR_2, VEHICULE_LICENCE_PLATE_2, owner));

                // Act
                List<VehiculeResource> vehiculeResources = vehiculeMapper.toResourceList(vehicules);

                // Assert
                assertNotNull(vehiculeResources);
                assertEquals(2, vehiculeResources.size());
                // first vehicule
                assertEquals(VEHICULE_BRAND_1, vehiculeResources.get(0).getBrand());
                assertEquals(VEHICULE_MODEL_1, vehiculeResources.get(0).getModel());
                assertEquals(VEHICULE_NOTE_1, vehiculeResources.get(0).getNote());
                assertEquals(VEHICULE_EQ_YEAR_1, vehiculeResources.get(0).getEqYear());
                assertEquals(VEHICULE_TYPE_1, vehiculeResources.get(0).getType());
                assertEquals(location.getId(), vehiculeResources.get(0).getLocation().getId());
                assertEquals(location.getCapacity(), vehiculeResources.get(0).getLocation().getCapacity());
                assertEquals(location.getLocationName(), vehiculeResources.get(0).getLocation().getLocationName());
                assertEquals(VEHICULE_COLOR_1, vehiculeResources.get(0).getColor());
                assertEquals(VEHICULE_LICENCE_PLATE_1, vehiculeResources.get(0).getLicencePlate());
                assertEquals(owner.getId(), vehiculeResources.get(0).getOwner().getId());
                assertEquals(owner.getEmail(), vehiculeResources.get(0).getOwner().getEmail());
                assertEquals(owner.getFirstName(), vehiculeResources.get(0).getOwner().getFirstName());
                assertEquals(owner.getLastName(), vehiculeResources.get(0).getOwner().getLastName());
                assertEquals(owner.getPhoneNumber(), vehiculeResources.get(0).getOwner().getPhoneNumber());
                assertEquals(owner.getNote(), vehiculeResources.get(0).getOwner().getNote());
                // second vehicule
                assertEquals(VEHICULE_BRAND_2, vehiculeResources.get(1).getBrand());
                assertEquals(VEHICULE_MODEL_2, vehiculeResources.get(1).getModel());
                assertEquals(VEHICULE_NOTE_2, vehiculeResources.get(1).getNote());
                assertEquals(VEHICULE_EQ_YEAR_2, vehiculeResources.get(1).getEqYear());
                assertEquals(VEHICULE_TYPE_2, vehiculeResources.get(1).getType());
                assertEquals(location.getId(), vehiculeResources.get(1).getLocation().getId());
                assertEquals(location.getCapacity(), vehiculeResources.get(1).getLocation().getCapacity());
                assertEquals(location.getLocationName(), vehiculeResources.get(1).getLocation().getLocationName());
                assertEquals(VEHICULE_COLOR_2, vehiculeResources.get(1).getColor());
                assertEquals(VEHICULE_LICENCE_PLATE_2, vehiculeResources.get(1).getLicencePlate());
                assertEquals(owner.getId(), vehiculeResources.get(1).getOwner().getId());
                assertEquals(owner.getEmail(), vehiculeResources.get(1).getOwner().getEmail());
                assertEquals(owner.getFirstName(), vehiculeResources.get(1).getOwner().getFirstName());
                assertEquals(owner.getLastName(), vehiculeResources.get(1).getOwner().getLastName());
                assertEquals(owner.getPhoneNumber(), vehiculeResources.get(1).getOwner().getPhoneNumber());
                assertEquals(owner.getNote(), vehiculeResources.get(1).getOwner().getNote());
        }
}
