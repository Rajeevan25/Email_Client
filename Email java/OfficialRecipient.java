import java.io.IOException;

public class OfficialRecipient extends Recipient {

	public String designation;

	public OfficialRecipient(String name, String email, String designation) {
		super(name, email);
		this.designation = designation;
	}

	protected OfficialRecipient(String name, String email, String designation,String dateOfBirth) {   // for it  sub class-- Official friend recipient
		super(name, email,dateOfBirth);
		this.designation = designation;
	}

	@Override
	public void writeData() {
		try {
			fileWriter.write("Official: " + super.name + "," + super.email + "," + this.designation + "\n");
			fileWriter.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

}

