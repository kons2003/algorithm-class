// 숫자 세기
package lab0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class lab0_1 {
    public static void main(String[] args) throws IOException {
        System.out.println("lab0.lab0_1:이길수");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] num = new int[n];
        double sum = 0.0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            num[i] = Integer.parseInt(st.nextToken());
            sum += num[i];
        }

        double avg = sum / n;
        int cnt = 0;

        for (int i = 0; i < n; i++) {
            if (num[i] > avg) {
                cnt++;
            }
        }

        System.out.printf("%.1f\n", avg);
        System.out.println(cnt);
    }
}
