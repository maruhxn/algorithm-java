package boj.divideandconquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 샤워실 바닥 깔기(ㄹㅇ 분할정복..?)
public class BOJ14600_2 {

    static int[][] map;
    static int x, y, N;
    static int num;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        N = (int) Math.pow(2, N);
        map = new int[N + 1][N + 1];
        st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());

        map[y][x] = -1;

        calc(1, 1, N);

        for (int i = N; i >= 1; i--) {
            for (int j = 1; j <= N; j++) {
                System.out.printf(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void calc(int x, int y, int size) {
        num++;
        int ns = size / 2;
        if (check(x, y, ns)) map[x + ns - 1][y + ns - 1] = num;
        if (check(x + ns, y, ns)) map[x + ns][y + ns - 1] = num;
        if (check(x, y + ns, ns)) map[x + ns - 1][y + ns] = num;
        if (check(x + ns, y + ns, ns)) map[x + ns][y + ns] = num;

        if (size == 2) return;

        calc(x, y, ns);
        calc(x + ns, y, ns);
        calc(x, y + ns, ns);
        calc(x + ns, y + ns, ns);

    }

    public static boolean check(int x, int y, int s) {
        for (int i = x; i < x + s; i++) {
            for (int j = y; j < y + s; j++) {
                if (map[i][j] != 0) return false;
            }
        }

        return true;
    }
}
