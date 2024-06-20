package ch3.stack_queue;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 카드1(S5)
public class BOJ2161 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = sc.nextInt();
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i <= n; i++) {
            queue.add(i);
        }

        while (queue.size() != 1) {
            Integer polled = queue.poll();
            bw.append(polled + " ");
            queue.add(queue.poll());
        }
        Integer peek = queue.peek();
        bw.append(peek + "");

        bw.flush();
        bw.close();
    }
}
