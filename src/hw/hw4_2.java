// merge sort
package hw;

import java.util.Scanner;

public class hw4_2 {
    public static void main(String[] args) {
        System.out.println("hw4_2:이길수");

        // 사용자로부터 원소 개수(n)와 n개의 실수값을 입력받아 배열에 저장
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        double[] array = new double[n];

        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextDouble();
        }

        // 배열 원소들을 병합 정렬 알고리즘을 이용하여 오름차순으로 정렬
        mergeSort(array, 0, n - 1);

        for (int i = 0; i < n; i++) {
            System.out.print(array[i] + " ");
        }
        scanner.close();
    }

    // array[p...r]을 정렬한다.
    public static void mergeSort(double[] array, int p, int r) {
        if (p < r) {
            int q = (p + r) / 2; // p, r의 중간 지점 계산
            mergeSort(array, p, q); // 전반부 정렬
            mergeSort(array, q + 1, r); // 후반부 정렬
            merge(array, p, q, r); // 병합
        }
    }

    // 정렬된 두 배열 array[p...q]와 array[q+1...r]을 합쳐 정렬된 하나의 배열 array[p...r]을 만든다.
    public static void merge(double[] array, int p, int q, int r) {
        double[] tmp = new double[array.length]; // 병합을 위한 임시 배열
        int i = p; // 왼쪽 부분 배열 시작
        int j = q + 1; // 오른쪽 부분 배열 시작
        int t = 0; // 임시 배열 인덱스

        while (i <= q && j <= r) { // 임시 배열에 정렬해서 저장
            if ((int) array[i] <= (int) array[j]) {
                tmp[t++] = array[i++];
            } else {
                tmp[t++] = array[j++];
            }
        }
        while (i <= q) { // 왼쪽 부분 배열이 남은 경우
            tmp[t++] = array[i++];
        }
        while (j <= r) { // 오른쪽 부분 배열이 남은 경우
            tmp[t++] = array[j++];
        }

        i = p;
        t = 0;
        while (i <= r) { // 결과를 array[p...r]에 저장
            array[i++] = tmp[t++];
        }
    }
}
