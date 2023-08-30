import java.io.*;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] arr = {"MatKor", "WiCys", "CyKor", "AlKor", "$clear"};

        String c = br.readLine();

        Arrays.stream(arr).filter(e -> e.startsWith(c)).forEach(e -> System.out.println(e));
    }
}
