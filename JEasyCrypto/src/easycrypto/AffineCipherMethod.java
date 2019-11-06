package easycrypto;

import easycrypto.EasyCryptoAPI.Result;
import easycrypto.EasyCryptoAPI.ResultCode;
import java.lang.Math;

class AffineCipherMethod implements CryptoMethod {
	//attributes
	//Alphabet string for encryption
	private final static String alphabet = "abcdefghijklmnopqrstuvwxyz";
	
	private final static int m = alphabet.length();
	
	//Hard-coded parameters for encryption (https://en.wikipedia.org/wiki/Affine_cipher)
	private final static int a = 5;
	private final static int b = 8;
	
	//a^-1 (mod inverse) is calculated  based on a and length of alphabet vector (m)
	private final static int aInverted = 21;
	
	
	//Encryption method that takes the string to encrypt as the input and returns encrypted string
	@Override
	public Result encrypt(final String toEncrypt) {
		//Allocate new result string (empty string)
		String result = new String();
		
		//Do encryption for each character
		for(char c: toEncrypt.toCharArray()){
			//if the character is whitespace, insert whitespace for encryption (could be changed to some other char as well)
			if (c == ' ') {
				result += c;
				continue;
			}
			int x = alphabet.indexOf(c);
			result += alphabet.charAt((a*x + b) % m);
		}
		return new Result(ResultCode.ESuccess, result);
	}
	
	//Encryption method that takes the string to decrypt as the input and returns decrypted string
	@Override
	public Result decrypt(final String toDecrypt) {
		
		String result = new String();
		
		//Do decryption for each character
		for(char c: toDecrypt.toCharArray()){
			
			//if the character is whitespace, insert whitespace for decryption (could be changed to some other char as well)
			if (c == ' ') {
				result += c;
				continue;
			}
			
			int x = alphabet.indexOf(c);
			int extract = aInverted*(x - b);
			int moduloExtract;
			
			if (extract < 0){
				moduloExtract = m + (extract % m);
			} else {
				moduloExtract = extract % m;
			}
			
			result += alphabet.charAt(moduloExtract);
		}
		return new Result(ResultCode.ESuccess, result);
	}
	
	//Returns the name of the method as a string 
	@Override
	public String method() {
		return "affine";
	}

}
