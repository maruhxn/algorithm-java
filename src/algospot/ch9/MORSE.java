package algospot.ch9;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class MORSE {
    static int n, m, k, skip;

    static BufferedWriter bw;
    // K의 최댓값 + 100. 오버플로를 막기 위해 이보다 큰 값은 구하지 않는다
    static final int M = 1_000_000_000 + 100;
    static int[][] bino;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
        bw = new BufferedWriter(new java.io.OutputStreamWriter(System.out));
        int C = Integer.parseInt(br.readLine());
        while (C-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken()); // 1 <= n <= 100
            m = Integer.parseInt(st.nextToken()); // 1 <= m <= 100
            k = Integer.parseInt(st.nextToken()); // 1 <= k <= 1_000_000_000

            bino = new int[201][201];
            for (int i = 0; i <= 200; i++) {
                bino[i][0] = bino[i][i] = 1;
                for (int j = 1; j < i; j++) {
                    bino[i][j] = Math.min(M, bino[i - 1][j - 1] + bino[i - 1][j]);
                }
            }

            skip = k - 1;

            System.out.println(kth(n, m, skip));
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static void morse2(int n, int m, String s) throws IOException {
        if (skip < 0) return;
        if (n == 0 && m == 0) {
            if (skip == 0) bw.append(s + "\n");
            --skip;
            return;
        }

        if (n > 0) morse2(n - 1, m, s + "-");
        if (m > 0) morse2(n, m - 1, s + "o");
    }

    private static void morse3(int n, int m, String s) throws IOException {
        if (skip < 0) return;
        if (n == 0 && m == 0) {
            if (skip == 0) bw.append(s + "\n");
            --skip;
            return;
        }

        if (bino[n + m][m] <= skip) {
            skip -= bino[n + m][m];
            return;
        }

        if (n > 0) morse3(n - 1, m, s + "-");
        if (m > 0) morse3(n, m - 1, s + "o");
    }

    // n개의 -, m개의 o로 구성된 신호 중 skip 개를 건ㄴ너뛰고 만들어지는 신호를 반환
    private static String kth(int n, int m, int skip) {
        // n==0인 경우, 나머지 부분은 전부 o일 수밖에 없음
        if (n == 0) {
            String s = "";
            for (int i = 0; i < m; i++) s += "o";
            return s;
        }

        if (skip < bino[n + m - 1][n - 1]) {
            return "-" + kth(n - 1, m, skip);
        }

        return "o" + kth(n, m - 1, skip - bino[n + m - 1][n - 1]);
    }
}
