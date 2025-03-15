package com.jmroy.api.parkingmanager.exception.location;

import com.jmroy.api.parkingmanager.exception.BusinessLogicException;

public class CapacityExceededException extends BusinessLogicException {

    public CapacityExceededException(int numberOfVehicles, int capacity) {
        super(String.format("Cannot update location: number of vehicles (%d) exceeds capacity (%d)", numberOfVehicles,
                capacity));
    }

    public CapacityExceededException(String message, Throwable cause) {
        super(message, cause);
    }

    public CapacityExceededException(Throwable cause) {
        super(cause);
    }
}