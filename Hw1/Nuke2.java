import java.io.*;

public class Nuke2 {
   public static void main(final String[] args) throws IOException {
    final BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
    System.out.print("Please enter the name of a company (without spaces): ");
    System.out.flush(); 
    final String str = new String(keyboard.readLine());
    String newstr = str.substring(0,1) + str.substring(2);
    System.out.println(newstr);
    
        
    }


}
