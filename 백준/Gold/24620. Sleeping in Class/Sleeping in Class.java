// 주어진 원소들을 각각 총합이 s인 그룹으로 나눈다고 하면
// s는 전체 원소들의 합의 약수이다
// 그런 수가 만들어질 때마다 그룹으로 나누는 것은 힘들어 보이고,
// 모든 약수에 대해 다 해 보는 것이 가능할까?
// 원소들의 총합의 최댓값은 10^6 이다
// 약수가 가장 많이 생길 수 있는 경우를 생각해 보면
// 8! = 약 3 * 10^5
// 약수의 개수는 2^8 = 64개
// 모든 약수에 대해 다 해 보면서 변환 횟수의 최솟값을 구하자

import java.io.*;
import java.util.*;


public class Main {

    static final int INF = Integer.MAX_VALUE;
    static int T, N;
    static int[] A;

    public static void main(String[] args) throws Exception {
        // 각 테스트 케이스별
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            // 입력 받기
            N = Integer.parseInt(br.readLine());
            A = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                A[i] = Integer.parseInt(st.nextToken());
            }

            // 원소들 총합 구하기
            int sum = 0;
            for (int a : A) {
                sum += a;
            }

            // 총합이 0일 경우 바로 0 출력
            if (sum == 0) {
                sb.append("0\n");
                continue;
            }

            // 총합의 약수 구하기
            ArrayList<Integer> divisors = getDivisors(sum);

            // 모든 약수에 대해 해당 약수가 최종 원소가 되도록 하는 변환 진행 후 최솟값 갱신
            int answer = INF;
            for (int divisor : divisors) {
                answer = Math.min(answer, getNumModify(divisor));
            }

            // 정답 출력
            sb.append(answer).append('\n');
        }
        System.out.print(sb);
    }

    static ArrayList<Integer> getDivisors(int n) {
        ArrayList<Integer> divisors = new ArrayList<>();
        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                divisors.add(i);
                divisors.add(n / i);
            }
        }
        if (divisors.get(divisors.size() - 1) == divisors.get(divisors.size() - 2)) {
            divisors.remove(divisors.size() - 1);
        }
        return divisors;
    }

    static int getNumModify(int target) {
        int count = 0;
        int curSum = 0;
        boolean isNewGroup = true;
        for (int a : A) {
            // 바로 직전에 target이 완성된 것이 아니라면 현재 원소는 그 그룹에 병합해야 한다
            if (isNewGroup) {
                isNewGroup = false;
            } else {
                count++;
            }
            curSum += a;
            if (curSum == target) {
                curSum = 0;
                isNewGroup = true;
            } else if (curSum > target) {
                return INF;
            }
        }
        return count;
    }
}
