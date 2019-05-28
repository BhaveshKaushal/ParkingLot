package main.java.com.bk.model;

import java.util.Objects;

public class Vehicle {
   private String registrationNumber;

    private String color;

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public Vehicle(String registrationNumber, String color) {
        this.registrationNumber = registrationNumber;
        this.color = color;
    }

    public void setRegistrationNumber(String registrationNo) {
        this.registrationNumber = registrationNo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(registrationNumber, vehicle.registrationNumber) &&
                Objects.equals(color, vehicle.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(registrationNumber, color);
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "registrationNo='" + registrationNumber + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
