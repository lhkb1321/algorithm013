//给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。 
//
// 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。 
//
// 
//
// 示例: 
//
// 给定 nums = [2, 7, 11, 15], target = 9
//
//因为 nums[0] + nums[1] = 2 + 7 = 9
//所以返回 [0, 1]
// 
// Related Topics 数组 哈希表 
// 👍 8752 👎 0

package com.cute.leetcode.editor.cn;

import java.util.Arrays;
import java.util.LinkedHashSet;

public class TwoSum {


    public static void main(String[] args) {
        Solution solution = new TwoSum().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)

class Solution {
        /**
         * 关键词：不重复 一个答案
         * 1.暴力题解法 直接暴力循环每个答案
         * 2.hash表 暂时搁置
		
         */
    public int[] twoSum(int[] nums, int target) {


//        1.暴力解法
        //该用什么结构去重呢 set去重
        Set<List<Integer>> resultSet = new LinkedHashSet<List<Integer>>();

        //第一个数
        for (int i = 0;i < nums.length; i++) {
            //第二个数 因为不重复
            for (int j = i + 1;j < nums.length;j++) {
                //是否符合
                if(nums[i] + nums[j] == target ){

                    //结果去重
                    int[] resulet = {i,j};
                   return  resulet;

                }
            }
        }

        //如果没有
        return null;

        
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}