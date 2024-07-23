package ch11.dynamic_programming;

import java.util.Scanner;


// 정수를 1로 만들기(S3)
public class p084 {
    static int[] arr;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        arr = new int[n + 1];
        arr[1] = 0;

        for (int i = 2; i <= n; i++) {
            arr[i] = arr[i - 1] + 1;
            if (i % 2 == 0) arr[i] = Math.min(arr[i], arr[i / 2] + 1);
            if (i % 3 == 0) arr[i] = Math.min(arr[i], arr[i / 3] + 1);
        }

        System.out.println(arr[n]);
    }
}
