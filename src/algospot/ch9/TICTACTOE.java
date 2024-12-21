package algospot.ch9;

import java.io.BufferedReader;
import java.io.BufferedWriter;

public class TICTACTOE {

    static int[] cache;

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new java.io.OutputStreamWriter(System.out));

        cache = new int[19683];

        for (int i = 0; i < cache.length; i++) {
            cache[i] = -2;
        }


    }

    // 틱택토 게임판이 주어질 때 [0, 19682] 범위의 정수로 변환
    private static int bijection(String[] board) {
        int ret = 0;
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                ret *= 3;
                if (board[y].charAt(x) == 'o') ++ret;
                else if (board[y].charAt(x) == 'x') ret += 2;
            }
        }

        return ret;
    }

    /**
     * canWin(board) -> 틱택토 게임판이 현재 board일 때, 이번 차례인 사람이
     * 이길 수 있으면 1,
     * 이기진 못해도 비길 수 있으면 0,
     * 질 수밖에 없으면 -1
     */
    private static int canWin(String[] board, char turn) {
        // 기저 사례: 마지막에 상대가 둬서 한 줄이 만들어진 경우 -> 질수밖에 없음
        if (isFinished(board, (char) ('o' + 'x' - turn))) return -1;
        int ret = cache[bijection(board)];
        if (ret != -2) return ret;

        int minValue = 2;
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                if (board[y].charAt(x) == '.') {
                    // 현재 차례의 선수가 돌을 둚
                    board[y] = board[y].substring(0, x) + turn + board[y].substring(x + 1);
                    // 이 상태에서 승리 여부 확인
                    minValue = Math.min(minValue, canWin(board, (char) ('o' + 'x' - turn)));
                    // 다시 돌을 치움
                    board[y] = board[y].substring(0, x) + '.' + board[y].substring(x + 1);
                }
            }
        }

        // 플레할 수 없거나, 어떻게 해도 비기는 것이 최선인 경우
        if(minValue == 2 || minValue == 0) ret = 0;

        // 최선이 상대가 이기는거라면 난 무조건 지고, 상대가 지는거라면 난 이긴다
        return cache[bijection(board)] = ret = -minValue;
    }

    private static boolean isFinished(String[] board, char turn) {
        return false;
    }
}
