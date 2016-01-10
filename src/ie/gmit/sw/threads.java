package ie.gmit.sw;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

public class threads {

	QuadGramMap qgm = new QuadGramMap();
	textstuff txs = new textstuff();
	int capacity;
	private String encryptedText;
	Map<String, Double> loadedmap = new ConcurrentHashMap<String, Double>();
	private Result result;
	private int numberOfThreads;
	private volatile boolean running = true;
	BlockingQueue<Resultable> queue = new ArrayBlockingQueue<Resultable>(capacity);

	
	public void calculateThreads() {
		int textSize = txs.getText().length();
		if (textSize > 2) {
			capacity = numberOfThreads = textSize / 2;
		}
	}
	
	public void eat() throws Exception{
		loadedmap = qgm.readFromFile();
		for (int i = 0; i < numberOfThreads; i++) {
			new Thread(new DecryptionThreads(queue, encryptedText, i, (ConcurrentHashMap<String, Double>) loadedmap)).start();
		}
	}
	
	public void endqueue(){
		running = false;
	}
}
