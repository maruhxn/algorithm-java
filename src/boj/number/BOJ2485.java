package boj.number;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ2485 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        List<Integer> diffs = new ArrayList<>();

        int firstValue = Integer.parseInt(br.readLine());
        int prevValue = Integer.parseInt(br.readLine());
        int prevDiff = Math.abs(firstValue - prevValue);
        diffs.add(prevDiff);

        int lastGCD = prevDiff;

        for (int i = 0; i < N - 2; i++) {
            int currValue = Integer.parseInt(br.readLine());
            int diff = Math.abs(prevValue - currValue);
            diffs.add(diff);
            lastGCD = gcd(lastGCD, diff);
            prevValue = currValue;
        }

        int result = 0;
        for (Integer diff : diffs) {
            if(diff != lastGCD) result += diff / lastGCD - 1;
        }

        System.out.println(result);
    }

    public static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}

// 1 3 7 13
//  2 4 6

// 1 3 7 14
//  2 4 7

// 2 6 12 18
//  4 6  6