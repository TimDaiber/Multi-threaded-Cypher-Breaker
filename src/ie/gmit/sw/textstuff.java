package ie.gmit.sw;

import java.util.Scanner;

public class textstuff implements Textable {

	private static String passphrase;

	
	@Override
	public void setText() {

		System.out.println("Pleaseinput your to be decrypted message: ");
		// Gets user Input
		Scanner in = new Scanner(System.in);
		passphrase = in.nextLine().toUpperCase();
	}

	@Override
	public String getText() {
		// System.out.println(passphrase);
		return passphrase;
	}

}
