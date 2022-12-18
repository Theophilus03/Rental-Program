package Main;

import java.util.Scanner;
import java.util.Vector;

import AbstractAndInterface.Item;
import Classes.Admin;
import Classes.Customer;
import Classes.Employee;
import Classes.Games;
import Classes.Movie;
import Classes.Series;

public class Main {
	Scanner scan = new Scanner(System.in);

	// untuk menampung semua List Item yang direntalkan
	Vector<Item> Item_List = new Vector<>();
	// untuk menampung semua data employee
	Vector<Employee> Employee_List = new Vector<>();
	//untuk menampung semua data customer
	Vector<Customer> Customer_List = new Vector<>();
	
	// dummy data untuk admin
	Admin owner = new Admin("Theophilus", "1234", Employee_List, Item_List);
	
	//untuk membuat code employee
	int cnt = 2;
		
		
	public Main() {

//		bikin dummy data
//		dummy data untuk employee
		owner.addEmployee("Agus", "E1");
		owner.addEmployee("Budi", "E2");

//		dummy data untuk item
		Item_List.add(new Movie("InterStellar", "Movie","Admin", 169));
		Item_List.add(new Movie("Shutter Island", "Movie", "Admin", 138));
		Item_List.add(new Series("Breaking Bad, Season:1", "Series","Admin", 7));
		Item_List.add(new Series("Mouse", "Series", "Admin", 20));
		Item_List.add(new Games("Fifa 2018", "Games", "Admin", "Play Station"));
		Item_List.add(new Games("Call of Duty", "Games", "Admin", "XBOX"));

		int menu;
		do {
			menu = menu();

			switch (menu) {
			case 1:
				admin();
				break;

			case 2:
				employee();
				break;
				
			case 3:
				customer();
				break;
			}
		} while (menu != 0);
		System.out.println("Thank you for using this program :)");
	}

	// method untuk menampilkan menu dan menerima input
	int menu() {
		System.out.println("\nRental Program");
		System.out.println("==============");
		System.out.println("1. Admin");
		System.out.println("2. Employee");
		System.out.println("3. Customer");
		System.out.println("0. Exit");

		int status;
		do {
			System.out.print(">> ");
			status = scan.nextInt();scan.nextLine();
		} while (status < 0 || status > 3);
		return status;
	}

	//jika input yang dipilih adalah Admin
	void admin() {
		//verifikasi admin dengan memasukan password
		System.out.println("Password Hint (1234)");
		do{
			System.out.print("Input Admin Password [0 to exit]: ");
			String password = scan.nextLine();
			//jika yang dimasukan adalah "0" maka akan balik ke menu sebelumnya
			if(password.equals("0")) {
				return;
			}
			else if(owner.getPassword().equals(password)) {
				break;
			}
			System.out.println("Wrong Password. try: 1234");
		}while(true);
		
		//Jika berhasil terverifikasi akan masuk ke menu selanjutnya
		int input;
		do {
			System.out.println("\nWelcom " + owner.getName());
			System.out.println("=================");
			System.out.println("1. View Item");
			System.out.println("2. Add Item");
			System.out.println("3. Remove Item");
			System.out.println("4. Add Employee");
			System.out.println("5. Remove Employee");
			System.out.println("0. Log-out");
			System.out.print(">> ");
			input = scan.nextInt();scan.nextLine();

			switch (input) {
			case 1:
				viewItem();
				break;
				
			case 2:
				owner.addItem();
				break;
				
			case 3:
				viewItem();
				//jika list item kosomg maka akan balik ke menu
				if(Item_List.isEmpty()) {
					break;
				}
				System.out.print("Input Index [1 - " + Item_List.size() + "]: ");
				int idx = scan.nextInt(); scan.nextLine();
				owner.removeItem(--idx);
				break;
				
			case 4:
				System.out.print("Input Employee Name: ");
				String name = scan.nextLine();
				String id = "E"+(++cnt);
				owner.addEmployee(name, id);
				break;
				
			case 5:
				// jika list employee kosong maka akan balik ke menu
				if (Employee_List.isEmpty()) {
					System.out.println("There is no employee\n");
					break;
				}

				// menampilkan List employee
				System.out.println("\nEmployee List");
				System.out.println("==============");
				for (int i = 0; i < Employee_List.size(); i++) {
					System.out.printf("%d. %s (%s)\n", i + 1, Employee_List.get(i).getName(), Employee_List.get(i).getId());
				}
				
				// menerima index employee
				System.out.print("Input Employee Index [1 - " + Employee_List.size() +" ]: ");
				int idx1 = scan.nextInt();
				owner.removeEmployee(idx1);
				break;
			}

		} while (input != 0);
		
	}
	
