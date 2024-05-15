import java.io.*;
import java.util.*;

/*

Sol 1) N 이 약 20 이하일 때에만 가능한 풀이
1. 이진트리를 직접 구현하자
2. 루트의 번호는 1 이다
3. 두 자식 모두 비어있는 경우 서브트리의 공이 더 적은 쪽으로 굴린다
    => 각 노드마다 공이 굴러간 횟수를 센다
4. 두 자식 중 하나만 비어있는 경우 비어 있는 자식으로 굴린다
    => 각 노드마다 공의 존재 여부를 체크한다
5. 두 자식 모두 공이 존재한다면 현재 정점에 공을 위치시킨다

Sol 2) N <= 60 일 때 가능한 풀이
다수의 공을 한 번에 굴려야 한다 (공 굴리는 횟수 10^5 이하, 굴린 공 개수의 총합 2^60 미만)
이때 각 노드의 상태를 실시간으로 갱신하는 것은 시간적, 공간적으로 불가능하다
다수의 공을 굴릴 때마다 연관된 노드에 lazy 하게 갱신하면 된다
1. 모든 공을 (공 번호, 출력 순서)로 묶은 다음 공 번호의 오름차순으로 정렬한다
2. 직전 공 번호를 prev, 현재 공 번호를 cur 이라고 할 때, (cur - prev) 개의 공을 한 번에 굴린다
3. 각 노드에는 lazy 하게 처리해줘야 할 공의 개수를 저장한다
   이때 해당 정보가 저장될 노드의 개수는 최대 2 * 60 * 10^5 개다

[노드의 정보]
1. 노드 번호
2. 노드 깊이
3. 아직 자식에게 굴리지 않은 공의 개수(lazy 하게 처리할 대상)
4. 해당 노드를 루트로 하는 서브트리에서 공의 개수(해당 노드를 지나간 공의 개수)
   - 트리의 깊이와 노드의 깊이의 차를 D 라고 하자
     서브트리 공의 개수가 2^(D+1) - 1 이면 해당 서브트리는 포화 상태이다
     즉, 해당 노드에도 공이 존재한다

[구현 로직]
현재 x개의 공을 굴린다고 하자
만약 해당 x개의 공을 반영하여 서브트리가 포화 상태라면
    아래로는 (x-1)개의 공을 굴린다
    즉, x-- 처리를 한다
1. x가 짝수인 경우
    양쪽 자식으로 절반씩 공을 굴린다
    마지막 공을 받는 자식은 둘 중 공이 더 많은 쪽이다
2. x가 홀수인 경우
    1) 양쪽 자식의 공의 개수가 다른 경우
        양쪽 자식 중 서브트리 공 개수가 적은 자식으로 한 개 더 많이 굴린다
    2) 양쪽 자식의 공의 개수가 같은 경우
        노드 번호가 홀수이면 오른쪽으로 한 개 더 많이 굴리고,
        노드 번호가 짝수이면 왼쪽으로 한 개 더 많이 굴린다
    마지막 공을 받는 자식은 둘 중 공을 이번에 더 많이 받는 쪽이다
 */

public class Main {

    static class Ball {
        long id;
        int order;

        Ball(long id, int order) {
            this.id = id;
            this.order = order;
        }
    }

    static class Node {
        long id;
        int depth;
        long lazy;
        long ballCount;
        Node left;
        Node right;

        Node(long id, int depth) {
            this.id = id;
            this.depth = depth;
            this.lazy = 0L;
            this.ballCount = 0L;
            this.left = null;
            this.right = null;
        }

        boolean isFull() {
            return ballCount + 1L == (1L << (tree.depth + 1 - this.depth));
        }

        boolean isEdge() {
            return tree.depth == this.depth;
        }

        void updateLazy(long x) {
            this.lazy += x;
            this.ballCount += x;
        }

        long updateNode(long x) {
            this.ballCount += x;

            if (this.left == null) {
                this.left = new Node(this.id << 1L, this.depth + 1);
                this.right = new Node((this.id << 1L) + 1, this.depth + 1);
            }

            if (this.lazy > 0L) {
                x += this.lazy;
                this.lazy = 0L;
            }


            if (this.isFull()) {
                x--;
            }

            Node nodeTarget;
            Node lazyTarget;
            long nodeQuantity;
            long lazyQuantity;
            if (x % 2L == 0L) {
                nodeQuantity = x >> 1L;
                lazyQuantity = x >> 1L;
                if (left.ballCount > right.ballCount || (left.ballCount == right.ballCount && this.id % 2L == 1L)) {
                    nodeTarget = left;
                    lazyTarget = right;
                } else {
                    nodeTarget = right;
                    lazyTarget = left;
                }
            } else {
                nodeQuantity = (x >> 1L) + 1L;
                lazyQuantity = (x >> 1L);
                if (left.ballCount > right.ballCount || (left.ballCount == right.ballCount && this.id % 2L == 1L)) {
                    nodeTarget = right;
                    lazyTarget = left;
                } else {
                    nodeTarget = left;
                    lazyTarget = right;
                }
            }

            if (this.isEdge()) {
                return this.id;
            }

            lazyTarget.updateLazy(lazyQuantity);
            long res = nodeTarget.updateNode(nodeQuantity);
            return this.isFull() ? this.id : res;
        }
    }

    static class Tree {
        Node root;
        int depth;

        Tree(int depth) {
            this.root = new Node(1L, 1);
            this.depth = depth;
        }

        long rollBall(long x) {
            return root.updateNode(x);
        }
    }
    
    static int N, Q;
    static Ball[] balls;
    static Tree tree;
    static long[] answers;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        Q = Integer.parseInt(br.readLine());
        balls = new Ball[Q];
        for (int i = 0; i < balls.length; i++) {
            balls[i] = new Ball(Long.parseLong(br.readLine()), i);
        }

        // 공을 번호의 오름차순으로 정렬한다
        Arrays.sort(balls, Comparator.comparingLong(ball -> ball.id));

        // 정렬된 공을 처리하고 반환값을 정답 배열에 추가한다
        tree = new Tree(N);
        answers = new long[Q];
        long prevID = 0L;
        for (Ball ball : balls) {
            answers[ball.order] = tree.rollBall(ball.id - prevID);
            prevID = ball.id;
        }

        // 정답 배열을 출력한다
        StringBuilder sb = new StringBuilder();
        for (long answer : answers) {
            sb.append(answer).append('\n');
        }
        System.out.print(sb);
    }
}
