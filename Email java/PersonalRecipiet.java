import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PersonalRecipiet extends Recipient {

	private String nickName;

	public PersonalRecipiet(String name, String email, String nickName, String dateOfBirth) {  //constuctor
		super(name, email, dateOfBirth);
		this.nickName = nickName;
	}

	@Override
	public void writeData() {
		try {
			fileWriter.write("Personal: " + super.name + "," + super.email + "," + this.nickName + ","
					+ this.dateOfBirth + "\n");
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

		Mailer mailer = new Mailer(this.email, "Birthday Wish", "Hugs and love on your birthday . Rajeevan",
				currentDate);
		mailer.sendMail();
	    mailer.serializeMail();
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

}
