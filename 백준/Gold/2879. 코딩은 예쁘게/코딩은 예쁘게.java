import java.io.*;
import java.util.*;


public class Main {

    static int N;
    static int[] currents; // currents[i]: i번째 줄의 현재 탭 개수
    static int[] diffs;    // diffs[i]: i번째 줄을 올바르게 만들기 위해 제거해야 하는 탭의 개수
    static ArrayList<ArrayList<Integer>> intervals;

    public static void main(String[] args) throws Exception {
        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        currents = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            currents[i] = Integer.parseInt(st.nextToken());
        }
        diffs = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            diffs[i] = currents[i] - Integer.parseInt(st.nextToken());
        }
        // diffs 배열을 윗줄부터 순회하면서
        // 부호가 바뀔 때마다 구간을 나눠주기
            // 근거: (양수 - 음수 - 양수 구간에서 한 번에 탭을 제거한다고 해도, 음수 구간에서 손해를 본다.
            //       이는 각각의 양수 구간에서 한 번씩, 총 두 번 탭을 제거하는 것과 동일하다)
        // 각 구간을 절댓값 처리한다
        intervals = new ArrayList<>();
        ArrayList<Integer> interval = new ArrayList<>(Collections.singletonList(Math.abs(diffs[0])));
        boolean isPositive = diffs[0] >= 0;
        for (int i = 1; i < N; i++) {
            int diff = diffs[i];
            if (isPositive == (diff >= 0)) {
                interval.add(Math.abs(diff));
            } else {
                intervals.add(interval);
                interval = new ArrayList<>(Collections.singletonList(Math.abs(diff)));
            }
            isPositive = (diff >= 0);
        }
        intervals.add(interval);


        int answer = 0;
        // 각 구간을 편집한다
        for (ArrayList<Integer> itv : intervals) {
            int prevAbsDiff = 0;
            for (int curAbsDiff : itv) {
                // 구간 내에서 윗줄부터 순회하면서
                if (prevAbsDiff > curAbsDiff) {
                    // 현재 값보다 낮은 값이 나오면 그 차이만큼 편집 회수 추가
                    answer += prevAbsDiff - curAbsDiff;
                }
                // 현재 값보다 높은 값이 나오면 넘어간다
                prevAbsDiff = curAbsDiff;
            }
            // 마지막에 남은 값만큼 편집 회수 추가
            answer += prevAbsDiff;
        }
        System.out.println(answer);
    }
}
