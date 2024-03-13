import java.io.*;
import java.util.*;


public class Main {

    static int N, Q;
    static char[] S;
    static ArrayList<Integer> rList, bList;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        S = br.readLine().toCharArray();

        // R과 B의 인덱스들을 각각 저장한다
        rList = new ArrayList<>();
        bList = new ArrayList<>();
        for (int i = 0; i < S.length; i++) {
            char c = S[i];
            if (c == 'R') {
                rList.add(i);
            } else if (c == 'B') {
                bList.add(i);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            // 각 쿼리에 대해
            // 이분 탐색으로 각 문자가 범위 내에서 가질 수 있는 가장 작은 인덱스를 구하는데,
            // 마지막 d의 인덱스가 r 이하일 경우에만 유효하다
            int a = binarySearch(l, rList);
            int b = binarySearch(a + 1, rList);
            int c = binarySearch(b + 1, bList);
            int d = binarySearch(c + 1, bList);

            // 결과를 정답 문자열에 추가한다
            if (d == -2 || d > r) {
                sb.append("-1\n");
            } else {
                sb.append(a + " " + b + " " + c + " " + d + "\n");
            }
        }

        // 정답 문자열을 출력한다
        System.out.print(sb);
    }

    static int binarySearch(int lowerBound, ArrayList<Integer> list) {
        if (lowerBound == -1) {
            return -2;
        }
        int ans = -2;
        int s = 0;
        int e = list.size() - 1;
        while (s <= e) {
            int mid = (s + e) >> 1;
            int idx = list.get(mid);
            if (idx >= lowerBound) {
                ans = idx;
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }
        return ans;
    }
}
