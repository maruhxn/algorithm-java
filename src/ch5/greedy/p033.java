package ch5.greedy;

import java.util.PriorityQueue;
import java.util.Scanner;

// 카드 정렬하기(G4)
public class p033 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            pq.add(sc.nextInt());
        }

        // 큐가 빌 때까지 반복:
        // 우선순위 큐에서 2개 뽑고
        // 뽑은 값 2개 더하고 큐에 값이 남아있다면 다시 큐에 넣기

        int result = 0;
        int sum = 0;
        while (pq.size() >= 2) {
            Integer a = pq.poll();
            Integer b = pq.poll();
            sum = a + b;
            pq.add(sum);
            result += sum;
        }

        System.out.println(result);
    }
}
// 항상 작은 카드값이 먼저 나와야 함.

// 30 40 50 60
// (30 + 40) = 70 -> [70, 50, 60]
// (50 + 60) = 110 -> [110, 70]
// (30 + 40) + (50 + 60)