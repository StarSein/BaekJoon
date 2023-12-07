import java.io.*;
import java.util.*;


public class Main {

    static final int INF = 1_000_000_000;
    static int N, R, M, K;
    static HashMap<String, Integer> cityIndexMap;
    static int[] tourRoute;
    static int[][] normalMatrix, discountedMatrix;

    public static void main(String[] args) throws Exception {
        // 입력 받기
            // 정상가격과 내일로 할인가격 각각의 2차원 배열 구성
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        cityIndexMap = new HashMap<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            cityIndexMap.put(st.nextToken(), i); // key: 도시 이름, value: 도시 인덱스
        }
        M = Integer.parseInt(br.readLine());
        tourRoute = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            tourRoute[i] = cityIndexMap.get(st.nextToken());
        }
        normalMatrix = new int[N][N];
        discountedMatrix = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(normalMatrix[i], INF);
            Arrays.fill(discountedMatrix[i], INF);
        }
        K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            String type = st.nextToken();
            int from = cityIndexMap.get(st.nextToken());
            int to = cityIndexMap.get(st.nextToken());
            int normalCost = 2 * Integer.parseInt(st.nextToken()); // 50% 할인가격 계산 시 정확성을 위해 2배 가격을 사용
            int discountedCost = normalCost;
            switch (type) {
                case "Mugunghwa":
                case "ITX-Saemaeul":
                case "ITX-Cheongchun":
                    discountedCost = 0;
                    break;
                case "S-Train":
                case "V-Train":
                    discountedCost = normalCost / 2;
                    break;
                default:
                    break;
            }
            normalMatrix[from][to] = Math.min(normalMatrix[from][to], normalCost);
            normalMatrix[to][from] = Math.min(normalMatrix[to][from], normalCost);
            discountedMatrix[from][to] = Math.min(discountedMatrix[from][to], discountedCost);
            discountedMatrix[to][from] = Math.min(discountedMatrix[to][from], discountedCost);
        }
        
        // 플로이드 워셜을 이용해 도시의 모든 순서쌍에 대해 최단 거리 계산
        floydWarshall(normalMatrix);
        floydWarshall(discountedMatrix);
        
        // 내일로 티켓 구매 여부에 따른 총 비용 계산
        long normalCost = getTotalCost(normalMatrix);
        long nailRoCost = 2 * R + getTotalCost(discountedMatrix); // 2배로 계산하는 운임료에 맞춰서 내일로 티켓 가격도 2배로 계산
        System.out.println(normalCost > nailRoCost ? "Yes" : "No");
    }

    static void floydWarshall(int[][] matrix) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    matrix[j][k] = Math.min(matrix[j][k], matrix[j][i] + matrix[i][k]);
                }
            }
        }
    }

    static long getTotalCost(int[][] matrix) {
        long totalCost = 0L;
        for (int i = 1; i < M; i++) {
            int from = tourRoute[i - 1];
            int to = tourRoute[i];
            totalCost += matrix[from][to];
        }
        return totalCost;
    }
}
