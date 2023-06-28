import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int C = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < C; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int[] arr = new int[N];
            for (int j = 0; j < N; j++) {
                arr[j] = Integer.parseInt(st.nextToken());
            }
            int sum = Arrays.stream(arr).sum();
            int cnt = (int) Arrays.stream(arr).filter(score -> score * N > sum).count();

            float answer = ((float) cnt / N) * 100;
            sb.append(String.format("%.3f%c\n", answer, '%'));
        }
        System.out.print(sb);
    }
}
