import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main_Encoder {

	public static void main(String[] args) throws IOException {
		File fileInput = new File(args[1]);
		File fileOutput = new File(args[2]);
		
		String message = " ";
		FileReader in = new FileReader(args[0]);
	    BufferedReader br = new BufferedReader(in);

	    String line;
	    while ((line = br.readLine()) != null) {
	        message += line;
	    }
	    in.close();
		
		Encoder encoder = new Encoder(message, fileInput,fileOutput);
		encoder.encodeMessage();
	}

}
