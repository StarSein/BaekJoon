// 누적합을 만들면서 d로 나눈 나머지가 i인 것의 개수를 counts[i] 에 저장하자
// 그 다음 모든 왼쪽 인덱스에 대해
// 오른쪽에 있는 인덱스들 중 자신과 d로 나눈 나머지가 같은 것들의 개수를 합산하면 된다
// O(CN)만큼의 읽기 쓰기 작업을 HashMap에서 하는 것은 다소 무거우므로,
// 테스트 케이스 간에 초기화 해 줄 원소를 저장하는 ArrayList와 함께 배열을 사용하자
// 그런데 어차피 정답을 합산하는 과정에서 모든 counts[i]의 값이 0까지 차감되므로,
// 초기화를 따로 해주지 않아도 되는구나


import java.io.*;
import java.util.*;


public class Main {

    static int c, d, n;
    static int[] prefixModular, counts = new int[1_000_000];

    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        c = Integer.parseInt(br.readLine());
        for (int t = 0; t < c; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            d = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            prefixModular = new int[n + 1];

            // 입력을 받아서 바로 누적합 배열 만들기
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                prefixModular[i] = (Integer.parseInt(st.nextToken())
                        + prefixModular[i - 1]) % d;
            }

            // 누적합 배열을 순회하면서 counts 값 증가
            for (int mod : prefixModular) {
                counts[mod]++;
            }

            // 현재의 누적합을 d로 나눈 나머지와 같은 나머지를 갖는 누적합의 개수만큼 정답에 합산
            int answer = 0;
            for (int mod : prefixModular) {
                answer += --counts[mod];
            }

            // 정답 문자열에 정답 추가
            sb.append(answer).append('\n');
        }

        // 정답 문자열 출력
        System.out.print(sb);
    }
}
