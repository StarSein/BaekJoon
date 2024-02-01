// perm(n, 5) 가지 경우의 수에서 탐색 대상을 줄여야 한다
// 역할군이 2개만 있는 상황을 가정하자
// (1) 딱 하나의 역할군이라도 일단 최댓값으로 고정해 놓아도 되는가?
// 2
// 10 7
// 8  4
// 위와 같은 입력이 주어진다고 할 때 A 역할군을 10으로 고정하면 총합은 14로 최대가 아니다
// 따라서 안 된다
// (2) 역할군 2개에 대해서만 완전 탐색을 한다고 해도 제한 시간을 초과할 것이다
// (3) 역할군별로 탐색 대상을 줄일 수 있지 않을까?
// 최적으로 팀을 구성했을 때 어느 한 역할군에 6번째로 큰 실력을 가진 후보자가 배정될 수 있을까?
// 해당 역할군을 제외한 4개의 역할군에 1~4번째로 큰 실력을 가진 후보자가 배정되었다고 하더라도,
// 5번째로 큰 실력을 가진 후보자를 배정할 기회가 있기 때문에,
// 어느 역할군이든 6번째로 큰 실력을 가진 후보자가 배정된다면 그것은 최적의 팀 구성이 아니다.
// 따라서 역할군별로 1~5번째로 큰 실력을 가진 후보자로 탐색 대상을 한정해도 논리적으로 정합하다
// 이때 탐색하게 되는 경우의 수는 5^5 = 625 * 25 < 21000 으로 모든 케이스를 다 탐색해도 충분하다


import java.io.*;
import java.util.*;


public class Main {

    static class Ability implements Comparable<Ability> {
        int id, ability;

        Ability(int id, int ability) {
            this.id = id;
            this.ability = ability;
        }

        @Override
        public int compareTo(Ability e) {
            return e.ability - this.ability;
        }
    }
    static int n;
    static Ability[][] abilities;
    static boolean[] selected;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        abilities = new Ability[5][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                abilities[j][i] = new Ability(i, Integer.parseInt(st.nextToken()));
            }
        }

        // 각 역할군별로 실력의 내림차순으로 정렬한다
        for (int i = 0; i < 5; i++) {
            Arrays.sort(abilities[i]);
        }

        // 팀을 구성하는 최대 5^5 가지 경우의 수에 대해 실력 합의 최댓값을 출력한다
        selected = new boolean[n];
        System.out.println(getMaxSum(0, 0));
    }

    static int getMaxSum(int position, int abilitySum) {
        if (position == 5) {
            return abilitySum;
        }
        int maxSum = 0;
        Ability[] curPositionAbility = abilities[position];
        for (int i = 0; i < 5; i++) {
            Ability a = curPositionAbility[i];
            if (selected[a.id]) {
                continue;
            }
            selected[a.id] = true;
            maxSum = Math.max(maxSum, getMaxSum(position + 1, abilitySum + a.ability));
            selected[a.id] = false;
        }
        return maxSum;
    }
}
