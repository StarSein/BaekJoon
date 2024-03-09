import java.io.*;
import java.util.*;


public class Main {

    static int N;
    static int[] next, compoNo;
    static ArrayDeque<Integer> dq = new ArrayDeque<>();
    static ArrayList<Integer> cycleNodeList = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        next = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            int nex = Integer.parseInt(br.readLine());
            next[i] = nex;
        }

        // 유향 그래프에서 존재하는 모든 사이클의 크기의 합이 정답이다
        compoNo = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            if (compoNo[i] != 0) {
                continue;
            }

            dq.clear();

            int cur = i;
            while (compoNo[cur] == 0) {
                dq.offerLast(cur);
                compoNo[cur] = i;
                cur = next[cur];
            }

            if (compoNo[cur] == i) {
                while (!dq.isEmpty()) {
                    int node = dq.pollLast();
                    cycleNodeList.add(node);
                    if (node == cur) {
                        break;
                    }
                }
            }
        }

        // 정답을 출력한다
        StringBuilder sb = new StringBuilder();
        sb.append(cycleNodeList.size()).append('\n');
        cycleNodeList.sort(Comparator.naturalOrder());
        cycleNodeList.forEach(e -> sb.append(e).append('\n'));
        System.out.print(sb);
    }
}
