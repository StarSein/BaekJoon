import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String lineInp = "";

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while ((lineInp = br.readLine()) != null) {
            int lower = 0, upper = 0, digit = 0, blank = 0;
            for (int i = 0; i < lineInp.length(); i++) {
                if ('a' <= lineInp.charAt(i) && lineInp.charAt(i) <= 'z') {
                    lower++;
                } else if ('A' <= lineInp.charAt(i) && lineInp.charAt(i) <= 'Z') {
                    upper++;
                } else if ('0' <= lineInp.charAt(i) && lineInp.charAt(i) <= '9') {
                    digit++;
                } else {
                    blank++;
                }
            }
            String ans = String.valueOf(lower) + " " + upper + " " + digit + " " + blank;
            bw.write(ans);
            bw.newLine();
        }
        bw.flush();
        return;
    }
}
