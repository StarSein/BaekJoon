import java.io.*;


public class Main {

    static char[] inp;
    static int N, Q, answer;
    static int[] arr, sumSeg, negSeg;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        inp = br.readLine().toCharArray();
        N = inp.length;
        arr = new int[N + 1];
        sumSeg = new int[4 * N + 1];
        negSeg = new int[4 * N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = (inp[i - 1] == '(' ? 1 : - 1);
            update(1, 1, N, i, arr[i]);
        }

        Q = Integer.parseInt(br.readLine());

        for (int i = 0; i < Q; i++) {
            int idx = Integer.parseInt(br.readLine());
            arr[idx] *= -1;
            update(1, 1, N, idx, arr[idx]);
            if (sumSeg[1] == 0 && negSeg[1] >= 0) answer++;
        }

        System.out.println(answer);
    }

    static void update(int node, int start, int end, int target, int val) {
        if (start > target || end < target) {
            return;
        }
        if (start == target && end == target) {
            sumSeg[node] = negSeg[node] = val;
            return;
        }
        int mid = (start + end) >> 1;
        update(node << 1, start, mid, target, val);
        update(node << 1 | 1, mid + 1, end, target, val);
        sumSeg[node] = sumSeg[node << 1] + sumSeg[node << 1 | 1];
        negSeg[node] = Math.min(negSeg[node << 1], sumSeg[node << 1] + negSeg[node << 1 | 1]);
    }
}
