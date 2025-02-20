package boj.swm;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ1463 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int[] arr = new int[N + 1];
        Arrays.fill(arr, -1);

        Queue<Integer> queue = new LinkedList<>();
        queue.add(N);
        arr[N] = 0;
        while (!queue.isEmpty()) {
            Integer curr = queue.poll();

            for (int i = 0; i < 3; i++) {
                int nx = curr;

                if(i == 0 && curr % 3 == 0) nx /= 3;
                else if (i == 1 && curr % 2 == 0) nx /= 2;
                else if(i == 2 && curr > 1) --nx;

                if(arr[nx] != -1) continue;
                queue.offer(nx);
                arr[nx] = arr[curr] + 1;
            }
        }

        System.out.println(arr[1]);
    }
}
