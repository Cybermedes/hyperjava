package com.chucknorriscipher;

public class CipherDecryptor {

    protected static String decryptMessage(String encryptedText) {
        StringBuilder byteChar = new StringBuilder();
        StringBuilder decryptedText = new StringBuilder();
        String[] blocks = encryptedText.split(" ");

        for (int i = 1; i <= blocks.length; i += 2) {
            if (blocks[i - 1].equals("0")) {
                byteChar.append("1".repeat(blocks[i].length()));
            } else {
                byteChar.append("0".repeat(blocks[i].length()));
            }
        }

        int i = 6;
        while (i <= byteChar.length()) {
            char[] bits = new char[7];
            byteChar.getChars(i - 6, i + 1, bits, 0);
            decryptedText.append((char) getAsciiValue(bits));
            i += 7;
        }

        return decryptedText.toString();
    }

    private static int getAsciiValue(char[] bits) {
        int valueOfBaseTwo = 1;
        int asciiValue = 0;
        for (int i = bits.length - 1; i >= 0; i--) {
            asciiValue += Integer.parseInt(String.valueOf(bits[i])) * valueOfBaseTwo;
            valueOfBaseTwo *= 2;
        }
        return asciiValue;
    }
}
