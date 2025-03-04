package boj.swm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ10816_2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int M = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M; i++) {
            int key = Integer.parseInt(st.nextToken());
            sb.append(upperBound(arr, key) - lowerBound(arr, key)).append(" ");
        }

        System.out.println(sb);
    }

    // 찾고자 하는 값 이상의 값이 처음으로 나타나는 위치
    private static int lowerBound(int[] arr, int key) {
        int left = 0, right = arr.length;

        while (left < right) {
            int mid = (left + right) / 2;
            if (arr[mid] >= key) right = mid;
            else left = mid + 1;
        }

        return left;
    }

    // 찾고자 하는 값을 초과한 값을 처음 만나는 위치
    private static int upperBound(int[] arr, int key) {
        int left = 0, right = arr.length;

        while (left < right) {
            int mid = (left + right) / 2;
            if (arr[mid] > key) right = mid;
            else left = mid + 1;
        }

        return left;
    }
}
