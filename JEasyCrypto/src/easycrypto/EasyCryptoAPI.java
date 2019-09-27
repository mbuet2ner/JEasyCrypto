package easycrypto;

import java.util.Vector;

/**

@brief This class is the public interface to the crypting library.

The library supports multiple encrypting methods and also decrypts the encrypted text back
to plain text.<br />
All the methods in this class are static, so there is no need to instantiate the class.
Implementation creates necessary internal objects which do the actual encrypting
and decrypting.<br />

Usage:
-# Query encryption methods you can use by calling methods(). Resulting string lists the methods separated by comma.</li>
-# Encrypt by calling encrypt() using one of the methods returned by methods()</li>
-# Decrypt by calling decrypt() using the same method which was used in encrypt()</li>

@author Antti Juustila
@version 1.0.0
 */

public class EasyCryptoAPI {

	private static final String currentVersion = "1.0.0";

	public enum ResultCode {
		ESuccess,      /*!< Text was successfully en/decrypted. */
		EError,        /*!< There was an error with en/decryption. */
		ENotSupported  /*!< The method of en/decription is not supported by the library */
	}

	public static class Result {
		private ResultCode code = ResultCode.ENotSupported;
		private String result = null;

		public Result(ResultCode c, String r) {
			code = c;
			result = r;
		}

		public ResultCode resultCode() {
			return code;
		}

		public String result() {
			return result;
		}
	}



	/**
    Encrypts the given plain text and stores it to the parameter to be used by the caller.
    @param toEncrypt Text to encrypt.
    @param toStoreTo A string to store the encrypted text to.
    @param m The method of encryption.
    @returns Returns success code of the encryption. See Result enum for details.
	 */
	public static Result encrypt(final String toEncrypt, final String method) {
		try {
			CryptoMethod theMethod = CryptoMethodFactory.createMethod(method);
			if (null != theMethod) {
				return theMethod.encrypt(toEncrypt);
			} else {
				return new Result(ResultCode.ENotSupported, "Error: Method not supported!");
			}
		} catch (Exception e) {
			return new Result(ResultCode.EError, e.getMessage());
		}
	}

	/**
    Decrypts the given encrypted text and stores it to the parameter to be used by the caller.
    @param toDecrypt Text to decrypt.
    @param toStoreTo A string to store the derypted plain text to.
    @param m The method of decryption.
    @returns Returns success code of the decryption. See Result enum for details.
	 */
	public static Result decrypt(final String toDecrypt, final String method) {
		try {
			CryptoMethod theMethod = CryptoMethodFactory.createMethod(method);
			if (null != theMethod) {
				return theMethod.decrypt(toDecrypt);
			} else {
				return new Result(ResultCode.ENotSupported, "Error: Method not supported!");
			}
		} catch (Exception e) {
			return new Result(ResultCode.EError, e.getMessage());
		}
	}

	/** To query the version of the library.
    @returns The library version number in the form "1.0.0".
	 */
	public static String version() {
		return currentVersion;
	}

	/** To query the supported en/decryption methods of the library. Method returns the supported method names separated by comma.
    @returns The supported methods in the form "matrix,reverse".
	 */
	public static String methods() {
		String methods = "";
		Vector<String> m = CryptoMethodFactory.getMethods();
		int count = m.size();
		int counter = count;
		for (String s : m) {
			methods += s;
			counter--;
			if (counter >= 1) {
				methods += ",";
			}
		}
		return methods;
	}


}
