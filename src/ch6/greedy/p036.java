package ch6.greedy;

// 최솟값을 만드는 괄호 배치 찾기(S2)

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * + 또는 -가 나오기 전까지의 문자를 parseInt
 * -가 나오면 다음 -가 나올 때까지 나오는 모든 수 더하기.
 */
public class p036 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        int result = 0;
        if (str.contains("-")) {
            String[] sub = str.split("\\-");
            for (int i = 0; i < sub.length; i++) {
                if (sub[i].contains("+")) {
                    String[] numbers = sub[i].split("\\+");
                    int sum = 0;
                    for (String number : numbers) {
                        sum += Integer.parseInt(number);
                        sub[i] = String.valueOf(sum);
                    }
                }
                if (i == 0) result += Integer.parseInt(sub[i]);
                else result -= Integer.parseInt(sub[i]);
            }
        } else {
            String[] numbers = str.split("\\+");
            for (String number : numbers) {
                result += Integer.parseInt(number);
            }
        }
        System.out.println(result);
    }
}
