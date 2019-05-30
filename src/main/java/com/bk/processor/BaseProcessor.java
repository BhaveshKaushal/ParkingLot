package com.bk.processor;

import com.bk.ParkingLot;
import com.bk.model.Operation;

public class BaseProcessor {

    private ParkingLot parkingLot;

    protected boolean exit = false;

    public void process(String[] inputs) {

        String operation = inputs[0];

        if (parkingLot == null &&
                !Operation.operationFromvalue(operation).equals(Operation.CREATE_PARKING_LOT)
                 && !Operation.operationFromvalue(operation).equals(Operation.EXIT)) {
            throw new IllegalArgumentException(String.format("%s operation cannnot be performed before %s",
                    operation, Operation.CREATE_PARKING_LOT.getValue()));
        }
        switch (Operation.operationFromvalue(operation)) {

            case CREATE_PARKING_LOT:
                int size = Integer.parseInt(inputs[1]);
                parkingLot = new ParkingLot(size);
                System.out.println("Created a parking lot with " + size + " slots");
                break;

            case PARK:
                String vehicleNumber = inputs[1];
                String color = inputs[2];
                parkingLot.parkVehicle(vehicleNumber, color);
                break;

            case LEAVE:
                int slotNumber = Integer.parseInt(inputs[1]);
                parkingLot.freeSlot(slotNumber);
                break;

            case STATUS:
                parkingLot.status();
                break;

            case SLOT_NUMBERS_FOR_COLOR:
                parkingLot.getSlotNumbersByColor(inputs[1]);
                break;

            case REGISTRATION_NUMBERS_FOR_COLOR:
                parkingLot.getRegistrationNumbersByColor(inputs[1]);
                break;

            case SLOT_NUMBER_FOR_REGISTRATION_NUMBER:
                parkingLot.getSlotNumberByRegistrationNumber(inputs[1]);
                break;

            case EXIT:
                this.exit = true;
                break;
            default:
                System.out.println("Invalid operation: "+operation);


        }
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    protected String[] getInputs(String line) {
        return line.split("\\s");
    }

}
