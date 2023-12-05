import java.io.*;
import java.util.*;


public class Main {

    static int N, K, M, F;
    static HashSet<Integer>[] cards;
    static HashMap<String, ArrayList<Integer>> sequenceCardTable;

    public static void main(String[] args) throws Exception {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        F = Integer.parseInt(st.nextToken());
        cards = new HashSet[K];
        for (int i = 0; i < K; i++) {
            cards[i] = new HashSet<>();
            HashSet<Integer> curCards = cards[i];
            st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                curCards.add(Integer.parseInt(st.nextToken()));
            }
        }

        // 어떤 수 i가 마음 속 숫자 후보군이 될 수 있는 yes/no 나열의 경우의 수는 단 한 개다
        // 1 <= i <= N 인 모든 i에 대해 yes/no 나열을 저장해 놓는다
        // 단, yes/no 나열을 key, 모든 가능한 i의 리스트를 value로 하는 map으로 관리한다
        sequenceCardTable = new HashMap<>();
        for (Integer i = 1; i <= N; i++) {
            StringBuilder sb = new StringBuilder();
            for (Set<Integer> curCards : cards) {
                sb.append(curCards.contains(i) ? 'Y' : 'N');
            }
            String sequence = sb.toString();
            if (!sequenceCardTable.containsKey(sequence)) {
                sequenceCardTable.put(sequence, new ArrayList<>());
            }
            sequenceCardTable.get(sequence).add(i);
        }

        // 입력으로 받은 yes/no 나열에 대해 정답을 출력한다
        StringBuilder answers = new StringBuilder();
        for (int i = 0; i < F; i++) {
            String sequence = br.readLine().trim();
            if (sequenceCardTable.containsKey(sequence) && sequenceCardTable.get(sequence).size() == 1) {
                answers.append(sequenceCardTable.get(sequence).get(0));
            } else {
                answers.append(0);
            }
            answers.append('\n');
        }
        System.out.println(answers);
    }
}