import java.io.*;
import java.util.*;


public class Main {

    static int N, H;
    static int[] obstacleCounts;

    public static void main(String[] args) throws Exception {
        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        obstacleCounts = new int[H + 2];
        obstacleCounts[H + 1] = -(N / 2);
        obstacleCounts[1] = N + obstacleCounts[H + 1];
        for (int i = 1; i <= N; i++) {
            int obstacleSize = Integer.parseInt(br.readLine());
            if (i % 2 == 1) {
                // 홀수 번째 입력 o에 대해서는 구간 [1, o]에 개수를 1만큼 추가
                obstacleCounts[obstacleSize + 1]--;
            } else {
                // 짝수 번째 입력 e에 대해서는 구간 [H - e + 1, H]에 개수를 1만큼 추가
                obstacleCounts[H - obstacleSize + 1]++;
            }
        }

        // 입력을 다 받고 imos법을 이용하여 변경점을 반영하기 위해 누적합 처리
        for (int i = 1; i <= H; i++) {
            obstacleCounts[i] += obstacleCounts[i - 1];
        }

        // 모든 높이를 다 순회하면서 장애물 개수의 최솟값 갱신
        int minCount = N + 1;
        int intervalCount = 0;
        for (int i = 1; i <= H; i++) {
            if (obstacleCounts[i] < minCount) {
                minCount = obstacleCounts[i];
                intervalCount = 1;
            } else if (obstacleCounts[i] == minCount) {
                intervalCount++;
            }
        }

        // 최솟값과 그 개수 출력
        System.out.println(minCount + " " + intervalCount);
    }
}
