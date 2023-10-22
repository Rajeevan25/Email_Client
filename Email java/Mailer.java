import javax.mail.*;
import javax.mail.PasswordAuthentication;
import javax.mail.Authenticator;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.Serializable;
import javax.mail.MessagingException;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
@SuppressWarnings("unchecked")

public class Mailer implements Serializable {

	private String email;
	private String subject;
	private String content;
	private String date;

	// add your data
	private static String fromEmail = "rajeevany.20@uom.lk";
	private static String password = "Rajeevan@25";

	private static Properties prop;
	private static Session session;
	static ArrayList<Mailer> Arr= new ArrayList<>();


	public Mailer(String email, String subject, String content, String date) {
		this.email = email;
		this.subject = subject;
		this.content = content;
		this.date = date;
	}

	public void sendMail() {
		// setup email
		prop = new Properties();
		prop.put("mail.smtp.host", "submit.uom.lk");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");

		try {

			session = Session.getInstance(prop, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(fromEmail, password);
				}
			});

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(fromEmail));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(this.email));
			message.setSubject(this.subject);
			message.setText(this.content);

			Transport.send(message);

		} catch (MessagingException e) {
			System.out.println("error");
			e.printStackTrace();
		}
	}

	public void serializeMail() {
		ExistenceOfFile.check_file_exist("sentEmailList.txt");
		Arr.add(this);
        try{ 
 //Saving of object in a file
            FileOutputStream file = new FileOutputStream("sentEmailList.txt");
            ObjectOutputStream out = new ObjectOutputStream(file);
			
// Method for serialization of object
            out.writeObject(Arr);
			
            out.close(); 
            file.close();
            System.out.println("Object has been serialized");
        }
        catch(IOException ex){
            System.out.println("IOException is caught");
        }
		
	}

	public static ArrayList<Mailer> deserializeMail() {
		ArrayList<Mailer> mails= new ArrayList<>();
        // Deserialization
        try{
            // Reading the object from a file
            FileInputStream file = new FileInputStream("sentEmailList.txt");
            ObjectInputStream in = new ObjectInputStream(file);
			
            mails= (ArrayList<Mailer>)in.readObject();     
			
            in.close();
            file.close();
			//System.out.println(mails);
            System.out.println("Object has been deserialized ");
			return mails;
        }
        catch(IOException ex){
            System.out.println("IOException is caught");
			return null;
        } 
        catch (Exception c) {
			System.out.println("Mailer class not found");
			c.printStackTrace();
			return null;
		}
        
        

		
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}

