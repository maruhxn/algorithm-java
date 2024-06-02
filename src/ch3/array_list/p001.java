package ch3.array_list;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 숫자의 합 구하기
public class p001 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        char[] num = br.readLine().toCharArray();
        long sum = 0;

        for (int i = 0; i < n; i++) {
            sum += num[i] - '0';
        }

        System.out.println(sum);
    }
}
