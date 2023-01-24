import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            q.add(i);
        }
        for (int i = 1; i < n; i++) {
            q.remove();
            q.add(q.remove());
        }
        System.out.println(q.remove());
    }
}