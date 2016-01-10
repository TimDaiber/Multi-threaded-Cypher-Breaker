package ie.gmit.sw;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

public class DecryptionThreads implements Runnable {

	int key;
	double topScore;
	private int numberOfThreads;
	private String newPlainText;
	private String encryptedText;
	private Result result;
	// private double score;
	textstuff txs = new textstuff();
	RailFence rf = new RailFence();
	private ConcurrentHashMap<String, Double> quadGram;
	TextScorer ts = new TextScorer(quadGram);
	int capacity = 1000;
	BlockingQueue<Resultable> queue = new ArrayBlockingQueue<Resultable>(capacity);
	
	public DecryptionThreads(int key, double topScore, int numberOfThreads,
			String newPlainText, textstuff txs, RailFence rf, TextScorer ts,
			int capacity, BlockingQueue<Resultable> queue) {
		super();
		this.key = key;
		this.topScore = topScore;
		this.numberOfThreads = numberOfThreads;
		this.newPlainText = newPlainText;
		this.txs = txs;
		this.rf = rf;
		this.ts = ts;
		this.capacity = capacity;
		this.queue = queue;
	}

	public void run(){
		
		newPlainText = rf.decrypt(encryptedText, key);
		topScore = ts.getScore(newPlainText);
		result = new Result(newPlainText, this.key, topScore);
		
		try {
			queue.put(result);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
