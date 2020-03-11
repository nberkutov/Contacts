package contacts;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String file = null;
        PhoneBookManager manager;
        PhoneBook book;
        if (args.length > 0) {
            file = args[0];
            if (Files.exists(Path.of(file))) {
                try {
                    if (Files.size(Path.of(file)) == 0) {
                        book = new PhoneBook();
                    } else {
                        book = (PhoneBook) SerializationUtils.deserialize(file);
                    }
                } catch (IOException | ClassNotFoundException e1) {
                    book = new PhoneBook();
                    e1.printStackTrace();
                }
            } else {
                book = new PhoneBook();
                try {
                    Files.createFile(Path.of(file));
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
        } else {
            book = new PhoneBook();
        }
        manager = new PhoneBookManager(book, file);
        String name, surname, number;
        String input = "";
        int numInput = 0;

        while (!"exit".equals(input)) {
            System.out.print("[menu] Enter action (add, list, search, count, exit): ");
            input = scanner.nextLine();
            switch (input) {
                case "add":
                    manager.add();
                    break;
                case "list":
                    manager.list();
                    break;
                case "search":
                    manager.search();
                    break;
                case "count":
                    manager.count();
                    break;
                case "exit":
                    break;
            }
            System.out.println();
        }

        /*while (!input.equals("exit")) {
            input = scanner.nextLine();
            System.out.println(Record.validateNumber(input));
        }*/

    }
}
