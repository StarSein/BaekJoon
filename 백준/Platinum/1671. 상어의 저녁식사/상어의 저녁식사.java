/*
(포식자, 피식자)의 관계를 유향 간선으로 나타내면
방향 비순환 그래프가 그려진다
따라서 이분 매칭이 이뤄질 수 있다

이분 매칭이 이뤄지기만 하면
먹이 사슬의 가장 하위에 있는 피식자부터 처리할 수 있으므로
잡아먹힌 상어의 최댓값은 이분 매칭의 크기와 같다
 */


import java.io.*;
import java.util.*;


public class Main {

    static class Shark implements Comparable<Shark> {
        int size, speed, intellect;

        Shark(int size, int speed, int intellect) {
            this.size = size;
            this.speed = speed;
            this.intellect = intellect;
        }

        @Override
        public int compareTo(Shark s) {
            if (size != s.size) return s.size - size;
            if (speed != s.speed) return s.speed - speed;
            return s.intellect - intellect;
        }
    }
    static int N;
    static Shark[] sharks;
    static ArrayList<Integer>[] preys;
    static int[] predator;
    static boolean[] checked;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        sharks = new Shark[N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            sharks[i] = new Shark(Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()));
        }

        // 능력치가 서로 같은 상어가 서로를 잡아먹는 경우를 배제하기 위해
        // 상어들을 능력치의 내림차순으로 정렬해 놓는다
        Arrays.sort(sharks);

        // 각 상어마다, 잡아먹을 수 있는 상어를 리스트에 저장한다
        preys = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            preys[i] = new ArrayList<>();
            Shark a = sharks[i];
            for (int j = i + 1; j < N; j++) {
                Shark b = sharks[j];
                if (a.size >= b.size && a.speed >= b.speed && a.intellect >= b.intellect) {
                    preys[i].add(j);
                }
            }
        }

        // 각 상어에 대해 잡아먹을 상어를 최대 1개 배정한다
        predator = new int[N];
        Arrays.fill(predator, -1);
        checked = new boolean[N];
        int answer = N;
        for (int i = 0; i < N; i++) {
            Arrays.fill(checked, false);
            if (isAble(i)) {
                answer--;
            }
        }

        // 각 상어에 대해 잡아먹을 상어를 추가로 최대 1개 배정한다
        for (int i = 0; i < N; i++) {
            Arrays.fill(checked, false);
            if (isAble(i + N)) {
                answer--;
            }
        }

        // (전체 상어 수 - 잡아먹힌 상어 수)를 출력한다
        System.out.println(answer);
    }

    static boolean isAble(int x) {
        ArrayList<Integer> curPreys = (x >= N ? preys[x - N] : preys[x]);

        for (int prey : curPreys) {
            if (checked[prey]) {
                continue;
            }
            checked[prey] = true;
            if (predator[prey] == -1 || isAble(predator[prey])) {
                predator[prey] = x;
                return true;
            }
        }

        return false;
    }
}
