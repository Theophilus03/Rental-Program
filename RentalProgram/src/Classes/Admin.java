package Classes;

import java.util.Scanner;
import java.util.Vector;

import AbstractAndInterface.Employee_editable;
import AbstractAndInterface.Item;
import AbstractAndInterface.Item_editable;
import AbstractAndInterface.Person;

public class Admin extends Person implements Employee_editable, Item_editable {
	Scanner scan = new Scanner(System.in);

	// untuk keperluan verifikasi admin
	private String password;
	// untuk mengedit list Employee dan Item yang ada di main
	private Vector<Employee> employeeList;
	private Vector<Item> itemList;

	public Admin(String name, String password, Vector<Employee> employee, Vector<Item> item) {
		super(name);
		this.password = password;
		this.employeeList = employee;
		this.itemList = item;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// override dari interface Employee_editable
	// untuk menambahkan employee
	public void addEmployee(String name, String id) {
		Employee E = new Employee(name, id, "Admin", itemList);
		employeeList.add(E);
	}

	// untuk menghapus employee
	public void removeEmployee(int idx) {
		// decrement karena arraylist dimulai dari 0
		employeeList.remove(--idx);
	}

	// override dari interface Item_editable
	// untuk menambahkan item rental
	public void addItem() {
		System.out.print("Input Title[max 25]:");
		String title = scan.nextLine();
		System.out.print("Input Type[Movie | Series | Games]: ");
		String type = scan.nextLine();

		if (type.equalsIgnoreCase("Movie")) {
			System.out.print("Input Movie Duration (minutes): ");
			int duration = scan.nextInt();
			scan.nextLine();
			itemList.add(new Movie(title, type, "Admin", duration));

		} else if (type.equalsIgnoreCase("Series")) {
			System.out.print("Input Total Episode: ");
			int episode = scan.nextInt();
			scan.nextLine();
			itemList.add(new Series(title, type, "Admin", episode));

		} else {
			System.out.print("Input Game Console: ");
			String console = scan.nextLine();
			itemList.add(new Games(title, type, "Admin", console));
		}

	}
	
	// untuk menghapus item rental
	public void removeItem(int idx) {
		//jika status item yang akan di buang adalah "Rental" maka proses removeItem akan di batalkan karena sedang di pinjam
		if (itemList.get(idx).getStatus().equals("Rental")) {
			System.out.println("Can't Remove Item, because Item is bieng Rental.");
			return;
		}
		
		// decrement karena arraylist dimulai dari 0
		itemList.remove(idx);
	}

}
