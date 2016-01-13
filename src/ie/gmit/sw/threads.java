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

	private volatile int counter = 0;
	Object lock = new Object();
	private int MAX_QUEUE_SIZE;

	public threads(String encryptedString) throws Exception {

		queue = new ArrayBlockingQueue<Resultable>(calculateThreads());
		this.encryptedText = encryptedString;
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
		for (int i = 2; i < txs.getText().length() / 2; i++) {
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

						increment();

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
	public void increment() throws InterruptedException {
		synchronized (lock) {
			counter++;
			if (counter == MAX_QUEUE_SIZE) {
				// queue.put(new PoisonResult(result.getPlainText(),
				// result.getKey(), result.getScore()));

				System.out.println("MAX QUEUE SIZE Reached "
						+ calculateThreads());
				System.out.println("Counter count: " + counter);

				// queue.put((Resultable) new
				// PoisonResult(poisonResult.getPoisonPlaintext(),
				// poisonResult.getPoisonKey(), poisonResult.getPoisonScore()));
				// queue.put(poisonResult.printPoisonResult());

				/*
				 * poisonResult.shutDown(); shutDown(); running = false;
				 */
			}
		}
	}

	// ---------------------------------------------will
	@Override
	public void endqueue() {
		running = false;
	}
}
