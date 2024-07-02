import java.io.*;
import java.util.*;

/*
'a의 약수 집합이 b의 약수 집합에 포함된다'는 'a가 b의 약수다'와 동치이다
 */

public class Main {

    static int z, a, b;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        z = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (z-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            sb.append(b % a == 0 ? "TAK\n" : "NIE\n");
        }
        System.out.print(sb);
    }
}
