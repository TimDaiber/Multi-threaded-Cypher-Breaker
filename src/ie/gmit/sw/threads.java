package ie.gmit.sw;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

public class threads implements Threadable {

	QuadGramable qgm = new QuadGramMap();
	textstuff txs = new textstuff();
	private String encryptedText;
	Map<String, Double> loadedmap = new ConcurrentHashMap<String, Double>();
	private Result result;
	private int numberOfThreads;
	private volatile boolean running = true;
	BlockingQueue<Resultable> queue;

	TopResult tps = new TopResult();
	private volatile int counter;
	Object lock = new Object();
	private int threadCount = 24;

	public threads(String encryptedString) throws Exception {

		queue = new ArrayBlockingQueue<Resultable>(threadCount);
		this.encryptedText = encryptedString;
		// calculateThreads();
		eat();
	}

	@Override
	public int calculateThreads() {
		int textSize = txs.getText().length();
		if (textSize > 2) {
			numberOfThreads = textSize / 2;

		}
		return numberOfThreads;
	}

	@Override
	public void eat() throws Exception {
		loadedmap = qgm.readFromFile();
		for (int i = 2; i < threadCount; i++) {
			new Thread(new DecryptionThreads(queue, encryptedText, i,
					(ConcurrentHashMap<String, Double>) loadedmap)).start();

		}

		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (running) {
					try {
						result = (Result) queue.take();
						result.printResult();

						if (tps.getTopReult() <= result.getScore()) {
							System.out.println("Is this thing on?");
							// tp.setTopReult() =result.getScore();
							tps.setTopReult(result.getScore());
							tps.setTopMessage(result.getPlainText());
							tps.setTopKey(result.getKey());
						}

						result.checkstuff();

						System.out.println(counter);

						System.out.println("Message: " + tps.getTopMessage());
						System.out.println("Key: " + tps.getTopKey());
						System.out.println("Score: " + tps.getTopReult());

						increment();
						// hellomyfriend

						topresult();
						// endqueue();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
		t.start();
		t.join();
		endqueue();
	}

	// ---------------------------------------------will
	@Override
	public synchronized void increment() {
		counter++;
		if (counter == threadCount) {
			endqueue();

		}
	}

	public synchronized void topresult() {

	}

	// ---------------------------------------------will
	@Override
	public void endqueue() {
		running = false;
	}
}
