package boj.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ1541_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String formula = br.readLine();
        String[] target = formula.split("-");

        int ret = getSubSplitSum(target[0]);
        for (int i = 1; i < target.length; i++) {
            ret -= getSubSplitSum(target[i]);
        }

        System.out.println(ret);
    }

    private static int getSubSplitSum(String target) {
        return Arrays.stream(target.split("[+]"))
                .mapToInt(Integer::parseInt).sum();
    }
}
