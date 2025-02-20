package boj.swm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ5525 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // O의 수
        int M = Integer.parseInt(br.readLine());
        String S = br.readLine();

        int ret = 0;

        for (int i = 0; i < M; i++) {
            if (S.charAt(i) != 'I') continue;

            int idx = i;

            int cnt = 0;

            while (idx + 2 < M) {
                if (S.charAt(idx + 1) != 'O' || S.charAt(idx + 2) != 'I') break;

                ++cnt;
                idx += 2;

                if (cnt == N) {
                    --cnt;
                    ++ret;
                }
            }

            i = idx;
        }
        System.out.println(ret);
    }
}
