
import java.util.Scanner;

public class Encrypt_Decrypt {

	public static void main(String[] args) {

// Intializing scanner "sc" 

		Scanner sc = new Scanner(System.in);
		int mode = 0;

// Select operating mode 

		System.out.println("Select mode:");
		System.out.println("1 - Encrypt");
		System.out.println("2 - Decrypt");
		System.out.println("");

		while (mode < 1 || mode > 2 || (mode > 1 && mode < 2)) {
			if (sc.hasNextInt()) {
				mode = sc.nextInt();
			} else {
				sc.nextLine();
			}
		}

// Apparently nextInt features an issue where the next nextLine 
// automatically grabs the \n character if sc.nextLine(); is not 
// used to flush the input back to a null state 

		sc.nextLine();
		System.out.println("");

// Initializing variables for encryption input 

		String message = null;
		String password = null;

// Initializing variables for decryption input 

		String encrypted = null;
		String key = null;

		switch (mode) {

// -Mode 1/Encrypt- 

		case 1:

// Ask user for input 

			System.out.println("Enter your message: ");
			message = sc.nextLine();

			System.out.println("");
			System.out.println("(Longer password provides better obfuscation)");
			System.out.println("Enter your password (case sensitive): ");
			password = sc.nextLine();

			System.out.println("");
			System.out.println("Encrypting...");
			System.out.println("");

// Initializing array variables for input converted into arrays of characters 

			int[] messageconvert = new int[message.length()];
			int[] passwordconvert;

			if (message.length() > password.length()) {
				passwordconvert = new int[message.length()];
			} else {
				passwordconvert = new int[password.length()];
			}

// Intializing array variable for final encrypted message 

			int[] finalmessage = new int[message.length()];

// For the length of input, add the Code Point Value of each character to the 
// corresponding array where Nth letter = Nth position on the array 
// Message to array 

			for (int m = 0; m < message.length(); m++) {
				int currentcharacter = message.codePointAt(m);
				messageconvert[m] = currentcharacter;
			}

// Password to array 

			for (int p = 0; p < password.length(); p++) {
				int currentcharacter = password.codePointAt(p);
				passwordconvert[p] = currentcharacter;
			}

// Repeat contents of the password array until it is filled if password 

// is shorter than the message (highly likely) 

			if (password.length() < message.length()) {
				for (int i = password.length(); i < message.length(); i++) {
					passwordconvert[i] = passwordconvert[i % password.length()];
				}
			}

// Encrypting message to array 

			for (int f = 0; f < message.length(); f++) {
				finalmessage[f] = messageconvert[f] * passwordconvert[f];
			}

			System.out.println("Done");
			System.out.println("");

// Printing out final encrypted message 

			for (int x = 0; x < finalmessage.length; x++) {
				System.out.print(finalmessage[x]);
				if (x != finalmessage.length - 1) {
					System.out.print("-");
				}
			}

			break;

// -Mode 2/Decrypt- 

		case 2:

// Asking for user input 

			System.out.println("Enter your encrypted message: ");
			encrypted = sc.nextLine();

			System.out.println("");
			System.out.println("Enter your password: ");
			key = sc.nextLine();

			System.out.println("");
			System.out.println("Decrypting...");
			System.out.println("");

// Initializing array variables 
// checking length of original message before encrypting 
// so we can make reasonable-sized arrays 

			int messagelength = 1;
			for (int a = 0; a < encrypted.length(); a++) {
				if (encrypted.charAt(a) == '-') {
					messagelength++;
				}
			}

			int[] encryptedarray = new int[messagelength];
			int[] keyarray;

			if (encryptedarray.length > key.length()) {
				keyarray = new int[encryptedarray.length];
			} else {
				keyarray = new int[key.length()];
			}

// Intializing array variable for final decrypted message 

			int[] decrypted = new int[encryptedarray.length];
			String decryptedstring = "";

// Password to array 

			for (int k = 0; k < key.length(); k++) {
				int currentcharacter = key.codePointAt(k);
				keyarray[k] = currentcharacter;
			}

// Repeat contents of the password array until it is filled if password 
// is shorter than the message (highly likely) 

			if (key.length() < messagelength) {
				for (int i = key.length(); i < messagelength; i++) {
					keyarray[i] = keyarray[i % key.length()];
				}
			}

// Encrypted message to array 
// for concat purposes the number is first a string, which is then converted to 
// an integer 

			int currentlyfilling = 0;
			String currentstring = "";
			int currentstringint;

			for (int e = 0; e < encrypted.length(); e++) {
				if (encrypted.charAt(e) != '-') {
					char currentcharacter = encrypted.charAt(e);
					currentstring += currentcharacter;

				} else {
					currentstringint = Integer.parseInt(currentstring);
					encryptedarray[currentlyfilling] = currentstringint;
					currentlyfilling = currentlyfilling + 1;
					currentstring = "";
				}
			}

			currentstringint = Integer.parseInt(currentstring);

			encryptedarray[currentlyfilling] = currentstringint;

// Dividing encrypted message with the password key 

			for (int d = 0; d < decrypted.length; d++) {
				decrypted[d] = encryptedarray[d] / keyarray[d];
			}

			System.out.println("Done");
			System.out.println("");

// Printing out final encrypted message 

			for (int x = 0; x < decrypted.length; x++) {

// cast decimal code points to character and build final decrypted message 

				char c = (char) decrypted[x];
				decryptedstring += c;
			}

			System.out.println(decryptedstring);

			break;
		}
	}
}
