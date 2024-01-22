// 경로상 열매의 수가 최대인 경로의 양 끝에 노드 A, B가 있다고 하자
// 결론: 트리 상 임의의 노드 X에서 시작하여 최대의 열매를 얻을 수 있는 경로를 얻으면 그 끝에는 노드 A 또는 B가 있다
// 가정: 트리 상 임의의 노드 X(X != A and X != B)가
//      Y(Y != A and Y != B)와 연결되었을 때 최대의 열매를 얻는다고 하자
//      그리고 경로 XY와 경로 AB는 노드 M에서 처음 만난다고 하자
//      경로 PQ에서 얻을 수 있는 열매의 수를 f(PQ)라고 할 때
//      가정에 의해 f(MY) >= max(f(MA), f(MB)) 이고
//      f(MA) + f(MY) >= f(MA) + f(MB) 가 성립하므로,
//      최대의 경로 양 끝에 노드 A, B가 있다는 전제에 모순이 된다
//      따라서 Y == A or Y == B 가 항상 성립한다
// 이에 착안하여 임의의 노드 X에서 최적의 노드 Y를, 노드 Y에서 최적의 노드 Z를 구하면
// f(YZ)가 정답이 된다.
// 해당 트리에 노드가 N개 있다고 할 때 최적의 노드 Y는 최대 N-1개까지 존재할 수 있고,
// 최적의 노드 Z도 최대 N-1개까지 존재할 수 있다
// Y = A 일 때 존재하는 Z 의 목록과 Y = B 일 때 존재하는 Z 의 목록이 A, B 를 제외하고
// 서로 같으므로 최적의 노드를 구할 때 후보가 여러 개 생긴다면 노드 번호가 낮은 것을 택하자


import java.io.*;
import java.util.*;


public class Main {

    static class Pair {
        int node, score;

        Pair(int node, int score) {
            this.node = node;
            this.score = score;
        }

        static Pair max(Pair a, Pair b) {
            if (a.score == b.score) {
                return a.node < b.node ? a : b;
            } else {
                return a.score < b.score ? b : a;
            }
        }
    }

    static int n;
    static int[] scores;
    static boolean[] checked;
    static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        scores = new int[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            scores[i] = Integer.parseInt(st.nextToken());
        }
        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        // 그래프 상에 존재하는 모든 트리에 대해 최적의 경로를 구한다
        checked = new boolean[n + 1];
        Pair bestPair = new Pair(-1, -1);
        for (int i = 1; i <= n; i++) {
            if (checked[i]) {
                continue;
            }
            Pair y = dfs(i, 0);
            Pair z = dfs(y.node, 0);

            z.node = Math.min(y.node, z.node);
            bestPair = Pair.max(bestPair, z);
        }

        // 정답을 출력한다
        System.out.println(bestPair.score + " " + bestPair.node);
    }

    static Pair dfs(int cur, int par) {
        checked[cur] = true;
        Pair ret = new Pair(cur, scores[cur]);
        Pair bestPair = new Pair(cur, -1);
        for (int nex : graph[cur]) {
            if (nex == par) {
                continue;
            }
            bestPair = Pair.max(bestPair, dfs(nex, cur));
        }
        if (bestPair.score > 0) {
            ret.node = bestPair.node;
            ret.score += bestPair.score;
        } else if (bestPair.score == 0) {
            ret.node = Math.min(ret.node, bestPair.node);
        }
        return ret;
    }
}
