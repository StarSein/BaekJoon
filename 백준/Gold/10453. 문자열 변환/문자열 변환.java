import java.io.*;
import java.util.*;

public class Main {

    static int T;
    static char[] A, B;
    static List<Integer> bInA, bInB;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            A = st.nextToken().toCharArray();
            B = st.nextToken().toCharArray();
            final int HALF = A.length / 2;
            bInA = new ArrayList<>(HALF);
            bInB = new ArrayList<>(HALF);
            for (int j = 0; j < A.length; j++) {
                if (A[j] == 'b') {
                    bInA.add(j);
                }
                if (B[j] == 'b') {
                    bInB.add(j);
                }
            }
            int answer = 0;
            for (int j = 0; j < HALF; j++) {
                answer += Math.abs(bInA.get(j) - bInB.get(j));
            }
            sb.append(answer).append('\n');
        }
        System.out.println(sb);
    }
}
