import java.io.*;
import java.util.*;


public class Main {

    static class Trie {
        Trie[] childs;

        Trie() {
            this.childs = new Trie[2];
        }
        void add(int offset, char[] str) {
            if (offset >= str.length) {
                return;
            }
            char cur = str[offset];
            int idx = cur - '0';
            if (childs[idx] == null) {
                childs[idx] = new Trie();
            }
            childs[idx].add(offset + 1, str);
        }

        int getMaxXorSum(int offset, char[] str) {
            if (offset >= str.length) {
                return 0;
            }
            char cur = str[offset];
            int idx = cur - '0';
            if (childs[1 - idx] == null) {
                // 현재 bit를 1로 만들 수 있는 xor 상대가 없는 경우
                return childs[idx].getMaxXorSum(offset + 1, str);
            } else {
                // 현재 bit를 1로 만들 수 있는 xor 상대가 있는 경우
                return childs[1 - idx].getMaxXorSum(offset + 1, str) | (1 << (BIT_SZ - 1 - offset));
            }
        }
    }

    static final int BIT_SZ = 31;
    static int T;
    static int N;
    static int[] A;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            N = Integer.parseInt(br.readLine());
            A = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            sb.append(solve()).append('\n');
        }
        System.out.println(sb);
    }

    static int solve() {
        // 누적 xor 배열 만들기
        int[] prefXors = new int[A.length + 1];
        for (int i = 0; i < A.length; i++) {
            prefXors[i + 1] = prefXors[i] ^ A[i];
        }

        Trie root = new Trie();
        root.add(0, intToBinStr(prefXors[0]));
        // 누적 xor 배열을 순회하면서
        int answer = 0;
        for (int i = 1; i < prefXors.length; i++) {
            // 순회중인 원소를 32비트 이진 문자열로 변환하기
            char[] binStr = intToBinStr(prefXors[i]);
            // 트라이에 저장된 문자열 중 현재 순회중인 원소와 xor 시 결과값이 가장 큰 것을 찾기
            int maxXorSum = root.getMaxXorSum(0, binStr);
            // 가장 큰 xor 합 갱신
            answer = Math.max(answer, maxXorSum);
            // 문자열을 트라이에 저장
            root.add(0, binStr);
        }
        return answer;
    }

    static char[] intToBinStr(int xorValue) {
        char[] binStr = new char[BIT_SZ];
        for (int i = 0; i < BIT_SZ; i++) {
            binStr[BIT_SZ - 1 - i] = ((xorValue & (1 << i)) != 0) ? '1' : '0';
        }
        return binStr;
    }
}
