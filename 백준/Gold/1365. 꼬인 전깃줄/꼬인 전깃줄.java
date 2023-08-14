import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {

    static int N;
    static int[] arr;
    static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        IntStream.of(arr).forEach(e -> binaryInsert(e));

        int answer = N - list.size();
        System.out.println(answer);
    }

    static void binaryInsert(int num) {
        int idx = Collections.binarySearch(list, num);
        if (idx < 0) {
            idx = -(idx + 1);
        }
        if (idx >= list.size()) {
            list.add(num);
        } else {
            list.set(idx, num);
        }
    }
}
