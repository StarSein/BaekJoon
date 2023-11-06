import java.io.*;
import java.util.*;
import java.util.stream.IntStream;


public class Main {
    static int T, N, A, B;
    static final int TARGET_LIMIT = 100_000;
    static final int CUSTOM_RANGE = 1_500_000;
    static boolean[] isPrime = new boolean[TARGET_LIMIT + 1];
    static boolean[] isTarget = new boolean[CUSTOM_RANGE + 1];
    static boolean[] visit = new boolean[CUSTOM_RANGE + 1];
    static ArrayDeque<Integer> dq = new ArrayDeque<>();

    public static void main(String[] args) throws Exception {
        initPrimes();

        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            initTargets();
            sb.append(findAnswer()).append('\n');
        }
        
        System.out.println(sb);
    }

    static void initPrimes() {
        Arrays.fill(isPrime, true);

        for (int i = 2; i <= TARGET_LIMIT; i++) {
            if (isPrime[i]) {
                for (int j = 2 * i; j <= TARGET_LIMIT; j += i) {
                    isPrime[j] = false;
                }
            }
        }
    }

    static void initTargets() {
        Arrays.fill(isTarget, false);

        IntStream.rangeClosed(A, B)
                .filter(e -> isPrime[e])
                .forEach(e -> isTarget[e] = true);
    }
    
    static int findAnswer() {
        Arrays.fill(visit, false);
        dq.clear();
        dq.offerLast(N);
        visit[N] = true;

        int breadthCnt = 0;
        while (!dq.isEmpty()) {
            int dqSize = dq.size();
            for (int i = 0; i < dqSize; i++) {
                int curNum = dq.pollFirst();

                if (isTarget[curNum]) {
                    return breadthCnt;
                }

                procedureDeque(curNum / 2);
                procedureDeque(curNum / 3);
                procedureDeque(curNum + 1);
                procedureDeque(curNum - 1);
            }
            breadthCnt++;
        }

        return -1;
    }

    static void procedureDeque(int nexNum) {
        if (0 <= nexNum && nexNum <= CUSTOM_RANGE && !visit[nexNum]) {
            dq.offerLast(nexNum);
            visit[nexNum] = true;
        }
    }

}
