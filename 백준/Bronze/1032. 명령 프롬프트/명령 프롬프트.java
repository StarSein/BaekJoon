import java.io.*;


public class Main {

    static int N;
    static char[] ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        ans = br.readLine().toCharArray();

        for (int i = 1; i < N; i++) {
            char[] inp = br.readLine().toCharArray();
            for (int j = 0; j < ans.length; j++) {
                if (ans[j] != inp[j]) {
                    ans[j] = '?';
                }
            }
        }

        System.out.println(ans);
    }
}
