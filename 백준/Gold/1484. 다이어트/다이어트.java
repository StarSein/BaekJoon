// 제곱수와 제곱수의 차이가 100000 이하가 되는 선에서 제곱수를 만든다면 몇 개까지 만들어야 하는가?
// x^2 - (x-1)^2 = 2x - 1 <= 100000
// 최대 5만 개만 만들면 된다


import java.io.*;
import java.util.*;


public class Main {

    static int G;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        G = new Scanner(System.in).nextInt();

        // 투 포인터를 이용해 두 제곱수의 차가 G인 경우를 모두 찾는다
        ArrayList<Integer> weights = new ArrayList<>();
        int size = (G + 1) / 2;
        int s = 1;
        int e = 2;
        while (s < size) {
            long diff = (long) e * e - (long) s * s;
            if (diff == G) {
                weights.add(e);
                s++;
                e++;
            } else if (diff > G) {
                s++;
            } else {
                e++;
            }
        }

        // 찾은 경우에서 현재 몸무게를 모두 출력한다
        if (weights.isEmpty()) {
            System.out.println(-1);
            return;
        }
        StringBuilder sb = new StringBuilder();
        weights.forEach(x -> sb.append(x).append('\n'));
        System.out.print(sb);
    }
}
