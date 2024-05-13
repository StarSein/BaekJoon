import java.io.*;
import java.util.*;


public class Main {

    static int N;
    static int[] prices;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        // 책 가격의 배열을 오름차순 정렬한다
        prices = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();

        // 낮은 가격의 책부터 페이지에 진열한다
        // 이때 현재 페이지에 가장 먼저 넣은 책 가격보다 2배 이상 높은 가격일 경우 새로운 페이지에 진열한다
        // 새로운 페이지에 진열할 때마다 페이지 수를 센다
        int pageCount = 0;
        int firstPrice = 0;
        for (int price : prices) {
            if (price >= 2 * firstPrice) {
                pageCount++;
                firstPrice = price;
            }
        }
        System.out.println(pageCount);
    }
}
