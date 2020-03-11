package contacts;

import java.util.Scanner;

public class AddOrganizationCommand implements Command {
    private PhoneBook phoneBook;

    public AddOrganizationCommand(PhoneBook phoneBook) {
        this.phoneBook = phoneBook;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        String name, address, number;
        OrganizationRecord organization = new OrganizationRecord();
        System.out.print("Enter the organization name: ");
        name = scanner.nextLine();
        organization.setName(name);

        System.out.print("Enter the address: ");
        address = scanner.nextLine();
        organization.setAddress(address);

        System.out.print("Enter the number: ");
        number = scanner.nextLine();
        organization.setNumber(number);

        phoneBook.addRecord(organization);
        System.out.println("The record added.");
    }
}
