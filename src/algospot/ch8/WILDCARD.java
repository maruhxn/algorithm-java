package algospot.ch8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// 실패..
public class WILDCARD {
    static String target;
    static List<String> results;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int c = Integer.parseInt(br.readLine());
        while (c-- > 0) {
            target = br.readLine();
            results = new ArrayList<>();
            int n = Integer.parseInt(br.readLine());
            for (int i = 0; i < n; i++) {
                String input = br.readLine();
                if (wildcard(input, 0)) results.add(input);
            }

            results.sort(String::compareTo);

            for (String result : results) {
                System.out.println(result);
            }
        }
    }

    public static boolean wildcard(String str, int index) {
        // 남은 와일드카드가 없는데 아직 문자열이 남은 경우
        if (index == target.length() && !str.isEmpty()) return false;

        if (index == target.length()) return true;

        char currTargetChar = target.charAt(index);

        if (currTargetChar == '*') {
            // 아직 와일드카드 문자가 남아있을 경우
            if (index != target.length() - 1 && str.length() > 1) {

                if (target.lastIndexOf('*') == index) {
                    int lastTargetIndex = str.lastIndexOf(target.charAt(index + 1));
                    if (lastTargetIndex == -1) return false;
                    return wildcard(str.substring(lastTargetIndex), index + 1);
                } else {
                    int targetIndex = str.indexOf(target.charAt(index + 1));

                    if (targetIndex == -1) return false;

                    return wildcard(str.substring(targetIndex), index + 1);
                }
            } else {
                return true;
            }
        }

        if (currTargetChar == '?') {
            if (index != target.length() - 1) {
                return wildcard(str.substring(1), index + 1);
            } else {
                return str.length() == 1;
            }
        }

        if (currTargetChar == str.charAt(0)) {
            return wildcard(str.substring(1), index + 1);
        }

        return true;
    }
}
