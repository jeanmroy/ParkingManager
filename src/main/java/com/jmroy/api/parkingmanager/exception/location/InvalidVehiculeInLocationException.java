package com.jmroy.api.parkingmanager.exception.location;

import com.jmroy.api.parkingmanager.domain.vehicule.Vehicule;
import com.jmroy.api.parkingmanager.exception.BusinessLogicException;

public class InvalidVehiculeInLocationException extends BusinessLogicException {

    public InvalidVehiculeInLocationException(Vehicule vehicule) {
        super(String.format("Le véhicule : " + vehicule.toString()) + " n'est pas autorisé dans la location.");
    }

    public InvalidVehiculeInLocationException(String message) {
        super(message);
    }

    public InvalidVehiculeInLocationException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidVehiculeInLocationException(Throwable cause) {
        super(cause);
    }
}