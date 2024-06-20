import java.io.*;
import java.util.*;


public class Main {

    static class Canvas {
        int l, r, cnt;

        Canvas(int l, int r, int cnt) {
            this.l = l;
            this.r = r;
            this.cnt = cnt;
        }
    }
    static int n, p;
    static Canvas[] canvases;
    static int[] pegs;
    static HashSet<Integer> pegSet = new HashSet<>();
    static ArrayList<Integer> newPegs = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        canvases = new Canvas[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            canvases[i] = new Canvas(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0);
        }
        p = Integer.parseInt(br.readLine());
        if (p > 0) {
            pegs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int x : pegs) {
                pegSet.add(x);
            }
        } else {
            pegs = new int[0];
        }

        /*
        1. 펙의 배열을 순회하면서 영향을 받는 캔버스에 대해 cnt++
        */
        int i = 0, j = 0;
        while (i < pegs.length && j < canvases.length) {
            int x = pegs[i];
            Canvas cur = canvases[j];
            if (x < cur.l) {
                i++;
            } else if (x < cur.r) {
                cur.cnt++;
                i++;
            } else if (x == cur.r) {
                cur.cnt++;
                j++;
            } else {
                j++;
            }
        }

        /*
        2. 캔버스의 배열을 순회하면서 새로운 펙을 최소한으로 추가
            cur.cnt > 2 이면 "impossible" 출력 후 종료
            cur.cnt < 2 일 때
            1) prev.r < cur.l 이면
                cur.l <= x < cur.r 인 좌표 중 펙이 없는 곳에 부족분만큼 추가
            2) prev.r == cur.l 이면
                prev.cnt == 2 일 경우, 1)과 같이 처리
                prev.cnt < 2 이고 set에 cur.l이 없는 경우, 새로운 펙의 마지막 원소 제거, cur.l을 추가
                그리고 cur.l < x < cur.r 인 좌표 중 펙이 없는 곳에 (부족분 - 1)만큼 추가
         */
        Canvas prev = new Canvas(-11, -1, 2);
        for (Canvas cur : canvases) {
            if (cur.cnt > 2) {
                System.out.println("impossible");
                return;
            }
            if (cur.cnt == 2) {
                continue;
            }
            if (prev.r == cur.l && prev.cnt < 2 && !pegSet.contains(cur.l)) {
                newPegs.remove(newPegs.size() - 1);
                newPegs.add(cur.l);
                cur.cnt++;
            }
            int lack = 2 - cur.cnt;
            for (int x = cur.l + 1; x < cur.r && lack > 0; x++) {
                if (pegSet.contains(x)) {
                    continue;
                }
                newPegs.add(x);
                lack--;
            }
            prev = cur;
        }

        // 정답을 출력한다
        StringBuilder sb = new StringBuilder();
        sb.append(newPegs.size()).append('\n');
        newPegs.forEach(e -> sb.append(e).append(' '));
        System.out.println(sb);
    }
}
