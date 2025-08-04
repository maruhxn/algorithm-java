package boj.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ9251_2 {

    static String first, second;
    static int[][] cache;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        first = br.readLine();
        second = br.readLine();
        cache = new int[first.length()][second.length()];
        for (int i = 0; i < first.length(); i++) {
            Arrays.fill(cache[i], -1);
        }

        System.out.println(solve(0, 0));
    }

    private static int solve(int i1, int i2) {
        if (i1 == first.length() || i2 == second.length()) return 0;
        if (cache[i1][i2] != -1) return cache[i1][i2];

        if (first.charAt(i1) == second.charAt(i2)) return cache[i1][i2] = 1 + solve(i1 + 1, i2 + 1);
        else return cache[i1][i2] = Math.max(solve(i1 + 1, i2), solve(i1, i2 + 1));
    }
}
