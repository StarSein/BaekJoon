import java.io.*;
import java.util.*;


public class Main {

    static final int AXIS = 1_000_000;
    static int n;
    static int[] xArr, yArr;
    static int[] horizonCount = new int[2_000_001];
    static int[] vertCount = new int[2_000_001];

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        xArr = new int[n + 1];
        yArr = new int[n + 1];
        for (int i = 0; i < n; i++) {
            // 단, 꼭짓점 사이의 값에 대해서도 탐색을 하기 위해 좌표값의 2배를 저장한다
            StringTokenizer st = new StringTokenizer(br.readLine());
            xArr[i] = 2 * Integer.parseInt(st.nextToken());
            yArr[i] = 2 * Integer.parseInt(st.nextToken());
        }
        xArr[n] = xArr[0];
        yArr[n] = yArr[0];

        // imos법을 이용하여 가로선은 가로 배열, 세로선은 새로 배열의 해당 구간에 개수를 1만큼 증가시킨다
        for (int i = 0; i < n; i++) {
            int px = xArr[i];
            int py = yArr[i];
            int cx = xArr[i + 1];
            int cy = yArr[i + 1];

            if (py == cy) {
                int min = Math.min(px, cx) + AXIS;
                int max = Math.max(px, cx) + AXIS;
                horizonCount[min]++;
                horizonCount[max]--;
            } else {
                int min = Math.min(py, cy) + AXIS;
                int max = Math.max(py, cy) + AXIS;
                vertCount[min]++;
                vertCount[max]--;
            }
        }

        // 누적합 연산을 하기 전에 값이 존재한다면 수평/수직선과 겹치는 선분이 해당 좌표에 존재하는 것이므로 제외하고
        // 그 이외의 좌표 위에 존재하는 구간 개수의 최댓값을 갱신한다
        int maxHorizonCount = 0;
        int maxVertCount = 0;
        for (int i = 1; i < 2_000_000; i++) {
            horizonCount[i] += horizonCount[i - 1];
            if (horizonCount[i] == horizonCount[i - 1]) {
                maxHorizonCount = Math.max(maxHorizonCount, horizonCount[i]);
            }
            vertCount[i] += vertCount[i - 1];
            if (vertCount[i] == vertCount[i - 1]) {
                maxVertCount = Math.max(maxVertCount, vertCount[i]);
            }
        }

        // 정답을 출력한다
        System.out.println(Math.max(maxHorizonCount, maxVertCount));
    }
}
