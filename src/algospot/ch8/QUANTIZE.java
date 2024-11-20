package algospot.ch8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class QUANTIZE {
    static int[] arr;
    static int INF = 987654321;
    static int[][] cache;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int c = Integer.parseInt(br.readLine());
        while (c-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int S = Integer.parseInt(st.nextToken());
            arr = new int[N];
            cache = new int[S][N];

            for (int i = 0; i < S; i++) {
                for (int j = 0; j < N; j++) {
                    cache[i][j] = -1;
                }
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(arr);

            int result = INF;

            for (int q = 1; q <= S; q++) {
                result = Math.min(result, quantize(0, q));
            }

            System.out.println(result);
        }
    }

    public static int quantize(int index, int q) {
        if (q == 1 || index == arr.length - 1)
            return subQuantize(index, arr.length); // q가 1일 경우, 마지막 원소까지 subQuantize 구하고 반환

        int ret = cache[q - 1][index];
        if (ret != -1) return ret;

        ret = INF;

        for (int i = 1; i <= arr.length - index - 1; i++) {
            ret = Math.min(ret, subQuantize(index, index + i) + quantize(index + i, q - 1));
        }

        return cache[q - 1][index] = ret;
    }

    // arr[st..ed) 구간 양자화
    public static int subQuantize(int st, int ed) {
        int sum = 0;
        for (int i = st; i < ed; i++) {
            sum += arr[i];
        }

        int round = Math.round((float) sum / (ed - st));

        int result = 0;
        for (int i = st; i < ed; i++) {
            result += (int) Math.pow(arr[i] - round, 2);
        }

        return result;
    }
}
