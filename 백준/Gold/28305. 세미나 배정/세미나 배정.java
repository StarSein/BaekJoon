import java.io.*;
import java.util.*;

public class Main {

    static int N, T;
    static int[] a;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        a = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(a);

        int left = 1;
        int right = N;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (able(mid)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(left);
    }

    static boolean able(int limit) {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        for (int day : a) {
            while (!q.isEmpty() && q.peekFirst() <= day - T) {
                q.pollFirst();
            }
            int latest = 0;
            while (q.size() >= limit) {
                latest = q.pollFirst();
                if (latest >= day) {
                    return false;
                }
            }
            q.offerLast(latest == 0 && day >= T ? day : latest + T);
        }
        return true;
    }
}