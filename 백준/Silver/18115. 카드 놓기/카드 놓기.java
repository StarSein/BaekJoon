import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] arr;
    static int[] answer;
    public static void main(String[] args) throws IOException {
        readInput();
        solve();
        printOutput();
    }
    static void readInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        br.close();
    }
    static void solve() {
        answer = new int[n];
        MyDeque dq = new MyDeque(n);
        // i번째 기술은 (N-i)가 적힌 카드에 적용된 것이다
        // 기술 1이면 해당 카드는 맨 위에 있던 것
        // 기술 2이면 해당 카드는 위에서 두 번째에 있던 것
        // 기술 3이면 해당 카드는 맨 밑에 있던 것
        for (int i = 0; i < n; i++) {
            int skill = arr[i];
            int num = n - i;
            int pos = -1;
            switch (skill) {
                case 1:
                    pos = dq.removeFirst();
                    break;
                case 2:
                    pos = dq.removeSecond();
                    break;
                case 3:
                    pos = dq.removeLast();
            }
            answer[pos] = num;
        }
    }
    static void printOutput() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int e : answer) {
            bw.write(e + " ");
        }
        bw.flush();
        bw.close();
    }
}

class MyDeque {
    boolean[] used;
    int firstPointer;
    int secondPointer;
    int lastPointer;
    public MyDeque(int n) {
        used = new boolean[n];
        firstPointer = 0;
        secondPointer = 1;
        lastPointer = n - 1;
    }
    public int removeFirst() {
        while (used[firstPointer]) {
            firstPointer++;
        }
        used[firstPointer] = true;
        return firstPointer;
    }
    public int removeSecond() {
        while (used[firstPointer]) {
            firstPointer++;
        }
        secondPointer = Math.max(secondPointer, firstPointer + 1);
        while (used[secondPointer]) {
            secondPointer++;
        }
        used[secondPointer] = true;
        return secondPointer;
    }
    public int removeLast() {
        while (used[lastPointer]) {
            lastPointer--;
        }
        used[lastPointer] = true;
        return lastPointer;
    }
}