import java.io.*;
import java.util.*;

public class Main {

    static class Mine {
        int x, y, w;

        Mine(int x, int y, int w) {
            this.x = x;
            this.y = y;
            this.w = w;
        }
    }

    static class Node {
        long lMax, rMax, max, sum;

        Node() {}
        Node(long value) {
            this.lMax = this.rMax = this.max = this.sum = value;
        }

        Node(long lMax, long rMax, long max, long sum) {
            this.lMax = lMax;
            this.rMax = rMax;
            this.max = max;
            this.sum = sum;
        }
    }
    static int N, xCnt;
    static ArrayList<Integer> xList = new ArrayList<>();
    static HashMap<Integer, Integer> xMap = new HashMap<>();
    static Mine[] mines;
    static Node[] seg;
    static ArrayList<ArrayList<Mine>> mineList = new ArrayList<>();
    static long answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        mines = new Mine[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            mines[i] = new Mine(Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()));
            xList.add(mines[i].x);
        }

        xList.sort(Comparator.naturalOrder());
        for (Integer x : xList) {
            if (xMap.containsKey(x)) continue;
            xMap.put(x, ++xCnt);
        }
        for (int i = 0; i < N; i++) {
            mines[i].x = xMap.get(mines[i].x);
        }

        Arrays.sort(mines, Comparator.comparingInt(e -> e.y));


        int prevY = -1_000_000_005;
        for (int i = 0; i < N; i++) {
            if (mines[i].y != prevY) {
                mineList.add(new ArrayList<>());
            }
            mineList.get(mineList.size() - 1).add(mines[i]);
            prevY = mines[i].y;
        }

        seg = new Node[4 * xCnt];
        for (int s = 0; s < mineList.size(); s++) {
            Arrays.fill(seg, new Node()); // dummy node 들이 동일한 객체를 참조하도록 한다

            for (int e = s; e < mineList.size(); e++) {
                for (Mine mine : mineList.get(e)) {
                    update(1, 1, N, mine.x, mine.w);
                }
                answer = Math.max(answer, seg[1].max);
            }
        }

        System.out.println(answer);
    }

    static void update(int node, int start, int end, int target, long value) {
        if (target > end || target < start) {
            return;
        }
        if (start == target && target == end) {
            seg[node] = new Node(seg[node].max + value);
            return;
        }
        int mid = (start + end) >> 1;
        update(node << 1, start, mid, target, value);
        update(node << 1 | 1, mid + 1, end, target, value);
        seg[node] = merge(seg[node << 1], seg[node << 1 | 1]);
    }

    static Node merge(Node left, Node right) {
        return new Node(
                Math.max(left.lMax, left.sum + right.lMax),
                Math.max(right.rMax, right.sum + left.rMax),
                Math.max(
                        Math.max(left.max, right.max),
                        left.rMax + right.lMax
                ),
                left.sum + right.sum
        );
    }
}
