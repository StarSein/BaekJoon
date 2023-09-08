import java.io.*;
import java.util.*;

public class Main {

    static class Dir {
        String name;
        String prefix;
        List<Dir> sub;

        Dir(String name, String prefix) {
            this.name = name;
            this.prefix = prefix;
            this.sub = new ArrayList<>();
        }
    }

    static int N;
    static Dir root = new Dir("", "");
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), "\\");
            Dir cur = root;
            while (st.hasMoreTokens()) {
                String name = st.nextToken();
                String prefix = cur.prefix;
                List<Dir> sub = cur.sub;
                boolean flag = true;
                for (Dir dir : sub) {
                    if (name.equals(dir.name)) {
                        cur = dir;
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    cur = new Dir(name, (cur.equals(root) ? prefix : prefix + " "));
                    sub.add(cur);
                }
            }
        }

        dfs(root);

        System.out.println(sb);
    }

    static void dfs(Dir cur) {
        cur.sub.sort(Comparator.comparing(e -> e.name));

        for (Dir dir : cur.sub) {
            sb.append(dir.prefix).append(dir.name).append('\n');
            dfs(dir);
        }
    }

}
