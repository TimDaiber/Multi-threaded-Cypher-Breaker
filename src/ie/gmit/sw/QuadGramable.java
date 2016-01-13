package ie.gmit.sw;

import java.io.IOException;
import java.util.Map;

public interface QuadGramable {

	public static final int GRAM_SIZE = 4;

	public abstract Map<String, Double> readFromFile() throws IOException;

}