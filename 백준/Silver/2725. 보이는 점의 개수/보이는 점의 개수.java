// x와 y가 서로소인 (x, y)는 원점에서 보인다
// x와 y가 서로소가 아닐 경우 (x, y) = (aG, bG) (G > 1)
// 원점에서 (a, b)에 그은 반직선과 (x, y)에 그은 반직선은 기울기가 같으므로
// 서로소가 아닌 (x, y)는 원점에서 보이지 않는다
// 반대로 서로소인 (x, y)는 원점에서 그은 반직선 상의 점들 중 원점과 가장 가깝다
// 그런데 테스트케이스가 주어질 때마다 새로 서로소인 좌표 개수를 계산할 경우
// O(CN^2logN) 으로 시간 제한을 초과한다
// 따라서 미리 N값에 대한 정답을 계산해 놓고 테스트케이스가 주어질 때마다 출력하자
// 그러면 시간 복잡도가 O(N^2logN + C)가 된다


import java.io.*;
import java.util.*;


public class Main {

    static final int MAX_N = 1000;
    static int C, N;
    static int[] answers;

    public static void main(String[] args) throws Exception {
        // 'answers[i]: 입력 i에 대한 정답값' 계산하기
        answers = new int[MAX_N + 1];
        answers[1] = 3;
        for (int i = 2; i <= MAX_N; i++) {
            int coprimeCount = 0;
            for (int x = 1; x < i; x++) {
                if (gcd(x, i) == 1) {
                    coprimeCount += 2;
                }
            }
            answers[i] = answers[i - 1] + coprimeCount;
        }

        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        C = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < C; i++) {
            N = Integer.parseInt(br.readLine());
            sb.append(answers[N]).append('\n');
        }

        // 정답 문자열 출력하기
        System.out.print(sb);
    }

    static int gcd(int a, int b) {
        while (b > 0) {
            int temp = a;
            a = b;
            b = temp % b;
        }
        return a;
    }
}
