package Classes;

import java.util.Scanner;
import java.util.Vector;

import AbstractAndInterface.Item;
import AbstractAndInterface.Item_editable;
import AbstractAndInterface.Person;

public class Employee extends Person implements Item_editable {
	Scanner scan = new Scanner(System.in);

	// ID untuk meverifikasi employee
	private String id, recruiter;
	//// untuk mengedit list item yang ada di main
	private Vector<Item> itemList;

	public Employee(String name, String id, String recruiter, Vector<Item> item) {
		super(name);
		this.id = id;
		this.recruiter = recruiter;
		this.itemList = item;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRecruiter() {
		return recruiter;
	}

	public void setRecruiter(String recruiter) {
		this.recruiter = recruiter;
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
			itemList.add(new Movie(title, type, id, duration));

		} else if (type.equalsIgnoreCase("Series")) {
			System.out.print("Input Total Episode: ");
			int episode = scan.nextInt();
			scan.nextLine();
			itemList.add(new Series(title, type, id, episode));

		} else {
			System.out.print("Input Game Console: ");
			String console = scan.nextLine();
			itemList.add(new Games(title, type, id, console));
		}

	}

	// untuk menghapus item rental
	public void removeItem(int idx) {
		// jika status item yang akan di buang adalah "Rental" maka proses removeItem akan di batalkan karena sedang di pinjam
		if (itemList.get(idx).getStatus().equals("Rental")) {
			System.out.println("Can't Remove Item, because Item is bieng Rental.");
			return;
		}

		// decrement karena arraylist dimulai dari 0
		itemList.remove(idx);
	}

}
