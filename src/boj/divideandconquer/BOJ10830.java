package boj.divideandconquer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ10830 {
    static int N;
    static long B;
    static int[][] A, result, temp;
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        B = Long.parseLong(st.nextToken());

        A = new int[N][N];
        result = new int[N][N];
        temp = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
                result[i][j] = A[i][j];
            }
        }

        int[][] ret = fpow(B);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(ret[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    static int[][] fpow(long size) {
        if (size == 1) {
            int[][] ret = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    ret[i][j] = A[i][j] % 1000;
                }
            }
            return ret;
        }

        int[][] K = fpow(size / 2);

        if (size % 2 == 1) return multiply(multiply(K, K), A);
        else return multiply(K, K);
    }

    static int[][] multiply(int[][] s1, int[][] s2) {
        int[][] ret = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int temp = 0;
                for (int k = 0; k < N; k++) {
                    temp += s1[i][k] * s2[k][j];
                }
                ret[i][j] = temp % 1000;
            }
        }

        return ret;
    }


}
