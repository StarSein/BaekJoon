import java.io.*;
import java.util.*;

/*
절대로 왕이 될 수 없는 메기 무게의 임계값이 존재한다
그 값을 찾으면 된다
무게 배열을 순회하면서
지금까지 무게의 총합을 관리한다
무게가 증가하였는지 여부를 관리한다
(무게의 증가가 한 번이라도 있었다면, 현재의 메기는 지금까지 나온 모든 메기를 먹을 수 있다)
현재 메기가 지금까지 먹을 수 있는 모든 메기를 먹는다고 하더라도,
다음 메기를 먹을 수 없게 되면, 해당 메기 이하의 무게를 가진 메기는 모두 최후의 한 마리가 될 수 없다
순회를 계속 반복하면서 해당 값의 최댓값을 구한다
 */

public class Main {

    static int n;
    static int[] weights, sortedWeights;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        weights = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        // 무게 배열을 오름차순으로 정렬한 새로운 배열을 만든다
        sortedWeights = Arrays.copyOf(weights, weights.length);
        Arrays.sort(sortedWeights);

        // 무게 배열을 순회하며 최후 생존 불가 무게의 임계값을 최댓값으로 갱신한다
        int criticalValue = 0;
        long weightSum = 0L;
        boolean ascend = false;
        int minWeight = sortedWeights[0];
        for (int i = 0; i < sortedWeights.length - 1; i++) {
            int curWeight = sortedWeights[i];
            if (curWeight > minWeight) {
                ascend = true;
            }
            weightSum += curWeight;
            if (ascend && weightSum > sortedWeights[i + 1]) {
                continue;
            }
            criticalValue = curWeight;
        }

        // 최댓값을 기준으로 'T', 'N'을 나눠 정답 문자열에 추가한다
        StringBuilder sb = new StringBuilder();
        for (int weight : weights) {
            sb.append(weight > criticalValue ? 'T' : 'N');
        }

        // 정답 문자열을 출력한다
        System.out.println(sb);
    }
}
