package com.bk.model;

import java.util.Objects;

public class Ticket {

    private String registrationNumber;

    private String color;

    private int slot;

    public Ticket(String registrationNumber, String color, int slot) {
        this.registrationNumber = registrationNumber;
        this.color = color;
        this.slot = slot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return slot == ticket.slot &&
                Objects.equals(registrationNumber, ticket.registrationNumber) &&
                Objects.equals(color, ticket.color);
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    @Override
    public int hashCode() {
        return Objects.hash(registrationNumber, color, slot);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "registrationNumber='" + registrationNumber + '\'' +
                ", color='" + color + '\'' +
                ", slot=" + slot +
                '}';
    }
}

