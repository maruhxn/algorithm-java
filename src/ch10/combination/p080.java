package ch10.combination;

import java.util.Scanner;

// 조약돌 꺼내기(S3)
public class p080 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int[] D = new int[m + 1];

        int sum = 0;
        for (int i = 1; i <= m; i++) {
            int cnt = sc.nextInt();
            D[i] = cnt;
            sum += cnt;
        }
        int k = sc.nextInt();

        double result = 0.0;
        for (int i = 1; i <= m; i++) {
            double p = 1.0;
            for (int j = 0; j < k; j++) {
                p *= (double) (D[i] - j) / (sum - j);
            }

            result += p;
        }

        System.out.println(result);
    }
}
