package ch7.prime_number;

import java.util.Scanner;

// 거의 소수 구하기(G5)

/**
 * 최대 범위에 해당하는 모든 소수를 구해놓고, 이 소수들이 입력된 A와 B 사이에 존재하는지 판단
 * 10^14가 최대이지만, 우리가 찾으려는 거의 소수는 2보다 큰 N 제곱 수를 찾아야 하므로,
 * 실제로 관심있는 값의 범위는 2 ~ 10^7이다.
 */
public class p038 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long a = sc.nextLong();
        long b = sc.nextLong();
        sc.close();


        boolean[] isNotPrime = new boolean[10000001];

        isNotPrime[0] = true;
        isNotPrime[1] = true;

        for (int i = 2; i <= Math.sqrt(isNotPrime.length); i++) {
            if (isNotPrime[i]) continue;
            for (int j = i * i; j < isNotPrime.length; j += i) {
                isNotPrime[j] = true;
            }
        }

        int result = 0;
        for (int i = 2; i < 10000001; i++) {
            if (!isNotPrime[i]) {
                long temp = i;
                while ((double) i <= (double) b / (double) temp) {
                    if ((double) i >= (double) a / (double) temp) {
                        result++;
                    }
                    temp *= i;

                }
            }
        }

        System.out.println(result);
    }
}
