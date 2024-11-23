package boj.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

// 결국 최솟값끼리만 계속해서 더하고 싶다.. -> Priority Queue 사용
public class BOJ1715 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();


        for (int i = 0; i < n; i++) {
            pq.add(Integer.parseInt(br.readLine()));
        }

        int result = 0;

        while (pq.size() >= 2) {
            int a = pq.poll();
            int b = pq.poll();

            result += a + b;

            pq.add(a + b);
        }

        if (pq.size() == 1) System.out.println(result);
    }
}