	// jika menu yang dipilih adalah Employee
	void employee() {
		// jika list employee kosong maka akan balik ke menu
		if (Employee_List.isEmpty()) {
			System.out.println("There is no employee\n");
			return;
		}

		// menampilkan List employee
		System.out.println("\nEmployee List");
		System.out.println("==============");
		for (int i = 0; i < Employee_List.size(); i++) {
			System.out.printf("%d. %s (%s)\n", i + 1, Employee_List.get(i).getName(), Employee_List.get(i).getId());
		}

		// verifikasi employee
		Employee employee = null;
		do {
			System.out.print("Input Employee ID (0 to exit): ");
			String id = scan.nextLine();

			if (id.equals("0")) {
				return;
			}

			// mencari employee dari ID yang dimasukan
			for (Employee E : Employee_List) {
				if (E.getId().equals(id)) {
					employee = E;
				}
			}
			// jika employee ketemu maka akan langsung keluar dari loop
			if (employee != null) {
				break;
			}
			System.out.println("Wrong Employee ID");
		} while (true);

		// menampilan menu yang bisa dilakukan oleh employee
		int input;
		do {
			System.out.println("\nWelcom Employee " + employee.getName());
			System.out.println("=================");
			System.out.println("1. View Item");
			System.out.println("2. Add Item");
			System.out.println("3. Remove Item");
			System.out.println("0. Log-out");
			System.out.print(">> ");
			input = scan.nextInt();scan.nextLine();

			switch (input) {
			case 1:
				viewItem();
				break;
			case 2:
				employee.addItem();
				break;
			case 3:
				viewItem();
				if(Item_List.isEmpty()) {
					break;
				}
				System.out.print("Input Index [1 - " + Item_List.size() + "]: ");
				int idx = scan.nextInt(); scan.nextLine();
				employee.removeItem(--idx);
				break;
			}

		} while (input != 0);
	}

//	menampilkan list item rental
	void viewItem() {
		if(Item_List.isEmpty()) {
			System.out.println("No Item!");
			return;
		}
		System.out.println("================================================================================================================");
		System.out.printf("| %-3s | %-25s | %-6s | %-11s | %-13s | %-15s | %-9s | %-5s |\n", "No.", "Title", "Type", "Duration", "Total Episode", "Game Console", "Status" ,"Adder");
		System.out.println("================================================================================================================");

		int i=0;
		for (Item I : Item_List) {
			System.out.printf("| %-3d |", ++i);
			I.view();
		}
		System.out.println("================================================================================================================");
	}

	// jika status yang dipilih adalah Customer
	void customer() {
		// isi data diri
		System.out.print("\nCustomer Phone Number: ");
		String phoneNumber = scan.nextLine();
		
		//mencari data customer di list_Customer
		int idx=-1;
		for(int i=0; i<Customer_List.size(); i++) {
			if(Customer_List.get(i).getPhoneNumber().equals(phoneNumber)) {
				idx = i;
			}
		}
		
		//variable untuk menampung data customer yang sedang log-in
		Customer customer;
		
		//jika tidak ditemukan di Customer_List, maka akan dibuat data customer baru
		if(idx==-1) {
			System.out.print("Customer Name: ");
			String name = scan.nextLine();
			customer = new Customer(name, phoneNumber, Item_List);
			Customer_List.add(customer);
		}else {
			customer = Customer_List.get(idx);
		}
		
		

		// mmenampilkan menu yang bisa dilakukan oleh customer
		int input;
		do {
			System.out.println("\nWelcome Customer " + customer.getName());
			System.out.println("===================");
			System.out.println("1. View Rental Item");
			System.out.println("2. Borrow Item");
			System.out.println("3. Return Item");
			System.out.println("0. Log-out");
			System.out.print(">> ");
			input = scan.nextInt(); scan.nextLine();
			
			switch (input) {
			case 1:
				viewItem();
				break;

			case 2:
				viewItem();
				customer.borrowItem();
				break;
				
			case 3:
				customer.returnItem();
				break;
			}
			
		} while (input != 0);
	}

	public static void main(String[] args) {

		new Main();
	}

}
