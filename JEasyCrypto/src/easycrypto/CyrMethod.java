package easycrypto;

import java.text.BreakIterator;
import java.util.Locale;

import easycrypto.EasyCryptoAPI.Result;
import easycrypto.EasyCryptoAPI.ResultCode;

class CyrMethod implements CryptoMethod {
	
	private static final int CLEAR_TEXT_UNICODE_START_VALUE = 0x0020; // Basic latin, Range: 0020— 007F
	private static final int CRYPTED_TEXT_UNICODE_START_VALUE = 0x0400; // Cyrillic, Range: 0400— 04FF
	
	@Override
	public Result encrypt(String toEncrypt) {
		String result = new String();

        BreakIterator breakIterator = BreakIterator.getCharacterInstance(Locale.ENGLISH);
        breakIterator.setText(toEncrypt);

        String tmp;
        int start = breakIterator.first();
        for (int end = breakIterator.next(); end != BreakIterator.DONE; start = end, end = breakIterator.next()) {
        		tmp = toEncrypt.substring(start, end);
        		int valueOfChar = tmp.codePointAt(0); 
        		int newValue = CRYPTED_TEXT_UNICODE_START_VALUE + (valueOfChar-CLEAR_TEXT_UNICODE_START_VALUE);
        		result += String.copyValueOf(Character.toChars(newValue));
        }
		return new Result(ResultCode.ESuccess, result);
	}

	@Override
	public Result decrypt(String toDecrypt) {
		String result = new String();

        BreakIterator breakIterator = BreakIterator.getCharacterInstance(Locale.ENGLISH);
        breakIterator.setText(toDecrypt);

        String tmp;
        int start = breakIterator.first();
        for (int end = breakIterator.next(); end != BreakIterator.DONE; start = end, end = breakIterator.next()) {
        		tmp = toDecrypt.substring(start, end);
        		int valueOfChar = tmp.codePointAt(0); 
        		int newValue = CLEAR_TEXT_UNICODE_START_VALUE + (valueOfChar-CRYPTED_TEXT_UNICODE_START_VALUE);
        		result += String.copyValueOf(Character.toChars(newValue));
        }
		return new Result(ResultCode.ESuccess, result);
	}

	@Override
	public String method() {
		return "cyr";
	}

}
