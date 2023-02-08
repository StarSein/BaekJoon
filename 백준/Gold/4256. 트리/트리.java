import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n, cnt;
    static int[] preOrder = new int[1000];
    static int[] inOrder = new int[1000];
    static int[] preCheck = new int[1001];
    static int[] left;
    static int[] right;
    static StringBuilder answer;

    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            readTestCase();
            init();
            solve();
        }
    }

    static void readTestCase() throws Exception {
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            preOrder[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            inOrder[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void init() {
        cnt = 1;
        left = new int[n + 1];
        right = new int[n + 1];
        answer = new StringBuilder();
    }
    static void solve() {
        for (int i = 0; i < n; i++) {
            preCheck[preOrder[i]] = i;
        }
        int root = preOrder[0];
        int rootIdx = -1;
        for (int i = 0; i < n; i++) {
            if (inOrder[i] == root) {
                rootIdx = i;
                break;
            }
        }
        left[root] = findChild(0, rootIdx - 1);
        right[root] = findChild(rootIdx + 1, n - 1);

        postOrder(root);
        System.out.println(answer);
    }
    static int findChild(int l, int r) {
        int child = 0, childIdx = -1;
        for (int i = l; i <= r; i++) {
            if (preCheck[inOrder[i]] == cnt) {
                cnt++;
                child = inOrder[i];
                childIdx = i;
                break;
            }
        }
        if (childIdx == -1) return 0;
        left[child] = findChild(l, childIdx - 1);
        right[child] = findChild(childIdx + 1, r);
        return child;
    }

    static void postOrder(int node) {
        if (left[node] != 0) postOrder(left[node]);
        if (right[node] != 0) postOrder(right[node]);
        answer.append(node).append(' ');
    }
}
