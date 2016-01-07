package ie.gmit.sw;

import java.io.IOException;

public class Runner {

	public static void main(String[] args) throws IOException,
			InterruptedException {
		threadstuff threadstuff = new threadstuff();
		textstuff ts = new textstuff();

		ts.setText();

		System.out.println(ts.getText());

		Test t = new Test();
		QuadGramMap qg = new QuadGramMap();
		qg.readFromFile();

		// System.out.println(qg.map);

		threadstuff.createThreads();
		threadstuff.takeThread();
		System.out.println(threadstuff.getNewPlainText());

	}

}
