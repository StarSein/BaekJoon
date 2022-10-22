import java.util.*;
import java.io.*;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        int[] days = {0, 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30};
        for (int m = 2; m <= 12; m++) {
            days[m] += days[m - 1];
        }

        String[] ans = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
        
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(ans[(days[x] + y) % 7]));
        bw.flush();
        return;
    }
}
