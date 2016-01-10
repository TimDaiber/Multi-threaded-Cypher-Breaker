package ie.gmit.sw;

import java.io.IOException;

public class Runner {

	public static void main(String[] args) throws IOException,
			InterruptedException {
		threadstuff threadstuff = new threadstuff();
		
		// gets the message to be encrypted
		textstuff ts = new textstuff();

		ts.setText();

		System.out.println(ts.getText());

		int encryptionKey = (int) (Math.floor(Math.random() * 10) + 1);
		System.out.println(encryptionKey);
		String encryptedString = new RailFence().encrypt(ts.getText(), encryptionKey);
		System.out.println(encryptedString);
		//Test t = new Test();
		//QuadGramMap qg = new QuadGramMap();
		//qg.readFromFile();

		// System.out.println(qg.map);

		//threadstuff.createThreads();
		//threadstuff.takeThread();
		//hiiamjack
		System.out.println(threadstuff.getNewPlainText());

	}

}
