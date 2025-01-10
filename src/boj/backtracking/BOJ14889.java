package boj.backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14889 {
    static int[][] S;
    static boolean[] isTeamA;
    static int ret, N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        S = new int[N][N];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                S[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        isTeamA = new boolean[N];
        ret = Integer.MAX_VALUE;

        makeTeam(-1, N / 2);

        System.out.println(ret);
    }

    static void makeTeam(int last, int remain) {
        // 인원이 N / 2가 되면 양쪽 팀 능력치 계산 후 차이를 기록
        if (remain <= 0) {
            int stat = 0;

            for (int i = 0; i < isTeamA.length; ++i) {
                for (int j = i + 1; j < isTeamA.length; ++j) {
                    if (isTeamA[i] && isTeamA[j]) {
                        stat += S[i][j] + S[j][i];
                    } else if (!isTeamA[i] && !isTeamA[j]) {
                        stat -= S[i][j] + S[j][i];
                    }
                }
            }

            ret = Math.min(ret, Math.abs(stat));
            return;
        }

        for (int i = last + 1; i < N; i++) {
            if (!isTeamA[i]) {
                isTeamA[i] = true;
                makeTeam(i, remain - 1);
                isTeamA[i] = false;
            }
        }
    }
}

