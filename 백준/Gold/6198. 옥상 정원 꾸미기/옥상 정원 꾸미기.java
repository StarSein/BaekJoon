import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] heights;
    static ArrayDeque<Integer> stack;
    static long answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        heights = new int[N];
        for (int i = 0; i < N; i++) {
            heights[i] = Integer.parseInt(br.readLine());
        }

        stack = new ArrayDeque<>();
        for (int height : heights) {
            while (!stack.isEmpty() && stack.peekLast() <= height) {
                stack.pollLast();
            }
            answer += stack.size();
            stack.offerLast(height);
        }

        System.out.println(answer);
    }
}
