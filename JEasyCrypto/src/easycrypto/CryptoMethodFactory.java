package easycrypto;

import java.util.Vector;

class CryptoMethodFactory {

	public static CryptoMethod createMethod(String method) {
		if (method.equalsIgnoreCase("reverse")) {
			return new ReverseMethod();
		} else if (method.equalsIgnoreCase("matrix")) {
			return new MatrixMethod();
		} else if (method.equalsIgnoreCase("cyr")) {
			return new CyrMethod();
		}
		return null;
	}
	
	public static Vector<String> getMethods() {
		Vector<String> methods = new Vector<String>();
		CryptoMethod method = null;
		method = new ReverseMethod();
		methods.add(method.method());
		method = new MatrixMethod();
		methods.add(method.method());
		method = new CyrMethod();
		methods.add(method.method());
		return methods;
	}
}
