package ie.gmit.sw;

//import java.io.IOException;

public class Runner {

	public static void main(String[] args) throws Exception {

		textstuff ts = new textstuff();
		ts.setText();
		String mymessage = ts.getText();
		System.out.println(mymessage);

		int encryptionKey = (int) (Math.floor(Math.random() * 12) + 2);
		System.out.println(encryptionKey);
		String encryptedString = new RailFence().encrypt(mymessage,
				encryptionKey);
		System.out.println(encryptedString);

		Threadable threads = new threads(encryptedString);
		threads.endqueue();
		// threads.calculateThreads();
		// threads.eat();
		// threads.endqueue();
		// Test t = new Test();
		// QuadGramMap qg = new QuadGramMap();
		// qg.readFromFile();

		// System.out.println(qg.map);

		// threadstuff.createThreads();
		// threadstuff.takeThread();
		// hiiamjack
		// System.out.println(threadstuff.getNewPlainText());

	}

}
