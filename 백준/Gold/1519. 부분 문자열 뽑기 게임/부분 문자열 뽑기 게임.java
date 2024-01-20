// 메모이제이션을 적용한 백트래킹의 시간 복잡도는 O(D^3 * N) (D는 N의 자릿수) 이다


import java.util.*;

public class Main {

    static int N;
    static int[][] winner, count;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        N = new Scanner(System.in).nextInt();

        // 진 부분 문자열을 모두 구하고 정수로 변환 후, 크기의 오름차순으로 정렬한다
        ArrayList<Integer> properSubsequences = getProperSubsequences(N);
        properSubsequences.sort(Comparator.naturalOrder());

        // 처음 주어진 수에서 가장 작은 수부터 M이라 두고, M을 빼 보면서
        // 무조건 이길 수 있는 경우라면 M을 출력하고 프로그램을 종료한다
        winner = new int[3][N + 1];
        count = new int[3][N + 1];
        for (int M : properSubsequences) {
            if (getWinner(2, N - M) == 1) {
                System.out.println(M);
                return;
            }
        }

        // 절대 이길 수 없으면 -1을 출력한다
        System.out.println(-1);

        for (int i = 1; i <= 2; i++) {
            for (int j = 1; j <= N; j++) {
                if (count[i][j] > 1) {
                    System.out.println(i + " " + j + " " + count[i][j]);
                }
            }
        }
    }

    // n의 진부분문자열로 만들 수 있는 양의 정수의 리스트를 반환한다
    static ArrayList<Integer> getProperSubsequences(int n) {
        ArrayList<Integer> properSubsequences = new ArrayList<>();

        int digitCount = getDigitCount(n);
        for (int i = 0; i < digitCount; i++) {
            for (int j = i; j < digitCount; j++) {
                int temp = n;
                for (int k = 0; k < i; k++) {
                    temp /= 10;
                }
                int num = 0;
                int pow = 1;
                for (int k = i; k <= j; k++) {
                    num += (temp % 10) * pow;
                    temp /= 10;
                    pow *= 10;
                }
                if (num == 0 || num == n) {
                    continue;
                }
                properSubsequences.add(num);
            }
        }

        return properSubsequences;
    }

    static int getDigitCount(int n) {
        int digitCount = 0;
        while (n > 0) {
            digitCount++;
            n /= 10;
        }
        return digitCount;
    }

    // player(1 or 2)에게 N이 주어졌을 때 이기는 선수의 번호를 반환한다
    static int getWinner(int player, int n) {
        if (winner[player][n] != 0) {
            return winner[player][n];
        }
        count[player][n]++;
        if (n < 10) {
            return winner[player][n] = 3 - player;
        }

        ArrayList<Integer> properSubsequences = getProperSubsequences(n);
        for (int m : properSubsequences) {
            if (getWinner(3 - player, n - m) == player) {
                return winner[player][n] = player;
            }
        }
        return winner[player][n] = 3 - player;
    }
}
