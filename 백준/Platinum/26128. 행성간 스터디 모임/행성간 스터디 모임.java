import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] pArr, indCnt, num;
    static boolean[] isStart, isCycle;
    static ArrayList<Integer> cycle = new ArrayList<>();
    static ArrayList<Integer> startList = new ArrayList<>();
    static ArrayList<Integer>[] revGraph;
    public static void main(String[] args) throws Exception {
        readInput();
        System.out.print(solve());
    }
    static void readInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        pArr = new int[N + 1];
        num = new int[N + 1];
        isStart = new boolean[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            pArr[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int start = Integer.parseInt(st.nextToken());
            num[start]++;
            if (isStart[start]) continue;
            startList.add(start);
            isStart[start] = true;
        }
    }
    static long solve() {
        findCycle();
        revGraph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            revGraph[i] = new ArrayList<>();
        }
        for (int i = 1; i <= N; i++) {
            revGraph[pArr[i]].add(i);
        }

        boolean[] visited = new boolean[N + 1];
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for (int node : cycle) {
            visited[node] = true;
            queue.offerLast(node);
        }
        while (!queue.isEmpty()) {
            int curNode = queue.pollFirst();
            for (int nextNode : revGraph[curNode]) {
                if (visited[nextNode]) continue;
                queue.offerLast(nextNode);
                visited[nextNode] = true;
            }
        }
        for (int startNode : startList) {
            if (!visited[startNode]) return -1;
        }

        isCycle = new boolean[N + 1];
        for (int node : cycle) {
            isCycle[node] = true;
        }
        boolean targetInCycle = false;
        for (int i = 1; i <= N; i++) {
            if (isStart[i] && isCycle[i]) {
                targetInCycle = true;
                break;
            }
        }
        if (!targetInCycle) {
            int outTargetCnt = 0;
            for (int node : cycle) {
                if (findTarget(node)) outTargetCnt++;
            }
            if (outTargetCnt >= 2) targetInCycle = true;
        }
        if (targetInCycle) {
            long answer = 0;
            long[] weight = new long[N + 1];
            for (int node : cycle) {
                Result result = bfs(node);
                weight[node] += result.startCnt;
                answer += result.totalDist;
            }
            long curCost = 0;
            for (int i = 0; i < cycle.size(); i++) {
                curCost += i * weight[cycle.get(i)];
            }
            long minCost = curCost;
            for (int i = 0; i < cycle.size(); i++) {
                curCost -= M;
                curCost += weight[cycle.get(i)] * cycle.size();
                minCost = Math.min(minCost, curCost);
            }
            answer += minCost;
            return answer;
        } else {
            indCnt = new int[N + 1];
            for (int i = 1; i <= N; i++) {
                if (i == pArr[i]) continue;
                indCnt[pArr[i]]++;
            }
            ArrayDeque<Integer> topoQ = new ArrayDeque<>();
            int[] startCnt = new int[N + 1];
            for (int i = 1; i <= N; i++) {
                if (isStart[i]) startCnt[i] = num[i];
                if (indCnt[i] == 0) topoQ.offerLast(i);
            }
            int target = -1;
            while (!topoQ.isEmpty()) {
                int node = topoQ.pollFirst();
                if (startCnt[node] == M) {
                    target = node;
                    break;
                }
                int next = pArr[node];
                startCnt[next] += startCnt[node];
                indCnt[next]--;
                if (indCnt[next] == 0) {
                    topoQ.offerLast(next);
                }
            }
            return bfs(target).totalDist;
        }
    }
    static void findCycle() {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        boolean[] visited = new boolean[N + 1];
        int curNode = startList.get(0);
        while (true) {
            if (visited[curNode]) {
                while (!stack.isEmpty() && stack.peekLast() != curNode) {
                    cycle.add(stack.pollLast());
                }
                cycle.add(curNode);
                return;
            }
            stack.offerLast(curNode);
            visited[curNode] = true;
            curNode = pArr[curNode];
        }
    }

    static boolean findTarget(int node) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.offerLast(node);
        while (!stack.isEmpty()) {
            int curNode = stack.pollLast();
            for (int nextNode : revGraph[curNode]) {
                if (isCycle[nextNode]) continue;
                if (isStart[nextNode]) return true;
                stack.offerLast(nextNode);
            }
        }
        return false;
    }

    static Result bfs(int target) {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.offerLast(target);
        int startCnt = 0;
        long totalDist = 0;
        long cnt = 0;
        while (!q.isEmpty()) {
            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                int node = q.pollFirst();
                if (isStart[node]) {
                    startCnt += num[node];
                    totalDist += cnt * num[node];
                }
                for (int next : revGraph[node]) {
                    if (isCycle[next]) continue;
                    q.offerLast(next);
                }
            }
            cnt++;
        }
        return new Result(startCnt, totalDist);
    }

    static class Result {
        int startCnt;
        long totalDist;
        Result(int startCnt, long totalDist) {
            this.startCnt = startCnt;
            this.totalDist = totalDist;
        }
    }
}
