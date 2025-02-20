package boj.swm;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ11057 {
    static int N;
    static int[][] cache;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        cache = new int[10][N];

        for (int i = 0; i < 10; i++) {
            Arrays.fill(cache[i], -1);
        }

        System.out.println(permutation(0, 0));
    }

    private static int permutation(int start, int depth) {
        if(depth == N) return 1;

        if(cache[start][depth] != -1) return cache[start][depth];

        int ret = 0;
        for (int i = start; i < 10; i++) {
            ret += permutation(i, depth + 1) % 10007;
        }

        return cache[start][depth] = ret % 10007;
    }
}
