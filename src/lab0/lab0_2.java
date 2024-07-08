// 재귀 알고리즘
package lab0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class lab0_2 {

    static int sum = 0;

    public static void main(String[] args) throws IOException {
        System.out.println("lab0_2:이길수");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        System.out.println(sumFunction(n));
        print(n);
        System.out.println(); // print 메소드 실행 후 줄바꿈
    }

    static int sumFunction(int n) {

        if (n == 0) {
            return sum;
        }
        sum += n;

        return sumFunction(n - 1);
    }

    static void print(int n) {

        if (n > 1) { // n이 1일 때까지 재귀함
            print(n - 1);
        }
        System.out.printf("%d ", n);
    }
}
