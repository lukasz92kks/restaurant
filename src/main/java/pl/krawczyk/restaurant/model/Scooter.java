package pl.krawczyk.restaurant.model;

import pl.krawczyk.restaurant.enums.VehicleType;

// klasa skutera
public class Scooter extends Vehicle {

    public Scooter(int capacity, int speed, String number, int tankSize, VehicleType vehicleType) {
        super(capacity, speed, number, tankSize, vehicleType);
    }
    
    @Override
    public VehicleType getVehicleType() {
        return VehicleType.SCOOTER;
    }
}
