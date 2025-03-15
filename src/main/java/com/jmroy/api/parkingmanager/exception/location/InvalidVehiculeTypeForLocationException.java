package com.jmroy.api.parkingmanager.exception.location;

import com.jmroy.api.parkingmanager.domain.vehicule.VehiculeType;
import com.jmroy.api.parkingmanager.exception.BusinessLogicException;

public class InvalidVehiculeTypeForLocationException extends BusinessLogicException {

    public InvalidVehiculeTypeForLocationException(VehiculeType vehiculeType) {
        super(String.format("Le véhicule de type " + vehiculeType + " n'est pas autorisé dans la location."));
    }

    public InvalidVehiculeTypeForLocationException(String message) {
        super(message);
    }

    public InvalidVehiculeTypeForLocationException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidVehiculeTypeForLocationException(Throwable cause) {
        super(cause);
    }
}