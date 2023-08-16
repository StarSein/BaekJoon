import java.io.*;
import java.util.*;

public class Main {

    static class Line {
        int s, e, c;

        Line(int s, int e, int c) {
            this.s = s;
            this.e = e;
            this.c = c;
        }
    }
    static int N;
    static Line[] lines;
    static List<Line> answer = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        lines = new Line[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            lines[i] = new Line(s, e, c);
        }

        Arrays.sort(lines, Comparator.comparingInt(line -> line.s));

        Line prev = lines[0];
        for (int i = 1; i < N; i++) {
            Line cur = lines[i];
            if (prev.e < cur.s) {
                answer.add(prev);
                prev = cur;
            } else {
                prev.e = Math.max(prev.e, cur.e);
                prev.c = Math.min(prev.c, cur.c);
            }
        }
        answer.add(prev);

        StringBuilder sb = new StringBuilder();
        sb.append(answer.size()).append('\n');
        answer.forEach(line -> sb.append(line.s).append(' ').append(line.e).append(' ').append(line.c).append('\n'));
        System.out.println(sb);
    }
}
