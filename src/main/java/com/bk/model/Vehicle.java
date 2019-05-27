package main.java.com.bk.model;

import java.util.Objects;

public class Vehicle {
   private String registrationNo;

    private String color;

    public String getRegistrationNo() {
        return registrationNo;
    }

    public Vehicle(String registrationNo, String color) {
        this.registrationNo = registrationNo;
        this.color = color;
    }

    public void setRegistrationNo(String registrationNo) {
        this.registrationNo = registrationNo;
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
        return Objects.equals(registrationNo, vehicle.registrationNo) &&
                Objects.equals(color, vehicle.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(registrationNo, color);
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "registrationNo='" + registrationNo + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
