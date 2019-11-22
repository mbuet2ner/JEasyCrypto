package easycrypto;

import easycrypto.EasyCryptoAPI.Result;
import easycrypto.EasyCryptoAPI.ResultCode;

class ROT13Method implements CryptoMethod {

    @Override
    public Result encrypt(final String toEncrypt) {
        String toStoreTo = toRot13(toEncrypt);
        return new Result(ResultCode.ESuccess, toStoreTo);
    }

    @Override
    public Result decrypt(final String toDecrypt) {
        String toStoreTo = toRot13(toDecrypt);
        return new Result(ResultCode.ESuccess, toStoreTo);
    }

    @Override
    public String method() {
        return "rot13";
    }

    private String toRot13(final String input) {
        return input.codePoints().map(c -> {
                    if ((c >= 'a' && c <= 'm') || (c >= 'A' && c <= 'M')) {
                        return c + 13;
                    } else if ((c >= 'n' && c <= 'z') || (c >= 'N' && c <= 'Z')) {
                        return c - 13;
                    } else {
                        System.err.println("Warning: character " + (char) c +
                                           " not converted (not in basic Latin alphabet)");
                        return c;
                    }
                })
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

}
