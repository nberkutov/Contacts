package contacts;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PhoneBook implements Serializable {
    private List<Record> records;

    public PhoneBook() {
        records = new ArrayList<>();
    }

    public int count() {
        return records.size();
    }

    public void addRecord(Record record) {
        records.add(record);
    }

    public Record get(int i) {
        return records.get(i);
    }

    public void remove(int i) {
        records.remove(i);
    }

    public void remove(Record record) {
        int i = records.indexOf(record);
        if (i != -1) {
            records.remove(i);
        }
    }

    public List<Record> getRecords() {
        return records;
    }

    public void print() {
        int counter = 1;
        for (var record : records) {
            System.out.print(counter++ + ". ");
            System.out.println(record);
        }
    }
}
