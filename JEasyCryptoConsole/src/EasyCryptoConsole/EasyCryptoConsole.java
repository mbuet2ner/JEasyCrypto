package EasyCryptoConsole;

import java.io.Console;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import easycrypto.EasyCryptoAPI;



public class EasyCryptoConsole {

	public static void main(String[] args) {

		try {
			System.setOut(new PrintStream(System.out, true, "UTF-8"));
			System.setProperty("file.encoding", "UTF-8");
			Console console = System.console();

			console.printf("Welcome to CryptoClient!\n");
			console.printf("Supported methods are: %s\n", EasyCryptoAPI.methods());

			while (true) {
				String d = console.readLine("Do you wish to encrypt or decrypt (e or d)? > ");
				String e = console.readLine("Please enter text to be encrypted > ");
				String m = console.readLine("Please enter encryption method > ");

				EasyCryptoAPI.Result result;

				if (d.equalsIgnoreCase("e")) {
					result = EasyCryptoAPI.encrypt(e, m);
				} else if (d.equalsIgnoreCase("d")) {
					result = EasyCryptoAPI.decrypt(e, m);
				} else {
					return;
				}
		        
				console.printf("\nResult is: %d - %s\n", result.resultCode().ordinal(), result.resultCode().toString());
				switch (result.resultCode()) {
				case ESuccess: {
					console.printf("Encrypted text is: %s \n", result.result());
					System.out.println(result.result());
					break;
				}
				case EError:
				case ENotSupported: {
					console.printf("Encrypted text is: %s \n", result.result());
					break;
				}
				}
			}

		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
