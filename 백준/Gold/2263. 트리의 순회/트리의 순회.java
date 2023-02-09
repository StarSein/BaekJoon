import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
    static int n, cnt = 1, idx = 0;
    static int[] inOrder, postOrder, revInOrder, revPreOrder, revPostOrder;
    static int[] inCheck, left, right;
    public static void main(String[] args) throws Exception {
        readInput();
        solve();
    }
    static void readInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        inOrder = new int[n];
        postOrder = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            inOrder[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            postOrder[i] = Integer.parseInt(st.nextToken());
        }
    }
    static void solve() {
        revInOrder = new int[n];
        revPreOrder = new int[2 * n];
        for (int i = 0; i < n; i++) {
            revInOrder[i] = inOrder[n - 1 - i];
            revPreOrder[i] = postOrder[n - 1 - i];
        }

        inCheck = new int[n + 1];
        for (int i = 0; i < n; i++) {
            inCheck[revInOrder[i]] = i;
        }

        left = new int[n + 1];
        right = new int[n + 1];
        int revRoot = revPreOrder[0];
        int rootIdx = inCheck[revRoot];
        left[revRoot] = findChild(0, rootIdx - 1);
        right[revRoot] = findChild(rootIdx + 1, n - 1);

        revPostOrder = new int[n];
        postOrderTraversal(revRoot);

        StringBuilder sb = new StringBuilder();
        for (int i = n - 1; i >= 0; i--) {
            sb.append(revPostOrder[i]).append(' ');
        }
        System.out.println(sb);
    }
    static int findChild(int l, int r) {
        int child = revPreOrder[cnt];
        int childIdx = inCheck[child];
        if (l <= childIdx && childIdx <= r) {
            cnt++;
            left[child] = findChild(l, childIdx - 1);
            right[child] = findChild(childIdx + 1, r);
            return child;
        }
        return 0;
    }
    static void postOrderTraversal(int node) {
        if (left[node] != 0) postOrderTraversal(left[node]);
        if (right[node] != 0) postOrderTraversal(right[node]);
        revPostOrder[idx++] = node;
    }
}
