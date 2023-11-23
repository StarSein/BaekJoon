import java.util.*;


class Solution {
    final int NUM = 50;
    final int SIZE = NUM * NUM;
    final String EMPTY = "EMPTY";
    String[] table;
    int[] roots;
    List<String> answer;
    
    public String[] solution(String[] commands) {
        table = new String[SIZE];
        roots = new int[SIZE];
        answer = new ArrayList<>();
        
        Arrays.fill(table, EMPTY);
        for (int i = 0; i < SIZE; i++) {
            roots[i] = i;
        }
        
        for (String command : commands) {
            String[] tokens = command.split(" ");
            switch (tokens[0]) {
                case "UPDATE":
                    if (tokens.length == 4) {
                        update(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), tokens[3]);
                    } else {
                        update(tokens[1], tokens[2]);
                    }
                    break;
                case "MERGE":
                    merge(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4]));
                    break;
                case "UNMERGE":
                    unmerge(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
                    break;
                case "PRINT":
                    print(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
                    break;
                default:
                    break;
            }
        }
        
        return answer.toArray(new String[answer.size()]);
    }
    
    void update(int r, int c, String value) {
        int idx = locToIndex(r, c);
        int root = findRoot(idx);
        table[root] = value;
    }
    
    void update(String value1, String value2) {
        for (int i = 0; i < SIZE; i++) {
            if (value1.equals(table[i])) {
                table[i] = value2;
            }
        }
    }
    
    void merge(int r1, int c1, int r2, int c2) {
        int idx1 = locToIndex(r1, c1);
        int idx2 = locToIndex(r2, c2);
        int root1 = findRoot(idx1);
        int root2 = findRoot(idx2);
        
        if (root1 != root2) {
            String value1 = table[root1];
            String value2 = table[root2];
            
            roots[root2] = root1;
            if (value1.equals(EMPTY)) {
                table[root1] = value2;
            } else {
                table[root1] = value1;
            }
            
            table[root2] = EMPTY;
        }
    }
    
    void unmerge(int r, int c) {
        int idx = locToIndex(r, c);
        int target = findRoot(idx);
        String value = table[target];
        
        for (int i = 0; i < SIZE; i++) {
            findRoot(i);
        }
        
        for (int i = 0; i < SIZE; i++) {
            if (roots[i] == target) {
                roots[i] = i;
                table[i] = EMPTY;
            }
        }
        
        table[idx] = value;
    }
    
    void print(int r, int c) {
        int idx = locToIndex(r, c);
        int root = findRoot(idx);
        
        answer.add(table[root]);
    }
    
    int locToIndex(int r, int c) {
        return (r - 1) * NUM + (c - 1);
    }
    
    int findRoot(int x) {
        if (roots[x] == x) {
            return x;
        }
        return roots[x] = findRoot(roots[x]);
    }
}