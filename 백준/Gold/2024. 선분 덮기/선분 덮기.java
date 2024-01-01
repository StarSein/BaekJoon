import java.io.*;
import java.util.*;


public class Main {

    static class Line {
        int l, r;

        Line(int l, int r) {
            this.l = l;
            this.r = r;
        }
    }

    static int M;
    static ArrayList<Line> lines = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        M = Integer.parseInt(br.readLine());
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            if (l == 0 && r == 0) {
                break;
            }

            if (l > r) {
                int temp = l;
                l = r;
                r = temp;
            }

            lines.add(new Line(l, r));
        }

        // 선들을 l의 오름차순으로 정렬한다
        lines.sort(Comparator.comparingInt(e -> e.l));

        int prevMaxR = 0;
        int maxR = 0;
        int count = 1;
        for (Line cur : lines) {
            // 현재의 r이 최대 r 이하인 경우 건너뛴다
            if (cur.r <= maxR) {
                continue;
            }

            // 최대 r보다 현재의 l이 큰 경우 불가능
            if (maxR < cur.l) {
                System.out.println(0);
                return;
            }

            // 이전의 최대 r보다 현재의 l이 큰 경우 count를 증가시킨다
            if (prevMaxR < cur.l) {
                prevMaxR = maxR;
                count++;
            }

            // 현재의 최대 r을 갱신한다
            maxR = Math.max(maxR, cur.r);

            // 갱신한 최대 r이 M 이상일 경우 반복 중단
            if (maxR >= M) {
                System.out.println(count);
                return;
            }
        }

        // 선들을 연결했을 때 M 미만에서 끝날 경우 불가능
        System.out.println(0);
    }
}
