import java.io.*;
import java.util.*;


public class Main {

    static int T, n;
    static int[] x;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            n = Integer.parseInt(br.readLine());
            x = new int[n];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                x[i] = Integer.parseInt(st.nextToken());
            }

            // x좌표 배열을 오름차순으로 정렬한다
            Arrays.sort(x);

            // 임의의 a, c 에 대해 이분 탐색으로 b가 존재하는지를 찾는다
            int answer = 0;
            for (int a = 0; a < n - 2; a++) {
                int Xa = x[a];
                for (int c = a + 2; c < n; c++) {
                    int Xc = x[c];
                    if (((Xa + Xc) & 1) == 1) {
                        continue;
                    }
                    int Xb = (Xa + Xc) / 2;
                    int s = a + 1;
                    int e = c - 1;
                    while (s <= e) {
                        int mid = (s + e) / 2;
                        if (x[mid] == Xb) {
                            answer++;
                            break;
                        } else if (x[mid] > Xb) {
                            e = mid - 1;
                        } else {
                            s = mid + 1;
                        }
                    }
                }
            }
            sb.append(answer).append('\n');
        }

        // 정답을 출력한다
        System.out.print(sb);
    }
}
