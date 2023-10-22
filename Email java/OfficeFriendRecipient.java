
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OfficeFriendRecipient extends OfficialRecipient {

	public OfficeFriendRecipient(String name, String email, String designation, String dateOfBirth) {
		super(name, email, designation, dateOfBirth);
	}

	@Override
	public void writeData() {
		try {
			fileWriter.write("Office_friend: " + super.name + "," + super.email + "," + super.designation + ","
					+ super.dateOfBirth + "\n");
			fileWriter.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

	}

	@Override
	public void sendBirthdayWish() {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		String currentDate = formatter.format(date);

		Mailer mailer = new Mailer(this.email, "Birthday Wish", "Wish you a Happy Birthday. Rajeevan", currentDate);
		mailer.sendMail();
		mailer.serializeMail();
	}

}

