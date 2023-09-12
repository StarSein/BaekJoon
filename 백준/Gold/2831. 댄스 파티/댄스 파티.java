import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static List<Integer> boyPlus, boyMinus, girlPlus, girlMinus;

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        boyPlus = new ArrayList<>();
        boyMinus = new ArrayList<>();
        girlPlus = new ArrayList<>();
        girlMinus = new ArrayList<>();
        inputProcedure(boyPlus, boyMinus);
        inputProcedure(girlPlus, girlMinus);

        int answer = calcNumPair(boyPlus, girlMinus) + calcNumPair(girlPlus, boyMinus);
        System.out.println(answer);
    }

    static void inputProcedure(List<Integer> plus, List<Integer> minus) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int height = Integer.parseInt(st.nextToken());
            if (height > 0) {
                plus.add(height);
            } else {
                minus.add(-height);
            }
        }
    }

    static int calcNumPair(List<Integer> plus, List<Integer> minus) {
        int ret = 0;
        plus.sort(Comparator.naturalOrder());
        minus.sort(Comparator.naturalOrder());

        int mi = 0;
        int mSize = minus.size();
        for (int p : plus) {
            while (mi < mSize && p >= minus.get(mi)) {
                mi++;
            }
            if (mi == mSize) {
                break;
            }
            ret++;
            mi++;
        }
        return ret;
    }
}
