import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;

public class Decoder {

	private File fileInput;
	private int fileSize;

	public Decoder(File fileInput){

		this.fileInput = fileInput;
		fileSize = (int) fileInput.length();

	}

	public byte[] decode() {

		byte byteArrayInput[] = new byte[fileSize];
		int offset = 44;

		try{
			//Open the file, read all the bytes and place into byteArrayInput
			DataInputStream in = new DataInputStream(new FileInputStream(fileInput));
			in.read(byteArrayInput, 0, fileSize);
			in.close();
			
			byte[] result = new byte[6];
			
			for(int b = 0; b < result.length; ++b) {
				for(int i = 0; i < 8; ++i, ++offset) {
					result[b] = (byte) (( result[b] << 1) | (byteArrayInput[offset] & 1));
				}
			}
			
			return result;
			
		} catch (Exception e){
			
		}
		return byteArrayInput;

	}

}
