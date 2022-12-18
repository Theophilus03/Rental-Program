package AbstractAndInterface;

public abstract class Item {
//	status = Available karena saat pertama kali kita bikin object item pasti akan available
	protected String title, type, status = "Available", adder;

	public Item(String title, String type, String adder) {
		super();
		this.title = title;
		this.type = type;
		this.adder = adder;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAdder() {
		return adder;
	}

	public void setAdder(String adder) {
		this.adder = adder;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public abstract void view();


}
