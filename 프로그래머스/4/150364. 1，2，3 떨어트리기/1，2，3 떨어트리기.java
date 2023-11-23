import java.util.*;


class Solution {
    
    final int ROOT = 1;
    final int[] IMPOSSIBLE = new int[] {-1};
    int n, targetCnt, dropCnt, completeCnt;
    int[] scores;
    int[] arrows;
    int[] targets;
    boolean[] isLeaf;
    List<Integer>[] graph;
    List<Integer>[] tree;
    List<Integer>[] moments;
    
    public int[] solution(int[][] edges, int[] target) {
        // 트리를 만든다
        n = target.length;
        targets = new int[n + 1];
        for (int i = 0; i < n; i++) {
            if (target[i] > 0) {
                targetCnt++;
                targets[i + 1] = target[i];
            }
        }
        graph = new ArrayList[n + 1];
        tree = new ArrayList[n + 1];
        moments = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
            tree[i] = new ArrayList<>();
            moments[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        isLeaf = new boolean[n + 1];
        makeTree(ROOT, 0);
        
        for (int i = 1; i <= n; i++) {
            if (tree[i].isEmpty()) {
                isLeaf[i] = true;
            } else {
                Collections.sort(tree[i]);
            }
        }
        
        // 일단 3번 구슬만 떨어뜨린다
            // 모든 리프 노드의 숫자 합이 목표치를 달성할 때까지
            // 리프 노드별 구슬이 도착한 시점 t를 저장해 놓는다
        scores = new int[n + 1];
        arrows = new int[n + 1];
        completeCnt = 0;
        while (completeCnt < targetCnt) {
            dropNumber(ROOT);
            dropCnt++;
        }
        
        int[] answer = new int[dropCnt];
        // 각 리프 노드별 구슬 개수 초과분 만큼 answer의 앞에서부터 차감한다
            // 구슬 도착 시점 t의 개수 > 목표치 인 경우 '불가능'
        for (int i = 1; i <= n; i++) {
            if (isLeaf[i]) {
                List<Integer> moment = moments[i];
                int size = moment.size();
                
                if (size > targets[i]) {
                    return IMPOSSIBLE;
                }
                
                targets[i] -= size;
                
                for (int j = moment.size() - 1; j >= 0; j--) {
                    int addition = Math.min(2, targets[i]);
                    targets[i] -= addition;
                    
                    int idx = moment.get(j);
                    answer[idx] = 1 + addition;
                }
            }
        }
        
        return answer;
    }
    
    void makeTree(int cur, int par) {
        for (int nex : graph[cur]) {
            if (nex == par) {
                continue;
            }
            tree[cur].add(nex);
            
            makeTree(nex, cur);
        }
    }
    
    void dropNumber(int node) {
        if (isLeaf[node]) {
            int score = scores[node];
            int target = targets[node];
            if (score < target && target <= score + 3) {
                completeCnt++;
            }
            scores[node] = score + 3;
            
            moments[node].add(dropCnt); 
            return;
        }
        
        int idx = arrows[node];

        int child = tree[node].get(idx);
        
        arrows[node] = (idx + 1) % tree[node].size();
        
        dropNumber(child);
    }
}