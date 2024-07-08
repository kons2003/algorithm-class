// quick sort
package hw;

import java.util.Scanner;

public class hw4_1 {
    public static void main(String[] args) {
        System.out.println("hw4_1:이길수");

        // 사용자로부터 원소 개수(n)와 n개의 실수값을 입력받아 배열에 저장
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        double[] array = new double[n];

        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextDouble();
        }

        // 배열 원소들을 퀵 정렬 알고리즘을 이용하여 오름차순으로 정렬
        quickSort(array, 0, n - 1);

        for (int i = 0; i < n; i++) {
            System.out.print(array[i] + " ");
        }
        scanner.close();
    }

    // 배열 array[p...r]을 퀵 정렬
    public static void quickSort(double[] array, int p, int r) {
        if (p < r) {
            int q = partition(array, p, r);
            quickSort(array, p, q - 1);
            quickSort(array, q + 1, r);
        }
    }

    // array[p...r]을 분할하여 기준원소 인덱스를 리턴
    public static int partition(double[] array, int p, int r) {
        int x = (int) array[r];
        int i = p - 1;
        for (int j = p; j < r; j++) {
            if (array[j] < x) {
                double temp = array[++i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        double temp = array[i + 1];
        array[i + 1] = array[r];
        array[r] = temp;

        return i + 1;
    }
}
