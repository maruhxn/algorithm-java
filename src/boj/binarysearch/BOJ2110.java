package boj.binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2110 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int[] points = new int[N];
        for (int i = 0; i < N; i++) {
            points[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(points);

        int left = 1;
        int right = points[N - 1] - points[0] + 1;
        int mid;

        while (left < right) {
            mid = (left + right) / 2; // 최소 거리

            int cnt = 1;
            int lastLocate = points[0]; // 반드시 첫 집에는 공유기를 둔다고 가정
            for (int i = 1; i < points.length; i++) {
                if (points[i] - lastLocate >= mid) {
                    ++cnt;
                    lastLocate = points[i];
                }
            }

            // 최소 거리가 mid일 때, 설치가능한 공유기 수가 C보다 작다면
            if (cnt < C) right = mid;
            else left = mid + 1;
        }

        System.out.println(left - 1);
    }
}