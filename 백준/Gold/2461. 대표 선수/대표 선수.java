import java.io.*;
import java.util.*;

public class Main {

    static class Student {
        int room;
        int stat;

        Student(int room, int stat) {
            this.room = room;
            this.stat = stat;
        }
    }

    static int N, M, num;
    static Student[] arr;
    static int[] cnt;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new Student[N * M];
        for (int i = 0, idx = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[idx++] = new Student(i, Integer.parseInt(st.nextToken()));
            }
        }
        Arrays.sort(arr, Comparator.comparingInt(e -> e.stat));

        int lp = 0;
        int rp = -1;
        int answer = Integer.MAX_VALUE;
        cnt = new int[N];
        while (lp < arr.length) {
            if (num < N && rp + 1 < arr.length) {
                if (++cnt[arr[++rp].room] == 1) {
                    if (++num == N) {
                        answer = Math.min(answer, arr[rp].stat - arr[lp].stat);
                    }
                }
            } else {
                if (--cnt[arr[lp++].room] == 0) {
                    --num;
                }
                if (num == N) {
                    answer = Math.min(answer, arr[rp].stat - arr[lp].stat);
                }
            }
        }
        System.out.println(answer);
    }
}
