// 수가 3개만 주어지고 A, B, A+B 에 해당하는 상황을 가정해 보자
// 이때 C, B+C, C+A, A+B+C 중 한 개의 수만 주어지더라도 C의 값을 확인 및 검증할 수 있다
// 이를 일반화하면, 수가 어떻게 주어지든 4개가 주어지면,
// 방정식은 4개, 미지수는 3개가 되므로 3개의 수 모두를 확인하고
// 1 <= A <= B <= C 라는 조건과 연립방정식에 대입 검증해 볼 수 있다

// 조건에 의해 A <= B <= (A+B) or C <= (C+A) <= (B+C) <= (A+B+C) 가 성립한다
// (A+B)와 C 사이의 대소 관계가 불명확하므로 모든 경우의 수를 조합으로 따지는 일은 다소 복잡하다
// perm(7, N)으로도 경우의 수가 충분히 적으므로 순열로 경우의 수를 따지자

// 연립 방정식의 소거법을 일반적으로 적용하기 위해 이진수 인덱스와 이진 연산을 사용하면
// 짧은 코드로 구현이 가능하겠지만, 복잡하고 실수할 여지가 크다
// 따라서 다소 길어지더라도 일일이 조건 분기를 하자


import java.io.*;
import java.util.*;


public class Main {

    static class Triple {
        final long A, B, C;

        Triple(long A, long B, long C) {
            this.A = A;
            this.B = B;
            this.C = C;
        }

        @Override
        public boolean equals(Object e) {
            if (e instanceof Triple) {
                Triple t = (Triple) e;
                return A == t.A && B == t.B && C == t.C;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(A, B, C);
        }
    }
    static int T, N;
    static long[] x, chosenValues, values;
    static HashSet<Triple> tripleSet;

    public static void main(String[] args) throws Exception {
        // 각 테스트케이스마다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int tc = 0; tc < T; tc++) {
            // 입력을 받는다
            N = Integer.parseInt(br.readLine());
            x = new long[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                x[i] = Integer.parseInt(st.nextToken());
            }

            // 입력받은 수를 배정하는 perm(7, N) 가지 경우의 수 중
            // 논리적으로 오류가 없는 케이스의 개수를 정답 문자열에 추가한다
            tripleSet = new HashSet<>();
            chosenValues = new long[7];
            findAllTriple(0);
            sb.append(tripleSet.size()).append('\n');
        }

        // 정답 문자열을 출력한다
        System.out.print(sb);
    }

    static void findAllTriple(int idx) {
        if (idx == N) {
            if (valid()) {
                Triple triple = new Triple(values[0], values[1], values[2]);
                tripleSet.add(triple);
            }
            return;
        }

        for (int i = 0; i < 7; i++) {
            if (chosenValues[i] == 0) {
                chosenValues[i] = x[idx];
                findAllTriple(idx + 1);
                chosenValues[i] = 0;
            }
        }
    }

    static boolean valid() {
        // values = {A, B, C, A+B, B+C, C+A, A+B+C}
        values = Arrays.copyOf(chosenValues, 7);

        // (A, B), (B, C), (C, A) 중 둘 다 배정된 순서쌍이 있다면 각각 A+B, B+C, C+A 와 교차검증 및 할당한다
        // A+B, B+C, C+A 중 배정된 것이 있다면 각각 (A, B), (B, C), (C, A) 와 교차검증 및 할당한다
        return crossCheckOrAssign(3, 0, 1) &&
                crossCheckOrAssign(4, 1, 2) &&
                crossCheckOrAssign(5, 2, 0) &&
        // (A, B+C), (B, C+A), (C, A+B) 중 둘 다 배정된 순서쌍이 있다면 모두 A+B+C 와 교차검증 및 할당한다
        // A+B+C 에 배정된 값이 있다면 (A, B+C), (B, C+A), (C, A+B) 와 교차검증 및 할당한다
                crossCheckOrAssign(6, 0, 4) &&
                crossCheckOrAssign(6, 1, 5) &&
                crossCheckOrAssign(6, 2, 3) &&
        // 1 <= A <= B <= C && A + B + C == (A+B+C) 를 만족하는지 검증한다
                1 <= values[0] && values[0] <= values[1] && values[1] <= values[2] && 
                values[0] + values[1] + values[2] == values[6];
    }

    // values[p] + values[q] == values[pq] 가 성립해야 한다
    // 오류가 있는 것으로 파악될 경우 false를, 그렇지 않을 경우 true를 반환하는 함수
    static boolean crossCheckOrAssign(int pq, int p, int q) {
        if (values[pq] > 0) {
            if (values[p] < values[q]) {
                int temp = p;
                p = q;
                q = temp;
            }
            if (values[p] > 0) {
                if (values[q] > 0) {
                    return values[p] + values[q] == values[pq];
                } else if (values[pq] - values[p] > 0) {
                    values[q] = values[pq] - values[p];
                } else {
                    return false;
                }
            }
        } else if (values[p] > 0 && values[q] > 0) {
            values[pq] = values[p] + values[q];
        }
        return true;
    }
}
