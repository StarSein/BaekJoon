import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int probType;
    static long k;
    static int[] seq;
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        probType = Integer.parseInt(st.nextToken());

        if (probType == 1) {
            k = Long.parseLong(st.nextToken());
            solution1(k);
        } else {
            seq = new int[st.countTokens()];
            for (int i = 0; i < seq.length; i++) {
                seq[i] = Integer.parseInt(st.nextToken());
            }
            solution2(seq);
        }
    }

    static long factorial(int n) {
        long ret = 1;
        for (int i = 1; i <= n; i++) {
            ret *= i;
        }
        return ret;
    }
    static void solution1(long k) {
        int[] answer = new int[N];
        boolean[] used = new boolean[N + 1];
        for (int offset = N; offset > 0; offset--) {
            int pos = 0;
            for (int num = offset - 1; num > 0; num--) {
                long val = num * factorial(offset - 1);
                if (k > val) {
                    pos = num;
                    k -= val;
                    break;
                }
            }
            int cnt = 0;
            for (int i = 1; i <= N; i++) {
                if (used[i]) {
                    continue;
                }
                if (cnt == pos) {
                    answer[N - offset] = i;
                    used[i] = true;
                    break;
                }
                cnt++;
            }
        }

        for (int ans : answer) {
            System.out.printf("%d ", ans);
        }
    }

    static void solution2(int[] seq) {
        long ans = 1;
        boolean[] used = new boolean[N + 1];
        for (int i = 0; i < seq.length; i++) {
            int cnt = 0;
            for (int j = 1; j <= N; j++) {
                if (j == seq[i]) {
                    break;
                }
                if (!used[j]) cnt++;
            }
            ans += cnt * factorial(N - 1 - i);
            used[seq[i]] = true;
        }
        System.out.println(ans);
    }
}