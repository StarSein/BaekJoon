import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    static int K;
    static int[] arr;
    static ArrayList<Integer>[] answer;
    public static void main(String[] args) {
        readInput();
        solve();
        writeOutput();
    }
    static void readInput() {
        Scanner sc = new Scanner(System.in);
        K = sc.nextInt();
        arr = new int[1 << K];
        sc.nextLine();
        StringTokenizer st = new StringTokenizer(sc.nextLine());
        for (int i = 0; i < ((1 << K) - 1); i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }
    static void solve() {
        answer = new ArrayList[K];
        for (int i = 0; i < K; i++) {
            answer[i] = new ArrayList<>();
        }
        int halfLen = 1 << (K - 1);
        int rootIdx = halfLen - 1;
        inOrder(rootIdx, 0, halfLen >> 1);
    }
    static void inOrder(int nodeIdx, int depth, int halfLen) {
        if (depth == K) return;
        inOrder(nodeIdx - halfLen, depth + 1, halfLen >> 1);
        answer[depth].add(arr[nodeIdx]);
        inOrder(nodeIdx + halfLen, depth + 1, halfLen >> 1);
    }
    static void writeOutput() {
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < K; i++) {
            answer[i].forEach((e) -> ans.append(e).append(' '));
            ans.append('\n');
        }
        System.out.print(ans);
    }
}
