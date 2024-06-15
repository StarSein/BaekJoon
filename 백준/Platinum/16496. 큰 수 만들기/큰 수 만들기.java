import java.io.*;
import java.util.*;

/*
1. 0만 있는 경우를 따로 처리하자
[x] 2. 수들을 사전 순으로 내림차순 정렬하여 순서대로 배치한다
       단, "10" > "1" 과 같이 맨 뒤에 "0"이 붙는 것이 더 앞에 오는 것에 유의해야 한다
       -> 문자열로된 수들을 뒤집은 다음 오름차순 정렬하고,
          오른쪽 문자열의 마지막 문자부터 왼쪽 방향으로 가면서 하나씩 나열하면 된다
       반례) 10 100 1004 // https://www.acmicpc.net/board/view/91123

[o] 2.  705 70 -> 70 이 앞으로 와야 함
        709 70 -> 70 이 뒤로 와야 함
        두 수 A, B 를 사전 순으로 비교하되,
        A가 B의 접두사일 경우
        [x] 해당 부분을 제외한 B의 시작 숫자가 A의 시작 숫자보다 클 경우 B를 앞으로,
            작을 경우 A를 앞으로 보낸다
            반례) 21421 214 // https://www.acmicpc.net/board/view/96921
        [o] 시작 숫자가 동일할 경우, 그 이후까지 비교해야 한다
 */

public class Main {

    static class Num implements Comparable<Num> {
        char[] data;

        Num(char[] data) {
            this.data = data;
        }

        @Override
        public int compareTo(Num o) {
            // 용이한 구현을 위해 길이가 더 짧은 것과 긴 것을 구분한다
            boolean swapped;
            char[] small;
            char[] big;
            if (this.data.length > o.data.length) {
                swapped = true;
                small = o.data;
                big = this.data;
            } else {
                swapped = false;
                small = this.data;
                big = o.data;
            }

            // 두 수의 숫자가 달라지는 첫 지점을 찾는다
            // 해당 지점에서 숫자가 더 큰 수를 앞으로 보낸다
            for (int i = 0; i < small.length; i++) {
                if (small[i] != big[i]) {
                    if (small[i] > big[i]) {
                        return swapped ? 1 : -1;
                    } else {
                        return swapped ? -1 : 1;
                    }
                }
            }

            // 두 수가 서로 일치하는 경우
            if (small.length == big.length) {
                return 0;
            }

            // 둘 중 하나가 접두사일 경우 (두 수는 A, B 이고 B = AC 라고 할 때)
            // 달라지는 지점이 나올 때까지 계속 비교하고 더 큰 값이 나오는 수를 앞으로 보낸다
            for (int i = 0, j = small.length; j < big.length; i = (i + 1) % small.length, j++) {
                if (small[i] != big[j]) {
                    if (small[i] > big[j]) {
                        return swapped ? 1 : -1;
                    } else {
                        return swapped ? -1 : 1;
                    }
                }
            }

            // A가 B에서 반복되는 경우
            // 두 문자열을 합치는 두 가지 방법 중 더 큰 값을 도출하는 방법을 따른다
            char[] smallBig = new char[small.length + big.length];
            char[] bigSmall = new char[small.length + big.length];
            for (int i = 0; i < small.length; i++) {
                smallBig[i] = bigSmall[big.length + i] = small[i];
            }
            for (int i = 0; i < big.length; i++) {
                smallBig[small.length + i] = bigSmall[i] = big[i];
            }
            long smallBigNum = Long.parseLong(String.valueOf(smallBig));
            long bigSmallNum = Long.parseLong(String.valueOf(bigSmall));
            if (smallBigNum > bigSmallNum) {
                return swapped ? 1 : -1;
            } else {
                return swapped ? -1 : 1;
            }
        }

        @Override
        public String toString() {
            return String.valueOf(this.data);
        }

        public boolean isZero() {
            return this.data.length == 1 && this.data[0] == '0';
        }
    }
    static int N;
    static Num[] nums;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        nums = new Num[N];
        for (int i = 0; i < N; i++) {
            nums[i] = new Num(st.nextToken().toCharArray());
        }

        // 0만 있는 경우 "0"을 출력한다
        boolean onlyZero = true;
        for (Num num : nums) {
            if (!num.isZero()) {
                onlyZero = false;
            }
        }
        if (onlyZero) {
            System.out.println("0");
            return;
        }

        // Num 의 배열을 정렬한다
        Arrays.sort(nums, Comparator.naturalOrder());

        // 정렬된 순서대로 정답 문자열에 추가한 뒤 출력한다
        StringBuilder sb = new StringBuilder();
        for (Num num : nums) {
            sb.append(num);
        }
        System.out.println(sb);
    }
}
