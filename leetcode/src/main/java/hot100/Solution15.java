package hot100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Description: 三数之和
 * Author: yhong
 * Date: 2023/12/25
 */
public class Solution15 {
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int j = nums.length - 1;
            int target = -nums[i];
            for (int k = i + 1; k < nums.length; k++) {
                if (k > i + 1 && nums[k] == nums[k - 1]) {
                    continue;
                }
                while (k < j && nums[j] + nums[k] > target) {
                    j--;
                }
                if (j == k) {
                    break;
                }
                if (nums[j] + nums[k] == target) {
                    List<Integer> ans = new ArrayList<>();
                    ans.add(nums[i]);
                    ans.add(nums[j]);
                    ans.add(nums[k]);
                    res.add(ans);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {-1,0,1,2,-1,-4};
        System.out.println(threeSum(nums));
    }
}


