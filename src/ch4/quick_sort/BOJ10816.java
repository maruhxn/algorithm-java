package ch4.quick_sort;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// 숫자 카드 2(S4)
public class BOJ10816 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = sc.nextInt();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int num = sc.nextInt();
            if (map.containsKey(num)) {
                map.replace(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }

        int m = sc.nextInt();
        for (int i = 0; i < m; i++) {
            int key = sc.nextInt();
            Integer cnt = map.get(key);
            String result = cnt != null ? cnt.toString() : "0";
            bw.append(result + " ");
        }

        bw.flush();
        bw.close();
    }
}
