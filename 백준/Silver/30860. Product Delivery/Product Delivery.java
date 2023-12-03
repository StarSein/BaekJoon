import java.io.*;
import java.util.*;


public class Main {

    static class Range implements Cloneable {
        int l;
        int m;

        Range(int l, int m) {
            this.l = l;
            this.m = m;
        }

        boolean intersect(Range e) {
            return (this.l <= e.m && e.m <= this.m || this.l <= e.l && e.l <= this.m);
        }

        @Override
        public Range clone() {
            try {
                return (Range) super.clone();
            } catch (CloneNotSupportedException e) {
                throw new AssertionError();
            }
        }
    }
    static int n;
    static Range[] ranges;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        ranges = new Range[n];
        for (int i = 0; i < n; i++) {
            String[] tokens = br.readLine().split(" ");
            ranges[i] = new Range(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
        }

        int answer = 1;
        Range prevRange = ranges[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            // 오른쪽 끝에서부터 왼쪽으로 순회하면서
            Range curRange = ranges[i];
            if (curRange.intersect(prevRange)) {
                // 새로 만난 범위가 기존 범위와 겹친다면 l을 최댓값으로 갱신, m을 최솟값으로 갱신
                prevRange.l = Math.max(prevRange.l, curRange.l);
                prevRange.m = Math.min(prevRange.m, curRange.m);
                continue;
            }
            if (curRange.l > prevRange.m) {
                // 겹치지 않지만 기존 범위보다 왼쪽에 위치한다면 l과 m을 새로 만난 범위로 대체
                // 겹치지 않으면서 기존 범위보다 오른쪽에 위치한다면 배송 횟수를 1만큼 증가시키고 l과 m을 새로 만난 범위로 대체
                answer++;
            }
            prevRange = curRange.clone();
        }

        System.out.println(answer);
    }
}
