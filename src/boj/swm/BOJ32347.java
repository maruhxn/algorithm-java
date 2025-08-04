package boj.swm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ32347 {
    static int[] isOn;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        isOn = new int[N];
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            isOn[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0, right = N;
        while (left < right) {
            int mid = (left + right) / 2;
            if (canReach(mid, K)) right = mid;
            else left = mid + 1;
        }

        System.out.println(left);
    }

    // T로 타임머신을 최대 K번 사용해 1일에 도착 가능한지 조사
    private static boolean canReach(int T, int K) {
        int current = isOn.length - 1;
        while (K > 0) {
            int next = getNext(current, T); // 타임머신 사용 후 도착할 위치

            if (next == -1) return false; // 불가능
            else if (next == 0) return true; // 1일 도착 성공

            K--;
            current = next; // current 갱신
        }
        return false;
    }

    // start에서 T일 전으로 이동 가능한 가장 가까운 전원이 켜진 날을 찾음
    private static int getNext(int start, int T) {
        int result = -1;
        if (start - T <= 0) return 0;

        int end = start - T;
        for (int i = start - 1; i >= end; i--) {
            if (isOn[i] == 1) {
                result = i;
            }
        }
        return result;
    }

}
