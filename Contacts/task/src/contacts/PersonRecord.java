package contacts;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class PersonRecord extends Record {
    private String surname;
    private LocalDate birthDate;
    private String gender;

    public PersonRecord() {}

    public PersonRecord(String name, String surname, String birthDate, String number, String gender) {
        super(name, number);
        this.name = name;
        this.surname = surname;
        setBirthDate(birthDate);
        setGender(gender);
        setNumber(number);
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
        this.lastEdited = LocalDateTime.of(LocalDate.now().getYear(), LocalDate.now().getMonth(),
                LocalDate.now().getDayOfMonth(), LocalTime.now().getHour(), LocalTime.now().getMinute());
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        if (validateNumber(number)) {
            this.number = number;
        } else {
            this.number = "";
            System.out.println("Wrong number format!");
        }
        this.lastEdited = LocalDateTime.of(LocalDate.now().getYear(), LocalDate.now().getMonth(),
                LocalDate.now().getDayOfMonth(), LocalTime.now().getHour(), LocalTime.now().getMinute());
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        try {
            this.birthDate = LocalDate.parse(birthDate);
        } catch (DateTimeParseException e) {
            System.out.println("Bad birth date!");
            this.birthDate = null;
        }
        this.lastEdited = LocalDateTime.of(LocalDate.now().getYear(), LocalDate.now().getMonth(),
                LocalDate.now().getDayOfMonth(), LocalTime.now().getHour(), LocalTime.now().getMinute());
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        if (!("M".equals(gender) || "F".equals(gender))) {
            System.out.println("Bad gender!");
            this.gender = "";
        } else {
            this.gender = gender;
        }
        this.lastEdited = LocalDateTime.of(LocalDate.now().getYear(), LocalDate.now().getMonth(),
                LocalDate.now().getDayOfMonth(), LocalTime.now().getHour(), LocalTime.now().getMinute());
    }

    private boolean validateBirthDate(String date) {
        String pattern = "[0-9]{4}[- ][0-9]{2}[- ][0-9]{2}";
        return date.matches(pattern);
    }

    @Override
    public String[] availableFields() {
        return new String[]{"name", "surname", "birth", "gender", "number"};
    }

    @Override
    public void changeField(String field, String fieldValue) {
        switch (field) {
            case "name":
                this.name = fieldValue;
                break;
            case "surname":
                this.surname = fieldValue;
                break;
            case "number":
                setNumber(fieldValue);
                break;
            case "birth":
                setBirthDate(fieldValue);
                break;
            case "gender":
                setGender(fieldValue);
                break;
            default:
                break;
        }
    }

    @Override
    public void info() {
        System.out.println("Name: " + name);
        System.out.println("Surname: " + surname);
        System.out.print("Birth date: ");
        if (birthDate == null) {
            System.out.println("[no data]");
        } else {
            System.out.println(birthDate);
        }

        System.out.print("Gender: ");
        if ("".equals(gender)) {
            System.out.println("[no data]");
        } else {
            System.out.println(gender);
        }

        System.out.print("Number: ");
        if ("".equals(number)) {
            System.out.println("[no number]");
        } else {
            System.out.println(number);
        }
        System.out.println("Time created: " + created);
        System.out.println("Time last edit: " + lastEdited);
    }

    @Override
    public String toString() {
        return name + " " + surname;
    }

    @Override
    public String toSearchPattern() {
        String s = name + " " + surname + " " + gender + " " + number;
        if (birthDate != null)
            s += birthDate.toString();
        return s;
    }
}
