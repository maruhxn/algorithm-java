package boj.recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 별찍기

/**
 * f(n)은 f(n - 1)을 3번 호출하면 됨. 이때, 호출하는 기준점(삼각형의 상단 꼭짓점)은 달라져야 함.
 * n > 3일 때, f(n)에서의 최상단 꼭짓점이 (y, x)라고 할 때, f(n - 1)에서 중심 3개는 다음과 같음
 * -> (y, x), (y + n/2, x - n/2), (y + n/2, x + n/2)
 * 가로 최댓값: 2 * N - 1 (배열에서 행의 최대 길이)
 */
public class BOJ2448 {

    static int N;
    static StringBuilder sb;
    static char[][] stars;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
        sb = new StringBuilder();
        stars = new char[N][2 * N - 1];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 2 * N - 1; j++) {
                stars[i][j] = ' ';
            }
        }

        solve(N, 0, N - 1); // 0번째 행에서의 중심값을 전달

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 2 * N - 1; j++) {
                sb.append(stars[i][j]);
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    private static void solve(int n, int centerY, int centerX) {
        drawStar(centerY, centerX);

        if (n == 3) return;

        int halfN = n / 2;
        solve(halfN, centerY, centerX);
        solve(halfN, centerY + halfN, centerX - halfN);
        solve(halfN, centerY + halfN, centerX + halfN);
    }

    private static void drawStar(int y, int x) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                if (i == 0 && j == 2) stars[y + i][x + j - 2] = '*';
                else if (i == 1 && (j == 1 || j == 3)) stars[y + i][x + j - 2] = '*';
                if (i == 2) stars[y + i][x + j - 2] = '*';
            }
        }
    }
}
