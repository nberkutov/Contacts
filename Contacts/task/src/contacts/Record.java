package contacts;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public abstract class Record implements Serializable {
    protected String name;
    protected String number;
    protected LocalDateTime created;
    protected LocalDateTime lastEdited;

    public Record() {
        this.created = LocalDateTime.of(LocalDate.now().getYear(), LocalDate.now().getMonth(),
                LocalDate.now().getDayOfMonth(), LocalTime.now().getHour(), LocalTime.now().getMinute());
        this.lastEdited = this.created;
    }

    public Record(String name, String number) {
        this();
        this.name = name;
        setNumber(number);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public static boolean validateNumber(String number) {
        String p1 = "[+]?((\\([0-9A-Za-z]+\\)([ -][0-9A-Za-z]{2,})?)|([0-9A-Za-z]+([ -]\\([0-9A-Za-z]{2,}\\))?))";
        String p2 = "([ -][a-zA-Z0-9]{2,})*";
        return number.matches(p1 + p2);
    }

    public abstract String[] availableFields();

    public abstract void changeField(String field, String fieldValue);

    public abstract void info();

    public abstract String toSearchPattern();
}
