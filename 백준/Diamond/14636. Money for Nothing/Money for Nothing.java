// dp[i]를 i번째 Consumer와 거래할 때 얻을 수 있는 가장 큰 이윤이라고 하자
// dp[i] = max((e[i] - d[t]) * (q[i] - p[t]) (0 <= t < m)
// 전체 풀이의 시간 복잡도는 O(MN)으로 제한을 초과한다

// 우선 Producer, Consumer 배열을 각각 날짜 기준으로 오름차순 정렬하자
// 그러면 배열의 원소간에 날짜 관점에서만큼은 단조성이 존재한다

// Producer 배열에서 왼쪽부터 순회하다가 높은 가격이 나타나면 그 생산자는 탐색할 필요가 없다
// Consumer 배열에서 왼쪽부터 순회하다가 나타난 가격보다 낮은 가격의 소비자는 탐색할 필요가 없다
// 이같이 최적이 아님이 자명한 생산자와 소비자를 제거한 배열에서는 가격 관점에서도 단조성이 존재한다
// 즉 가격 기준으로 내림차순되어 있는 것이다

// dp[i] = (e[i] - d[opt]) * (q[i] - p[opt]) 라고 하자
// 그러면 low <= opt 인 모든 low 에 대해
// (e[i] - d[opt]) * (q[i] - p[opt]) >= (e[i] - d[low]) * (q[i] - p[low]) 가 성립한다 - (1)
// 식 (1) 로부터 (e[i] - d[opt]) / (e[i] - d[low]) >= (q[i] - p[low]) / (q[i] - p[opt])
// 이때 d[low] <= d[opt] 이므로 (좌변) <= 1, 그에 따라 (우변) <= 1 이 성립한다
// e[i] <= e[i+1], q[i] >= q[i+1] 이 성립하여
// i가 증가함에 따라 좌변의 값은 단조증가하고 우변의 값은 단조감소한다
// 따라서 식 (1) 은 아래와 같이 변형될 수 있다
// (e[i+1] - d[opt]) * (q[i+1] - p[opt]) >= (e[i+1] - d[low]) * (q[i+1] - p[low])
// 그러므로 분할 정복을 이용해 전체 풀이의 시간 복잡도를 O(NlogM) 으로 최적화 할 수 있다

import java.io.*;
import java.util.*;


public class Main {

    static class Company implements Comparable<Company> {
        int price;
        int date;

        Company(int price, int date) {
            this.price = price;
            this.date = date;
        }

        @Override
        public int compareTo(Company e) {
            return (this.date == e.date ? this.price - e.price : this.date - e.date);
        }
    }
    static class Producer extends Company {
        Producer(int price, int date) {
            super(price, date);
        }
    }

    static class Consumer extends Company {
        Consumer(int price, int date) {
            super(price, date);
        }
    }
    static int m;
    static int n;
    static Producer[] producers;
    static Consumer[] consumers;
    static ArrayDeque<Producer> producerDq = new ArrayDeque<>();
    static ArrayDeque<Consumer> consumerDq = new ArrayDeque<>();
    static long answer;

    public static void main(String[] args) throws Exception {
        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        producers = new Producer[m];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            producers[i] = new Producer(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        consumers = new Consumer[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            consumers[i] = new Consumer(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        // (1순위 기준: 날짜의 오름차순, 2순위 기준: 가격의 오름차순)으로 배열 정렬하기
        Arrays.sort(producers);
        Arrays.sort(consumers);

        // Producer 배열 왼쪽부터 순회하면서 이전 값보다 높거나 같은 값은 제거하기
        producerDq.offerLast(producers[0]);
        for (int i = 1; i < m; i++) {
            if (producers[i].price < producerDq.peekLast().price) {
                producerDq.offerLast(producers[i]);
            }
        }

        // Consumer 배열 왼쪽부터 순회하면서 현재 값보다 낮거나 같은 이전 값은 제거하기
        consumerDq.offerLast(consumers[0]);
        for (int i = 1; i < n; i++) {
            while (!consumerDq.isEmpty() && consumerDq.peekLast().price <= consumers[i].price) {
                consumerDq.pollLast();
            }
            consumerDq.offerLast(consumers[i]);
        }

        // 유의미한 케이스만 남은 Producer, Consumer 배열 만들기
        m = producerDq.size();
        n = consumerDq.size();
        producers = new Producer[m];
        consumers = new Consumer[n];
        producerDq.toArray(producers);
        consumerDq.toArray(consumers);

        // 분할 정복을 이용해 정답 계산하기
        calculate(0, n - 1, 0, m - 1);

        System.out.println(answer);
    }

    static void calculate(int s, int e, int l, int r) {
        if (s > e) {
            return;
        }

        int mid = (s + e) >> 1;
        Consumer consumer = consumers[mid];

        long optimalVal = Long.MIN_VALUE;
        int optimalI = l;
        for (int i = l; i <= r; i++) {
            Producer producer = producers[i];
            long dateDiff = consumer.date - producer.date;
            long priceDiff = consumer.price - producer.price;
            long curVal = (dateDiff < 0L && priceDiff < 0L) ? Long.MIN_VALUE : dateDiff * priceDiff;
            if (curVal > optimalVal) {
                optimalVal = curVal;
                optimalI = i;
            }
        }

        answer = Math.max(answer, optimalVal);

        calculate(s, mid - 1, l, optimalI);
        calculate(mid + 1, e, optimalI, r);
    }
}
