package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class QuadGramMap {
	// test hgjkh lets see
	public static final int GRAM_SIZE = 4;
	public static Map<String, Double> map = new ConcurrentHashMap<String, Double>();

	public Map<String, Double> readFromFile() throws IOException {

		String text = "4grams.txt";
		// Hello contains:
		// HELLO 12312
		// BYE 12213
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				new FileInputStream(text), Charset.forName("UTF-8")));

		while ((text = reader.readLine()) != null) {
			// Splits the read line in two where the space char is the
			// separating
			String[] stuff = text.split(" ");

			map.put(stuff[0], Double.parseDouble(stuff[1]));

			
		}
		return map;
	}

	
}
