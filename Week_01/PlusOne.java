//给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。 
//
// 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。 
//
// 你可以假设除了整数 0 之外，这个整数不会以零开头。
package com.cute.leetcode.editor.cn;

import java.util.Collections;

public class PlusOne {
    public static void main(String[] args) {
        Solution solution = new PlusOne().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 注意 数组中每个元素只存储单个数字  长度不够还要开辟新的数组空间
         * 加一 [1,0,9,9]
         * 问题的核心在于如何是的这一层的状态被传递到下一层
         * 思路： 用栈存储传递给下一层的状态值  下一层根据这个状态更新
         */
        public int[] plusOne(int[] digits) {

            //构建一个辅助栈 存入是否进位的状态值 1 进位 0 不进位 初始一个1代表末尾进位
            //倒着循环数组 num + 1 < 10 push 0 else 1
            //下一层取用判断栈顶值 知道遍历完数组

            //边界条件
            if (digits == null || digits.length == 0) {

                int[] arr = {};
                return arr;
            }

            Stack<Integer> helper = new Stack();

            //初始化 加一 操作
            helper.push(1);

            //倒序遍历
            for (int i = digits.length - 1; i >= 0; i--) {

                int flag = helper.pop();

                //针对[9,9,9] => [10,9,9]
                //针对[9,9,9] => [1,0,9,9]
//            if (i == digits.length - 1) {
//              digits[i] = digits[i] + flag;
//              break;
//            }

                // 长度风波
//                if (i == digits.length - 1) {  ##注意这个时候尾巴是头
                if (i == 0) {


                    int[] res = null;
                    int last = digits[i] + flag;
                    if (last >= 10) {
                        //注意递给层重新赋值
                        digits[i] = last - 10;
                        res = new int[digits.length + 1];
                        System.arraycopy(digits, 0, res, 1, digits.length);
                        res[0] = 1;
                        return res;
                    } else {

                        digits[i] = last;
                        return digits;
                    }

                }

                if (flag == 1) {
                    int tmp = digits[i] + 1;
                    if (tmp >= 10) {
                        digits[i] = tmp - 10;
                        helper.push(1);
                    } else {
//                        digits[i] = tmp - 10;  ##这里是不进位的
                        digits[i] = tmp;
                        helper.push(0);
                    }
                } else {
                    helper.push(0);
                }
            }

            //这里必须返回
            return digits;

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}