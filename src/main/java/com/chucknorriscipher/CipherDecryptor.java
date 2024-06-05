package com.chucknorriscipher;

public class CipherDecryptor {

    protected static String decryptMessage(String encryptedText) {

        // If the encrypted contains any char besides zeros or spaces
        for (char c : encryptedText.toCharArray()) {
            if (!(c == '0' || c == ' ')) {
                throw new IllegalArgumentException();
            }
        }

        StringBuilder byteChar = new StringBuilder();
        StringBuilder decryptedText = new StringBuilder();
        String[] blocks = encryptedText.split(" ");

        // If the number of blocks is odd
        if (blocks.length % 2 != 0) {
            throw new IllegalArgumentException();
        }

        for (int i = 1; i <= blocks.length; i += 2) {
            if (blocks[i - 1].equals("0")) {
                byteChar.append("1".repeat(blocks[i].length()));
            } else if (blocks[i - 1].equals("00")) {
                byteChar.append("0".repeat(blocks[i].length()));
            } else {
                throw new IllegalArgumentException();
            }
        }

        int i = 6;
        while (i <= byteChar.length()) {
            char[] bits = new char[7];
            byteChar.getChars(i - 6, i + 1, bits, 0);
            int asciiValue = getAsciiValue(bits);

            // All printable ascii characters go from 32 to 126 (except DEL=127)
            if (asciiValue < 32 || asciiValue > 126) {
                throw new IllegalArgumentException();
            }
            decryptedText.append((char) asciiValue);
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
