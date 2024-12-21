package algospot.ch14;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PrimeNumber {

    static boolean[] isPrime;
    // minFactor[i] = i의 가장 작은 소인수(i가 소수인 경우 자기 자신)
    static int[] minFactor;

    public static boolean isPrime(int n) {
        if (n <= 1) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;

        int sqrtN = (int) Math.sqrt(n);
        for (int i = 3; i < sqrtN; i += 2) {
            if (n % i == 0) return false;
        }

        return true;
    }

    public static List<Integer> getFactorSimple(int n) {
        List<Integer> factors = new ArrayList<>();

        int sqrtN = (int) Math.sqrt(n);
        for (int div = 2; div < sqrtN; div++) {
            while (n % div == 0) {
                factors.add(div);
                n /= div;
            }
        }

        if (n > 1) factors.add(n);

        return factors;
    }

    // n 이하의 소수들을 모두 찾기
    public static void eratosthenes(int n) {
        isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);

        isPrime[0] = isPrime[1] = false;
        int sqrtN = (int) Math.sqrt(n);

        for (int i = 2; i < sqrtN; i++) {
            // 이미 지워졌다면(이미 소수가 아님이 밝혀졌다면)
            if (!isPrime[i]) continue;

            for (int j = i * i; j <= n; j += i) {
                isPrime[j] = false;
            }
        }
    }

    public static void eratosthenes2(int n) {
        minFactor = new int[n + 1];
        minFactor[0] = minFactor[1] = -1;
        for (int i = 2; i <= n; i++) {
            minFactor[i] = i;
        }

        int sqrtN = (int) Math.sqrt(n);
        for (int i = 2; i < sqrtN; i++) {
            if (minFactor[i] != i) continue;

            for (int j = i * i; j <= n; j += i) {
                if (minFactor[j] == j) minFactor[j] = i;
            }
        }
    }

    public static List<Integer> getFactor(int n) {
        List<Integer> factors = new ArrayList<>();

        while (n > 1) {
            factors.add(minFactor[n]);
            n /= minFactor[n];
        }

        return factors;
    }

    public static void main(String[] args) {
        for (int i : getFactorSimple(28)) {
            System.out.println(i);
        }

        eratosthenes(28);

        for (int i = 0; i < isPrime.length; i++) {
            if (isPrime[i]) System.out.printf("%d ", i);
        }

        System.out.println();

        eratosthenes2(28);

        for (int i : getFactor(28)) {
            System.out.println(i);
        }
    }

}
