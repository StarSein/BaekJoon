import java.io.*;
import java.util.*;
import java.util.stream.IntStream;


public class Main {

    static int N, M;
    static int[] bucket;

    public static void main(String[] args) throws Exception {
        // 바구니의 초기 상태를 만든다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        bucket = IntStream.rangeClosed(0, N).toArray();

        // 입력을 받을 때마다 공을 교환한다
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            int temp = bucket[i];
            bucket[i] = bucket[j];
            bucket[j] = temp;
        }

        // 바구니의 최종 상태를 출력한다
        StringBuilder sb = new StringBuilder();
        Arrays.stream(bucket).skip(1L).forEach(e -> sb.append(e).append(' '));
        System.out.println(sb);
    }
}
