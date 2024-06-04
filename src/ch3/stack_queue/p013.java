package ch3.stack_queue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 카드게임(S4)
public class p013 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i <= n; i++) {
            queue.add(i);
        }


        while (queue.size() > 1) {
            queue.poll();
            Integer polled = queue.poll();
            queue.add(polled);
        }

        System.out.println(queue.poll());
    }
}
