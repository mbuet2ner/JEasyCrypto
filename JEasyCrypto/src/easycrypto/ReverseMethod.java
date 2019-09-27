package easycrypto;

import easycrypto.EasyCryptoAPI.Result;
import easycrypto.EasyCryptoAPI.ResultCode;

class ReverseMethod implements CryptoMethod {

	@Override
	public Result encrypt(final String toEncrypt) {
		String toStoreTo = new StringBuilder(toEncrypt).reverse().toString();
		return new Result(ResultCode.ESuccess, toStoreTo);
	}

	@Override
	public Result decrypt(final String toDecrypt) {
		return encrypt(toDecrypt);
	}

	@Override
	public String method() {
		return "reverse";
	}

}
