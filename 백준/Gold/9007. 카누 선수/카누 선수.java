// A = c1 + c2, B = k - c3 - c4 라고 하면
// (c1 + c2 + c3 + c4) 의 값이 k 에 근접한다는 것은
// (A - B) 의 값이 0 에 근접하다는 것과 동치이다
// 즉 이 문제는 A와 가장 가까운 B를 찾는 것과 같다.
// 단, A가 고정되어 있다고 할 때 B는 가능한 한 최댓값이어야 한다


import java.io.*;
import java.util.*;


public class Main {

    static int T, k, n;
    static int[][] c;
    static int[] A, B;
    static ArrayList<Integer> bList;

    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            k = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());

            c = new int[4][n];
            for (int i = 0; i < 4; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    c[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // A: (c1 + c2), B: (k - c3 - c4)를 저장하는 배열을 만든다
            int size = n * n;
            A = new int[size];
            int ai = 0;
            for (int c1 : c[0]) {
                for (int c2 : c[1]) {
                    A[ai++] = c1 + c2;
                }
            }
            bList = new ArrayList<>(size);
            for (int c3 : c[2]) {
                for (int c4 : c[3]) {
                    bList.add(k - c3 - c4);
                }
            }

            // B는 오름차순 정렬되고 중복이 없는 배열로 만든다
            B = bList.stream().sorted().distinct().mapToInt(Integer::intValue).toArray();

            // A의 모든 원소를 순회하면서 B에서 해당 원소 이상의 값 중 최솟값의 인덱스 t를 찾는다
            int minAbsDiff = Integer.MAX_VALUE;
            int bestSum = Integer.MAX_VALUE;
            for (int a : A) {
                int start = 0;
                int end = B.length - 1;
                int t = end;
                while (start <= end) {
                    int mid = (start + end) / 2;
                    if (B[mid] >= a) {
                        t = mid;
                        end = mid - 1;
                    } else {
                        start = mid + 1;
                    }
                }

                // t 뿐만 아니라 t-1 또한 후보가 될 수 있다. 이를 반영하여 정답을 갱신한다
                for (int i = 0; i < 2 && t >= 0; i++, t--) {
                    int curAbsDiff = Math.abs(a - B[t]);
                    int curSum = a - B[t] + k;
                    if (curAbsDiff < minAbsDiff || (curAbsDiff == minAbsDiff && curSum < bestSum)) {
                        minAbsDiff = curAbsDiff;
                        bestSum = curSum;
                    }
                }
            }

            // 정답 문자열에 정답을 추가한다
            sb.append(bestSum).append('\n');
        }

        // 정답 문자열을 출력한다
        System.out.print(sb);
    }
}
