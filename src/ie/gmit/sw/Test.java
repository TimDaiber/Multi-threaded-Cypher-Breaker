package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;



public class Test {
	private Map<String, Double> map = new ConcurrentHashMap<String, Double>();
	
	/*public void parse() throws IOException {

		try (BufferedReader br = new BufferedReader(new FileReader("file.txt"))) {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			String everything = sb.toString();
		}
	}*/

	public  void readFromFile() throws IOException {

		String text = "Hello.txt";
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				new FileInputStream(text), Charset.forName("UTF-8")));
		
		while((text = reader.readLine()) != null){
			// Add each line to the parse
			String [] stuff = text.split(" ");
			map.put(stuff[0], Double.parseDouble(stuff[1]) );
			System.out.println(map);
		}

	}
	public static void main(String[] args) throws IOException {
	Test t = new Test();
		t.readFromFile();
		
		
		
	}
}
