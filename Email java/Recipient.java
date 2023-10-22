import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Recipient {

	protected String name;
	protected String email;
	protected String dateOfBirth;

	protected File file;
	protected FileWriter fileWriter;
	protected Scanner fileReader;

	public static int count = 0;   // count of recipient

	public Recipient(String name, String email, String dateOfBirth) {
		super();
		this.name = name;
		this.email = email;
		this.dateOfBirth = dateOfBirth;

		// increase user count
		count++;

		// create file
		try {
			file = new File("clientList.txt");
			file.createNewFile();

		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		// initialize file writer
		try {
			fileWriter = new FileWriter(file, true);
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		// initialize file reader
		try {
			fileReader = new Scanner(file);
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	public Recipient(String name, String email) {
		super();
		this.name = name;
		this.email = email;

		// increase user count
		count++;

		// create file
		try {
			file = new File("clientList.txt");
			file.createNewFile();

		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		// initialize file writer
		try {
			fileWriter = new FileWriter(file, true);
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		// initialize file reader
		try {
			fileReader = new Scanner(file);
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

	}

	public Recipient() {
		super();

		// create file
		try {
			file = new File("clientList.txt");
			file.createNewFile();

		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		// initialize file writer
		try {
			fileWriter = new FileWriter(file, true);
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		// initialize file reader
		try {
			fileReader = new Scanner(file);
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	public void writeData() {
		// implement in sub classes
	};

	public ArrayList<Recipient> readData() {

		ArrayList<Recipient> recipients = new ArrayList<Recipient>();

		try {

			Scanner myReader = new Scanner(file);
			while (myReader.hasNextLine()) {

				String line = myReader.nextLine();
				Recipient recipient;
				String[] splitedInput = line.split(":");

				if (line.startsWith("Official:")) {

					String[] data = splitedInput[1].split(",");
					recipient = new OfficialRecipient(data[0], data[1], data[2]);
					recipients.add(recipient);

				} else if (line.startsWith("Personal:")) {

					String[] data = splitedInput[1].split(",");
					recipient = new PersonalRecipiet(data[0], data[1], data[2], data[3]);
					recipients.add(recipient);

				} else if (line.startsWith("Office_friend:")) {

					String[] data = splitedInput[1].split(",");
					recipient = new OfficeFriendRecipient(data[0], data[1], data[2], data[3]);
					recipients.add(recipient);

				}
			}

			myReader.close();

			return recipients;

		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
			return recipients;
		}

	};

	public void sendBirthdayWish() {
		// implement in sub classes
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDateOfBirth() {
		if(this.dateOfBirth!=null){
			String birthDate= this.dateOfBirth.substring(5);
			return birthDate;

		}
		
		return this.dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

}

