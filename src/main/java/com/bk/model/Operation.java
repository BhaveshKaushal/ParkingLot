package com.bk.model;

public enum Operation {

    PARK("park"),
    CREATE_PARKING_LOT("create_parking_lot"),
    LEAVE("leave"),
    STATUS("status"),
    REGISTRATION_NUMBERS_FOR_COLOR("registration_numbers_for_cars_with_colour"),
    SLOT_NUMBERS_FOR_COLOR("slot_numbers_for_cars_with_colour"),
    SLOT_NUMBER_FOR_REGISTRATION_NUMBER("slot_number_for_registration_number"),
    INVALID_OPERATION("INVALID_OPERATION"),
    EXIT("exit");

    String value;

    Operation(String value) {
        this.value =value;
    }

    public String getValue() {
        return this.value;
    }

    public static Operation operationFromvalue(String label) {
        for (Operation e : values()) {
            if (e.value.equals(label)) {
                return e;
            }
        }
        return INVALID_OPERATION;
    }



}
