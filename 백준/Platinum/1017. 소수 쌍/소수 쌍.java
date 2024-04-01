import java.io.*;
import java.util.*;


public class Main {

    static int N;
    static int[] nums, match;
    static ArrayList<Integer>[] partners;
    static boolean[] isPrime, checked;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        isPrime = new boolean[2_000];
        Arrays.fill(isPrime, true);
        for (int i = 2; i < isPrime.length; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j < isPrime.length; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        partners = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            partners[i] = new ArrayList<>();
        }
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                int sum = nums[i] + nums[j];
                if (isPrime[sum]) {
                    partners[i].add(j);
                    partners[j].add(i);
                }
            }
        }

        match = new int[N];
        checked = new boolean[N];
        ArrayList<Integer> answers = new ArrayList<>();
        for (int i : partners[0]) {
            Arrays.fill(match, -1);

            match[0] = i;
            match[i] = 0;
            int matchCount = 1;

            for (int j = 1; j < N; j++) {
                Arrays.fill(checked, false);
                checked[0] = true;
                checked[i] = true;

                if (match[j] == -1 && isAble(j)) {
                    matchCount++;
                }
            }

            if (2 * matchCount == N) {
                answers.add(nums[i]);
            }
        }

        if (answers.isEmpty()) {
            System.out.println("-1");
        } else {
            StringBuilder sb = new StringBuilder();
            answers.stream().sorted().forEach(e -> sb.append(e).append(' '));
            System.out.println(sb);
        }
    }

    static boolean isAble(int x) {
        checked[x] = true;

        for (int partner : partners[x]) {
            if (checked[partner]) {
                continue;
            }
            checked[partner] = true;

            if (match[partner] == -1 || isAble(match[partner])) {
                match[partner] = x;
                match[x] = partner;
                return true;
            }
        }

        return false;
    }
}
