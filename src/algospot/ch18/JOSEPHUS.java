package algospot.ch18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class JOSEPHUS {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int c = Integer.parseInt(br.readLine());
        while (c-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            LinkedList<Integer> list = new LinkedList<>();

            for (int i = 1; i <= n; i++) {
                list.add(i);
            }

            Iterator it = list.iterator();
            it.next();
            it.remove();
            --n;
            while (n > 2) {
                for (int i = 0; i < k; i++) {
                    if (it.hasNext()) it.next();
                    else {
                        it = list.iterator();
                        it.next();
                    }
                }
                it.remove();
                --n;
            }

            for (Integer i : list) {
                System.out.print(i + " ");
            }
        }
    }
}
