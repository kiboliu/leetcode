/*
你给出一个整数数组(size为n)，其具有以下特点：

相邻位置的数字是不同的
A[0] < A[1] 并且 A[n - 2] > A[n - 1]
假定P是峰值的位置则满足A[P] > A[P-1]且A[P] > A[P+1]，返回数组中任意一个峰值的位置。
*/

public class Solution {
    /*
     * @param A: An integers array.
     * @return: return any of peek positions.
     */
    public int findPeak(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int start = 0;
        int end = A.length - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (mid > 0 && A[mid] >= A[mid - 1] && A[mid] >= A[mid + 1]) {
                return mid;
            }
            if (A[mid] >= A[mid + 1]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }
}