import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] skills, sortedS, fwTree;
    static Map<Integer, Integer> table;

    public static void main(String[] args) throws Exception {
        readInput();
        solve();
    }

    static void readInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        skills = new int[N];
        for (int i = 0; i < N; i++) {
            skills[i] = Integer.parseInt(br.readLine());
        }
    }
    
    static void solve() {
        sortedS = Arrays.copyOf(skills, N);
        Arrays.sort(sortedS);
        table = new HashMap<>();
        for (int i = 0; i < N; i++) {
            table.put(sortedS[i], i + 1);
        }

        StringBuilder answer = new StringBuilder();
        fwTree = new int[N + 1];
        for (int i = 0; i < N; i++) {
            int idx = table.get(skills[i]);
            answer.append(i + 1 - query(idx)).append('\n');
            update(idx);
        }
        System.out.print(answer);
    }

    static int query(int i) {
        int ret = 0;
        while (i >= 1) {
            ret += fwTree[i];
            i -= i & -i;
        }
        return ret;
    }

    static void update(int i) {
        while (i <= N) {
            fwTree[i]++;
            i += i & -i;
        }
    }
}
