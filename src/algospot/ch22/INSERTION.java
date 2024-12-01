package algospot.ch22;

import java.io.*;
import java.util.StringTokenizer;
import java.util.TreeSet;

// 아이디어는 맞는데.. 계속 시간초과 오류가 난다 ㅠㅠ
// 결국 트립을 구현해야 하는건가..?
// TreeSet의 경우 kth 번째 원소를 찾는 연산이 구현되어 있지 않다.. 직접 구현하다보니 O(NlogN)의 시간이 걸리게 된다.
public class INSERTION {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int C = Integer.parseInt(br.readLine());

        while (C-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int[] moves = new int[50000];
            int[] result = new int[50000];
            TreeSet<Integer> treeSet = new TreeSet<>();

            for (int i = 1; i <= N; i++) treeSet.add(i);

            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int i = 0; i < N; i++) {
                moves[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = N - 1; i >= 0; --i) { // 각 스테이지마다 kth 요소 찾아서 제거하는 과정 -> 스테이지마다 O(NlogN) 시간 => 전체는 O(N^2logN)
                int count = 0;
                int offset = i + 1 - moves[i];
                for (int num : treeSet) {
                    ++count;
                    if (count == offset) {
                        result[i] = num;
                        treeSet.remove(num);
                        break;
                    }
                }
            }

            for (int i = 0; i < N; i++) {
                bw.write(result[i] + " ");
            }

            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
