import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static ArrayDeque<Boolean> dq;
    static long answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dq = new ArrayDeque<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            long c = Long.parseLong(st.nextToken());
            if (c > 0) {
                answer += c;
                dq.add(true);
            } else {
                dq.add(false);
            }
        }

        for (int i = 0; i < N; i++) {
            Boolean curOccupied = dq.pollFirst();
            if (curOccupied) {
                dq.offerFirst(curOccupied);
                break;
            } else {
                dq.offerLast(curOccupied);
            }
        }

        boolean prevOccupied = false;
        for (int i = 0; i < N; i++) {
            Boolean curOccupied = dq.pollFirst();
            if (curOccupied || prevOccupied) {
                prevOccupied = false;
            } else {
                answer++;
                prevOccupied = true;
            }
        }

        System.out.println(answer);
    }
}
