import java.io.*;
import java.util.*;


public class Main {

    static int N;
    static int[][] data;
    static int[] counts;
    static int[] answer;

    public static void main(String[] args) throws Exception {
        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        data = new int[3][];
        for (int i = 0; i < 3; i++) {
            data[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        // 세 개의 변경점이 모두 구간 내에 존재하는 길이가 N인 구간에 대해
        // N번째 단계는 항상 '세 데이터 모두 같거나 하나의 데이터만 다르다'
        // 하나의 데이터만 다른 경우
        //  해당 단계에 두 번 나온 데이터를 구간의 마지막에 위치시키고
        //  다른 단계들의 데이터를 그 왼쪽에 순서대로 배치하면 된다
        // 세 데이터가 모두 같은 경우
        //  해당 단계의 세 번 나온 데이터를 구간의 마지막에 위치시키고
        //  길이가 (N - 1)인 구간에 대해 위의 작업을 반복하면 된다
        counts = new int[N + 1];
        answer = new int[N];
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j < 3; j++) {
                int num = data[j][i];
                counts[num]++;
            }

            int[] nums = new int[4];
            for (int j = 0; j < 3; j++) {
                int num = data[j][i];
                int count = counts[num];
                nums[count] = num;
            }

            if (nums[3] == 0) {
                answer[i] = nums[2];

                int targetJ = -1;
                for (int j = 0; j < 3; j++) {
                    if (data[j][i] == nums[1]) {
                        targetJ = j;
                        break;
                    }
                }

                int[] line = data[targetJ];
                int k = 0;
                for (int j = 0; j <= i; j++) {
                    if (line[j] == nums[2]) {
                        continue;
                    }
                    answer[k++] = line[j];
                }
                break;
            }
            answer[i] = nums[3];
        }

        // 원본 데이터 출력
        StringBuilder sb = new StringBuilder();
        for (int num : answer) {
            sb.append(num).append(' ');
        }
        System.out.println(sb);
    }
}
