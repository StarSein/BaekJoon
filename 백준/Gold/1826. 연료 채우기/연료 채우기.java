import java.io.*;
import java.util.*;

public class Main {

    static class Station {
        int x;
        int fuel;

        Station(int x, int fuel) {
            this.x = x;
            this.fuel = fuel;
        }
    }
    static int N, L, P, answer;
    static Station[] arr;
    static PriorityQueue<Integer> pq;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new Station[N + 1];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i] = new Station(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        arr[N] = new Station(L, 0);

        Arrays.sort(arr, Comparator.comparingInt(e -> e.x));

        pq = new PriorityQueue<>(Comparator.reverseOrder());

        for (Station station : arr) {
            while (P < station.x) {
                if (pq.isEmpty()) {
                    System.out.println(-1);
                    return;
                } else {
                    P += pq.poll();
                    answer++;
                }
            }
            pq.offer(station.fuel);
        }

        System.out.println(answer);
    }

}
