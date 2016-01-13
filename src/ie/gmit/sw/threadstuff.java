package ie.gmit.sw;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class threadstuff {
	int key;
	double topScore;
	private int numberOfThreads;
	private String newPlainText;
	// private double score;
	textstuff txs = new textstuff();
	RailFence rf = new RailFence();
	TextScorer ts = new TextScorer(null);
	int capacity = 1000;
	BlockingQueue<Thread> queue = new ArrayBlockingQueue<Thread>(capacity);

	public void createThreads() throws InterruptedException {
		calculateThreads();
		for (int i = 0; i < numberOfThreads; i++) {

			
		
			
				Thread t =	new Thread(new Runnable() {

				int currkey = increaseKey();

				@Override
				public void run() {
					while(!queue.isEmpty()){
						
					try{
						 // Resultable r =queue.take();
					
					// TODO Auto-generated method stub
					String text = rf.decrypt(txs.getText(), currkey);
					// can I make this a private variable?
					double score = ts.getScore(text);
					calTopScore(score, text);
					}catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					}
				}
				//return topScore;
			});
				t.start();
				queue.put(t);
				queue.clear();
		}
		//poison();
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public double getTopScore() {
		return topScore;
	}

	public void setTopScore(double topScore) {
		this.topScore = topScore;
	}

	public int getNumberOfThreads() {
		return numberOfThreads;
	}

	public void setNumberOfThreads(int numberOfThreads) {
		this.numberOfThreads = numberOfThreads;
	}

	public Textable getTxs() {
		return txs;
	}

	public void setTxs(Textable txs) {
		this.txs = (textstuff) txs;
	}

	public RailFence getRf() {
		return rf;
	}

	public void setRf(RailFence rf) {
		this.rf = rf;
	}

	public TextScorer getTs() {
		return ts;
	}

	public void setTs(TextScorer ts) {
		this.ts = ts;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public BlockingQueue<Thread> getQueue() {
		return queue;
	}

	public void setQueue(BlockingQueue<Thread> queue) {
		this.queue = queue;
	}

	/*public void poison(){
		if(queue.){
			
		}
	}*/
	public void calculateThreads() {
		int textSize = txs.getText().length();
		if (textSize > 2) {
			numberOfThreads = textSize / 2;
		}
	}

	public synchronized int increaseKey() {
		key++;
		return key;
	}

	public synchronized void calTopScore(double score, String text) {

		if (score > topScore) {
			topScore = score;
			setNewPlainText(text);
		}

	}
	public void takeThread() throws InterruptedException{
		queue.take();
	}

	public String getNewPlainText() {
		return newPlainText;
	}

	public void setNewPlainText(String newPlainText) {
		this.newPlainText = newPlainText;
	}

}
