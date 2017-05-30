import java.io.*;
import java.util.*;

public class fileHandler {
	public void writeFile(String encryptedText, String location) throws IOException {
		try{
			FileWriter fileWriter = new FileWriter(new File(location));
			fileWriter.write(encryptedText);
			fileWriter.close();
		}catch (Exception e){
			System.err.println("In file writer " + e);
		}finally {
			System.out.println("Saved to:\n" + location);
		}
	}
	
	public String readFile(String location){
		String text = "";
		try{
			File file = new File(location);		
			Scanner s = new Scanner(file);
			while(s.hasNext()){
				text += s.next();
			}
			s.close();
		}catch(Exception e){
		System.out.println("Can't read file.");	
		}				
		return text;
	}
}
