package com.jmroy.api.parkingmanager.application.owner;

import com.jmroy.api.parkingmanager.domain.owner.Owner;
import com.jmroy.api.parkingmanager.domain.owner.OwnerRepository;
import com.jmroy.api.parkingmanager.domain.vehicule.Vehicule;
import com.jmroy.api.parkingmanager.exception.owner.OwnerNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
class OwnerServiceTest {

    private static final long DEFAULT_OWNER_ID = 1L;
    private static final String DEFAULT_OWNER_EMAIL = "test@example.com";
    private static final String DEFAULT_OWNER_FIRST_NAME = "John";
    private static final String DEFAULT_OWNER_LAST_NAME = "Doe";
    private static final String DEFAULT_OWNER_PHONE_NUMBER = "123-456-7890";
    private static final String DEFAULT_OWNER_NOTE = "Some notes";
    private static final long CREATED_OWNER_ID = 2L;

    @Mock
    private OwnerRepository ownerRepository;

    @InjectMocks
    private OwnerService ownerService;

    private Owner owner;
    private OwnerForm ownerForm;

    @BeforeEach
    void setUp() {
        owner = new Owner(DEFAULT_OWNER_EMAIL, DEFAULT_OWNER_FIRST_NAME, DEFAULT_OWNER_LAST_NAME,
                DEFAULT_OWNER_PHONE_NUMBER,
                DEFAULT_OWNER_NOTE, new HashSet<>());
        owner.setId(DEFAULT_OWNER_ID);

        ownerForm = new OwnerForm();
        ownerForm.setEmail(DEFAULT_OWNER_EMAIL);
        ownerForm.setFirstName(DEFAULT_OWNER_FIRST_NAME);
        ownerForm.setLastName(DEFAULT_OWNER_LAST_NAME);
        ownerForm.setPhoneNumber(DEFAULT_OWNER_PHONE_NUMBER);
        ownerForm.setNote(DEFAULT_OWNER_NOTE);
        ownerForm.setVehicules(new HashSet<>());
    }

    @Test
    void getAllOwners_shouldReturnListOfOwners() {
        // Arrange
        List<Owner> owners = new ArrayList<>();
        owners.add(owner);
        owners.add(new Owner("test2@example.com", "Jane", "Smith", "987-654-3210", "Other notes", new HashSet<>()));
        when(ownerRepository.findAll()).thenReturn(owners);

        // Act
        List<Owner> result = ownerService.getAllOwners();

        // Assert
        assertEquals(2, result.size());
        assertEquals(owners, result);
    }

    @Test
    void getOwnerById_shouldReturnOwner_whenOwnerExists() {
        // Arrange
        when(ownerRepository.findById(DEFAULT_OWNER_ID)).thenReturn(Optional.of(owner));

        // Act
        Owner result = ownerService.getOwnerById(DEFAULT_OWNER_ID);

        // Assert
        assertNotNull(result);
        assertEquals(owner, result);
    }

    @Test
    void getOwnerById_shouldThrowOwnerNotFoundException_whenOwnerDoesNotExist() {
        // Arrange
        when(ownerRepository.findById(DEFAULT_OWNER_ID)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(OwnerNotFoundException.class, () -> ownerService.getOwnerById(DEFAULT_OWNER_ID));
    }

    @Test
    void getOwnerVehicles_shouldReturnVehicles_whenOwnerExists() {
        // Arrange
        Set<Vehicule> vehicules = new HashSet<>();
        vehicules.add(new Vehicule("brand", "model", "note", 2023, null, null, "color", "AA-123-AA", owner));
        owner.setVehicules(vehicules);
        when(ownerRepository.findById(DEFAULT_OWNER_ID)).thenReturn(Optional.of(owner));

        // Act
        Set<Vehicule> result = ownerService.getOwnerVehicles(DEFAULT_OWNER_ID);

        // Assert
        assertEquals(vehicules, result);
    }

    @Test
    void getOwnerVehicles_shouldThrowOwnerNotFoundException_whenOwnerDoesNotExist() {
        // Arrange
        when(ownerRepository.findById(DEFAULT_OWNER_ID)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(OwnerNotFoundException.class, () -> ownerService.getOwnerVehicles(DEFAULT_OWNER_ID));
    }

    @Test
    void createOwner_shouldCreateOwner_whenValidData() {
        // Arrange
        Owner createdOwner = new Owner(DEFAULT_OWNER_EMAIL, DEFAULT_OWNER_FIRST_NAME, DEFAULT_OWNER_LAST_NAME,
                DEFAULT_OWNER_PHONE_NUMBER, DEFAULT_OWNER_NOTE, new HashSet<>());
        createdOwner.setId(CREATED_OWNER_ID);
        when(ownerRepository.save(any(Owner.class))).thenReturn(createdOwner);

        // Act
        Owner result = ownerService.createOwner(ownerForm);

        // Assert
        assertNotNull(result);
        assertEquals(ownerForm.getEmail(), result.getEmail());
        assertEquals(ownerForm.getFirstName(), result.getFirstName());
        assertEquals(ownerForm.getLastName(), result.getLastName());
        assertEquals(ownerForm.getPhoneNumber(), result.getPhoneNumber());
        assertEquals(ownerForm.getNote(), result.getNote());
        assertEquals(ownerForm.getVehicules(), result.getVehicules());

    }

    @Test
    void updateOwner_shouldUpdateOwner_whenValidData() {
        // Arrange
        Owner updatedOwner = new Owner("updated@example.com", "Updated", "Name", "000-000-0000", "Updated note",
                new HashSet<>());
        updatedOwner.setId(DEFAULT_OWNER_ID);
        when(ownerRepository.findById(DEFAULT_OWNER_ID)).thenReturn(Optional.of(owner));
        when(ownerRepository.save(any(Owner.class))).thenReturn(updatedOwner);

        OwnerForm updateForm = new OwnerForm();
        updateForm.setEmail("updated@example.com");
        updateForm.setFirstName("Updated");
        updateForm.setLastName("Name");
        updateForm.setPhoneNumber("000-000-0000");
        updateForm.setNote("Updated note");
        updateForm.setVehicules(new HashSet<>());

        // Act
        Owner result = ownerService.updateOwner(DEFAULT_OWNER_ID, updateForm);

        // Assert
        assertNotNull(result);
        assertEquals(updateForm.getEmail(), result.getEmail());
        assertEquals(updateForm.getFirstName(), result.getFirstName());
        assertEquals(updateForm.getLastName(), result.getLastName());
        assertEquals(updateForm.getPhoneNumber(), result.getPhoneNumber());
        assertEquals(updateForm.getNote(), result.getNote());
    }

    @Test
    void updateOwner_shouldThrowOwnerNotFoundException_whenOwnerDoesNotExist() {
        // Arrange
        when(ownerRepository.findById(DEFAULT_OWNER_ID)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(OwnerNotFoundException.class, () -> ownerService.updateOwner(DEFAULT_OWNER_ID, ownerForm));
    }
}
