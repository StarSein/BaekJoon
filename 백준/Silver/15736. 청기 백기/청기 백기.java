// 모든 수는 자신의 약수의 개수만큼 뒤집어진다
// 백색이 위로 놓여있으려면 홀수 번 뒤집어져야 하고,
// 그런 수는 약수의 개수가 홀수 개인 수, 즉 완전제곱수이다
// N = 21억(약 2^31 - 4천만) 일 경우 제곱근은 약 2^16 보다 작으므로
// i * i <= N 을 조건으로 반복문이 동작해도 도중에 정수 오버플로우가 발생하지 않는다


import java.io.*;
import java.util.*;


public class Main {

    static int N;

    public static void main(String[] args) throws Exception {
        // 입력 받기
        N = new Scanner(System.in).nextInt();

        // N 이하의 완전제곱수의 개수 세기
        int count = 1;
        while (count * count <= N) {
            count++;
        }
        count--;
        
        // 개수 출력
        System.out.println(count);
    }
}
