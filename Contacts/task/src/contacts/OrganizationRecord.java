package contacts;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class OrganizationRecord extends Record {
    private String address;

    public OrganizationRecord() {}

    public OrganizationRecord(String name, String number, String address) {
        super(name, number);
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
        this.lastEdited = LocalDateTime.of(LocalDate.now().getYear(), LocalDate.now().getMonth(),
                LocalDate.now().getDayOfMonth(), LocalTime.now().getHour(), LocalTime.now().getMinute());
    }

    @Override
    public String[] availableFields() {
        return new String[]{"name", "address", "number"};
    }

    @Override
    public void changeField(String field, String fieldValue) {
        switch (field) {
            case "name":
                this.name = fieldValue;
                break;
            case "address":
                this.address = fieldValue;
                break;
            case "number":
                setNumber(fieldValue);
                break;
        }
    }

    @Override
    public void info() {
        System.out.println("Organization name: " + this.name);

        System.out.print("Address: ");
        if ("".equals(this.address)) {
            System.out.println("[no data]");
        } else {
            System.out.println(this.address);
        }

        System.out.print("Number: ");
        if ("".equals(address)) {
            System.out.println("[no number]");
        } else {
            System.out.println(this.number);
        }
        System.out.println("Time created: " + this.created);
        System.out.println("Time last edit: " + this.lastEdited);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public String toSearchPattern() {
        return name + " " + address + " " + number;
    }
}
