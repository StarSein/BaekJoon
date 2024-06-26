import java.io.*;
import java.util.*;

/*
각 항목별 등장 횟수를 두 개의 채점 시스템 각각에 대해 센다
각 항목별 등장 횟수 2가지 중 더 적은 값의 총합이 정답이다
<- (더 적은 값이다) == (일치하는 것으로 짝지을 상대가 존재한다)
 */

public class Main {

    static int n;
    static HashMap<String, Integer> aMap, bMap;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        aMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String result = br.readLine();
            aMap.put(result, aMap.getOrDefault(result, 0) + 1);
        }
        bMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String result = br.readLine();
            bMap.put(result, bMap.getOrDefault(result, 0) + 1);
        }

        int answer = 0;
        for (String result : aMap.keySet()) {
            answer += Math.min(aMap.get(result), bMap.getOrDefault(result, 0));
        }
        System.out.println(answer);
    }
}
