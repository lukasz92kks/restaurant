package pl.krawczyk.restaurant.model;

import pl.krawczyk.restaurant.enums.VehicleType;

// klasa samochód
public class Car extends Vehicle {

    public Car(int capacity, int speed, String number, int tankSize, VehicleType vehicleType) {
        super(capacity, speed, number, tankSize, vehicleType);
    }
    
    @Override
    public VehicleType getVehicleType() {
        return VehicleType.CAR;
    }
}
