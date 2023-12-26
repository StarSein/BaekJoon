// 모든 테스트케이스에는 유일한 해답이 존재하므로 아래와 같은 접근이 유효하다
// 영어 단어 - 고유한 문자 (해당 문자가 고유해지는 시점)
// zero - z (1)
// one - o (5)
// two - w (1)
// three - h (2)
// four - f (4)
// five - v (3)
// six - x (1)
// seven - s (2)
// eight - g (1)
// nine - n (3) - n이 2번 등장하므로 부적절하다 -> i (4) 로 수정

import java.io.*;


public class Main {

    static class Tuple {
        int idx;
        int num;
        char[] word;

        Tuple(char c, int num, String word) {
            this.idx = c - 'A';
            this.num = num;
            this.word = word.toCharArray();
        }
    }

    static Tuple[] searchOrder = new Tuple[] {
            new Tuple('Z', 0, "ZERO"),
            new Tuple('W', 2, "TWO"),
            new Tuple('X', 6, "SIX"),
            new Tuple('G', 8, "EIGHT"),
            new Tuple('H', 3, "THREE"),
            new Tuple('S', 7, "SEVEN"),
            new Tuple('V', 5, "FIVE"),
            new Tuple('F', 4, "FOUR"),
            new Tuple('I', 9, "NINE"),
            new Tuple('O', 1, "ONE")
    };
    static int T;
    static char[] S;
    static int[] charCounts;
    static int[] wordCounts;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());
        for (int i = 1; i <= T; i++) {
            S = br.readLine().toCharArray();

            charCounts = new int[26];
            for (char c : S) {
                charCounts[c - 'A']++;
            }

            wordCounts = new int[10];
            for (Tuple p : searchOrder) {
                int count = charCounts[p.idx];
                if (count != 0) {
                    boolean isContained = true;
                    for (char c : p.word) {
                        charCounts[c - 'A'] -= count;
                    }
                    for (char c : p.word) {
                        if (charCounts[c - 'A'] < 0) {
                            isContained = false;
                        }
                    }
                    if (isContained) {
                        wordCounts[p.num] = count;
                        continue;
                    }
                    for (char c : p.word) {
                        charCounts[c - 'A'] += count;
                    }
                }
            }

            sb.append("Case #").append(i).append(": ");
            for (int num = 0; num < 10; num++) {
                int wordCount = wordCounts[num];
                for (int wc = 0; wc < wordCount; wc++) {
                    sb.append(num);
                }
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }
}

