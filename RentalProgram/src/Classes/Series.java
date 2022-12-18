package Classes;

import AbstractAndInterface.Item;

public class Series extends Item{
	private int totalEpisode;

	public Series(String title, String type, String adder, int totalEpisode) {
		super(title, type, adder);
		this.totalEpisode = totalEpisode;
	}

	public int getTotalEpisode() {
		return totalEpisode;
	}

	public void setTotalEpisode(int totalEpisode) {
		this.totalEpisode = totalEpisode;
	}
	
	public void view() {
		System.out.printf(" %-25s | %-6s | %-11s | %-13d | %-15s | %-9s | %-5s |\n",title, type, "-", totalEpisode, "-", status, adder);
	}
	
	
}
