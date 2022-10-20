import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int A = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        final int MAX_NUM = (int) 5e5;
        boolean[] visited = new boolean[MAX_NUM];
        Stack<Integer> D = new Stack<>();
        while (!visited[A]) {
            D.push(A);
            visited[A] = true;
            A = getNextA(A, P);
        }

        while (D.pop() != A) {}
        
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(D.size()));
        bw.newLine();
        bw.flush();
        return;
    }

    public static int getNextA(int A, int P) {
        int ret = 0;
        for (int pos = 0; pos < 6; pos++) {
            ret += Math.pow(A % 10, P);
            A /= 10;
        }
        return ret;
    }
}
