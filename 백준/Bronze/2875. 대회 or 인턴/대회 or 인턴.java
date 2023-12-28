import java.io.*;
import java.util.*;


public class Main {

    static int N, M, K;

    public static void main(String[] args) throws Exception {
        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // K명의 인원 중 남학생의 수를 0부터 min(M, K)까지 다 두면서 최댓값 갱신
        int maxNumTeam = 0;
        int maxNumMale = Math.min(M, K);
        for (int numMale = 0; numMale <= maxNumMale; numMale++) {
            int numFemale = K - numMale;
            if (numFemale > N) {
                continue;
            }
            int curNumTeam = Math.min((N - numFemale) / 2, M - numMale);
            maxNumTeam = Math.max(maxNumTeam, curNumTeam);
        }

        // 정답 출력
        System.out.println(maxNumTeam);
    }
}

