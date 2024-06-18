package ch3.two_pointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 좋은 수 구하기(G4)
public class p008 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        StringTokenizer st = new StringTokenizer(bf.readLine());
        long[] num = new long[n];
        for (int i = 0; i < n; i++) {
            num[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(num);

        int count = 0;
        for (int i = 0; i < num.length; i++) {
            long target = num[i];
            int left = 0;
            int right = n - 1;
            while (left < right) {
                long sum = num[left] + num[right];
                if (sum == target) {
                    if(left != i && right != i) {
                        count++;
                        break;
                    } else if (left == i) {
                        left++;
                    } else if (right == i) {
                        right--;
                    }
                } else if (sum > target) {
                    right--;
                } else {
                    left++;
                }
            }
        }

        System.out.println(count);
        bf.close();
    }
}
