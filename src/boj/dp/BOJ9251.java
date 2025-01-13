package boj.dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ9251 {
    static String A, B;
    static int[][] cache;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        A = br.readLine();
        B = br.readLine();

        cache = new int[A.length()][B.length()];
        for (int i = 0; i < A.length(); i++) {
            for (int j = 0; j < B.length(); j++) {
                cache[i][j] = -1;
            }
        }

        System.out.println(lcs(0, 0));
    }

    static int lcs(int index1, int index2) {
        if (index1 == A.length() || index2 == B.length()) return 0;
        if (A.charAt(index1) == B.charAt(index2)) return cache[index1][index2] = 1 + lcs(index1 + 1, index2 + 1);

        if (cache[index1][index2] != -1) return cache[index1][index2];

        return cache[index1][index2] = Math.max(lcs(index1, index2 + 1), lcs(index1 + 1, index2));
    }
}