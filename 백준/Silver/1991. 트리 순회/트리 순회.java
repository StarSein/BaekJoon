import java.io.*;
import java.util.*;


public class Main {

    static final int EMPTY = '.' - 'A';
    static int N;
    static int[] left, right;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        left = new int[N];
        right = new int[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int node = st.nextToken().charAt(0) - 'A';
            left[node] = st.nextToken().charAt(0) - 'A';
            right[node] = st.nextToken().charAt(0) - 'A';
        }

        preOrder(0);
        sb.append('\n');
        inOrder(0);
        sb.append('\n');
        postOrder(0);
        System.out.print(sb);
    }

    static void preOrder(int cur) {
        if (cur == EMPTY) {
            return;
        }
        sb.append((char) (cur + 'A'));
        preOrder(left[cur]);
        preOrder(right[cur]);
    }

    static void inOrder(int cur) {
        if (cur == EMPTY) {
            return;
        }
        inOrder(left[cur]);
        sb.append((char) (cur + 'A'));
        inOrder(right[cur]);
    }

    static void postOrder(int cur) {
        if (cur == EMPTY) {
            return;
        }
        postOrder(left[cur]);
        postOrder(right[cur]);
        sb.append((char) (cur + 'A'));
    }
}
