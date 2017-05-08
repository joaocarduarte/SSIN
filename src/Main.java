import java.io.File;
import java.io.UnsupportedEncodingException;

public class Main {

	public static void main(String[] args) throws UnsupportedEncodingException {
		
		String message = "pixota para a tua mae na boca";
		File fileInput = new File("C:/Users/veryc/Downloads/teste.wav");
		File fileOutput = new File("C:/Users/veryc/Downloads/teste1.wav");
		
		Encoder encoder = new Encoder(message, fileInput,fileOutput);
		encoder.encodeMessage();
		
		Decoder decoder = new Decoder(fileOutput);
		
		System.out.println(new String(decoder.decode()));

	}

}
