package ch7.euclidean_algorithm;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

// 최대 공약수 구하기(S1)
public class p043 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        long a = sc.nextLong();
        long b = sc.nextLong();

        long gcd = gcd(a, b);
        while (gcd-- > 0) {
            bw.append("1");
        }

        bw.flush();
        bw.close();
    }

    private static long gcd(long a, long b) {
        if(b == 0) return a;
        else return gcd(b, a % b);
    }
}
