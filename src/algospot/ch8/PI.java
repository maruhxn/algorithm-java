package algospot.ch8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 실패.. ㅠㅠ
public class PI {
    static int[] arr;
    static int INF = 987654321;
    static int[] types = {1, 2, 4, 5, 10};
    static int[][] cache;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int c = Integer.parseInt(br.readLine());
        while (c-- > 0) {
            String line = br.readLine().trim();
            arr = new int[line.length()];
            cache = new int[line.length()][3];
            for (int i = 0; i < line.length(); i++) {
                for (int j = 0; j < 3; j++) {
                    cache[i][j] = -1;
                }
            }

            for (int i = 0; i < line.length(); i++) {
                arr[i] = line.charAt(i) - '0';
            }

            int result = Math.min(Math.min(solve(0, 3), solve(0, 4)), solve(0, 5));
            System.out.println(result);
        }
    }

    public static int solve(int index, int chunkSize) {
        if (index == arr.length) return 0;
        if (arr.length - index < chunkSize) return INF;

        int ret = cache[index][chunkSize - 3];
        if (ret != -1) {
            return ret;
        }

        for (int type = 0; type < types.length; type++) {
            if (find(index, chunkSize, type)) {
                ret = types[type];
                break;
            }
            ret = types[types.length - 1];
        }

        int a = solve(index + chunkSize, 3);
        int b = solve(index + chunkSize, 4);
        int c = solve(index + chunkSize, 5);

        return cache[index][chunkSize - 3] = ret + Math.min(Math.min(a, b), c);
    }

    private static boolean find(int index, int chunkSize, int type) {
        boolean ok = true;

        switch (type) {
            case 0:
                for (int i = 1; i < chunkSize; i++) {
                    if (arr[index] != arr[index + i]) {
                        ok = false;
                        break;
                    }
                }
                break;

            case 1:
                for (int i = 1; i < chunkSize; i++) {
                    if (Math.abs(arr[index + i] - arr[index + i - 1]) != 1) {
                        ok = false;
                        break;
                    }
                }
                break;

            case 2:
                for (int i = 1; i < chunkSize - 1; i++) {
                    if (arr[index + i + 1] != arr[index + i - 1]) {
                        ok = false;
                        break;
                    }
                }
                break;

            case 3:
                for (int i = 1; i < chunkSize - 1; i++) {
                    if ((arr[index + i + 1] - arr[index + i]) != (arr[index + i] - arr[index + i - 1])) {
                        ok = false;
                        break;
                    }
                }
                break;

            default:
                // 기본 동작 (필요한 경우 추가)
                break;
        }

        return ok;
    }
}
