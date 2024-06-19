package ch3.sliding_window;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 블로그(S3)

/**
 * 슬라이딩 윈도우, 구간합을 모두 사용해야 하는 문제.
 * 구간합을 사용하지 않고 for문을 통해 합을 구하면, 이중for문으로 인해 시간 초과 발생..
 */
public class BOJ21921 {
    // 시간 초과..
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        int n = Integer.parseInt(st.nextToken());
//        int x = Integer.parseInt(st.nextToken());
//        st = new StringTokenizer(br.readLine());
//        int[] arr = new int[n];
//        for (int i = 0; i < n; i++) {
//            arr[i] = Integer.parseInt(st.nextToken());
//        }
//
//        Map<Long, Integer> map = new HashMap<>(); // <sum, count>
//        for (int i = 0; i <= n - x; i++) {
//            long sum = 0;
//            for (int j = i; j < i + x; j++) {
//                sum += arr[j];
//            }
//
//            if (map.containsKey(sum)) {
//                map.put(sum, map.get(sum) + 1);
//            } else {
//                map.put(sum, 1);
//            }
//        }
//
//        Long max = Collections.max(map.keySet());
//        if (max == 0) {
//            System.out.println("SAD");
//        } else {
//            System.out.println(max);
//            System.out.println(map.get(max));
//        }
//    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = arr[i - 1] + Integer.parseInt(st.nextToken());
        }

        int left = 1;
        int right = x;
        long max = 0;
        int count = 0;
        while (right <= n) {
            long sum = arr[right] - arr[left - 1];
            if (max < sum) {
                max = sum;
                count = 1;
            } else if (max == sum) {
                count++;
            }

            left++;
            right++;
        }

        if (max == 0) {
            System.out.println("SAD");
        } else {
            System.out.println(max);
            System.out.println(count);
        }
    }
}
