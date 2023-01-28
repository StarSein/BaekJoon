import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        HashMap<Long, Integer> table = new HashMap<>();
        long[] primes = findPrimes();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            long num = Long.parseLong(st.nextToken());
            for (long prime : primes) {
                while ((num % prime) == 0) {
                    if (table.containsKey(prime)) {
                        table.replace(prime, table.get(prime) + 1);
                    } else {
                        table.put(prime, 1);
                    }
                    num /= prime;
                }
            }
            if (num > 1) {
                if (table.containsKey(num)) {
                    table.replace(num, table.get(num) + 1);
                } else {
                    table.put(num, 1);
                }
            }
        }
        long answer = 1;
        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        boolean isExceed = false;
        for (int i = 0; i < m; i++) {
            long num = Long.parseLong(st.nextToken());
            for (long prime : primes) {
                while ((num % prime) == 0) {
                    if (table.containsKey(prime) && table.get(prime) > 0) {
                        table.replace(prime, table.get(prime) - 1);
                        if (answer * prime >= 1_000_000_000) isExceed = true;
                        answer = answer * prime % 1_000_000_000;
                    }
                    num /= prime;
                }
            }
            if (table.containsKey(num)) {
                table.replace(num, table.get(num) - 1);
                if (answer * num >= 1_000_000_000) isExceed = true;
                answer = answer * num % 1_000_000_000;
            }
        }
        String ans = String.valueOf(answer);
        if (isExceed) {
            for (int i = ans.length(); i < 9; i++) {
                System.out.print("0");
            }
        }
        System.out.println(ans);
    }
    static long[] findPrimes() {
        ArrayList<Long> list = new ArrayList<>();
        boolean[] checked = new boolean[100_000];
        for (int i = 2; i < checked.length; i++) {
            if (!checked[i]) {
                list.add((long) i);
                for (int j = i * 2; j < checked.length; j += i) {
                    checked[j] = true;
                }
            }
        }
        return list.stream().mapToLong(Long::longValue).toArray();
    }
}
