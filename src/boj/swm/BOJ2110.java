package boj.swm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2110 {

    static int[] house;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        house = new int[N];

        for (int i = 0; i < N; i++) {
            house[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(house);

        // 우리가 찾아야 하는 것은 '최소 거리에 대해 설치 가능한 공유기'가 문제에서 주어지는 '설치 해야 할 공유기의 개수(M)'와 같은 거리 중 최대로 가질 수 있는 '최소 거리'를 찾는 것

        int left = 1, right = house[N - 1] - house[0] + 1, mid = 0;

        while (left < right) {
            mid = (left + right) / 2;

            if (getInstallableCnt(mid) < C) right = mid;
            else left = mid + 1;
        }

        System.out.println(left - 1);
    }

    private static int getInstallableCnt(int distance) {
        int count = 1;
        int lastLocate = house[0];

        for (int i = 1; i < house.length; i++) {
            if (house[i] - lastLocate >= distance) {
                ++count;
                lastLocate = house[i];
            }
        }

        return count;
    }
}
