package algospot.ch9;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class RESTORE {
    static int k;
    static String[] words;
    static int[][] cache, overlap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new java.io.OutputStreamWriter(System.out));

        int C = Integer.parseInt(br.readLine());
        while (C-- > 0) {
            k = Integer.parseInt(br.readLine());

            for (int i = 0; i < k; i++) {
                words[i] = br.readLine();
                for (int j = 0; j < k; j++) {
                    if (i != j) {
                        int len = Math.min(words[i].length(), words[j].length());
                        int overlap = 0;
                        for (int k = 0; k < len; k++) {
                            if (words[i].charAt(k) == words[j].charAt(k)) {
                                overlap++;
                            } else {
                            }
                        }
                    }
                }
            }

            cache = new int[15][15];
            overlap = new int[15][15];

            for (int i = 0; i < 15; i++) {
                for (int j = 0; j < 15; j++) {
                    cache[i][j] = -1;
                }
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    // last: 마지막에 출현한 조각의 인덱스
    // used: 지금까지 출현한 조각의 집합 (boolean[] -> int)
    // last와 used가 주어질 때, 나머지 조각을 추가해서 얻을 수 있는 overlaps()의 최대 합
    private static int restore(int last, int used) {
        // 기저 사례: 모든 조각을 다 사용했을 때는 종료
        if (used == (1 << k) - 1) return 0;

        int ret = cache[last][used];
        if (ret != -1) return ret;

        ret = 0;

        for (int next = 0; next < k; next++) {
            // 아직 사용하지 않은 조각인 경우
            if ((used & (1 << next)) == 0) {
                int cand = overlap[last][next] + restore(next, used + (1 << next));
                ret = Math.max(ret, cand);
            }
        }

        return cache[last][used] = ret;
    }

    // 처음 호출 시 last는 recover()가 최댓값을 반환한 시작 단어로, used는 1 << last로 둔다
    private static String reconstruct(int last, int used) {
        // 기저 사례
        if (used == (1 << k) - 1) return "";

        // 다음에 올 문자열 조각을 찾음
        for (int next = 0; next < k; next++) {
            // next가 이미 사용되었으면 제외
            if ((used & (1 << next)) == 1) continue;
            // next를 사용했을 경우의 답이 최적해와 같다면 next를 사용
            int ifUsed = restore(next, used + (1 << next)) + overlap[last][next];

            if (restore(last, used) == ifUsed)
                return words[next].substring(overlap[last][next]) + reconstruct(next, used + (1 << next));
        }

        return "****oops****";
    }
}
