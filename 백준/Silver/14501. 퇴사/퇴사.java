import java.io.*;
import java.util.*;


public class Main {

    static int N;
    static int[] T, P;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        T = new int[N];
        P = new int[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }

        // 상담을 선택하는 2^N가지 경우의 수 중 가능한 경우에 한해서 수익의 최댓값을 출력한다
        System.out.println(subset(0, 0, 0));
    }

    static int subset(int curDay, int ableDay, int totalProfit) {
        if (curDay == N) {
            return totalProfit;
        }
        return Math.max(
                subset(curDay + 1, ableDay, totalProfit),
                (ableDay > curDay || curDay + T[curDay] > N) ? 
                        0 : 
                        subset(curDay + 1, curDay + T[curDay], totalProfit + P[curDay])
        );
    }
}
