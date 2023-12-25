package hot100;

import java.util.Map;

/**
 * Description: maxArea
 * Author: yhong
 * Date: 2023/12/21
 */
public class Solution11 {
    public int maxArea(int[] heights) {
        int res = 0;
        int left = 0, right = heights.length - 1;
        while (left < right) {
            int current = (right - left) * Math.min(heights[left], heights[right]);
            res = Math.max(current, res);
            if (heights[left] <= heights[right]) {
                while (left < heights.length - 1 && heights[left] >= heights[left + 1]) {
                    left++;
                }
                left++;
            } else {
                while (right >= 1 &&heights[right] >= heights[right - 1]) {
                    right--;
                }
                right--;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] heights = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        int[] heights2 = {1, 1};
        Solution11 s = new Solution11();
        System.out.println(s.maxArea(heights2));
    }
}
