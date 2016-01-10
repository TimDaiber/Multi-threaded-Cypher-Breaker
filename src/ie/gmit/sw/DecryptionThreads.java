package ie.gmit.sw;

//import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

public class DecryptionThreads implements Runnable {

	//double topScore;
	//private int numberOfThreads;
	//private String newPlainText;
	// private double score;
	//textstuff txs = new textstuff();
	private int key;
	private String encryptedText;
	private Result result;	
	private ConcurrentHashMap<String, Double> quadGram;	
	private BlockingQueue<Resultable> queue;

	public DecryptionThreads(BlockingQueue<Resultable> queue2,
			String encryptedText2, int i,
			ConcurrentHashMap<String, Double> loadedmap) {
		// TODO Auto-generated constructor stub
		super();
		this.queue = queue2;
		this.encryptedText = encryptedText2;
		this.key= i;
		this.quadGram = loadedmap;
	}
	
	

	public void run(){
		RailFence rf = new RailFence();
		String plainText = rf.decrypt(encryptedText, key);
		TextScorer ts = new TextScorer(quadGram);
		
		double topScore = ts.getScore(plainText);
		
		result = new Result(plainText, this.key, topScore);
		
		try {
			queue.put(result);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
