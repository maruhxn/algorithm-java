package boj.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ1931 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] times = new int[N][2];
        for (int i = 0; i < N; i++) {
            String[] s = br.readLine().split(" ");
            times[i][0] = Integer.parseInt(s[0]);
            times[i][1] = Integer.parseInt(s[1]);
        }

        Arrays.sort(times, (a, b) -> {
            if (a[1] == b[1]) {
                return a[0] - b[0];
            }
            return a[1] - b[1];
        });

        int ret = 0;
        int prevEndTime = 0;
        for (int[] time : times) {
            if (time[0] >= prevEndTime) {
                prevEndTime = time[1];
                ret++;
            }
        }

        System.out.println(ret);
    }
}
