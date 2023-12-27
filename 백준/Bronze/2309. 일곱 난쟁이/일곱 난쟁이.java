import java.io.*;
import java.util.*;
import java.util.stream.IntStream;


public class Main {

    static int[] heights;

    public static void main(String[] args) throws Exception {
        // 입력 받고 오름차순 정렬하기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        heights = IntStream.generate(() -> {
                    try {
                        return Integer.parseInt(br.readLine());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                .limit(9)
                .sorted()
                .toArray();

        // 아홉 난쟁이 키의 합 구해놓기
        int heightSum = IntStream.of(heights).sum();

        // 일곱 난쟁이에 포함시키지 않을 난쟁이 2명을 뽑는 9C2 가지 경우의 수에 대해 완전 탐색
        for (int i = 1; i < heights.length; i++) {
            heightSum -= heights[i];
            for (int j = 0; j < i; j++) {
                if (heightSum - heights[j] == 100) {
                    final int I = i;
                    final int J = j;

                    Arrays.stream(heights)
                            .filter(e -> e != heights[I] && e != heights[J])
                            .forEach(System.out::println);

                    return;
                }
            }
            heightSum += heights[i];
        }
    }
}
