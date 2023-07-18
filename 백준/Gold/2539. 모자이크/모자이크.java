import java.io.*;
import java.util.*;


public class Main {

    static final int R_LIMIT = 1_000_000;
    static int R, C, paperNum, errorNum, rMax;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        paperNum = Integer.parseInt(br.readLine());
        errorNum = Integer.parseInt(br.readLine());
        arr = new int[errorNum];
        for (int i = 0; i < arr.length; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            rMax = Math.max(rMax, r);
            arr[i] = c;
        }

        Arrays.sort(arr);
        int left = rMax;
        int right = R_LIMIT;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (able(mid)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(left);
    }

    static boolean able(int size) {
        int paperCnt = 0;
        int curCover = 0;
        for (int c : arr) {
            if (c > curCover) {
                if (++paperCnt > paperNum) {
                    return false;
                }
                curCover = c + size - 1;
            }
        }
        return true;
    }
}
