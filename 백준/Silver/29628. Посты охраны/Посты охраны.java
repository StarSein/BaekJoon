import java.util.*;

/*
편의상 a <= b <= c 라고 하자
1. a + b <= c 인 경우
    세 초소는 하나의 선분 위에 놓이면 되므로, 정답은 c/2 이다
2. a + b > c 인 경우
    세 초소가 삼각형을 이루는 것이 최적이다
    1) 직각삼각형, 둔각삼각형인 경우
        지름이 c인 원 위에 세 초소가 놓일 수 있으므로, 정답은 c/2 이다
    2) 예각삼각형인 경우
        (1) 외접원의 반지름이 정답이므로 그것을 구해야 한다
            세 초소를 A, B, C 라 하고 선분 AB, BC, CA 의 길이를 각각 c, a, b 라고 하자
            그러면 사인 법칙에 의해 a/2R = sinA 가 성립한다
            즉 R = a/(2sinA)
        (2) 한편 코사인 법칙에 의해 cosA = (b^2 + c^2 - a^2) / 2bc
        (3) sinA = sqrt(1 - (cosA)^2)
 */

public class Main {

    static long a, b, c;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        a = sc.nextInt();
        b = sc.nextInt();
        c = sc.nextInt();
        if (a > c) {
            long temp = a;
            a = c;
            c = temp;
        }
        if (b > c) {
            long temp = b;
            b = c;
            c = temp;
        }

        if (a + b <= c || c * c >= a * a + b * b) {
            System.out.println((double) c / 2);
        } else {
            double cosA = (double) (b * b + c * c - a * a) / (2 * b * c);
            double sinA = Math.sqrt(1 - cosA * cosA);
            double R = a / (2 * sinA);
            System.out.println(R);
        }
    }
}
