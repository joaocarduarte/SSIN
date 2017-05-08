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

		int length = 0;
		byte byteArrayInput[] = new byte[fileSize];
		int offset = 76;

		try{
			//Open the file, read all the bytes and place into byteArrayInput
			DataInputStream in = new DataInputStream(new FileInputStream(fileInput));
			in.read(byteArrayInput, 0, fileSize);
			in.close();
			
			for(int i = 44; i < 76; ++i) {
				length = (length << 1) | (byteArrayInput[i] & 1);
			}
			
			byte[] result = new byte[length];
			
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
