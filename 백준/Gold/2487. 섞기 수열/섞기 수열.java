import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    static int N;
    static int[] arr;
    static boolean[] visit, cntCheck;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        cntCheck = new boolean[N + 1];
        visit = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            int cur = i;
            int visitCnt = 0;
            while (!visit[cur]) {
                visit[cur] = true;
                visitCnt++;
                cur = arr[cur];
            }
            cntCheck[visitCnt] = true;
        }
        int answer = IntStream.rangeClosed(1, N)
                .filter(e -> cntCheck[e])
                .reduce(1, (a, b) -> lcm(a, b));
        System.out.println(answer);
    }

    static int gcd(int a, int b) {
        while (b > 0) {
            int temp = a;
            a = b;
            b = temp % b;
        }
        return a;
    }

    static int lcm(int a, int b) {
        return (int) ((long) a * b / gcd(a, b));
    }
}