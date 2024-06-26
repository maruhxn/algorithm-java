package ch6.greedy;

// 수를 묶어서 최댓값 만들기(G4)

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 부호가 같은 수끼리만 묶는다.
 * 절댓값이 최대한 큰 수들끼리 묶는다.
 * 음수 1개, 0 1개 있으면 서로 곱해서 음수를 없앤다.
 * 1보다 큰 값들만 곱한다.
 * -> 음수 또는 0 우선순위 큐, 양수 우선순위 큐
 */
public class p034 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        PriorityQueue<Integer> plusQ = new PriorityQueue<>((Collections.reverseOrder()));
        PriorityQueue<Integer> minusQ = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            int num = sc.nextInt();
            if (num > 0) {
                plusQ.add(num);
            } else {
                minusQ.add(num);
            }
        }

        int result = 0;
        while (plusQ.size() > 1) {
            int a = plusQ.poll();
            int b = plusQ.poll();
            if (a == 1 | b == 1) {
                result += a + b;
            } else {
                result += a * b;
            }
        }

        while (minusQ.size() > 1) {
            // 둘 다 음수면 곱해서 result에 더하기.
            // 둘 중 하나는 0이면, 아무것도 X
            int a = minusQ.poll();
            int b = minusQ.poll();
            if (a != 0 && b != 0) {
                result += a * b;
            }
        }

        if (!plusQ.isEmpty()) result += plusQ.poll();
        if (!minusQ.isEmpty()) result += minusQ.poll();
        System.out.println(result);
    }
}
