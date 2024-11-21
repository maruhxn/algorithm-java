package boj.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// LCS 2 실패..
public class BOJ9252 {

    static int[][] cache;
    static String A, B;
    static StringBuilder result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        A = br.readLine();
        B = br.readLine();
        cache = new int[A.length() + 1][B.length() + 1];

        for (int i = 1; i <= A.length(); i++) {
            for (int j = 1; j <= B.length(); j++) {
                cache[i][j] = -1;
            }
        }

        int lcsLength = LCS(A.length(), B.length());

        result = new StringBuilder();
        getResult(A.length(), B.length());

        System.out.println(lcsLength);
        if (lcsLength > 0) {
            System.out.println(result.toString());
        }
    }

    // 두 문자열의 현재 위치를 비교하며 최장 공통 부분 수열의 길이를 계산
    public static int LCS(int r, int c) {
        if (r == 0 || c == 0) return 0;

        int ret = cache[r][c];
        if (ret != -1) return ret;

        if (A.charAt(r - 1) == B.charAt(c - 1)) {
            ret = 1 + LCS(r - 1, c - 1);
        } else {
            ret = Math.max(LCS(r - 1, c), LCS(r, c - 1));
        }

        return cache[r][c] = ret;
    }

    static void getResult(int r, int c) {
        if (r == 0 || c == 0) return;

        if (A.charAt(r - 1) == B.charAt(c - 1)) {
            getResult(r - 1, c - 1);
            result.append(A.charAt(r - 1));
        } else {
            if (cache[r - 1][c] > cache[r][c - 1]) {
                getResult(r - 1, c);
            } else {
                getResult(r, c - 1);
            }
        }
    }
}
