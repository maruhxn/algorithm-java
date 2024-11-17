package algospot.ch7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FanMeeting1 {

    static char[] members;
    static char[] fans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int C = Integer.parseInt(br.readLine());

        while (C-- > 0) {
            members = br.readLine().toCharArray();
            fans = br.readLine().toCharArray();

            System.out.println(solve());
        }
    }

    public static int solve() {
        int left = 0, right = members.length - 1;

        int ret = 0;

        while (right < fans.length) {
            boolean ok = false;

            for (int i = 0; i < members.length; i++) {
                if (members[i] == 'M' && fans[left + i] == 'M') {
                    ok = false;
                    break;
                }
                ok = true;
            }

            if (ok) ret++;

            left++;
            right++;
        }

        return ret;
    }
}