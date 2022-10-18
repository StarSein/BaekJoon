import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        String[] arr = new String[4];
        for (int i = 0; i < 4; i++) {
            arr[i] = st.nextToken();
        }
        long sum = 0;
        for (int i = 0; i < 4; i += 2) {
            sum += Long.parseLong(arr[i] + arr[i + 1]);
        }
        System.out.println(sum);
    }
}
