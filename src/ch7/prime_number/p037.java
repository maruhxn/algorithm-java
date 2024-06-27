package ch7.prime_number;

import java.util.Scanner;

// 소수 구하기(S3)
public class p037 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        sc.close();
        boolean[] isNotPrime = new boolean[n + 1];

        isNotPrime[0] = true;
        isNotPrime[1] = true;

        for (int i = 2; i <= Math.sqrt(n); i++) {
            if(isNotPrime[i]) continue;
            for (int j = i * i; j <= n; j += i) {
                isNotPrime[j] = true;
            }
        }

        for (int i = m; i <= n; i++) {
            if(!isNotPrime[i]) System.out.println(i);
        }
    }
}
