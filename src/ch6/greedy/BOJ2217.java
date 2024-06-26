package ch6.greedy;

import java.util.Arrays;
import java.util.Scanner;

// 로프(S4)
public class BOJ2217 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(sc.next());
        }

        Arrays.sort(arr);

        int maxWeight = 0;
        for (int i = 0; i < n; i++) {
            int ableWeight =  arr[i] * (n - i);
            maxWeight = Math.max(maxWeight, ableWeight);
        }

        System.out.println(maxWeight);
    }
}
