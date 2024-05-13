package rentalServer.user.model;

public class UserResponseDto {
	
	private String id;
	private String name;
	private String birth;
	private String phone;
	private String licenseDate;

	public UserResponseDto() {
		super();
	}

	public UserResponseDto(String id, String name, String birth, String phone, String licenseDate) {
		super();
		this.id = id;
		this.name = name;
		this.birth = birth;
		this.phone = phone;
		this.licenseDate = licenseDate;
	}
	
	public UserResponseDto(User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.birth = user.getBirth();
		this.phone = user.getPhone();
		this.licenseDate = user.getLicenseDate();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getLicenseDate() {
		return licenseDate;
	}

	public void setLicenseDate(String licenseDate) {
		this.licenseDate = licenseDate;
	}
	
	
}
