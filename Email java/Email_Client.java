//your index number------200501P

//import libraries
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
/* 
import javax.mail.*;
import javax.mail.PasswordAuthentication;
import javax.mail.Authenticator;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.Serializable;
import javax.mail.MessagingException;
import java.util.Properties;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.Formatter;
import java.io.FileNotFoundException;

*/

public class Email_Client {

	static ArrayList<Recipient> recipients;   //create a arraylist for maintain recipient details

	static {
		Recipient recipient = new Recipient();
		recipients = recipient.readData();

		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd");
		Date date = new Date();
		String currentDate = formatter.format(date);  /// change the date format

		for (Recipient row : recipients) {
			if (row.getDateOfBirth() == null) {
				continue;
			}
			if (row.getDateOfBirth().equals(currentDate.toString())) {  // check the today birthday persons for wish
				row.sendBirthdayWish();
			}
		}
	}

	public static void main(String[] args) {

		String userInput;
		Mailer email;

		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter option type: \n" + "1 - Adding a new recipient\n" + "2 - Sending an email\n"
				+ "3 - Printing out all the recipients who have birthdays\n"
				+ "4 - Printing out details of all the emails sent\n"
				+ "5 - Printing out the number of recipient objects in the application");

		int option = scanner.nextInt();
		
		while(option != -1){
			switch (option) {
				case 1:
		
					System.out.print("\nEnter Recipient : ");
					userInput = scanner.next();
					Recipient recipient;
					String[] splitedInput = userInput.split(":");
		
					// check for recipient type1
					try {	
						if (userInput.startsWith("Official:")) {
							System.out.println("Successfully add the recipient");
		
							String[] data = splitedInput[1].split(",");
							recipient = new OfficialRecipient(data[0], data[1], data[2]);  // create the new official recipient 
							recipient.writeData();
		
						} else if (userInput.startsWith("Personal:")) {
							System.out.println("Successfully add the recipient");
		
							String[] data = splitedInput[1].split(",");
							recipient = new PersonalRecipiet(data[0], data[1], data[2], data[3]);   // create the new personal recipient 
							recipient.writeData();
		
						} else if (userInput.startsWith("Office_friend:")) {
							System.out.println("Successfully add the recipient");
		
							String[] data = splitedInput[1].split(",");
							recipient = new OfficeFriendRecipient(data[0], data[1], data[2], data[3]);  //create the new official_friend recipient 
							recipient.writeData();
		
						} else {
							System.out.println("Invalid user Input");
						}
					} catch (IndexOutOfBoundsException E) {
						System.out.println("Invalid/insufficient data inputed");
					}
		
					break;
				case 2:
					// input format - email, subject, content
		
					System.out.print("\nEnter email data(email,subject,content): ");
					userInput = scanner.next();
					try {
		
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
						Date date = new Date();
						String currentDate = formatter.format(date);
		
						String[] emailData = userInput.split(",");
						email = new Mailer(emailData[0], emailData[1], emailData[2], currentDate);
						email.sendMail();
						email.serializeMail();
		
					} catch (Exception E) {
						System.out.println("Invalid/insufficient data");
					}
		
					break;
		
				case 3:
					// input format - yyyy/MM/dd (ex: 2018/09/17)
					// code to print recipients who have birthdays on the given date
		
					System.out.print("\nEnter date(yyyy/MM/dd): ");
					userInput = scanner.next();
		
					for (Recipient row : recipients) {      
						if (row.getDateOfBirth() == null) {
							continue;
						}
						if (row.getDateOfBirth().equals(userInput.substring(5))) {
							System.out.println(row.getName());
						}
					}
		
					break;
				case 4:
					// input format - yyyy/MM/dd (ex: 2018/09/17)
					// code to print the details of all the emails sent on the input date
		
					System.out.print("\nEnter date(yyyy/MM/dd): ");
					userInput = scanner.next();
		
					ArrayList<Mailer> mails;
					mails = Mailer.deserializeMail();
		
					if (mails == null) {
						System.out.println("No data");
						break;
					}
		
					for (Mailer mail : mails) {
						
						if (mail.getDate().equals(userInput)) {
							System.out.println("Email : " + mail.getEmail() + "\nDate : " + mail.getDate() + "\nSubject : "
									+ mail.getSubject() + "\nContent : " + mail.getContent() + "\n");
						}
					}
		
					break;
				case 5:
					// code to print the number of recipient objects in the application
					System.out.println("Total Recipients : " + Recipient.count);
					break;
		
				
				}
				option = scanner.nextInt();
		}
		

		// start email client
		// code to create objects for each recipient in clientList.txt
		// use necessary variables, methods and classes

		scanner.close();

	}
}









