import java.io.*;
import java.util.*;

/*
자료구조
1. 뒤로 가기 공간: 덱
2. 앞으로 가기 공간: 스택
 */

public class Main {

    static int N, Q, C;
    static int[] cap;
    static int curPage, curUsage;
    static ArrayDeque<Integer> backDeq = new ArrayDeque<>();
    static ArrayDeque<Integer> frontDeq = new ArrayDeque<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        cap = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            cap[i] = Integer.parseInt(st.nextToken());
        }
        while (Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            switch (st.nextToken()) {
                case "B":
                    goBack();
                    break;
                case "F":
                    goFront();
                    break;
                case "A":
                    int i = Integer.parseInt(st.nextToken());
                    accessPage(i);
                    break;
                case "C":
                    compress();
                    break;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(curPage).append('\n');
        printProcedure(sb, backDeq);
        printProcedure(sb, frontDeq);
        System.out.print(sb);
    }

    static void goBack() {
        /*
        1. 뒤로 가기 실행 시
            1) 덱이 비어있는지 체크
            2) 현재 번호를 스택에 추가
            3) 덱의 뒤에서 pop 하여 현재 번호로 삼는다
         */
        if (backDeq.isEmpty()) {
            return;
        }
        frontDeq.offerLast(curPage);
        curPage = backDeq.pollLast();
    }

    static void goFront() {
        /*
        2. 앞으로 가기 실행 시
            1) 스택이 비어있는지 체크
            2) 현재 번호를 덱의 뒤에 추가
            3) 스택에서 pop 하여 현재 번호로 삼는다
         */
        if (frontDeq.isEmpty()) {
            return;
        }
        backDeq.offerLast(curPage);
        curPage = frontDeq.pollLast();
    }

    static void accessPage(int i) {
        /*
        3. 웹 페이지 접속 시
            1) 스택을 비운다. 캐시 사용량을 차감한다
            2) 현재 번호를 덱의 뒤에 추가 (현재 번호 없는 경우 제외)
            3) 주어진 번호를 현재 번호로 삼는다
            4) 현재 번호의 용량만큼 캐시 사용량 증가
            5) 캐시 사용량이 한도 초과할 경우, 덱의 앞에서 pop 하여 캐시 사용량 차감, 이를 충분할 때까지 반복
         */
        while (!frontDeq.isEmpty()) {
            int page = frontDeq.pollLast();
            curUsage -= cap[page];
        }
        if (curPage != 0) {
            backDeq.offerLast(curPage);
        }
        curPage = i;
        curUsage += cap[i];
        while (curUsage > C) {
            int page = backDeq.pollFirst();
            curUsage -= cap[page];
        }
    }

    static void compress() {
        /*
        4. 압축 실행 시
            1) 덱의 크기만큼 popLeft & offerLast 연산을 반복한다 (Q <= 2000 이므로 O(Q^2) 시간상 충분)
            2) 이때 pop 한 페이지 번호와 이전에 pop 한 페이지 번호가 같을 경우,
               offer 연산을 하지 않는다. 그리고 캐시 사용량을 차감한다
         */
        int size = backDeq.size();
        int prevPage = 0;
        while (size-- > 0) {
            int page = backDeq.pollFirst();
            if (page == prevPage) {
                curUsage -= cap[page];
            } else {
                backDeq.offerLast(page);
                prevPage = page;
            }
        }
    }

    static void printProcedure(StringBuilder sb, ArrayDeque<Integer> dq) {
        if (dq.isEmpty()) {
            sb.append("-1\n");
        } else {
            while (!dq.isEmpty()) {
                sb.append(dq.pollLast()).append(' ');
            }
            sb.append('\n');
        }
    }
}
