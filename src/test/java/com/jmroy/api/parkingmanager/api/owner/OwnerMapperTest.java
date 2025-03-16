package com.jmroy.api.parkingmanager.api.owner;

import com.jmroy.api.parkingmanager.domain.owner.Owner;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class OwnerMapperTest {

    // Constants for Owner
    private static final long OWNER_ID = 1L;
    private static final String OWNER_EMAIL = "test@example.com";
    private static final String OWNER_FIRST_NAME = "John";
    private static final String OWNER_LAST_NAME = "Doe";
    private static final String OWNER_PHONE_NUMBER = "123-456-7890";
    private static final String OWNER_NOTE = "Some notes";

    // Constants for Multiple Owners
    private static final long OWNER_ID_1 = 2L;
    private static final String OWNER_EMAIL_1 = "test1@example.com";
    private static final String OWNER_FIRST_NAME_1 = "Jane";
    private static final String OWNER_LAST_NAME_1 = "Smith";
    private static final String OWNER_PHONE_NUMBER_1 = "987-654-3210";
    private static final String OWNER_NOTE_1 = "Other notes";

    private static final long OWNER_ID_2 = 3L;
    private static final String OWNER_EMAIL_2 = "test2@example.com";
    private static final String OWNER_FIRST_NAME_2 = "Peter";
    private static final String OWNER_LAST_NAME_2 = "Jones";
    private static final String OWNER_PHONE_NUMBER_2 = "111-222-3333";
    private static final String OWNER_NOTE_2 = "More notes";
    @Autowired
    private OwnerMapper ownerMapper;

    @Test
    void toEntity_shouldMapCorrectly() {
        // Arrange
        OwnerResource ownerResource = new OwnerResource();
        ownerResource.setEmail(OWNER_EMAIL);
        ownerResource.setFirstName(OWNER_FIRST_NAME);
        ownerResource.setLastName(OWNER_LAST_NAME);
        ownerResource.setPhoneNumber(OWNER_PHONE_NUMBER);
        ownerResource.setNote(OWNER_NOTE);

        // Act
        Owner owner = ownerMapper.toEntity(ownerResource);

        // Assert
        assertNotNull(owner);
        assertEquals(OWNER_EMAIL, owner.getEmail());
        assertEquals(OWNER_FIRST_NAME, owner.getFirstName());
        assertEquals(OWNER_LAST_NAME, owner.getLastName());
        assertEquals(OWNER_PHONE_NUMBER, owner.getPhoneNumber());
        assertEquals(OWNER_NOTE, owner.getNote());
    }

    @Test
    void toResource_shouldMapCorrectly() {
        // Arrange
        Owner owner = new Owner(OWNER_EMAIL, OWNER_FIRST_NAME, OWNER_LAST_NAME, OWNER_PHONE_NUMBER, OWNER_NOTE,
                new HashSet<>());
        owner.setId(OWNER_ID);

        // Act
        OwnerResource ownerResource = ownerMapper.toResource(owner);

        // Assert
        assertNotNull(ownerResource);
        assertEquals(OWNER_EMAIL, ownerResource.getEmail());
        assertEquals(OWNER_FIRST_NAME, ownerResource.getFirstName());
        assertEquals(OWNER_LAST_NAME, ownerResource.getLastName());
        assertEquals(OWNER_PHONE_NUMBER, ownerResource.getPhoneNumber());
        assertEquals(OWNER_NOTE, ownerResource.getNote());
    }

    @Test
    void toResourceList_shouldMapCorrectly() {
        // Arrange
        List<Owner> owners = new ArrayList<>();
        Owner owner1 = new Owner(OWNER_EMAIL_1, OWNER_FIRST_NAME_1, OWNER_LAST_NAME_1, OWNER_PHONE_NUMBER_1,
                OWNER_NOTE_1, new HashSet<>());
        owner1.setId(OWNER_ID_1);
        Owner owner2 = new Owner(OWNER_EMAIL_2, OWNER_FIRST_NAME_2, OWNER_LAST_NAME_2, OWNER_PHONE_NUMBER_2,
                OWNER_NOTE_2, new HashSet<>());
        owner2.setId(OWNER_ID_2);
        owners.add(owner1);
        owners.add(owner2);

        // Act
        List<OwnerResource> ownerResources = ownerMapper.toResourceList(owners);

        // Assert
        assertNotNull(ownerResources);
        assertEquals(2, ownerResources.size());

        // Check first owner
        assertEquals(OWNER_EMAIL_1, ownerResources.get(0).getEmail());
        assertEquals(OWNER_FIRST_NAME_1, ownerResources.get(0).getFirstName());
        assertEquals(OWNER_LAST_NAME_1, ownerResources.get(0).getLastName());
        assertEquals(OWNER_PHONE_NUMBER_1, ownerResources.get(0).getPhoneNumber());
        assertEquals(OWNER_NOTE_1, ownerResources.get(0).getNote());

        // Check second owner
        assertEquals(OWNER_EMAIL_2, ownerResources.get(1).getEmail());
        assertEquals(OWNER_FIRST_NAME_2, ownerResources.get(1).getFirstName());
        assertEquals(OWNER_LAST_NAME_2, ownerResources.get(1).getLastName());
        assertEquals(OWNER_PHONE_NUMBER_2, ownerResources.get(1).getPhoneNumber());
        assertEquals(OWNER_NOTE_2, ownerResources.get(1).getNote());
    }
}
