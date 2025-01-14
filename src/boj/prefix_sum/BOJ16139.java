package boj.prefix_sum;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ16139 {

    static int[][] alphaSum;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String S = br.readLine();

        alphaSum = new int[26][S.length()];
        boolean[] isCalculated = new boolean[26];

        int q = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            char alpha = st.nextToken().charAt(0);
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            int charInt = alpha - 'a';

            if (!isCalculated[charInt]) {
                int cnt = 0;
                for (int j = 0; j < S.length(); j++) {
                    if (S.charAt(j) == alpha) cnt++;
                    alphaSum[charInt][j] = cnt;
                }
                isCalculated[charInt] = true;
            }

            sb.append(start == 0 ? alphaSum[charInt][end] : alphaSum[charInt][end] - alphaSum[charInt][start - 1]).append("\n");
        }
        System.out.println(sb);
    }
}

/**
 * 6~10 = (0 ~ 10) - (0 ~ 6)
 * pSum[i] : 0부터 i번째 위치까지 a가 등장하는 횟수
 * pSum은 어케 만들지?
 */
