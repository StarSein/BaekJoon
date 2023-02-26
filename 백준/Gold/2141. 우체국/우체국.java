import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {

    static int N;
    static Village[] villages;

    public static void main(String[] args) throws Exception {
        readInput();
        solve();
    }

    static void readInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        villages = new Village[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            villages[i] = new Village(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
    }

    static void solve() {
        Arrays.sort(villages);

        long leftNum = 0;
        long rightNum = 0;
        for (Village v : villages) {
            rightNum += v.a;
        }

        long minDiff = Long.MAX_VALUE;
        int answer = Integer.MAX_VALUE;
        for (Village v : villages) {
            rightNum -= v.a;
            long curDiff = Math.abs(leftNum - rightNum);
            leftNum += v.a;
            if (curDiff < minDiff) {
                minDiff = curDiff;
                answer = v.x;
            }
        }
        System.out.println(answer);
    }

    static class Village implements Comparable<Village> {
        int x, a;
        Village(int x, int a) {
            this.x = x;
            this.a = a;
        }

        @Override
        public int compareTo(Village o) {
            return this.x - o.x;
        }
    }
}
