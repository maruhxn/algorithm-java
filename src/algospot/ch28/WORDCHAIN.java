package algospot.ch28;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WORDCHAIN {

    // 그래프의 인접 행렬 표현
    static int[][] graph;
    // words[i][j] = i로 시작해서 j로 끝나는 단어의 목록
    static ArrayList<String>[][] words;
    static int n;

    static int[] indegree, outdegree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int c = Integer.parseInt(br.readLine());
        while (c-- > 0) {
            n = Integer.parseInt(br.readLine());

            graph = new int[26][26];
            words = new ArrayList[26][26];
            indegree = new int[26];
            outdegree = new int[26];

            for (int i = 0; i < 26; i++) {
                for (int j = 0; j < 26; j++) {
                    words[i][j] = new ArrayList<>();
                }
            }

            for (int i = 0; i < n; i++) {
                String str = br.readLine();
                int a = str.charAt(0) - 'a'; // 첫 글자
                int b = str.charAt(str.length() - 1) - 'a'; // 마지막 글자

                // a -> b 간선 추가
                words[a][b].add(str);
                graph[a][b]++;
                outdegree[a]++;
                indegree[b]++;
            }

            if (!checkEuler()) {
                System.out.println("IMPOSSIBLE");
                return;
            }

            // 오일러 서킷 찾기
            List<Integer> circuit = getEulerTrailOrCircuit();

            // 모든 간선을 방문하지 못했으면 실패(컴포넌트가 두 개 이상인 경우)
            if (circuit.size() != n + 1) {
                System.out.println("IMPOSSIBLE");
                return;
            }

            // 아닌 경우 방문 순서를 뒤집은 뒤 간선들을 모아 문자열로 만들어 반환
            Collections.reverse(circuit);

            // 역순으로 결과 출력
            String ret = "";
            for (int i = 1; i < circuit.size(); i++) {
                int a = circuit.get(i - 1);
                int b = circuit.get(i);
                if (ret.length() > 0) ret += " ";
                ret += words[a][b].get(words[a][b].size() - 1);
                words[a][b].remove(words[a][b].size() - 1);
            }

            System.out.println(ret);
        }
    }

    private static List<Integer> getEulerTrailOrCircuit() {
        List<Integer> circuit = new ArrayList<>();

        // 우선 트레일을 찾는다: 시작점이 존재하는 경우
        for (int i = 0; i < graph.length; ++i) {
            if (outdegree[i] == indegree[i] + 1) {
                // 나가는 간선의 수(outdegree)가 들어오는 간선의 수(indegree)보다 하나 많은 경우 -> 오일러 트레일 존재
                getEulerCircuit(i, circuit);
                return circuit;
            }
        }

        // 아니면 서킷이니까, 간선에 인접한 아무 정점에서나 시작한다.
        for (int i = 0; i < 26; i++) {
            if (outdegree[i] > 0) {
                getEulerCircuit(i, circuit);
                return circuit;
            }
        }

        return circuit;
    }

    private static void getEulerCircuit(int curr, List<Integer> circuit) {
        for (int i = 0; i < graph.length; i++) {
            while (graph[curr][i] > 0) { // 간선이 존재하면
                graph[curr][i]--; // 간선을 사용
                getEulerCircuit(i, circuit); // 다음 정점으로 이동
            }
        }
        circuit.add(curr); // 역순으로 결과 추가
    }

    private static boolean checkEuler() {
        // 예비 시작점과 끝점의 수
        int plus1 = 0, minus1 = 0;
        for (int i = 0; i < 26; i++) {
            int delta = outdegree[i] - indegree[i];

            // 모든 정점의 차수는 -1, 1 또는 0이어야 한다
            if (delta < -1 || 1 < delta) return false;
            if (delta == 1) plus1++;
            if (delta == -1) minus1++;
        }

        // 시작점과 끝점은 각 하나씩 있거나 하나도 없어야 한다.
        return (plus1 == 1 && minus1 == 1) || (plus1 == 0 && minus1 == 0);
    }
}
