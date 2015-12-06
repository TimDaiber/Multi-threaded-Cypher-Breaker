package ie.gmit.sw;

import java.io.IOException;

public class Runner {

	public static void main(String[] args) throws IOException {
		

		Test t = new Test();
		t.readFromFile();
		System.out.println(t.map);
		
		
	}

}
