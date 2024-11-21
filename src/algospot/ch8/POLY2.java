package algospot.ch8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class POLY2 {

    static int MOD = 10_000_000;
    static int[][] cache;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int c = Integer.parseInt(br.readLine());
        while (c-- > 0) {
            int n = Integer.parseInt(br.readLine());
            cache = new int[101][101];

            for (int i = 0; i < 101; i++) {
                for (int j = 0; j < 101; j++) {
                    cache[i][j] = -1;
                }
            }

            int result = 0;

            for (int i = 1; i <= n; i++) {
                result += poly(n, i);
                if (i != 1) {
                    result %= MOD;
                }
            }

            System.out.println(result);
        }
    }

    public static int poly(int n, int first) {
        if (n == first) return 1;

        int ret = cache[n][first];
        if (ret != -1) return ret;

        ret = 0;

        for (int second = 1; second <= n - first; second++) {
            int add = second + first - 1;
            add *= poly(n - first, second);
            add %= MOD;
            ret += add;
            ret %= MOD;
        }

        return cache[n][first] = ret;
    }
}

/**
 * 너무 어렵게 생각하는 것 같다. 아이디어를 떠올리는 것 까지는 좋았으나,
 * 첫 번째 줄(first)과 두 번째 줄(second)만 신경쓰면 되는 문제였는데, 모든 경우의 수를 직접 계산하며 규칙을 찾으려고 하니 산으로 가버린 느낌..
 * 완전 탐색은 내가 진짜 다 탐색해보며 규칙을 발견하는 것이 아니라, 잘 풀릴 것이라고 기대하며 조금은 추상적으로 생각하도록 하자. 모든 경우에 대한 모든 처리 방법을 내가 다 찾는게 아니다.
 */