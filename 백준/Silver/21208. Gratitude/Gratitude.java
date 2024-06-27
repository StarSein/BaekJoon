import java.io.*;
import java.util.*;


public class Main {

    static class Tuple {
        String gratitude;
        int frequency, lastWritten;

        Tuple(String gratitude, int frequency, int lastWritten) {
            this.gratitude = gratitude;
            this.frequency = frequency;
            this.lastWritten = lastWritten;
        }
    }
    static int N, K;
    static HashMap<String, Integer> frequencies = new HashMap<>();
    static HashMap<String, Integer> lastWrittens = new HashMap<>();
    static ArrayList<Tuple> tuples = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        // 입력을 받는다. 이때 감사 항목별 빈도 수, 최근 작성 시점을 저장한다
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        K = sc.nextInt();
        sc.nextLine();
        int endI = 3 * N;
        for (int i = 0; i < endI; i++) {
            String gratitude = sc.nextLine();
            frequencies.put(gratitude, frequencies.getOrDefault(gratitude, 0) + 1);
            lastWrittens.put(gratitude, i);
        }

        // 모든 감사 항목에 대해 (항목명, 빈도 수, 최근 작성 시점)을 리스트에 저장한다
        for (String gratitude : frequencies.keySet()) {
            tuples.add(new Tuple(gratitude, frequencies.get(gratitude), lastWrittens.get(gratitude)));
        }

        // 리스트를 (빈도 수의 내림차순, 최근 작성 시점의 내림차순)으로 정렬한다
        tuples.sort((a, b) -> {
            if (a.frequency == b.frequency) return b.lastWritten - a.lastWritten;
            return b.frequency - a.frequency;
        });

        // 리스트의 0번부터 K-1번 인덱스까지 정답 문자열에 추가한다
        StringBuilder sb = new StringBuilder();
        tuples.stream().limit(K).forEach(e -> sb.append(e.gratitude).append("\n"));

        // 정답 문자열을 출력한다
        System.out.print(sb);
    }
}
