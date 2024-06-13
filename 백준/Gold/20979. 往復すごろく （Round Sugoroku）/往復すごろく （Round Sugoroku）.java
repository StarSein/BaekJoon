import java.io.*;
import java.util.*;

/*
이중 연결 리스트를 사용하면 O(N)에 해결할 수 있다
Java 에서 제공하는 기본 컬렉션인 Linked List 를 활용하는 것은
listIterator 메소드의 역방향 버전도 있어야 가능하다
따라서 이중 연결 리스트를 직접 구현해서 사용하자
 */

public class Main {

    static class DualLinkedList {

        static class Node {
            int pos;
            Node prev;
            Node next;

            Node(int pos, Node prev, Node next) {
                this.pos = pos;
                this.prev = prev;
                this.next = next;
            }

            boolean equals(Node e) {
                return this.pos == e.pos;
            }
        }

        Node head;
        Node tail;
        Node cursor;

        DualLinkedList() {
            head = new Node(0, null, null);
            tail = new Node(N + 1, head, null);
            head.next = tail;

            cursor = head;
        }

        // cursor 의 뒤에 새로운 노드를 추가하고 해당 노드를 cursor 로 삼는다
        void offerLast(int pos) {
            Node oldNode = cursor.next;
            Node newNode = new Node(pos, cursor, oldNode);
            cursor.next = newNode;
            oldNode.prev = newNode;
            
            cursor = newNode;
        }

        // cursor 를 삭제하고 앞 또는 뒤의 노드를 cursor 로 삼는다. cursor 의 이동 거리 (pos의 변화분) 을 반환한다
        int eraseCursor(char dir) {
            Node prevNode = cursor.prev;
            Node nextNode = cursor.next;
            prevNode.next = nextNode;
            nextNode.prev = prevNode;
            cursor.prev = null;
            cursor.next = null;
            
            int oldPos = cursor.pos;
            cursor = (dir == '<' ? prevNode : nextNode);
            return Math.abs(cursor.pos - oldPos);
        }

        // 주어진 값 이상의 pos 중 최솟값을 가진 노드를 cursor 로 삼는다. cursor 의 pos 를 반환한다
        int setCursor(int target) {
            cursor = head;
            while (!cursor.equals(tail) && cursor.pos < target) {
                cursor = cursor.next;
            }
            return cursor.pos;
        }

        // cursor 를 앞 또는 뒤로 이동하고, 이동 거리를 반환한다
        int moveCursor(char dir) {
            int oldPos = cursor.pos;
            cursor = (dir == '<' ? cursor.prev : cursor.next);
            return Math.abs(cursor.pos - oldPos);
        }

        // 모든 '#'를 방문했는지 여부(리스트에 노드가 하나뿐인지 여부)를 반환한다
        boolean allVisited() {
            return cursor.prev == head && cursor.next == tail;
        }

        // 커서가 '#'(노드) 위에 있는지 여부를 반환한다
        boolean cursorOnNode() {
            return !(cursor.equals(head) || cursor.equals(tail));
        }
    }
    static int N, A;
    static char[] S;
    static DualLinkedList list;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        S = br.readLine().toCharArray();

        // 이중 연결 리스트를 초기화한다
        list = new DualLinkedList();

        // 이중 연결 리스트에 '#'의 위치를 오름차순으로 넣는다
        for (int i = 0; i < N; i++) {
            if (S[i] == '#') {
                list.offerLast(i + 1);
            }
        }

        // A의 오른쪽 가장 가까이에 위치한 노드부터 거리 계산 및 삭제를 시작하여 총합을 구한다
        long totalDist = 0L;
        char dir = '<';
        int pos = list.setCursor(A);
        totalDist += pos - A;
        if (!list.cursorOnNode()) {
            // A 오른쪽에 '#' 가 없는 경우 A 왼쪽의 '#'를 찾아 cursor 로 삼고 이동 거리를 더한다
            totalDist += list.moveCursor(dir);
            dir = '>';
        }
        while (!list.allVisited()) {
            if (list.cursorOnNode()) {
                totalDist += list.eraseCursor(dir);
            } else {
                totalDist += list.moveCursor(dir);
            }
            dir = (dir == '>' ? '<' : '>');
        }

        // 총합을 출력한다
        System.out.println(totalDist);
    }
}
