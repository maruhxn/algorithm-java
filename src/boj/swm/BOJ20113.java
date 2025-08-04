package boj.swm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ20113 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] voted = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            voted[Integer.parseInt(st.nextToken())]++;
        }

        int target = -1;
        int max = -1;
        for (int i = 1; i < voted.length; i++) {
            if (voted[i] > max) {
                max = voted[i];
                target = i;
            }
        }

        int cnt = 0;
        for (int i = 1; i < voted.length; i++) {
            if (voted[i] == max) {
                ++cnt;
            }
        }

        if (cnt > 1) {
            System.out.println("skipped");
        } else {
            System.out.println(target);
        }
    }
}
