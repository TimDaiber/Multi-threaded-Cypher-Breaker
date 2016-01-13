package ie.gmit.sw;

public class poisonTime {
	private long start;
	long now;
	long time;

	public void starttimer(){
		start = System.nanoTime();
			// long startTime = System.nanoTime();
	}
	public void nowtimer(){
		now = System.nanoTime();
			// long startTime = System.nanoTime();
	}
	public long checkelapsedTime(){
		time =  (long) ((now-start) / 1000000000.0) ;
		return time;
	}

	public long getStart() {
		return start;
	}

	public void setStart(long start) {
		this.start = start;
	}

	public long getNow() {
		return now;
	}

	public void setNow(long now) {
		this.now = now;
	}

	

}
