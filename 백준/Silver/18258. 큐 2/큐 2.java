import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        MyQueue q = new MyQueue(n);
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            switch (cmd) {
                case "push":
                    int x = Integer.parseInt(st.nextToken());
                    q.push(x);
                    break;
                case "pop":
                    bw.write(q.pop() + "\n");
                    break;
                case "size":
                    bw.write(q.size() + "\n");
                    break;
                case "empty":
                    bw.write(q.empty() + "\n");
                    break;
                case "front":
                    bw.write(q.front() + "\n");
                    break;
                case "back":
                    bw.write(q.back() + "\n");
            }
        }
        br.close();
        bw.flush();
        bw.close();
    }
}
class MyQueue {
    int[] arr;
    int lp;
    int rp;
    public MyQueue(int n) {
        arr = new int[n];
        lp = 0;
        rp = 0;
    }
    public void push(int e) {
        arr[rp++] = e;
    }
    public int pop() {
        return lp == rp ? -1 : arr[lp++];
    }
    public int size() {
        return rp - lp;
    }
    public int empty() {
        return lp == rp ? 1 : 0;
    }
    public int front() {
        return lp == rp ? -1 : arr[lp];
    }
    public int back() {
        return lp == rp ? -1 : arr[rp - 1];
    }
}