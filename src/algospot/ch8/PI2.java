package algospot.ch8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PI2 {
    static String S;
    static int INF = 987654321;
    static int[] cache;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int c = Integer.parseInt(br.readLine());
        while (c-- > 0) {
            S = br.readLine().trim();
            cache = new int[10002];
            for (int i = 0; i < 10002; i++) {
                cache[i] = -1;
            }

            System.out.println(solve(0));
        }
    }

    // 수열 S[index..]를 외우는 방법 중 난이도의 최소 합을 출력
    public static int solve(int index) {
        // 기저 사례: 수열 끝에 도달
        if (index == S.length()) return 0;


        int ret = cache[index];
        if (ret != -1) return ret;

        ret = INF;

        for (int L = 3; L <= 5; L++) {
            if (index + L <= S.length()) {
                ret = Math.min(ret, classify(index, index + L) + solve(index + L));
            }
        }

        return cache[index] = ret;
    }

    // S[a..b] 구간의 난이도 반환
    private static int classify(int a, int b) {
        String M = S.substring(a, b); // 부분 문자열

        String str = "";
        for (int i = 0; i < M.length(); i++) {
            str += M.charAt(0);
        }

        // 첫 글자만으로 이루어진 문자열과 같으면 난이도는 1
        if (M.equals(str)) return 1;

        // 등차수열 검사
        boolean progressive = true;
        for (int i = 0; i < M.length() - 1; i++) {
            if (M.charAt(i + 1) - M.charAt(i) != M.charAt(1) - M.charAt(0)) progressive = false;
        }

        // 등차수열이고, 공차가 1 혹은 -1이면 난이도는 2
        if (progressive && Math.abs(M.charAt(1) - M.charAt(0)) == 1) return 2;

        // 번갈아 등장하는지 확인
        boolean alternating = true;
        for (int i = 0; i < M.length(); i++) {
            if (M.charAt(i) != M.charAt(i % 2)) alternating = false;
        }

        if (alternating) return 4;
        if (progressive) return 5;

        return 10;
    }
}
