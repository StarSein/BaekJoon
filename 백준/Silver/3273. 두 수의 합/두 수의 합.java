import java.io.*;
import java.util.*;


public class Main {

    static int n, x;
    static int[] a;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        a = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        x = Integer.parseInt(br.readLine());

        // 배열 a를 오름차순으로 정렬한다
        Arrays.sort(a);

        // 투 포인터를 이용해 두 수의 합이 x인 쌍의 개수를 센다
        int answer = 0;
        int s = 0;
        int e = n - 1;
        while (s < e) {
            int sum = a[s] + a[e];
            if (sum == x) {
                answer++;
                s++;
                e--;
            } else if (sum > x) {
                e--;
            } else {
                s++;
            }
        }

        // 개수를 출력한다
        System.out.println(answer);
    }
}
