package ch9.trie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 문자열 찾기(S4)
public class p069 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        tNode root = new tNode();

        while (n-- > 0) {
            String text = br.readLine();
            tNode now = root;
            for (int i = 0; i < text.length(); i++) {
                char c = text.charAt(i);
                if (now.next[c - 'a'] == null) { // c 변수에 해당하는 다음 노드가 null이면 신규 노드 생성
                    now.next[c - 'a'] = new tNode();
                }
                now = now.next[c - 'a']; // 현재 노드를 c 변수 노드로 변경
                if(i == text.length() - 1) now.isEnd = true;
            }
        }

        int count = 0;
        while (m-- > 0) {
            String text = br.readLine();
            tNode now = root;
            for (int i = 0; i < text.length(); i++) {
                char c = text.charAt(i);
                if (now.next[c - 'a'] == null) { // c 변수에 해당하는 다음 노드가 null이면 (공백 노드면)
                    break;
                }
                now = now.next[c - 'a']; // 현재 노드를 c 변수 노드로 변경
                if(i == text.length() - 1 && now.isEnd) count++;
            }
        }

        System.out.println(count);
    }

    static class tNode {
        tNode[] next = new tNode[26];
        boolean isEnd;
    }
}
