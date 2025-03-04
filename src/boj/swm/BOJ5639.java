package boj.swm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ5639 {
    static ArrayList<Integer> nums;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        nums = new ArrayList<>();

        String input;
        while ((input = br.readLine()) != null && !input.equals("")) {
            nums.add(Integer.parseInt(input));
        }

        postOrder(0, nums.size());
        System.out.println(sb);
    }

    private static void postOrder(int node, int end) {
        if (node == end) return;

        int currNumber = nums.get(node);

        int rightChildIndex = end;
        for (int i = node + 1; i < end; i++) {
            if (nums.get(i) > currNumber) {
                rightChildIndex = i;
                break;
            }
        }

        postOrder(node + 1, rightChildIndex);
        if (rightChildIndex != node) postOrder(rightChildIndex, end);
        sb.append(currNumber).append("\n");
    }

}
