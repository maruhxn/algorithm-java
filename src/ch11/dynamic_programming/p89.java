package ch11.dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 연속합2(G5)
public class p89 {
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 오른쪽 방향으로 index를 포함한 최대 연속 합 구하기
        int[] L = new int[n];
        L[0] = arr[0];
        int result = L[0];
        for (int i = 1; i < n; i++) {
            L[i] = Math.max(arr[i], L[i - 1] + arr[i]);
            result = Math.max(result, L[i]); // 1개도 제거하지 않았을 때를 기본 최댓값으로 저장
        }

        // 왼쪽 방향으로 index를 포함한 최대 연속 합 구하기
        int[] R = new int[n];
        R[n - 1] = arr[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            R[i] = Math.max(arr[i], R[i + 1] + arr[i]);
        }

        // L[i-1] + R[i+1] 2개의 구간 합 배열을 더하면 i번째 값을 제거한 효과를 얻음
        for (int i = 1; i < n - 1; i++) {
            int temp = L[i - 1] + R[i + 1];
            result = Math.max(result, temp);
        }

        System.out.println(result);
    }
}
