package ch5.binary_search;

import java.util.Arrays;
import java.util.Scanner;

// 공유기 설치(G4)
public class BOJ2110 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int c = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        Arrays.sort(arr);

        int left = 1;
        int right = arr[n - 1] -  arr[0];

        while (left <= right) {
            int mid = (left + right) /2;

            // 첫번째 집은 무조건 설치한다고 가정
            int count = 1;
            int last = arr[0];
            for (int i = 1; i < arr.length; i++) {
                int curr = arr[i];
                if (curr - last >= mid) {
                    /**
                     * 현재 탐색하는 집과 직전 설치된 집 간의 거리가
                     * 최소 거리(mid)보다 크거나 같을 때 공유기 설치 개수 늘리고
                     * 마지막 설치 위치 갱신
                     */
                    count++;
                    last = curr;
                }
            }

            if (count < c) {
                /**
                 * mid 거리에 설치 가능한 공유기 개수가 c개에 못 미치면
                 * 거리를 더 좁혀야 하므로 right를 줄인다.
                 */
                right = mid - 1;
            } else {
                /**
                 * 설치 가능한 공유기 개수가 c보다 크거나 같으면
                 * 거리를 더 벌려 최소 거리가 가질 수 있는 최대 거리를 찾아낸다.
                 */
                left = mid + 1;
            }
        }

        System.out.println(right);
    }
}
