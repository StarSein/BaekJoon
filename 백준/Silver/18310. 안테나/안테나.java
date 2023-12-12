import java.io.*;
import java.util.*;


public class Main {

    static int N;
    static int[] locations;

    public static void main(String[] args) throws Exception {
        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        locations = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            locations[i] = Integer.parseInt(st.nextToken());
        }

        // 집의 위치 정렬하기
        Arrays.sort(locations);

        // 중앙값을 정답으로 출력하기
        int mid = ((N + 1) / 2) - 1;
        System.out.println(locations[mid]);
    }
}
