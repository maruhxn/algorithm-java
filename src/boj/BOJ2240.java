package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2240 {

    static int T, W;
    static int[] dropOrder;
    static int[][] cache;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        dropOrder = new int[T + 1];
        cache = new int[W + 1][T + 1];

        for (int i = 0; i <= W; i++) {
            Arrays.fill(cache[i], -1);
        }

        for (int i = 1; i <= T; ++i) {
            dropOrder[i] = Integer.parseInt(br.readLine());
        }

        System.out.println(catchAt(0, 1)); // 초기 위치 1번 나무에서 시작
    }

    static int catchAt(int moveCnt, int time) {
        if (time > T) return 0; // 시간 초과 시 종료

        if (cache[moveCnt][time] != -1) return cache[moveCnt][time];

        int pos = (moveCnt % 2) + 1; // 현재 위치 (1번 또는 2번 나무)
        int ret = catchAt(moveCnt, time + 1); // 이동하지 않는 경우

        if (moveCnt < W) { // 이동할 수 있는 경우
            ret = Math.max(ret, catchAt(moveCnt + 1, time + 1));
        }

        if (dropOrder[time] == pos) { // 현재 위치에서 사과를 받는 경우
            ret++;
        }

        return cache[moveCnt][time] = ret;
    }
}
