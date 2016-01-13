package ie.gmit.sw;

public interface Threadable {

	public abstract int calculateThreads();

	public abstract void eat() throws Exception;

	// ---------------------------------------------will
	public abstract void increment() throws InterruptedException;

	// ---------------------------------------------will
	public abstract void endqueue();

}