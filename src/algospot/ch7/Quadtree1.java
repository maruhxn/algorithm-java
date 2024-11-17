package algospot.ch7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Quadtree1 {
    static int index;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int C = Integer.parseInt(br.readLine());

        while (C-- > 0) {
            String s = br.readLine();
            index = 0;
            System.out.println(solve(s));
        }
    }

    public static String solve(String s) {
        if (s.charAt(index) != 'x') {
            return String.valueOf(s.charAt(index++));
        }

        index++;

        String firstStr = solve(s);
        String secondStr = solve(s);
        String thirdStr = solve(s);
        String forthStr = solve(s);

        return "x" + thirdStr + forthStr + firstStr + secondStr;
    }
}
/**
 * 1. 입력값으로부터 원본을 도출한 후, 이를 상하반전 시켜서 다시 압축하는 것은 딱봐도 비효율적이고 크기 제한에 걸림
 * 2. 상하반전 시켰을 경우, 결과가 어떻게 바뀌는지 확인 (1번과 2번 문자열, 3번과 4번 문자열을 합쳐서 순서를 바꿔주면 됨)
 * 3. 'x'를 만나면 넷으로 쪼갠 결과를 받아와야 하는데, 여기서 재귀 호출 발생
 */