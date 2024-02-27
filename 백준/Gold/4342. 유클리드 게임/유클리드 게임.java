/*
언뜻 보기에는 (a, b)를 (a-b, b), (a-2b, b), (a-3b, b), ... , (a-nb, b), (a%b, b) 로 만드는 많은 경우의 수가 있는 듯 보인다
하지만 유효한 경우의 수는 두 가지뿐이다. 내 차례 때 (a%b, b)로 만드느냐, 상대 차례 때 (a%b, b)로 만드느냐.
그 외의 경우는 최적의 선택을 할 기회를 상대방에게 양도하는 비합리적인 경우일 뿐이므로 제외하면 된다.
두 가지 선택의 결과는 서로 다르므로, 둘 중 하나를 선택할 기회를 얻는다는 것은, 무조건 승리한다는 것과 같다.
단, a-b == a%b 인 경우에는 한 가지 선택만 할 수 있다.
 */


import java.io.*;
import java.util.*;


public class Main {
    
    static int a, b;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            if (a == 0 && b == 0) {
                break;
            }
            sb.append(win(Math.max(a, b), Math.min(a, b)) ? "A wins\n" : "B wins\n");
        }
        System.out.print(sb);
    }

    static boolean win(int a, int b) {
        if (b == 0) {
            return false;
        }
        if (a - b == a % b) {
            return !win(b, a - b);
        }
        return true;
    }
}
