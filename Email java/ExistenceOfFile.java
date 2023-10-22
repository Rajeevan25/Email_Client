import java.util.Scanner;
import java.util.Formatter;
import java.io.FileNotFoundException;
import java.io.File;  // Import the File class

public class ExistenceOfFile {
    public static void check_file_exist(String obj){
        Scanner sc = null;
        try {
            sc = new Scanner(new File(obj));
            sc.close();
        }
        catch (FileNotFoundException e) {
            try {
                try (Formatter format = new Formatter(obj)) {
                }
            } 
            catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
            
        }
    }   
}
