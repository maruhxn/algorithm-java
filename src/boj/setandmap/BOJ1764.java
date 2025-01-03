package boj.setandmap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class BOJ1764 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Set<String> set = new TreeSet<>();
        Set<String> resultSet = new TreeSet<>(Comparator.naturalOrder());

        for (int i = 0; i < N; i++) {
            set.add(br.readLine());
        }

        for (int i = 0; i < M; i++) {
            String target = br.readLine();
            if (set.contains(target)) {
                resultSet.add(target);
            }
        }

        sb.append(resultSet.size()).append("\n");
        for (String s : resultSet) {
            sb.append(s).append("\n");
        }

        System.out.println(sb);
        br.close();
    }
}

// 모범답안
/**
 * public class Main {
 *
 * 	public static void main(String[] args) throws Exception {
 * 		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 * 		StringBuilder sb = new StringBuilder();
 *
 * 		String s[] = br.readLine().split(" ");
 * 		int N = Integer.parseInt(s[0]);
 * 		int M = Integer.parseInt(s[1]);
 *
 * 		String[] nolisten = new String[N];
 * 		String[] nosee = new String[M];
 * 		for(int i=0; i<N; i++) nolisten[i] = br.readLine();
 * 		for(int i=0; i<M; i++) nosee[i] = br.readLine();
 *
 * 		Arrays.sort(nolisten);
 * 		Arrays.sort(nosee);
 *
 * 		int n=0, m=0, res=0;
 * 		while(true) {
 * 			if(n>=N || m>=M) break;
 * 			int comp = nolisten[n].compareTo(nosee[m]);
 *
 * 			if(comp==0){
 * 				sb.append(nolisten[n]).append("\n");
 * 				res++; n++; m++;
 * 			            }
 * 			else if(comp < 0) n++;
 * 			else m++;		        * 		}
 * 		System.out.println(res);
 * 		System.out.println(sb);
 * 	}
 * }
 */