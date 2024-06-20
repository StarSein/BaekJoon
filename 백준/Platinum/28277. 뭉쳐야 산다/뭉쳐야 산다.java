import java.io.*;
import java.util.*;

/*
모든 집합의 원소의 개수의 합 <= 50만 이므로,
합집합 연산 시 크기가 더 큰 집합에 작은 집합을 합친다고 하면,
최악의 케이스는 1~50만 까지의 원소를 50만 개의 집합이 1개씩 나눠가진 이후
동일한 크기의 집합끼리만 합집합 연산이 주어지는 케이스다
이 경우 합집합 연산의 횟수는
25만 + 12.5만 + 6.25만 + ... 이고
그때마다 이동하는 원소의 개수는
1 + 2 + 4 + ... 이고
이같은 반복은
logN 번 발생한다
따라서 시간 복잡도는 O(NlogN) 이다
 */

public class Main {

    static int N, Q;
    static HashSet<Integer>[] sets;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        sets = new HashSet[N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            sets[i] = new HashSet<>(n);
            for (int j = 0; j < n; j++) {
                sets[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        StringBuilder sb = new StringBuilder();
        while (Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            int a = Integer.parseInt(st.nextToken());
            if (cmd.equals("1")) {
                // 1번 쿼리가 주어지면
                int b = Integer.parseInt(st.nextToken());

                // 큰 집합에 작은 집합을 합친다
                HashSet<Integer> setA = sets[a];
                HashSet<Integer> setB = sets[b];
                if (setA.size() < setB.size()) {
                    HashSet<Integer> temp = setA;
                    setA = setB;
                    setB = temp;
                }
                setA.addAll(setB);
                setB.clear();

                // 큰 집합을 a가 가리키고, b는 빈 집합을 가리키도록 한다
                sets[a] = setA;
                sets[b] = setB;
            } else {
                // 2번 쿼리가 주어지면
                // a의 크기를 정답 문자열에 추가한다
                sb.append(sets[a].size()).append('\n');
            }
        }

        // 정답 문자열을 출력한다
        System.out.print(sb);
    }
}
