package pl.krawczyk.restaurant.model;

import pl.krawczyk.restaurant.enums.VehicleType;

// klasa pojazdu
public abstract class Vehicle {
    
    private int capacity;
    private int speed;
    private String number;
    private int fuel;
    private int tankSize;
    private VehicleType vehicleType;
    private boolean damage;

    public Vehicle(int capacity, int speed, String number, int tankSize, VehicleType vehicleType) {
        this.capacity = capacity;
        this.speed = speed;
        this.number = number;
        this.tankSize = tankSize;
        this.vehicleType = vehicleType;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getFuel() {
        return fuel;
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    public int getTankSize() {
        return tankSize;
    }

    public void setTankSize(int tankSize) {
        this.tankSize = tankSize;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public boolean isDamage() {
        return damage;
    }

    public void setDamage(boolean damage) {
        this.damage = damage;
    }
}
