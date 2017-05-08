import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Encoder {

	private String message;
	private File fileInput;
	private File fileOutput;

	private ByteArrayOutputStream messageBytesOut;
	private byte[] messageBytes;
	private int messageSize;
	private int fileSize;
	private byte byteArrayInput[];
	private int offset;

	public Encoder(String message, File fileInput, File fileOutput) {
		this.message = message;
		this.fileInput = fileInput;
		this.fileOutput = fileOutput;

		messageBytesOut = new ByteArrayOutputStream();

		messageBytes = message.getBytes();
		messageSize = messageBytes.length;
		fileSize = (int) fileInput.length();
		byteArrayInput = new byte[fileSize];



	}

	public boolean encodeMessage(){

		try{
			//Open the file, read all the bytes and place into byteArrayInput
			DataInputStream in = new DataInputStream(new FileInputStream(fileInput));
			in.read(byteArrayInput, 0, fileSize);
			in.close();
			
			offset = 44;
			
			byte len[] = lengthToByte(messageSize);
			
			for(int i = 0; i < len.length; i++){

				int msg = len[i];

				for(int bit = 7; bit >= 0; --bit, ++offset){

					int b = (msg >>> bit) & 1;
					byteArrayInput[offset] = (byte)((byteArrayInput[offset] & 0xFE) | b);

				}


			}

			offset = 76;

			for(int i = 0; i < messageSize; i++){

				int msg = messageBytes[i];

				for(int bit = 7; bit >= 0; --bit, ++offset){

					int b = (msg >>> bit) & 1;
					byteArrayInput[offset] = (byte)((byteArrayInput[offset] & 0xFE) | b);

				}


			}
			

			//Copy the input file header to the output stream
			messageBytesOut.write(byteArrayInput, 0, fileSize);
			//inputOutputMarker = 44;

			DataOutputStream out = new DataOutputStream(new FileOutputStream(fileOutput));
			messageBytesOut.writeTo(out);
			out.close();

		} catch (Exception e){

		}



		return true;
	}

	public byte[] lengthToByte(int i){

		byte byte3 = (byte) ((i & 0xFF000000) >>> 24); 
		byte byte2 = (byte) ((i & 0x00FF0000) >>> 16);
		byte byte1 = (byte) ((i & 0x0000FF00) >>> 8);
		byte byte0 = (byte) ((i & 0x000000FF));

		return(new byte[]{byte3, byte2, byte1, byte0});

	}
}
