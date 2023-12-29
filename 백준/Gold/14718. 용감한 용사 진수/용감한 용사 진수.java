// naive 하게 N^3 가지 경우의 수에 대해 이길 수 있는 병사의 수를 세는 경우
// O(N^4)로 시간 초과가 된다
// 병사들을 힘에 대해 오름차순 정렬하자 - O(NlogN)
// K번째 낮은 힘부터 최댓값까지 순회하며 힘을 설정하고 - O(N)
//  해당하는 병사의 민첩을 오름차순 정렬하자 - O(N) * O(NlogN)
//  K번째 낮은 민첩부터 최댓값까지 순회하며 민첩을 설정하고 - O(N) * O(N)
//   해당하는 병사의 지능을 오름차순 정렬하자 - O(N^2) * O(NlogN)
//   K번째 낮은 지능을 설정한다 - O(N^2) * O(1)
// 전체 시간 복잡도는 O(N^3 * logN)


import java.io.*;
import java.util.*;


public class Main {

    static class Soldier {
        final int STR;
        final int DEX;
        final int INT;

        Soldier(int STR, int DEX, int INT) {
            this.STR = STR;
            this.DEX = DEX;
            this.INT = INT;
        }
    }
    static int N, K, answer;
    static Soldier[] soldiers;
    static List<Soldier> strSortedSoldiers, dexSortedSoldiers, intSortedSoldiers;

    public static void main(String[] args) throws Exception {
        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        soldiers = new Soldier[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            soldiers[i] = new Soldier(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            );
        }

        answer = Integer.MAX_VALUE;

        // 병사들을 힘에 대해 오름차순 정렬하자
        strSortedSoldiers = Arrays.asList(soldiers);
        strSortedSoldiers.sort(Comparator.comparingInt(e -> e.STR));

        // K번째 낮은 힘부터 최댓값까지 순회하며 힘을 설정하고
        int strSize = strSortedSoldiers.size();;
        for (int i = K - 1; i < strSize; i++) {
            int myStr = strSortedSoldiers.get(i).STR;

            // 해당하는 병사의 민첩을 오름차순 정렬하자
            dexSortedSoldiers = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                dexSortedSoldiers.add(strSortedSoldiers.get(j));
            }
            dexSortedSoldiers.sort(Comparator.comparingInt(e -> e.DEX));

            // K번째 낮은 민첩부터 최댓값까지 순회하며 민첩을 설정하고
            int dexSize = dexSortedSoldiers.size();
            for (int j = K - 1; j < dexSize; j++) {
                int myDex = dexSortedSoldiers.get(j).DEX;

                // 해당하는 병사의 지능을 오름차순 정렬하자
                intSortedSoldiers = new ArrayList<>();
                for (int k = 0; k <= j; k++) {
                    intSortedSoldiers.add(dexSortedSoldiers.get(k));
                }
                if (intSortedSoldiers.size() < K) {
                    continue;
                }
                intSortedSoldiers.sort(Comparator.comparingInt(e -> e.INT));

                // K번째 낮은 지능을 설정한다
                int myInt = intSortedSoldiers.get(K - 1).INT;

                // 정답을 갱신한다
                answer = Math.min(answer, myStr + myDex + myInt);
            }
        }

        // 정답 출력하기
        System.out.println(answer);
    }
}
