package boj.divideandconquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1992 {

    static int N;
    static char[][] quadTree;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        quadTree = new char[N][N];

        for (int i = 0; i < N; i++) {
            quadTree[i] = br.readLine().toCharArray();
        }

        zip(0, 0, N);
        System.out.println(sb);
    }

    private static void zip(int y, int x, int size) {
        if (checkAllSame(y, x, size)) {
            sb.append(quadTree[y][x]);
        } else {
            int nextSize = size / 2;
            sb.append('(');
            zip(y, x, nextSize);
            zip(y, x + nextSize, nextSize);
            zip(y + nextSize, x, nextSize);
            zip(y + nextSize, x + nextSize, nextSize);
            sb.append(')');
        }
    }

    private static boolean checkAllSame(int y, int x, int size) {
        for (int i = y; i < y + size; i++) {
            for (int j = x; j < x + size; j++) {
                if (quadTree[y][x] != quadTree[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }
}
