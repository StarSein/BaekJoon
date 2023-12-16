import java.io.*;
import java.util.*;


public class Main {

    static final int MAX_POINT = 10_000;
    static int R;
    static int C;
    static int N;
    static int answer;
    static int[] points;
    static int[] adjacentPoints;
    static int[] pointPrefixSums;
    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
    static int[] chosenR = new int[3];
    static int[] chosenC = new int[3];
    static int[][] grid;

    public static void main(String[] args) throws Exception {
        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        br.readLine(); // 입력의 두 번째 줄은 풀이 과정에 불필요하므로 저장하지 않는다
        adjacentPoints = Arrays.stream(br.readLine().split(" "))
                .map(Integer::valueOf)
                .sorted(Comparator.reverseOrder())
                .mapToInt(Integer::intValue)
                .toArray();
        grid = new int[R][];
        for (int r = 0; r < R; r++) {
            grid[r] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        // 좌표별 점수들을 따로 리스트에 저장
        ArrayList<Integer> pointList = new ArrayList<>();
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                pointList.add(grid[r][c]);
            }
        }

        points = pointList.stream()
                .sorted(Comparator.reverseOrder())
                .mapToInt(Integer::intValue)
                .toArray();
        for (int i = 0; i < N; i++) {
            int point = points[i];

            // 시너지를 반영하지 않았을 때의 점수 최댓값 계산
            answer += point;
        }

        // 내림차순된 점수 리스트의 누적합 계산
        pointPrefixSums = new int[points.length + 1];

        for (int i = 0; i < points.length; i++) {
            pointPrefixSums[i + 1] += pointPrefixSums[i] + points[i];
        }

        // 모든 좌표에 대해
        // 우선 해당 좌표를 삼총사 중 한 명을 배치할 지점으로 삼는다
        for (int r = 0; r < R; r++) {
            chosenR[0] = r;
            for (int c = 0; c < C; c++) {
                chosenC[0] = c;
                // 인접한 좌표들 중 한 좌표를 선택하거나 두 좌표를 선택하여 나머지 삼총사를 배치했을 때 매력의 최댓값 계산한다
                for (int d1 = 0; d1 < dr.length; d1++) {
                    int nr1 = r + dr[d1];
                    int nc1 = c + dc[d1];
                    if (isOutOfGrid(nr1, nc1)) {
                        continue;
                    }
                    chosenR[1] = nr1;
                    chosenC[1] = nc1;
                    answer = Math.max(answer, getTotalPoint(2));
                    for (int d2 = 0; d2 < dr.length; d2++) {
                        if (d1 == d2) {
                            continue;
                        }
                        int nr2 = r + dr[d2];
                        int nc2 = c + dc[d2];
                        if (isOutOfGrid(nr2, nc2)) {
                            continue;
                        }
                        chosenR[2] = nr2;
                        chosenC[2] = nc2;
                        answer = Math.max(answer, getTotalPoint(3));
                    }
                }
            }
        }

        System.out.println(answer);
    }

    static boolean isOutOfGrid(int r, int c) {
        return r < 0 || r >= R || c < 0 || c >= C;
    }

    static int getTotalPoint(int numChosen) {
        int totalPoint = 0;
        
        // 인접한지 여부에 따라 추가 점수 계산
        int adjacentCount = 0;
        for (int i = 1; i < numChosen; i++) {
            for (int j = 0; j < i; j++) {
                if (isAdjacent(i, j)) {
                    adjacentCount++;
                }
            }
        }
        for (int i = 0; i < adjacentCount; i++) {
            totalPoint += adjacentPoints[i];
        }

        // 삼총사가 있는 위치의 점수를 내림차순으로 정렬한다
        Integer[] chosenPoint = new Integer[numChosen];
        for (int i = 0; i < numChosen; i++) {
            int r = chosenR[i];
            int c = chosenC[i];
            chosenPoint[i] = grid[r][c];
        }
        Arrays.sort(chosenPoint, Comparator.reverseOrder());

        // 삼총사의 점수가 상위 (N - numChosen)번째 이내의 높은 점수라면
        // 누적합 구간의 길이를 1만큼 증가시킨다
        // 그렇지 않을 경우, 그 점수를 직접 더한다
        int length = N - numChosen;
        int cutLine = (length == 0) ? MAX_POINT + 1 : points[length - 1];
        for (int point : chosenPoint) {
            if (point >= cutLine) {
                cutLine = points[++length - 1];
            } else {
                totalPoint += point;
            }
        }
        totalPoint += pointPrefixSums[length];

        return totalPoint;
    }
    
    static boolean isAdjacent(int i, int j) {
        return Math.abs(chosenR[i] - chosenR[j]) <= 1 && Math.abs(chosenC[i] - chosenC[j]) <= 1;
    }
}
