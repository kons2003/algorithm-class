// 배열 검색
package lab0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class lab0_3 {
    public static void main(String[] args) throws IOException {
        System.out.println("lab0_3:이길수");

        int[] array = {120, 990, 130, 150, 20, 300, 400, 990, 40, 100, 110, 150, 60, 80, 190, 200};
        int[] sortedArray = {20, 40, 60, 80, 100, 110, 120, 130, 150, 150, 190, 200, 300, 400, 990, 990};

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        System.out.println(sequentialSearch(array, 0, n));
        System.out.println(binarySearch(sortedArray, 0, sortedArray.length, n));
    }

    private static int sequentialSearch(int[] array, int index, int item) {
        if (array[index] == item) return index;
        if (index == array.length - 1) return -1; // 실패
        return sequentialSearch(array, ++index, item);
    }

    private static int binarySearch(int[] array, int from, int to, int item) {
        if (from > to) return -1; // 실패

        int mid = (from + to) / 2;
        if (array[mid] < item) {
            return binarySearch(array, mid + 1, to, item);
        } else if (array[mid] > item) {
            return binarySearch(array, from, mid - 1, item);
        }
        return mid;
    }
}
