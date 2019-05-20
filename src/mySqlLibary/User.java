package mySqlLibary;

public class User {
	
	private int id;
	private String name;
	private String lastname;
	private int telephone; //numer zazwyczaj przechowuje siê jako String
	
	public User() {}
	
	public User(String name, String lastname, int telephone) {
		super();
		this.name = name;
		this.lastname = lastname;
		this.telephone = telephone;
	}
	
	public User(int id, String name, String lastname, int telephone) {
		super();
	    this.id = id;
		this.name = name;
		this.lastname = lastname;
		this.telephone = telephone;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLastname() {
		return this.lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public int getTelephone() {
		return this.telephone;
	}
	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}

	@Override
	public String toString() {
		return name + " " + lastname + " nr tel: " + telephone;
	}
	
	
}
