package contacts;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneBookManager {
    private PhoneBook phoneBook;
    private Scanner scanner;
    private Command command;
    private List<Record> searchResults;
    private String file;
    private boolean fromSearch;

    public PhoneBookManager(PhoneBook phoneBook) {
        this.phoneBook = phoneBook;
        scanner = new Scanner(System.in);
        searchResults = new ArrayList<>();
        fromSearch = false;
    }

    public PhoneBookManager(PhoneBook phoneBook, String file) {
        this.phoneBook = phoneBook;
        scanner = new Scanner(System.in);
        searchResults = new ArrayList<>();
        fromSearch = false;
        this.file = file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public void setPhoneBook(PhoneBook phoneBook) {
        this.phoneBook = phoneBook;
    }

    public void add() {
        String input;

        System.out.print("Enter the type (person, organization): ");
        input = scanner.nextLine();

        switch (input) {
            case "person":
                command = new AddPersonCommand(phoneBook);
                break;
            case "organization":
                command = new AddOrganizationCommand(phoneBook);
                break;
            default:
                return;
        }
        command.execute();
        if (file != null) {
            save();
        }
    }

    public void search() {
        fromSearch = true;
        String input;
        System.out.print("Enter the query: ");
        input = scanner.nextLine();

        searchResults = new ArrayList<>();
        Pattern pattern = Pattern.compile(input, Pattern.CASE_INSENSITIVE);
        Matcher matcher;

        for (var record : phoneBook.getRecords()) {
            matcher = pattern.matcher(record.toSearchPattern());
            if (matcher.find()) {
                searchResults.add(record);
            }
        }
        System.out.println("Found " + searchResults.size() + " results:");
        printRecords(searchResults);
        System.out.println();

        System.out.print("[search] Enter action ([number], back, again): ");
        input = scanner.nextLine();
        switch (input) {
            case "again":
                search();
            case "back":
                return;
            default:
                try {
                    int n = Integer.parseInt(input);
                    int ind = phoneBook.getRecords().indexOf(searchResults.get(n - 1));
                    record(ind);
                } catch (Exception ignored) {
                }
        }

    }

    public void remove(int i) {
        int numInput = 0;
        if (phoneBook.count() == 0) {
            System.out.println("No records to remove!");
            return;
        }
        phoneBook.remove(i);
        if (fromSearch) {
            searchResults.remove(searchResults.get(i));
        }

        if (file != null) {
            save();
        }
        if (file != null) {
            save();
        }
        System.out.println("The record removed!");
    }

    public void edit(int i) {
        String field;
        String fieldValue;

        System.out.print("Select a field (" + String.join(", ", phoneBook.get(i).availableFields()) + "): ");
        field = scanner.nextLine();

        System.out.print("Enter " + field + ": ");
        fieldValue = scanner.nextLine();

        phoneBook.get(i).changeField(field, fieldValue);
        if (file != null) {
            save();
        }
        System.out.println("The record updated!");
    }

    public void list() {
        if (phoneBook.count() == 0) {
            count();
            return;
        }

        fromSearch = false;
        phoneBook.print();
        System.out.println();

        String input;
        int num;
        System.out.print("[list] Enter action ([number], back): ");
        input = scanner.nextLine();
        if ("back".equals(input)) {
            return;
        }
        try {
            num = Integer.parseInt(input);
            record(num - 1);
        } catch (Exception ignored) {
        }

    }

    private void record(int i) {
        phoneBook.get(i).info();

        String input;
        while (true) {
            System.out.print("\n[record] Enter action (edit, delete, menu): ");
            input = scanner.nextLine();
            switch (input) {
                case "menu":
                    return;
                case "edit":
                    edit(i);
                    break;
                case "delete":
                    remove(i);
                    return;
            }
        }
    }

    public void count() {
        System.out.printf("The Phone Book has %d records.\n", phoneBook.count());
    }

    private void printRecords(List<Record> records) {
        int i = 1;
        for (var record : records) {
            System.out.println(i++ + ". " + record.toString());
        }
    }

    private void save() {
        try {
            SerializationUtils.serialize(phoneBook, file);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
