import java.io.File;

public class Main_Decoder {

	public static void main(String[] args) {
		File fileOutput = new File(args[0]);
		Decoder decoder = new Decoder(fileOutput);
		System.out.println(new String(decoder.decode()));
	}

}
