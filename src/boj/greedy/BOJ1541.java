package boj.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// 가능한 한 큰 수를 빼야 한다.
// 항상 빼기만 하자! '+'가 있다면 걔네끼리 묶어서 계산하고, 최종적으로는 '-'와 숫자만 남도록하기
public class BOJ1541 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        List<Integer> numArr = new ArrayList<>();
        int result = 0;

        String[] split = str.split("-");
        for (String s : split) {
            String[] subSplit = s.split("\\+");
            int sub = 0;
            for (String string : subSplit) {
                sub += Integer.parseInt(string);
            }

            numArr.add(sub);
        }

        for (int i = 0; i < numArr.size(); i++) {
            if (i == 0) result = numArr.get(i);
            else result -= numArr.get(i);
        }

        System.out.println(result);
    }
}
