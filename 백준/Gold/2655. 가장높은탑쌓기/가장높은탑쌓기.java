import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {

    static class Brick implements Comparable<Brick> {
        int id, area, height, weight;

        Brick(int id, int area, int height, int weight) {
            this.id = id;
            this.area = area;
            this.height = height;
            this.weight = weight;
        }

        @Override
        public int compareTo(Brick e) {
            return e.area - this.area;
        }
    }
    static int N;
    static Brick[] bricks;
    static int[] dp;

    public static void main(String[] args) throws Exception {
        readInput();
        solve();
    }

    static void readInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        bricks = new Brick[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            bricks[i] = new Brick(i + 1,
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()));
        }
    }

    static void solve() {
        Arrays.sort(bricks);

        dp = new int[N];
        for (int i = 0; i < N; i++) {
            Brick cur = bricks[i];
            dp[i] = cur.height;
            for (int j = 0; j < i; j++) {
                if (bricks[j].weight < cur.weight) continue;
                if (dp[j] + cur.height <= dp[i]) continue;
                dp[i] = dp[j] + cur.height;
            }
        }

        int sumHeight = IntStream.of(dp).max().orElse(0);
        int prevIdx = N;
        ArrayList<Integer> chosen = new ArrayList<>();
        while (sumHeight > 0) {
            for (int i = 0; i < prevIdx; i++) {
                if (dp[i] == sumHeight) {
                    chosen.add(bricks[i].id);
                    sumHeight -= bricks[i].height;
                    prevIdx = i;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(chosen.size()).append('\n');
        chosen.forEach(e -> sb.append(e).append('\n'));
        System.out.print(sb);
    }
}
