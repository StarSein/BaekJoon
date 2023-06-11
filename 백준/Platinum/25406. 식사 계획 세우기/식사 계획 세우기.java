import java.io.*;
import java.util.*;

public class Main {

    static class Pair {
        int cnt, food;

        Pair(int cnt, int food) {
            this.cnt = cnt;
            this.food = food;
        }
    }

    static int N;
    static int[] A, answer;
    static TreeSet<Integer> fiSet;
    static TreeSet<Pair> cntSet;
    static ArrayDeque<Integer>[] idxQ;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        fiSet = new TreeSet<>();
        cntSet = new TreeSet<>((e1, e2) -> (e1.cnt != e2.cnt ? e2.cnt - e1.cnt : e1.food - e2.food));
        idxQ = new ArrayDeque[N + 1];
        for (int i = 1; i <= N; i++) {
            idxQ[i] = new ArrayDeque<>();
        }

        for (int i = 1; i <= N; i++) {
            idxQ[A[i]].offerLast(i);
        }

        int maxCnt = 0;
        for (int food = 1; food <= N; food++) {
            ArrayDeque<Integer> curQ = idxQ[food];
            if (curQ.isEmpty()) continue;
            fiSet.add(curQ.peekFirst());
            cntSet.add(new Pair(curQ.size(), food));
            maxCnt = Math.max(maxCnt, curQ.size());
        }

        if (2 * maxCnt > N + 1) {
            System.out.println(-1);
            return;
        }

        answer = new int[N];
        int prevFood = 0;
        for (int i = 0; i < N; i++) {
            while (idxQ[cntSet.first().food].size() < cntSet.first().cnt) {
                Pair modifyPair = cntSet.pollFirst();
                modifyPair.cnt = idxQ[modifyPair.food].size();
                cntSet.add(modifyPair);
            }
            
            Pair firstPair = cntSet.first();
            ArrayDeque<Integer> curQ;
            int tgtIdx;
            if (2 * firstPair.cnt >= N - i + 1) {
                curQ = idxQ[firstPair.food];
                tgtIdx = curQ.pollFirst();
                fiSet.remove(tgtIdx);
            } else {
                int fi = fiSet.pollFirst();
                if (A[fi] == prevFood) {
                    tgtIdx = fiSet.pollFirst();
                    fiSet.add(fi);
                } else {
                    tgtIdx = fi;
                }
                curQ = idxQ[A[tgtIdx]];
                curQ.pollFirst();
            }
            
            answer[i] = tgtIdx;
            prevFood = A[tgtIdx];
            if (!curQ.isEmpty()) {
                fiSet.add(curQ.peekFirst());
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int ans : answer) {
            sb.append(ans).append(' ');
        }
        System.out.println(sb);
    }
}
