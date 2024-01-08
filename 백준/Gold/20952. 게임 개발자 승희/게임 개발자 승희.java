import java.io.*;
import java.util.*;


public class Main {

    static int N, M;
    static int[] A, B;
    static ArrayList<Integer>[] indices;

    public static void main(String[] args) throws Exception {
        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        B = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        // A의 원소들을 7로 나눈 나머지를 기준으로 분류
        indices = new ArrayList[7];
        for (int i = 0; i < 7; i++) {
            indices[i] = new ArrayList<>();
        }
        for (int i = 0; i < N; i++) {
            int num = A[i];
            indices[num % 7].add(i);
        }

        // 모든 원소가 제거되지 않는 선에서 특정 나머지의 원소 전부 제거
        int emptyCount = 0;
        for (ArrayList<Integer> list : indices) {
            if (list.isEmpty()) {
                emptyCount++;
            }
        }

        long addition = 0L;
        int addMod = 0;
        for (int i = 0; i < M; i++) {
            addition += B[i];
            addMod = (addMod + B[i]) % 7;
            boolean canceled = false;
            for (int mod = 0; mod < 7; mod++) {
                if (indices[mod].isEmpty()) {
                    continue;
                }
                if ((mod + addMod) % 7 == 0) {
                    if (emptyCount < 6) {
                        indices[mod].clear();
                        emptyCount++;
                    } else {
                        canceled = true;
                    }
                }
            }
            if (canceled) {
                addition -= B[i];
                addMod = (addMod + 7 - (B[i] % 7)) % 7;
            }
        }

        // 남은 원소를 하나의 배열에 담아서 오름차순 정렬 후 출력
        ArrayList<Integer> result = new ArrayList<>();
        for (int mod = 0; mod < 7; mod++) {
            result.addAll(indices[mod]);
        }

        result.sort(Comparator.naturalOrder());

        StringBuilder sb = new StringBuilder();
        sb.append(result.size()).append('\n');
        for (int idx : result) {
            sb.append((addition + A[idx]) % 1_000_000_007L).append(' ');
        }
        System.out.println(sb);
    }
}
