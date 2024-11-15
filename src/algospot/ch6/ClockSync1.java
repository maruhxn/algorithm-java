package algospot.ch6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ClockSync1 {

    final static int MAX_VALUE = 987654321;
    static int[] clocks = new int[16];
    static int[][] switches = {
            {0, 1, 2},
            {3, 7, 9, 11},
            {4, 10, 14, 15},
            {0, 4, 5, 6, 7},
            {6, 7, 8, 10, 12},
            {0, 2, 14, 15},
            {3, 14, 15},
            {4, 5, 7, 14, 15},
            {1, 2, 3, 4, 5},
            {3, 4, 5, 9, 13},
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int c = Integer.parseInt(br.readLine());
        while (c-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int i = 0; i < 16; i++) {
                clocks[i] = (12 - Integer.parseInt(st.nextToken())) / 3;
            }

            int result = solve(clocks, 0);
            System.out.println(result == MAX_VALUE ? -1 : result);
        }
    }


    // 남은 스위치들을 눌러서 clocks를 12시로 맞출 수 있는 최소 횟수를 반환한다
    public static int solve(int[] clocks, int swtch) {
        // 마지막 스위치에 도달했을 때, 정렬 여부를 확인하고 종료한다.
        if (swtch == 10) return allAligned(clocks) ? 0 : MAX_VALUE;

        int ret = MAX_VALUE;

        // 특정 스위치를 0 ~ 3번 누르고, 남은 스위치들에 대해서도 같은 작업을 반복한다.
        for (int cnt = 0; cnt < 4; cnt++) {
            ret = Math.min(ret, cnt + solve(clocks, swtch + 1));
            int[] selectedSwitch = switches[swtch];
            for (int i = 0; i < selectedSwitch.length; i++) {
                clocks[selectedSwitch[i]]--;
                if (clocks[selectedSwitch[i]] == -1) clocks[selectedSwitch[i]] = 3;
            }
        }

        return ret;
    }

    private static boolean allAligned(int[] clocks) {
        for (int i = 0; i < clocks.length; i++) {
            if (clocks[i] != 0) return false;
        }

        return true;
    }
}

/**
 *
 */
