package rentalServer.user.model;

import java.sql.Timestamp;

public class User {
	private String id;
	private String name;
	private String birth;
	private String phone;
	private String licenseDate;
	private Timestamp regDate;
	private Timestamp modDate;
	
	public User(String id, String name, String birth, String phone, String licenseDate, Timestamp regDate, Timestamp modDate) {
		super();
		this.id = id;
		this.name = name;
		this.birth = birth;
		this.phone = phone;
		this.licenseDate = licenseDate;
		this.regDate = regDate;
		this.modDate = modDate;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getBirth() {
		return birth;
	}

	public String getPhone() {
		return phone;
	}

	public String getLicenseDate() {
		return licenseDate;
	}

	public Timestamp getRegDate() {
		return regDate;
	}

	public Timestamp getModDate() {
		return modDate;
	}
	
}
