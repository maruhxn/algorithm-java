package algospot.ch22;

import java.io.*;
import java.util.StringTokenizer;
import java.util.TreeSet;

// 아이디어는 맞는데.. 계속 시간초과 오류가 난다 ㅠㅠ
// 결국 트립을 구현해야 하는건가..?
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

            for (int i = N - 1; i >= 0; --i) {
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
