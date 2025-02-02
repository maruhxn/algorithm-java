package boj.twopointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2470 {
//    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        int N = Integer.parseInt(br.readLine());
//        long[] arr = new long[N];
//
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        for (int i = 0; i < N; i++) {
//            arr[i] = Long.parseLong(st.nextToken());
//        }
//
//        Arrays.sort(arr);
//        Pair minPair = new Pair(2_000_000_000, 0, 0);
//        int i = 0;
//        int j = N - 1;
//        while (i < j) {
//            long abs = Math.abs(arr[i] + arr[j]);
//
//            if (abs < minPair.v) {
//                minPair = new Pair(abs, arr[i], arr[j]);
//            }
//
//            if (Math.abs(arr[i + 1] + arr[j]) > Math.abs(arr[i] + arr[j - 1])) j--;
//            else i++;
//
//        }
//
//        System.out.println(minPair.x + " " + minPair.y);
//    }
//
//    static class Pair {
//        long v, x, y;
//
//        Pair(long v, long x, long y) {
//            this.v = v;
//            this.x = x;
//            this.y = y;
//        }
//    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int[] res = new int[2];

        int s = 0, e = n - 1, min = Integer.MAX_VALUE;
        Arrays.sort(arr);

        while (s < e) {
            int sum = arr[s] + arr[e];

            if (min > Math.abs(sum)) {
                min = Math.abs(sum);

                res[0] = arr[s];
                res[1] = arr[e];

                if (sum == 0) break;
            }

            if (sum < 0) s++;
            else e--;
        }

        System.out.println(res[0] + " " + res[1]);
    }
}