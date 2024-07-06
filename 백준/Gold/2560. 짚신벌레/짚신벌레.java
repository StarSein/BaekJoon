import java.util.*;

/*
성체가 되기 전의 개체를 q1, 성체를 q2, 개체를 만들지 않는 개체를 q3 에 저장한다
각 큐의 원소는 (탄생 시점, 개수)로 한다
이러면 전체 시간 복잡도는 O(N)이 된다
 */

public class Main {

    static class BugGroup {
        int birthTime, number;

        BugGroup(int birthTime, int number) {
            this.birthTime = birthTime;
            this.number = number;
        }
    }
    static int a, b, d, N, cnt1, cnt2, cnt3;
    static ArrayDeque<BugGroup> q1 = new ArrayDeque<>();
    static ArrayDeque<BugGroup> q2 = new ArrayDeque<>();
    static ArrayDeque<BugGroup> q3 = new ArrayDeque<>();

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        Scanner sc = new Scanner(System.in);
        a = sc.nextInt();
        b = sc.nextInt();
        d = sc.nextInt();
        N = sc.nextInt();

        int time = 0;
        q1.offerLast(new BugGroup(0, 1));
        cnt1 = 1;
        while (++time <= N) {
            // 0일부터 N일까지 시간을 경과시키면서 짚신벌레의 생애 주기를 관리한다
            // q1의 최상위 원소가 성체가 되면 q2로 보낸다
            if (!q1.isEmpty() && q1.peekFirst().birthTime + a == time) {
                BugGroup moving = q1.pollFirst();
                cnt1 = (cnt1 - moving.number + 1000) % 1000;
                q2.offerLast(moving);
                cnt2 = (cnt2 + moving.number) % 1000;
            }

            // q2의 최상위 원소가 더 이상 번식을 안 하게 되면 q3로 보낸다
            if (!q2.isEmpty() && q2.peekFirst().birthTime + b == time) {
                BugGroup moving = q2.pollFirst();
                cnt2 = (cnt2 - moving.number + 1000) % 1000;
                q3.offerLast(moving);
                cnt3 = (cnt3 + moving.number) % 1000;
            }

            // 성체의 개수만큼 q1에 새로운 짚신벌레를 추가한다
            if (cnt2 > 0) {
                q1.offerLast(new BugGroup(time, cnt2));
                cnt1 = (cnt1 + cnt2) % 1000;
            }

            // q3의 최상위 원소가 죽게 되면 제거한다
            if (!q3.isEmpty() && q3.peekFirst().birthTime + d == time) {
                BugGroup dying = q3.pollFirst();
                cnt3 = (cnt3 - dying.number + 1000) % 1000;
            }
        }

        // 모든 짚신벌레의 개체 수 총합을 1000으로 나눈 나머지를 출력한다
        System.out.println((cnt1 + cnt2 + cnt3) % 1000);
    }
}
