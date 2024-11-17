package algospot.ch7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Fence1 {

    static int[] h;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int C = Integer.parseInt(br.readLine());

        while (C-- > 0) {
            int n = Integer.parseInt(br.readLine());
            h = new int[n + 1];
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int i = 0; i < n; i++) {
                h[i] = Integer.parseInt(st.nextToken());
            }

            System.out.println(solve(0, n - 1));
        }
    }

    public static int solve(int left, int right) {
        if (left == right) return h[left];

        int mid = (left + right) / 2;

        int ret = Math.max(solve(left, mid), solve(mid + 1, right));

        int lo = mid, hi = mid + 1;
        int height = Math.min(h[lo], h[hi]);
        ret = Math.max(ret, height * 2); // [mid, mid + 1]만 포함하는 너비 2인 사각형을 고려한다.

        // 사각형이 입력 전체를 덮을 때까지 확장
        while (left < lo || hi < right) {
            // 항상 h[lo - 1]과 h[hi + 1] 중 높이가 더 높은 쪽으로 확장
            if (hi < right && (lo == left) || h[lo - 1] < h[hi + 1]) { // h[hi + 1]이 더 높이가 높은 경우
                ++hi;
                height = Math.min(height, h[hi]);
            } else { // h[lo - 1]이 더 높이가 높은 경우
                --lo;
                height = Math.min(height, h[lo]);
            }

            // 확장 후 사각형의 넓이
            ret = Math.max(ret, height * (hi - lo + 1));
        }

        return ret;
    }
}
