import java.io.*;
import java.util.*;


public class Main {

    static final int START = 0, END = 42, NUM_TURN = 10, NUM_TOKEN = 4;
    static int[] nextNodes;                      // 빨간색 화살표에 따른 연결 관계
    static HashMap<Integer, Integer> innerNodes; // 파란색 화살표에 따른 연결 관계
    static int[] dices;                          // 주사위에 나올 수 10개
    static int[] tokens;                         // 말 4개의 현재 위치

    public static void main(String[] args) throws Exception {
        // 게임판을 만든다
        // 번호가 중복되므로 대각선 위의 번호는 END만큼 값을 더한다
        nextNodes = new int[2 * END];
        for (int i = START; i < END; i += 2) {
            nextNodes[i] = i + 2;
        }
        nextNodes[13 + END] = 16 + END;
        nextNodes[16 + END] = 19 + END;
        nextNodes[19 + END] = 25 + END;
        nextNodes[22 + END] = 24 + END;
        nextNodes[24 + END] = 25 + END;
        nextNodes[28 + END] = 27 + END;
        nextNodes[27 + END] = 26 + END;
        nextNodes[26 + END] = 25 + END;
        nextNodes[25 + END] = 30 + END;
        nextNodes[30 + END] = 35 + END;
        nextNodes[35 + END] = 40;
        innerNodes = new HashMap<>();
        innerNodes.put(10, 13 + END);
        innerNodes.put(20, 22 + END);
        innerNodes.put(30, 28 + END);

        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        dices = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        // 4^10가지 경우의 수를 완전탐색 한다
        tokens = new int[NUM_TOKEN];
        int answer = dfs(0, 0);

        System.out.println(answer);
    }
    
    static int dfs(int depth, int score) {
        if (depth == NUM_TURN) {
            return score;
        }
        int maxScore = 0;
        for (int i = 0; i < NUM_TOKEN; i++) {
            int curPos = tokens[i];
            if (curPos == END) {
                continue;
            }

            int nextPos = (innerNodes.containsKey(curPos) ?
                    getNextPos(innerNodes.get(curPos), dices[depth] - 1) :
                    getNextPos(curPos, dices[depth]));
            if (isOccupied(nextPos)) {
                continue;
            }

            tokens[i] = nextPos;
            int scoreAdd = (nextPos >= END ? nextPos - END : nextPos);
            int res = dfs(depth + 1, score + scoreAdd);
            maxScore = Math.max(maxScore, res);
            tokens[i] = curPos;
        }

        return maxScore;
    }

    static int getNextPos(int pos, int step) {
        for (int i = 0; i < step; i++) {
            pos = nextNodes[pos];
            if (pos == END) {
                break;
            }
        }
        return pos;
    }

    static boolean isOccupied(int pos) {
        if (pos == END) {
            return false;
        }
        for (int token : tokens) {
            if (token == pos) {
                return true;
            }
        }
        return false;
    }
}
