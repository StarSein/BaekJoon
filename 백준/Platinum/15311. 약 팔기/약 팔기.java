import java.util.*;

/*
'적은 수의 봉지'라는 서술과는 무관하게, 
약봉지의 개수를 최소화할 필요는 없다
2000개까지 올려놓으면 된다
 */

public class Main {
    
    static int N;
    
    public static void main(String[] args) throws Exception {
        N = new Scanner(System.in).nextInt();
        
        StringBuilder sb = new StringBuilder();
        sb.append("1999\n");
        for (int i = 0; i < 1000; i++) {
            sb.append("1 ");
        }
        for (int i = 0; i < 999; i++) {
            sb.append("1000 ");
        }
        System.out.print(sb);
    }
}
