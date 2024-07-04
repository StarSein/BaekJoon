import java.io.*;
import java.util.*;


public class Main {

    static class Data {
        int length;
        String content;

        Data(int length, String content) {
            this.length = length;
            this.content = content;
        }
    }
    static final int NUMERIC = 1;
    static final int ALPHA_NUMERIC = 2;
    static final int EIGHT_BIT_BYTE = 4;
    static final int KANJI = 8;
    static final int TERMINATION = 0;
    static int P;
    static char[] dataBlocks;
    static String alphaNumerics = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ $%*+-./:";

    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        P = Integer.parseInt(br.readLine());
        while (P-- > 0) {
            dataBlocks = br.readLine().toCharArray();
            Data data = decode(dataBlocks);
            sb.append(data.length + " " + data.content + "\n");
        }
        System.out.print(sb);
    }

    static Data decode(char[] dataBlocks) {
        // 길이가 4인 구간 38개로 끊어서 각각을 2진수로 변환한다
        char[] binArr = new char[8 * 19];
        for (int i = 0, s = 0; i < dataBlocks.length; i++, s += 4) {
            int decimal = Integer.parseInt(String.valueOf(dataBlocks[i]), 16);
            for (int j = 0; j < 4; j++) {
                if ((decimal & 1 << (3 - j)) == 0) {
                    binArr[s + j] = '0';
                } else {
                    binArr[s + j] = '1';
                }
            }
        }

        // 2진수 배열을 해독한다
        int dataLength = 0;
        StringBuilder content = new StringBuilder();
        int idx = 0;
        while (idx + 4 < binArr.length) {
            // Mode Bits 을 읽는다
            int modeDecimal = getDecimal(binArr, idx, idx + 4);
            idx += 4;
            // Mode Bits == "0000" 인 경우 반복을 종료한다
            if (modeDecimal == TERMINATION) {
                break;
            }


            // Count Bits 를 읽는다
            int countBitLength = 0;
            switch (modeDecimal) {
                case NUMERIC:
                    countBitLength = 10; break;
                case ALPHA_NUMERIC:
                    countBitLength = 9; break;
                case EIGHT_BIT_BYTE:
                case KANJI:
                    countBitLength = 8;
            }

            int countDecimal = getDecimal(binArr, idx, idx + countBitLength);
            idx += countBitLength;

            dataLength += countDecimal;

            int div, mod;
            switch (modeDecimal) {
                case NUMERIC:
                    // 문자 정보를 읽는다
                    div = countDecimal / 3;
                    mod = countDecimal % 3;
                    while (div-- > 0) {
                        int charDecimal = getDecimal(binArr, idx, idx + 10);
                        idx += 10;
                        content.append(String.format("%03d", charDecimal));
                    }
                    if (mod != 0) {
                        int bitLen = (mod == 2 ? 7 : 4);
                        int charDecimal = getDecimal(binArr, idx, idx + bitLen);
                        idx += bitLen;
                        content.append(String.format(mod == 2 ? "%02d" : "%01d", charDecimal));
                    }
                    break;
                case ALPHA_NUMERIC:
                    // 문자 정보를 읽는다
                    div = countDecimal / 2;
                    mod = countDecimal % 2;
                    while (div-- > 0) {
                        int charDecimal = getDecimal(binArr, idx, idx + 11);
                        idx += 11;
                        char firstChar = alphaNumerics.charAt(charDecimal / 45);
                        char secondChar = alphaNumerics.charAt(charDecimal % 45);
                        content.append(firstChar).append(secondChar);
                    }
                    if (mod != 0) {
                        int charDecimal = getDecimal(binArr, idx, idx + 6);
                        idx += 6;
                        char oneChar = alphaNumerics.charAt(charDecimal);
                        content.append(oneChar);
                    }
                    break;
                case EIGHT_BIT_BYTE:
                    // 문자 정보를 읽는다
                    while (countDecimal-- > 0) {
                        int charDecimal = getDecimal(binArr, idx, idx + 8);
                        idx += 8;
                        if (' ' <= charDecimal && charDecimal <= '~') {
                            content.append(convertValid((char) charDecimal));
                        } else {
                            String hexaStr = Integer.toHexString(charDecimal);
                            content.append("\\" + "0".repeat(2 - hexaStr.length()) + hexaStr.toUpperCase());
                        }
                    }
                    break;
                case KANJI:
                    // 문자 정보를 읽는다
                    while (countDecimal-- > 0) {
                        int charDecimal = getDecimal(binArr, idx, idx + 13);
                        if (' ' <= charDecimal && charDecimal <= '~') {
                            content.append(convertValid((char) charDecimal));
                        } else {
                            content.append("#" + binArr[idx]);
                            int dec = getDecimal(binArr, idx + 1, idx + 13);
                            String hexaStr = Integer.toHexString(dec);
                            content.append("0".repeat(3 - hexaStr.length()) + hexaStr.toUpperCase());
                        }
                        idx += 13;
                    }
            }
        }
        return new Data(dataLength, content.toString());
    }

    static int getDecimal(char[] binArr, int s, int e) {
        char[] charBits = Arrays.copyOfRange(binArr, s, e);
        return Integer.parseInt(String.valueOf(charBits), 2);
    }

    static String convertValid(char c) {
        if (c == '\\') {
            return "\\\\";
        }
        if (c == '#'){
            return "\\#";
        }
        return String.valueOf(c);
    }
}
