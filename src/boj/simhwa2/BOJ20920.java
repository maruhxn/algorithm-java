package boj.simhwa2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ20920 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Map<String, Word> map = new HashMap();

        while (N-- > 0) {
            String str = br.readLine();
            if (str.length() < M) continue;

            Word word = map.getOrDefault(str, new Word(str, 0));
            word.freq++;
            map.put(str, word);
        }

        PriorityQueue<Word> pq = new PriorityQueue<>();
        for (Word word : map.values()) {
            pq.offer(word);
        }

        while (!pq.isEmpty()) {
            sb.append(pq.poll().value).append("\n");
        }

        System.out.println(sb);
    }

    static class Word implements Comparable<Word> {

        String value;
        int freq;

        public Word(String value, int freq) {
            this.value = value;
            this.freq = freq;
        }

        @Override
        public int compareTo(Word o) {
            // this.freq == o.freq 이면, 단어 길이 비교
            if (this.freq == o.freq) {
                // 단어 길이가 같으면, 단어는 사전 순.
                if (this.value.length() == o.value.length()) {
                    return String.CASE_INSENSITIVE_ORDER.compare(this.value, o.value);
                }

                // 더 큰 놈이 더 앞에 (내림차순)
                return o.value.length() - this.value.length();
            }

            // 더 큰 놈이 더 앞에 (내림차순)
            // o.freq가 더 크면, this가 뒤에 있어야 함 = 양수를 반환해야 함
            // o.freq가 더 작으면, this가 앞에 있어야 함 = 음수를 반환해야 함
            return o.freq - this.freq;
        }
    }
}
