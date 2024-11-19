package algospot.ch8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class WILDCARD2 {

    static int[][] cache;
    static String W, S;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int c = Integer.parseInt(br.readLine());
        while (c-- > 0) {
            W = br.readLine();
            List<String> results = new ArrayList<>();

            int n = Integer.parseInt(br.readLine());


            for (int i = 0; i < n; i++) {
                S = br.readLine();
                cache = new int[101][101];

                for (int j = 0; j < cache.length; j++) {
                    for (int k = 0; k < cache[0].length; k++) {
                        cache[j][k] = -1;
                    }
                }

                if (matchMemoizedV2(0, 0) == 1) results.add(S);
            }

            results.sort(String::compareTo);

            for (String result : results) {
                System.out.println(result);
            }
        }
    }

//    // 와일드카드 패턴 w가 문자열 s에 대응되는지 여부를 반환 -> O(n^3)
//    public static boolean match(String w, String s) {
//        int pos = 0;
//        while (pos < s.length() && pos < w.length() &&
//                (w.charAt(pos) == '?' || w.charAt(pos) == s.charAt(pos))) ++pos;
//
//        // 더 이상 대응할 수 없으면 왜 while문이 끝났는지 확인
//        // 2. 패턴 끝에 도달해서 끝난 경우: '*'이 하나도 없는 경우로, 문자열도 끝났어야 참
//        if (pos == w.length()) return pos == s.length();
//
//        // 4. '*'를 만나서 끝난 경우: '*'에 몇 글자를 대응해야 할지 재귀 호출하면서 확인
//        if (w.charAt(pos) == '*') {
//            for (int skip = 0; pos + skip <= s.length(); ++skip) {
//                if (match(w.substring(pos + 1), s.substring(pos + skip))) return true;
//            }
//        }
//
//        // 이 외의 경우에는 모두 대응되지 않는다.
//        return false;
//    }

//    // 메모이제이션 적용
//    public static int matchMemoized(int w, int s) {
//        int ret = cache[w][s];
//        if (ret != -1) return ret;
//
//        while (s < S.length() && w < W.length() &&
//                (W.charAt(w) == '?' || W.charAt(w) == S.charAt(s))) {
//            ++s;
//            ++w;
//        }
//
//        // 더 이상 대응할 수 없으면 왜 while문이 끝났는지 확인
//        // 2. 패턴 끝에 도달해서 끝난 경우: '*'이 하나도 없는 경우로, 문자열도 끝났어야 참
//        if (w == W.length())
//            return ret = (s == S.length()) ? 1 : 0;
//
//
//        // 4. '*'를 만나서 끝난 경우: '*'에 몇 글자를 대응해야 할지 재귀 호출하면서 확인
//        if (W.charAt(w) == '*') {
//            for (int skip = 0; s + skip <= S.length(); ++skip) {
//                if (matchMemoized(w + 1, s + skip) == 1) {
//                    return ret = 1;
//                }
//            }
//        }
//
//        return ret = 0;
//    }

    // 메모이제이션 적용 + 최적화 -> O(n^2)
    public static int matchMemoizedV2(int w, int s) {
        int ret = cache[w][s];
        if (ret != -1) return ret;

        while (s < S.length() && w < W.length() &&
                (W.charAt(w) == '?' || W.charAt(w) == S.charAt(s))) {
            return ret = matchMemoizedV2(w + 1, s + 1);
        }

        // 더 이상 대응할 수 없으면 왜 while문이 끝났는지 확인
        // 2. 패턴 끝에 도달해서 끝난 경우: '*'이 하나도 없는 경우로, 문자열도 끝났어야 참
        if (w == W.length())
            return ret = (s == S.length()) ? 1 : 0;


        // 4. '*'를 만나서 끝난 경우: '*'에 몇 글자를 대응해야 할지 재귀 호출하면서 확인
        // *에 아무 글자도 대응시키지 않을 것인지, 아니면 한 글자를 더 대응시킬 것인지를 결정
        if (W.charAt(w) == '*') {
            if (matchMemoizedV2(w + 1, s) == 1 ||
                    (s < S.length() && matchMemoizedV2(w, s + 1) == 1))
                return ret = 1;
        }

        return ret = 0;
    }
}
