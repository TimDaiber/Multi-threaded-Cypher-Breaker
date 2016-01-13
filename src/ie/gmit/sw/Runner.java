package ie.gmit.sw;

//import java.io.IOException;

public class Runner {

	public static void main(String[] args) throws Exception {

		textstuff ts = new textstuff();
		ts.setText();
		String mymessage = ts.getText();
		System.out.println(mymessage);
		int quickint = mymessage.length();
		// hellomynameisearl

		int encryptionKey = (int) (Math.floor(Math.random() * (24)) + 1);
		System.out.println(encryptionKey);
		String encryptedString = new RailFence().encrypt(mymessage,
				encryptionKey);
		System.out.println(encryptedString);

		Threadable threads = new threads(encryptedString);
		threads.endqueue();

		TopResult tps = new TopResult();
		System.out.println("Message: " + tps.getTopMessage());
		System.out.println("Key: " + tps.getTopKey());
		System.out.println("Score: " + tps.getTopReult());
	}

}
