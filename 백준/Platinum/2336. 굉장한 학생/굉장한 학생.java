import java.io.*;
import java.util.*;


public class Main {

    static class Student implements Comparable<Student> {
        int[] ranks;

        Student() {
            this.ranks = new int[3];
        }

        @Override
        public int compareTo(Student e) {
            return this.ranks[0] - e.ranks[0];
        }
    }

    static class MinSegmentTree {
        int[] tree;

        MinSegmentTree(int size) {
            this.tree = new int[size];

            Arrays.fill(tree, INF);
        }

        void update(int node, int start, int end, int target, int value) {
            if (target < start || target > end) {
                return;
            }
            if (start == target && target == end) {
                tree[node] = value;
                return;
            }
            int mid = (start + end) >> 1;
            update(node << 1, start, mid, target, value);
            update(node << 1 | 1, mid + 1, end, target, value);
            tree[node] = Math.min(tree[node << 1], tree[node << 1 | 1]);
        }

        int query(int node, int start, int end, int left, int right) {
            if (left > end || right < start) {
                return INF;
            }
            if (left <= start && end <= right) {
                return tree[node];
            }
            int mid = (start + end) >> 1;
            return Math.min(
                    query(node << 1, start, mid, left, right),
                    query(node << 1 | 1, mid + 1, end, left, right)
            );
        }
    }
    
    static final int INF = Integer.MAX_VALUE;
    static int N;
    static Student[] students;
    static MinSegmentTree minSegmentTree;

    public static void main(String[] args) throws Exception {
        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        students = new Student[N];
        for (int i = 0; i < N; i++) {
            students[i] = new Student();
        }
        for (int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int rank = 1; rank <= N; rank++) {
                int studentNo = Integer.parseInt(st.nextToken());
                students[studentNo - 1].ranks[i] = rank;
            }
        }

        // ranks[0]을 기준으로 오름차순 정렬
        Arrays.sort(students);

        // 최솟값 세그먼트 트리 초기화
        minSegmentTree = new MinSegmentTree(4 * N + 1);

        // 정렬된 학생 배열을 순회하면서
        int answer = 0;
        for (Student student : students) {
            // 최솟값 세그먼트 트리의 구간 [1 ~ ranks[1]]에서 ranks[2]보다 낮은 값 존재 여부 확인
            int minVal = minSegmentTree.query(1, 1, N, 1, student.ranks[1]);

            // 존재하면 현재 학생은 '굉장한' 학생이 아니고, 존재하지 않으면 현재 학생은 '굉장한' 학생임
            if (minVal > student.ranks[2]) {
                answer++;
            }

            // 최솟값 세그먼트 트리의 ranks[1] 위치를 ranks[2]로 갱신
            minSegmentTree.update(1, 1, N, student.ranks[1], student.ranks[2]);
        }

        // '굉장한' 학생의 수를 정답으로 출력
        System.out.println(answer);
    }
}
