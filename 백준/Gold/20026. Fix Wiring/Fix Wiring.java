import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

/*
최소 신장 트리 길이의
최솟값은 가장 작은 (N-1)개의 C 값의 총합이다 -> 순서대로 (1->2), (2->3), (3->4), ..., (N-1 -> N)에 붙인다

최댓값은 가장 큰 (N-1)개의 C 값 중 최솟값 -> (1, 2, ..., N-1)번 노드와 N번 노드를 연결하는 간선으로 삼는다
                +
       그 다음 (N-2)개의 C 값 중 최솟값 -> (1, 2, ..., N-2)번 노드와 (N-1)번 노드를 연결하는 간선으로 삼는다
                +
               ...
                +
       가장 작은 1개의 C 값 중 최솟값 -> 1번 노드와 2번 노드를 연결하는 간선으로 삼는다

오름차순 정렬 시 채택되는 C 값들을
C_1, C_2, C_4, C_7, C_11, ..., C_{a_{n-1}} 이라고 하자
그러면 a_n - a_{n-1} = n - 1
a_n = (n * (n - 1) / 2) + 1
 */

public class Main {

    static int N;
    static long[] C;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다. 이때 C 배열은 오름차순 정렬된 상태로 저장한다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        C = Arrays.stream(br.readLine().split(" ")).mapToLong(Integer::parseInt).sorted().toArray();

        // MST 길이의 최솟값을 계산 후 출력한다
        long min = Arrays.stream(C).limit(N - 1).sum();
        System.out.print(min + " ");

        // MST 길이의 최댓값을 계산 후 출력한다
        long max = IntStream.rangeClosed(1, N - 1)
                .mapToLong(n -> {
                    int idx = n * (n - 1) / 2;
                    return C[idx];
                })
                .sum();
        System.out.println(max);
    }
}
