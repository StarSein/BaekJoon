import java.io.*;
import java.util.*;


public class Main {

    static int[] heights;
    static boolean[] isFake;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        heights = new int[9];
        for (int i = 0; i < 9; i++) {
            heights[i] = Integer.parseInt(br.readLine());
        }

        // 난쟁이들 키를 오름차순으로 정렬한다
        Arrays.sort(heights);

        // 난쟁이들 키의 총합을 구한다
        int sum = 0;
        for (int height : heights) {
            sum += height;
        }

        // 두 난쟁이의 키의 합이 (총합 - 100)이 되는 경우의 인덱스를 체크한다
        isFake = new boolean[9];
        int target = sum - 100;
        int s = 0;
        int e = 8;
        while (s < e) {
            int twoSum = heights[s] + heights[e];
            if (twoSum == target) {
                isFake[s] = true;
                isFake[e] = true;
                break;
            } else if (twoSum > target) {
                e--;
            } else {
                s++;
            }
        }

        // 체크되지 않은 인덱스의 난쟁이 키만 출력한다
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            if (isFake[i]) {
                continue;
            }
            sb.append(heights[i]).append('\n');
        }

        System.out.print(sb);
    }
}
