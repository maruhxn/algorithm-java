package boj.setandmap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 이름으로부터 번호를 알아내는 방법은 String[] 배열을 사용하여 인덱스가 번호를 의미하도록 하면 더 빠를듯
 */
//public class BOJ1620 {
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringBuilder sb = new StringBuilder();
//
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        int N = Integer.parseInt(st.nextToken());
//        int M = Integer.parseInt(st.nextToken());
//
//        Map<String, String> map = new HashMap<>();
//
//        for (int i = 1; i <= N; i++) {
//            String name = br.readLine();
//            map.put(name, String.valueOf(i));
//            map.put(String.valueOf(i), name);
//        }
//
//        for (int i = 0; i < M; i++) {
//            String target = br.readLine();
//            sb.append(map.get(target));
//            sb.append('\n');
//        }
//
//        System.out.println(sb);
//        br.close();
//    }
//}

public class BOJ1620 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        String[] names = new String[N + 1];
        Map<String, Integer> map = new HashMap<>();

        for (int i = 1; i <= N; i++) {
            String name = br.readLine();
            names[i] = name;
            map.put(name, i);
        }

        for (int i = 0; i < M; i++) {
            String target = br.readLine();
            if (Character.isDigit(target.charAt(0))) {
                sb.append(names[Integer.parseInt(target)]);
            } else {
                sb.append(map.get(target));
            }
            sb.append('\n');
        }

        System.out.println(sb);
        br.close();
    }
}
