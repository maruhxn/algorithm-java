package ch3.two_pointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 부분합(G4)
public class BOJ1806 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int minLen = n + 1;
        int low = 0;
        int high = 0;
        long sum = arr[low];
        boolean ok = false;
        while (low <= high && high < n) {
            if (sum < s) {
                if (high < n - 1) {
                    sum += arr[++high];
                } else {
                    break;
                }
            } else {
                int len = high - low + 1;
                if (minLen > len) {
                    ok = true;
                    minLen = len;
                }
                sum -= arr[low++];
            }
        }

        if (ok) System.out.println(minLen);
        else System.out.println(0);
    }
}
