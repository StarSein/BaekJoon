import java.io.*;
import java.util.*;

/*
S_(i,j)는 i에 대한 증가수열이다
b_i 또한 i에 대한 증가수열이다
S_(i,0) + S_(i,1) + ... S_(i,p-1) = N 이 성립한다

ex1) YES
{1, 2, 3}

ex2) NO
{1, 3} 다음에 오는 원소는 짝수여야 하는데 그러면 b는 {1 2 2}가 될 수 없다 -- [x]
{1 2 2 2}까지는 되지만 그 다음은 3일 수밖에 없다 -- [o]

ex3) YES
{1, 2, 3}

ex4) NO
{1, 3, 5} 다음에 오는 원소는 짝수여야 하는데 그러면 b는 {1 2 3 2}가 될 수 없다 -- [x]
{1 2 3}까지는 되지만 그 다음은 3 이상이어야 한다 -- [o]

Sol 1)
수열 b의 첫 번째 항부터 맞춰 본다
나머지 값 j마다 해당하는 원소의 개수를 관리한다
원소의 개수에 대해서도 해당하는 나머지 값의 목록을 관리한다
b가 증가할 경우 현재의 원소 개수 최댓값에 대응되는 나머지 값 중 하나의 원소 개수를 1만큼 증가시킨다
b가 일정할 경우 현재의 원소 개수 최댓값이 변하지 않는 범위에서 임의의 나머지 값의 원소의 개수를 1만큼 증가시킨다
만약 최댓값이 b의 값과 다를 경우 "NO"를 출력한다

Sol 2)
원소 개수의 최댓값 maxCount 를 관리한다
해당 최댓값을 유지할 수 있는 횟수 spare 를 관리한다
b가 증가할 경우
    maxCount++;
    spare += p - 1;
b가 일정할 경우
    spare--;
spare == 0 이고 b가 일정할 경우 "NO"를 출력한다

--- 노트의 조건 "순열 a는 1부터 N까지 정수가 한 번씩만 사용되는 유한수열"을 간과함 ---

Sol 3)
Sol 2 에 아래 로직을 추가하자

하나의 나머지 값에 해당할 수 있는 원소의 개수 최댓값을 capacity 라고 하자
capacity 는 (N % p == 0 ? N / p : 1 + N / p)
maxCount > capacity 가 되면 "NO"를 출력한다
 */

public class Main {

    static int N, p, capacity;
    static int[] bArr;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());
        bArr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        // 수열 b 원소의 최댓값이 capacity 를 초과할 경우 "NO"를 출력한다
        capacity = N / p + (N % p == 0 ? 0 : 1);
        for (int cur : bArr) {
            if (cur > capacity) {
                System.out.println("NO");
                return;
            }
        }

        // 수열 b가 증가폭이 1 이하인 단조증가수열이 아닐 경우 "NO"를 출력한다
        int prev = 0;
        for (int cur : bArr) {
            if (cur == prev + 1) {
                prev = cur;
            } else if (cur != prev) {
                System.out.println("NO");
                return;
            }
        }

        // 상단 주석의 Sol 2 에 따라 최종 검증을 한다
        int maxCount = 0;
        long capacity = 0;
        for (int cur : bArr) {
            if (cur == maxCount) {
                if (capacity-- == 0) {
                    System.out.println("NO");
                    return;
                }
            } else {
                maxCount++;
                capacity += p - 1;
            }
        }

        // 조건이 모두 만족되었으므로 "YES"를 출력한다
        System.out.println("YES");
    }
}
