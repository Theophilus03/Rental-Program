package Classes;

import java.util.Scanner;
import java.util.Vector;

import AbstractAndInterface.Item;
import AbstractAndInterface.Person;
import AbstractAndInterface.Rentalable;

public class Customer extends Person implements Rentalable {
	Scanner scan = new Scanner(System.in);

	// Nomor telepon untuk data diri
	private String phoneNumber;
	// List untuk menampung item yang sedang dipinjam
	private Vector<Item> borrowList = new Vector<>();
	// untuk mengedit list item yang ada di main
	private Vector<Item> itemList;

	public Customer(String name, String phoneNumber, Vector<Item> itemList) {
		super(name);
		this.phoneNumber = phoneNumber;
		this.itemList = itemList;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	// override dari interface Rentalable
	// method untuk meminjam item
	public void borrowItem() {
		System.out.print("Input Index to Rental Item: ");
		int idx = scan.nextInt(); scan.nextLine();

		// jika item yang ingin di rental ternyata memiliki status
		// "Rental"(sedang dipinjam sehingga tidak tersedia) maka tidak akan bisa di
		// pinjam
		idx--;
		if (itemList.get(idx).getStatus().equals("Rental")) {
			System.out.println(itemList.get(idx).getType() + " " + itemList.get(idx).getTitle()
					+ " is bieng Rental (Not Available).");
			return;
		}

		// decerment karena arraylist dimulai dari 0
		borrowList.add(itemList.get(idx));

		// merubah status yang awalnya "Available" menjadi "Rental"
		itemList.get(idx).setStatus("Rental");
	}

	//method untuk mengembalikan item
	public void returnItem() {
		if(borrowList.isEmpty()) {
			System.out.println("My Rental List is Empty");
			return;
		}
		
		System.out.println("\nMy Rental List");
		System.out.println("============================================");
		System.out.printf("| %-3s | %-25s | %-6s |\n", "No.", "Title", "Type");
		System.out.println("============================================");

		int i=1;
		for (Item item : borrowList) {
			System.out.printf("| %-3s | %-25s | %-6s |\n", i++, item.getTitle(), item.getType());
		}
		
		//input index yang ingin dikembalikan
		int idx;
		do {
			System.out.print("Input Index [1 - "+ borrowList.size() +"]: ");
			idx = scan.nextInt(); scan.nextLine();	
		}while(idx<1 || idx>borrowList.size());
		
		idx--;
		
		//mencari item yang akan di kembalikan di itemList
		int idx2 = -1;
		for(int j=0; j<itemList.size(); j++) {
			if(itemList.get(j).getTitle().equals(borrowList.get(idx).getTitle())) {
				idx2= j;
			}
		}
		
		//remove dari borrowList
		borrowList.remove(idx);
		
		//mengubah status di itemList kembali menjadi "Available"
		itemList.get(idx2).setStatus("Available");
	}

}
