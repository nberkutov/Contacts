package contacts;

import java.util.Scanner;

public class AddPersonCommand implements Command {
    private PhoneBook phoneBook;

    public AddPersonCommand(PhoneBook phoneBook) {
        this.phoneBook = phoneBook;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        String name, surname, number, birthDate, gender;
        PersonRecord person = new PersonRecord();

        System.out.print("Enter the name: ");
        name = scanner.nextLine();
        person.setName(name);

        System.out.print("Enter the surname: ");
        surname = scanner.nextLine();
        person.setSurname(surname);

        System.out.print("Enter the birth date: ");
        birthDate = scanner.nextLine();
        person.setBirthDate(birthDate);

        System.out.print("Enter the gender (M, F): ");
        gender = scanner.nextLine();
        person.setGender(gender);

        System.out.print("Enter the number: ");
        number = scanner.nextLine();
        person.setNumber(number);

        phoneBook.addRecord(person);

        System.out.println("The record added.");
    }
}
