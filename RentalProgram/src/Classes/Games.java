package Classes;

import AbstractAndInterface.Item;

public class Games extends Item {

	private String gameConsole;

	public Games(String title, String type, String adder, String gameConsole) {
		super(title, type, adder);
		this.gameConsole = gameConsole;
	}

	public String getGameConsole() {
		return gameConsole;
	}

	public void setGameConsole(String gameConsole) {
		this.gameConsole = gameConsole;
	}
	
	public void view() {
		System.out.printf(" %-25s | %-6s | %-11s | %-13s | %-15s | %-9s | %-5s |\n",title, type, "-", "-", gameConsole, status, adder);
	}
}
