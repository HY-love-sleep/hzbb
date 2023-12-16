package hot100;

/**
 * Description: 两个正序数组的中位数
 * Author: yhong
 * Date: 2023/12/16
 */
public class Solution4 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int left = -1, right = -1;
        int cur1 = 0, cur2 = 0;
        for (int i = 0; i <= (m + n) / 2; i++) {
            left = right;
            if (cur1 < m && (cur2 >= n || nums1[cur1] < nums2[cur2])) {
                right = nums1[cur1++];
            } else {
                right = nums2[cur2++];
            }
        }
        if ((m + n) % 2 == 1) {
            return right;
        } else {
            return (double) (right + left) / 2;
        }
    }

}


