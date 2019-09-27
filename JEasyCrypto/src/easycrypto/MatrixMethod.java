package easycrypto;

import easycrypto.EasyCryptoAPI.Result;
import easycrypto.EasyCryptoAPI.ResultCode;

class MatrixMethod implements CryptoMethod {

	@Override
	public Result encrypt(final String toEncrypt) {
		String toStoreTo = new String();
		int matrixWidth = (int) Math.floor(Math.sqrt(toEncrypt.length()));

		String toRotate;
		String tmp = "";
		int extraCount = (int) Math.abs(Math.pow(matrixWidth, 2) - toEncrypt.length());
		if (extraCount > 0) {
			tmp = toEncrypt.substring(0,extraCount);
			tmp = new StringBuilder(tmp).reverse().toString();
			toRotate = toEncrypt.substring(extraCount);
		} else {
			toRotate = toEncrypt;
		}
		for (int outer = 0; outer < matrixWidth; outer++) {
			toStoreTo += toRotate.substring(outer,outer+1);
			for (int inner = outer+matrixWidth; inner < toRotate.length(); inner += matrixWidth) {
				toStoreTo += toRotate.substring(inner,inner+1);
			}
		}
		if (tmp.length() > 0) {
			toStoreTo += tmp;
		}
		return new Result(ResultCode.ESuccess, toStoreTo);
	}

	@Override
	public Result decrypt(final String toDecrypt) {
		String toStoreTo = new String();
		int matrixWidth = (int)Math.floor(Math.sqrt(toDecrypt.length()));

		String toRotate;
		String tmp = "";
		int extraCount = (int)Math.abs(Math.pow(matrixWidth, 2) - toDecrypt.length());
		if (extraCount > 0) {
			tmp = toDecrypt.substring(toDecrypt.length()-extraCount,(toDecrypt.length()-extraCount)+extraCount);
			tmp = new StringBuilder(tmp).reverse().toString();
			toRotate = toDecrypt.substring(0, toDecrypt.length()-extraCount);
		} else {
			toRotate = toDecrypt;
		}
		for (int outer = 0; outer < matrixWidth; outer++) {
			toStoreTo += toRotate.substring(outer,outer+1);
			for (int inner = outer+matrixWidth; inner < toRotate.length(); inner += matrixWidth) {
				toStoreTo += toRotate.substring(inner,inner+1);
			}
		}
		if (tmp.length() > 0) {
			toStoreTo = tmp + toStoreTo;
		}
		return new Result(ResultCode.ESuccess, toStoreTo);
	}

	@Override
	public String method() {
		return "matrix";
	}

}
