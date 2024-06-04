package ch3.stack_queue;

import java.util.PriorityQueue;
import java.util.Scanner;

// 절댓값 힙 구현하기(S1)

/**
 * Priority Queue -> 우선순위가 높은 요소가 더 앞으로 옴.
 * Comparator에서 반환값 (o1이 들어온 값, o2가 비교하는 값)
 * - 양수: 들어온 값이 더 우선순위가 낮다 -> 뒤에 추가
 * - 음수: 들어온 값이 더 우선순위가 높다 -> 앞에 추가
 * - 0: 우선순위가 서로 같다.
 */
public class p014 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        PriorityQueue<Integer> pq = new PriorityQueue<>((Integer o1, Integer o2) -> {
            int first_abs = Math.abs(o1);
            int second_abs = Math.abs(o2);
            if (first_abs == second_abs) {
                return o1 > o2 ? 1 : -1; // 절댓값이 같으면 음수 우선 정렬
            } else {
                return first_abs - second_abs; // 절댓값 기준 정렬
            }
        });

        for (int i = 0; i < n; i++) {
            int num = sc.nextInt();
            if (num == 0) {
                if (pq.isEmpty()) System.out.println(0);
                else System.out.println(pq.poll());
            } else {
                pq.add(num);
            }
        }
    }
}

// 1 -1
// -1 1