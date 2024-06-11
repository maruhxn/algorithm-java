package ch5.binary_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 기타 레슨(S1)
public class p030 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] A = new int[n];
        int start = 0;
        int end = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
            if (start < A[i]) start = A[i]; // start: 레슨 최댓값
            end += A[i]; // end: 모든 레슨의 총합
        }

        while (start <= end) {
            int mid = (start + end) / 2;
            int sum = 0;
            int count = 1;
            for (int i = 0; i < n; i++) {
                if (sum + A[i] > mid) { // 현재 블루레이에 저장할 수 없어 새로운 블루레이로 교체
                    sum = 0;
                    count++;
                }
                sum += A[i];
            }
            if (count > m) { // 중간 인덱스로 모든 레슨 저장 불가능 -> 블루레이 크기를 줄일 필요
                start = mid + 1;
            } else { // 중간 인덱스로 모든 레슨 저장 가능 -> 최소 크기를 구하기 위해 블루레이 크기를 늘려봄.
                end = mid - 1;
            }
        }

        System.out.println(start);
    }
}
