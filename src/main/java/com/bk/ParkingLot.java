package main.java.com.bk;

import main.java.com.bk.model.Slot;
import main.java.com.bk.model.Vehicle;

import java.util.*;
import java.util.stream.Collectors;

public class ParkingLot {

    private int size;

    private Map<String, Slot> registrationMap;

    private Map<Integer,Slot> slotMap;

    private Map<String,List<Slot>> reservvationByColors;

    private int nextFreeSlot;

    public ParkingLot(int size) {
        init(size);
    }

    private void init(int size) {
        this.size = size;
        this.registrationMap =  new HashMap<>();
        this.reservvationByColors = new HashMap<>();
        this.slotMap = new HashMap<>(size);
        this.nextFreeSlot = 1;
        for(int i = 0; i< size; i++) {
            Slot slot = new Slot(i+1);
            slotMap.put(i+1,slot);
        }
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }


    public Map<Integer,Slot> getSlotMap() {
        return this.slotMap;
    }

    public int parkVehicle(String registrationNumber, String color) {
        Vehicle vehicle  = new Vehicle(registrationNumber, color);

        if(nextFreeSlot < 1) {
            return -1;
        }

       Slot slot =  slotMap.get(nextFreeSlot);
      slot.setVehicle(vehicle);
       this.registrationMap.put(registrationNumber,slot);

       this.slotMap.put(slot.getSlotNumber(),slot);
       updateColorsMap(slot,false);
       updateNextFreeSlot();

       return slot.getSlotNumber();
    }

    public int getSlotNumberByRegistrationNumber(String registrationNumber) {
        Slot slot = this.registrationMap.get(registrationNumber);

        if(slot != null) {
           return slot.getSlotNumber();
        } else {
            return -1;
        }
    }

    public int freeSlot(int slotNumber) {

      Slot slot =  this.slotMap.get(slotNumber);

      if(slotNumber > this.size || slotNumber < 1) {
          System.out.println("Slot is not present in the parking system");
      }

     String registrationNumber =  slot.getVehicle().getRegistrationNumber();

     this.registrationMap.remove(registrationNumber);
      updateColorsMap(slot,true);
      slot.setVehicle(null);

      this.slotMap.put(slotNumber,slot);
      if(this.nextFreeSlot == -1) {
          this.nextFreeSlot = slotNumber;
      } else if(slotNumber  < nextFreeSlot) {
          this.nextFreeSlot = slotNumber;
      }

      return slotNumber;

    }

    private void updateColorsMap( Slot slot, boolean remove) {
        String color = slot.getVehicle().getColor();
        this.reservvationByColors.computeIfPresent(color, (k, v) -> {
            if(!remove) {
                v.add(slot);
            } else {
                v.remove(slot);
            }
            return v;
        });

        this.reservvationByColors.computeIfAbsent(color,(k) -> new ArrayList<>(Arrays.asList(slot)));
    }

    public List<Integer> getSlotNumbersByColor(String color) {

        List<Integer> slotNumbers = new ArrayList<>();
        List<Slot> slots = getSlotListByColor(color);
        if(slots != null) {
           slotNumbers.addAll(slots.stream()
                   .filter(slot -> color.equalsIgnoreCase(slot.getVehicle().getColor()))
                   .map(Slot::getSlotNumber).collect(Collectors.toList()));
        }
        return slotNumbers;
    }

    public int getNextFreeSlot() {
        return this.nextFreeSlot;
    }

    private List<Slot> getSlotListByColor(String color){

        return this.reservvationByColors.get(color);
    }

    private void updateNextFreeSlot(){
       Optional<Slot> nextSlot= this.slotMap.values().stream()
                .filter(slot -> slot.getVehicle()==null).findFirst();

       if(nextSlot.isPresent()) {
           this.nextFreeSlot =  nextSlot.get().getSlotNumber();
       } else {
           this.nextFreeSlot = -1;
       }

    }
}
