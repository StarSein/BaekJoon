/*
수학적으로 확률을 구할 생각을 하니 정신이 아득하다
52_C_6 은 얼마일까? 20,358,520 이다
카드를 뽑는 모든 경우의 수에 대해 족보를 분류하면 되겠다
 */

public class Main {

    static int[] ptnCnts = new int[4]; // 특정 문양의 개수
    static int[] dgtCnts = new int[13]; // 특정 숫자의 개수
    static int pairCnt; // 페어의 개수
    static int tripleCnt; // 트리플의 개수
    static boolean isFourCard; // 포카드 여부
    static int backStraight = (1 << 5) - 1; // 'A2345'를 나타내는 비트마스크
    static int mountain = (((1 << 13) - 1) ^ ((1 << 9) - 1)) | 1;  // '10JQKA'를 나타내는 비트마스크

    static int[] handsCnts = new int[12];
    static int totalCnt = 0;

    public static void main(String[] args) throws Exception {
        // 카드를 6장 뽑는 시뮬레이션을 하며 각각의 족보가 나올 경우의 수를 센다
        drawCard(0, 0, 0, 0L);

        // 각각의 족보가 나올 확률을 순서대로 정답 문자열에 추가한다
        StringBuilder sb = new StringBuilder();
        for (int handsCnt : handsCnts) {
            int gcd = getGCD(handsCnt, totalCnt);
            sb.append(handsCnt / gcd).append('/').append(totalCnt / gcd).append('\n');
        }

        // 정답 문자열을 출력한다
        System.out.print(sb);
    }

    static void drawCard(int numCard, int start, int digitBitmask, long cardBitmask) {
        // 카드를 6장 다 뽑은 경우 족보를 판정한다
        if (numCard == 6) {
            totalCnt++;

            // [11] 로얄 스트레이트 플러시
            long temp1 = cardBitmask;
            while (temp1 > 0L) {
                if ((temp1 & backStraight) == backStraight) {
                    handsCnts[11]++;
                    return;
                }
                temp1 >>= 13;
            }

            // [10] 스트레이트 플러시
            long temp2 = cardBitmask;
            while (temp2 > 0L) {
                int tmp = (int) (temp2 & ((1 << 13) - 1));
                while (tmp >= 31) {
                    if ((tmp & 31) == 31) {
                        handsCnts[10]++;
                        return;
                    }
                    tmp >>= 1;
                }
                temp2 >>= 13;
            }

            // [9] 포카드
            if (isFourCard) {
                handsCnts[9]++;
                return;
            }

            // [8] 풀하우스
            if (tripleCnt >= 2 || (tripleCnt == 1 && pairCnt == 1)) {
                handsCnts[8]++;
                return;
            }

            // [7] 플러쉬
            for (int i = 0; i < 4; i++) {
                if (ptnCnts[i] >= 5) {
                    handsCnts[7]++;
                    return;
                }
            }

            // [6] 마운틴
            if ((digitBitmask & mountain) == mountain) {
                handsCnts[6]++;
                return;
            }

            // [5] 빽스트레이트
            if ((digitBitmask & backStraight) == backStraight) {
                handsCnts[5]++;
                return;
            }

            // [4] 스트레이트
            int temp3 = digitBitmask;
            while (temp3 >= 31) {
                if ((temp3 & 31) == 31) {
                    handsCnts[4]++;
                    return;
                }
                temp3 >>= 1;
            }

            // [3] 트리플
            if (tripleCnt == 1) {
                handsCnts[3]++;
                return;
            }

            // [2] 투페어
            if (pairCnt >= 2) {
                handsCnts[2]++;
                return;
            }

            // [1] 원페어
            if (pairCnt == 1) {
                handsCnts[1]++;
                return;
            }

            // [0] 탑
            handsCnts[0]++;
            return;
        }

        // 다음 카드를 뽑는다
        for (int card = start; card < 52; card++) {
            int pattern = card / 13;
            int digit = card % 13;

            ++ptnCnts[pattern];

            int dgtCnt = ++dgtCnts[digit];
            if (dgtCnt == 2) {
                pairCnt++;
            } else if (dgtCnt == 3) {
                pairCnt--;
                tripleCnt++;
            } else if (dgtCnt == 4) {
                tripleCnt--;
                isFourCard = true;
            }

            drawCard(numCard + 1, card + 1, digitBitmask | 1 << digit, cardBitmask | 1L << card);

            --ptnCnts[pattern];

            dgtCnt = --dgtCnts[digit];
            if (dgtCnt == 1) {
                pairCnt--;
            } else if (dgtCnt == 2) {
                pairCnt++;
                tripleCnt--;
            } else if (dgtCnt == 3) {
                tripleCnt++;
                isFourCard = false;
            }
        }
    }

    static int getGCD(int a, int b) {
        while (b > 0) {
            int temp = a;
            a = b;
            b = temp % b;
        }
        return a;
    }
}
