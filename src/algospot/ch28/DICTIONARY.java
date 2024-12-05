package algospot.ch28;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DICTIONARY {

    static ArrayList<Character>[] graph;
    static boolean[] visited;
    static List<Character> answer;

    static boolean invalid;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int c = Integer.parseInt(br.readLine());
        while (c-- > 0) {
            int n = Integer.parseInt(br.readLine());
            graph = new ArrayList[26];

            for (int i = 0; i < 26; i++) {
                graph[i] = new ArrayList<>();
            }

            invalid = false;
            String prevStr = br.readLine();

            for (int i = 0; i < n - 1; i++) {
                String currStr = br.readLine();
                int len = Math.min(currStr.length(), prevStr.length());
                for (int k = 0; k < len; k++) {
                    char c1 = prevStr.charAt(k);
                    char c2 = currStr.charAt(k);
                    if (c1 != c2) {
                        graph[c1 - 'a'].add(c2);
                        break;
                    }
                }

                prevStr = currStr;
            }

            dfsAll();

            // 만약 그래프가 DAG가 아니라면 정렬 결과에 역방향 간선이 있다
            for (int i = 0; i < graph.length; i++) {
                for (int j = i + 1; j < graph.length; j++) {
                    if (graph[answer.get(j) - 'a'].contains(answer.get(i))) {
                        invalid = true;
                    }
                }
            }

            if (invalid) {
                bw.append("INVALID HYPOTHESIS\n");
                continue;
            } else {
                for (int i = 0; i < answer.size(); i++) {
                    bw.append(String.valueOf(answer.get(i)));
                }
                bw.newLine();
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static void dfs(char node) {
        visited[node - 'a'] = true;

        for (int i = 0; i < graph[node - 'a'].size(); i++) {
            char next = graph[node - 'a'].get(i);
            if (!visited[next - 'a']) dfs(next);
        }

        answer.add(node);
    }


    public static void dfsAll() {
        visited = new boolean[graph.length];
        answer = new ArrayList<>();

        for (int i = 0; i < graph.length; i++) {
            if (!visited[i]) dfs((char) ('a' + i));
        }

        Collections.reverse(answer);
    }
}
