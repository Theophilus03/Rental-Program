package Classes;

import AbstractAndInterface.Item;

public class Movie extends Item {
	private int duration;

	public Movie(String title, String type, String adder, int duration) {
		super(title, type, adder);
		this.duration = duration;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public void view() {
		System.out.printf(" %-25s | %-6s | %-3d minutes | %-13s | %-15s | %-9s | %-5s |\n",title, type, duration, "-", "-", status, adder);
	}

}
