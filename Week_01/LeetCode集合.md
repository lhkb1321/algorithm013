# LeetCode集合

## 双指针

#### 1）26 _ remove-duplicates-from-sorted-array

```java
解题思路：
解法： 双指针

首先注意数组是有序的，那么重复的元素一定会相邻。

要求删除重复元素，实际上就是将不重复的元素移到数组的左侧。

考虑用 2 个指针，一个在前记作 p，一个在后记作 q，算法流程如下：

1.比较 p 和 q 位置的元素是否相等。

如果相等，q 后移 1 位
如果不相等，将 q 位置的元素复制到 p+1 位置上，p 后移一位，q 后移 1 位
重复上述过程，直到 q 等于数组长度。

返回 p + 1，即为新数组长度。

借鉴 ：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/solution/shuang-zhi-zhen-shan-chu-zhong-fu-xiang-dai-you-hu/


代码：
public int removeDuplicates(int[] nums) {
    if(nums == null || nums.length == 0) return 0;
    int p = 0;
    int q = 1;
    while(q < nums.length){
        if(nums[p] != nums[q]){
            if(q - p > 1){
                nums[p + 1] = nums[q];
            }
            p++;
        }
        q++;
    }
    return p + 1;
}

考虑优化的细节
```



## 2）283 移动零

```shell
 https://leetcode-cn.com/problems/move-zeroes/solution/dong-hua-yan-shi-283yi-dong-ling-by-wang_ni_ma/
 
 方法一：使用二次遍历
 public void moveZeroes(int[] nums) {

        //使用另一个数组，遍历原数组，遇到非0按顺序放，遍历完后剩余的长度为0/或则使用末尾的指针放置 空间复杂度高o(n)

        //不使用新数组，直接移动原数组的指针。01 i标识为0的指针 j标识不为0的指针 02 用j填充i j置为0  old
        //不使用新数组，直接移动原数组的指针。01 j标识不为0的窗口边界 非0元素不包含这个边界值 i遍历数组寻找不为0的数 02 用i填充j j边界后的元素重置为0  new

        //窗口界限
        int j = 0 ;
        //寻物的猎犬
        for(int i = 0; i < nums.length; i++){

             if(nums[i] != 0){

                     //不原地交换，只移动 优化
                     if (i - j > 0) { 
                     //进行替换
                     nums[j] = nums[i];
                     }
                     //非0窗口前移
                     j++;
                 }

             }

        //清空非0边界后的元素 这里是++j 还是 j
        for(int a = j; a < nums.length; a++){

            nums[a] = 0;

        }

    }
    
    
  待补全
    * 移动零
         * 思路01：双指针构造滑动窗口 i代表不为0的滑动窗口边界 j捕获的非0的数
         * 思路02：思路一进行优化,对于原地替换进行过滤
         * 思路03: 一步到位还不明白
  
  
```

## 3）11 盛最多水的容器

```
       /**
         * 双指针夹逼法
         * 用一句话概括双指针解法的要点：指针每一次移动，都意味着排除掉了一个柱子。
         * 体现了木桶效应，最短的木板决定能装多少水
         * https://leetcode-cn.com/problems/container-with-most-water/solution/on-shuang-zhi-zhen-jie-fa-li-jie-zheng-que-xing-tu/
         */
        public int maxArea(int[] height) {

            //1.创造左右指针
            //2.比较左右指针的大小,替换柱子高度低的值(提高木桶的下限)
            //3.指针汇合，遍历所有可能，运用中间结果更新最大值

            int statIndex = 0;
            int tmpValue = 0;
            int endIndex = height.length - 1;
            int result = 0;
            while(statIndex < endIndex) {

                // 初始化值
                tmpValue = (endIndex - statIndex) * Math.min(height[endIndex],height[statIndex]);

                if(height[statIndex] <  height[endIndex]) {

                    //替换短柱
                    statIndex++;
                }else{

                    //替换短柱
//                    endIndex++  愚蠢 这边是回缩
                    endIndex--;
                }

                //找出最大值
                result = Math.max(tmpValue,result);

            }



            return result;


    }
```



## 4）70 爬楼梯

```
       /**
         * 注意每次只能爬一到两层楼梯
         * 数学归纳法的典型应用，找到重复的子问题进行求解
         * 从简单到复杂
         * 1  1
         * 2  1  2
         * 3  1  1 + 2 2 + 1
         *
         * 通项 f(n) = f(n - 1) + f(n -2)
         * 重复的问题是：站在终点想这个问题 我是每次从倒数第一级台阶走上来  还是从倒数第二个台阶走上来
         只有可能是从倒数第二阶(一次快二步) 和倒数第一阶(一次跨一步) 两种方式
         
         * -> 这就变成斐波那锲数列
         */
    public int climbStairs(int n) {


        //用循环完成菲波那切数列数列
        // 1. 定义三个初始值  两个基本情况 和一个状态累计值 三个状态怎么维护呢？ 用数组
        // 2. 循环不断的更新结果



        //循环模拟递归的过程的  用数组模拟进出栈的过程

//        int[] arr = new int(n); 长度为多少 n代表上到几级 0和1代表一次一步或两步 长度2是初始 n=1 只有一步机会 n=2才有一步和两步的机会
        // 所以n=2 才开始 2 = 1 + 1 满足通项公式 n = 2 需要三个数 所以n取n+1
        //准备的数组
        int[] arr = new int[n+1];
        int[] area = new int[n+1];

        area[0] = 1;
        area[1] = 1;

        //从递归出口开始遍历
        for(int index = 2; index < n+1 ;index++) {

            //每次走一步或者走两步
            area[index] = area[index - 1] + area[index - 2];


        }

        //返回数组最后一个值
        return area[area.length - 1];





    }
```

## 5）1 两数之和

```java
        /**
         * 关键词：不重复 一个答案
         * 1.暴力题解法 直接暴力循环每个答案
         * 2.借鉴三指针的夹逼法 退维到一维 算currentIndex taget startIndex左指针 endIndex右指针的模型
         */
    public int[] twoSum(int[] nums, int target) {


//        1.暴力解法
        //该用什么结构去重呢 set集合 两数之和 不考虑去重
        //Set<List<Integer>> resultSet = new LinkedHashSet<List<Integer>>();

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
        
        
        
 方法二 ： ------------------------------------------------------------------------- 
 失败的解法：两数之和要求返回index 所以这里不能排序   
     
        /**
         * 关键词：不重复 一个答案
         * 1.暴力题解法 直接暴力循环每个答案
         * 2.借鉴三指针的夹逼法 退维到一维 算currentIndex taget startIndex左指针 endIndex右指针
         *
         * https://leetcode-cn.com/problems/3sum/solution/san-shu-zhi-he-javajian-ji-ti-jie-by-wang-zi-hao-z/
         */
    public int[] twoSum(int[] nums, int target) {

        //2.使用双指针夹逼法 taget startIndex endIndex
        //1.定义首尾两个指针
        // 2.排序数组保证单调性为夹逼做准备
        // n*logn vs n*n
        int startIndex = 0;
        int endIndex = nums.length - 1;

        //排序
//        nums = Arrays.sort(nums);
        Arrays.sort(nums);
        //指针重叠结束
        while (startIndex < endIndex) {

            if (nums[startIndex] + nums[endIndex] < target) {

               //代表结果小了变大
                startIndex++;


            }else if(nums[startIndex] + nums[endIndex] == target){

                int[] result = {startIndex,endIndex};
                return result;

            }else {

                //结果大了
                endIndex--;

            }


        }

        //没有匹配到
        return null;
        
    }
```

6）三数之和

```java
错误点：     

       //结果找到了,指针也需要移动，遍历其他可能的结果
                    tail--;      

/**
         * 关键词 ： 请你找出所有满足条件且不重复的三元组。
         * 三数之和转换两数之和的思路
         * 使用三个指针 把 a + b + c = 0  转化成 a + b = -c 的两数之和
         * 再使用首尾两端的夹逼法,对排好序的数组进行,首尾逼近，再进行还原
         *
         * 题解：https://leetcode-cn.com/problems/3sum/solution/san-shu-zhi-he-javajian-ji-ti-jie-by-wang-zi-hao-z/
         */
    public List<List<Integer>> threeSum(int[] nums) {

        //01 定义三个指针 currentValue(标识c)  startIndex(首元素)  endIndex(尾元素)
        //02 数组排序保证夹逼的正确性 三元组的结果需要再排序一次保证不重复332和323
        //边界条件
        if(nums == null || nums.length<3) {

//            return null;
            //注意啊返回空数组
            return Collections.emptyList();
        }

        //数组排序
        Arrays.sort(nums);

        //set去重
//        Set<List<int>> resultSet = new LinkedHashSet<List<int>>();
        Set<List<Integer>> resultSet = new LinkedHashSet();

        //留下两个指针的空位 [-1,0,1,2,-1,-4] 0

// 减n就代表 数组至少有n+1个元素 这个是至少有三个 不用担心startIndex=endIndex 下面while不会处理
//        for(int currentIndex = 0; currentIndex < nums.length - 3;currentIndex++) {
        for(int currentIndex = 0; currentIndex < nums.length - 2;currentIndex++) {


            //head
            int startIndex = currentIndex + 1;
            //tail
//            int endIndex = nums[nums.length - 1];
            int endIndex = nums.length - 1;

//            int sum = -(nums[head] + nums[tail]);改进

            //开始双指针夹逼 指针汇合 一轮检查结束
            while (startIndex < endIndex) {
//           if(nums[startIndex] + nums[endIndex] < nums[currentIndex]) { this is -c
                if((nums[startIndex] + nums[endIndex]) < -nums[currentIndex]) {

                    //和小了 左边移动
                    startIndex++;

                }else if((nums[startIndex] + nums[endIndex]) > -nums[currentIndex]) {
                    //sum is biger
                    endIndex--;
                }else {
                    //equals is the result

//                    List<Integer> elements = Arrays.asList(startIndex, endIndex, currentIndex); 提交元素
//                    List<Integer> elements = Arrays.asList(nums[startIndex], nums[endIndex], nums[currentIndex]);
                    List<Integer> elements = Arrays.asList(nums[currentIndex],nums[startIndex], nums[endIndex]);
                    //result need sort ==> nums sort so need follow the numbers

                    resultSet.add(elements);

                    //index should continue move
                    endIndex--;

                }
            }

        }
//            return  resultSet. 如何转变
        return new ArrayList(resultSet);

    }
```

## 7）环形链表

```java
 /**
     * 判断链表是否有环
     * 暴力破解法 双重循环所有可能 n*n
     * 思路：快慢指针 慢指针 核心 希望兔子领先x步的时候能追上乌龟 x存在
     * 利用快慢指针的速度差 完成相遇 本题目的核心是 兔子和乌龟的相对位移 每一步始终相差1
     * 即是：1 2 3 4 5 如有环相对距离变化 1 2 3 x-3 x-2 x-1 直到 相对距离为0
     * 如果无环 相对距离 1 2 3 4 5 6 7 会一直增加
     * -> 所以我的想法是：按照相对距离入手 看看跑得最后相对距离是多少
     
     思路2：自己给自己制造跳出条件，造环。
     递归的解法：
     https://leetcode.com/problems/linked-list-cycle/discuss/44603/Shorter-Solution-in-Java
     
     *
     *
     */
    public boolean hasCycle(ListNode head) {

        //定义快慢指针
        //计算相对距离
        //判断兔子是否跑到末尾,lastNode is null or not


        //定义环形标识
        boolean flag = false;

        //边界条件1
        if(head == null || head.next == null) {


            return flag;
        }



        ListNode slowIndex = head;

//        ListNode fastIndex = head;
        //兔子需要比乌龟快一步吗 需要的 这是为啥？if (slowIndex == fastIndex) 这是原因
        ListNode fastIndex = head.next;


        //让兔子跑起来
        //兔子跑得快
        //fastIndex = fastIndex.next.next;决定了fastIndex不能为null  fastIndex.next不能为null
        while (fastIndex != null && fastIndex.next != null ) {

            if (slowIndex == fastIndex) {

                flag = true;

                return flag;

            }

            //乌龟 一步
            slowIndex = slowIndex.next;

            //兔子 两步
            fastIndex = fastIndex.next.next;

        }

        return flag;

    }



----- 方法二
https://leetcode.com/problems/linked-list-cycle/discuss/44603/Shorter-Solution-in-Java
/*** 自己制造环 1 -》2 -》3   环形的节点进入内节点后 内节点被套环 所以可以识别
Note that "head.next = head". This makes node.next point to itself. That is, each node that we have visited points to itself. So if there exist a loop, we will finally visit a node that points to itself.
*/
public boolean hasCycle(ListNode head){
       if(head == null || head.next == null) return false;
       if(head.next == head) return true;
       ListNode nextNode = head.next; 
       head.next = head;
       boolean isCycle = hasCycle(nextNode);
       return isCycle;
   }

----- 自己写出来的
    /**
     * 环形链表
     * 思路01 ： 使用快慢两个指针 慢指针每次移动1 快指针每次移动2 使得相对位移为1 如果无环指针间的距离增大 不会相遇
     * 思路02：用递归遍历链表 认为的每各节点制造环 使得有环的节点进入后 直接被检测出来它回头了
     */
    public boolean hasCycle(ListNode head) {

      // 递归遍历数组
      // 跳出 01有环  02链表为空 03链表只有单节点
      // 步骤 01 取出下一个节点 02 制环

      if (head == null || head.next == null) {

          return false;
      }

      if (head == head.next) {

          return true;
      }

      ListNode nextNode = head.next;
      head.next = head;

      boolean flag = hasCycle(nextNode);

      return flag;

    }

```

- ## 20 有效的括号

  ```java
      /**
             * 有效的字符串
             * 思路：暴力解法 循环字符串寻找抵消replace不太明白？？？
             *    宾果消消乐 一对属性值消失  直到留下空串  使用栈保留最里面的左一半和右一半一一抵消
             * 参考：https://leetcode-cn.com/problems/valid-parentheses/solution/20java-liang-chong-fang-fa-xiang-jie-by-ustcyyw/
             *
             * 最好答案：https://leetcode.com/problems/valid-parentheses/discuss/9178/Short-java-solution
             * 弥补continue的短板
             */
      public boolean isValid(String s) {
  
         // 创建保留栈
          // 放入所有左括号 放入相反的值
         // 没有匹配到就入栈
         // 匹配到就出栈
         // 栈空 字符串空就是正常的
         //边界条件
  
  //       if (s == null || s.length > 1) {
          //不能为奇数
         if (s == null || s.length() % 2 != 0) {
  
             return false;
         }
  
  //       Stack<char> matchStack = new Stack();
          //这里只能放入包装类型
         Stack<Character> matchStack = new Stack();
         for(char in : s.toCharArray()) {
  //           matchStack.
          //如何只放入左边 不放入右边呢
            if( '(' == in || '[' == in || '{' == in ) {
  
  //              char inMath = null;
                //这不是对象
                Character inMath = null;
                if ('(' == in) {
                    inMath = ')';
                    matchStack.push(inMath);
                    continue;
                }
  
                if ('{' == in) {
                    inMath = '}';
                    matchStack.push(inMath);
                    continue;
                }
  
                if ('[' == in) {
                    inMath = ']';
                    matchStack.push(inMath);
                    continue;
                }
  
            }
  
            //找到右边的括号了  测试用例:"){"  栈不能空
            if (!matchStack.empty()) {
  
                //自动拆箱的体现 需要类型显示转换时
                char topElement = matchStack.peek();
  
                if (topElement == in ) {
  
                    //弹出
                    matchStack.pop();
                }
                
                   //右括号
                  /*if ( !helper.empty() && Character.valueOf(element).equals(helper.peek())) {
                      helper.pop();*/
                  }
  
            }
  
  
         }
  
  
         if (matchStack.empty()) {
  
             return true;
         }
  
         return false;
  
      }
  
  
  ---- 解法二 国际站 高票
  
  public boolean isValid(String s) {
  	Stack<Character> stack = new Stack<Character>();
  	for (char c : s.toCharArray()) {
  		if (c == '(')
  			stack.push(')');
  		else if (c == '{')
  			stack.push('}');
  		else if (c == '[')
  			stack.push(']');
  		else if (stack.isEmpty() || stack.pop() != c)
  			return false;
  	}
  	return stack.isEmpty();
  }
  ```

- ## 最小栈

  ```java
  难点 ：空指针 stack.pop()).equals(minStack.peek())
        minStack.peek() >= x
      
  ## 空指针要死人   
  
      /**
       * 常数时间检测到最小值
       *
       *思路： 两个栈 一个栈存数  一个栈存最小值 https://leetcode-cn.com/problems/min-stack/solution/min-stack-fu-zhu-stackfa-by-jin407891080/
       * 思路02 用int代替栈 https://leetcode.com/problems/min-stack/discuss/49014/Java-accepted-solution-using-one-stack
        */
  class MinStack {
  
      /** initialize your data structure here. */
  
      //开辟两个栈 一个存值 一个存最小值
      //push 值进入栈一  和 栈二比较
      //pop 值从栈1拿到  比较栈2 处理空指针的问题
      //top peek栈1
      //getMin peek栈2
  
      private Stack<Integer> stack;
      private Stack<Integer> minStack ;
  
      //初始化栈的构造方法
      public MinStack() {
  
         this.stack = new Stack();
         this.minStack = new Stack();
  
      }
  
      public void push(int x) {
  
          stack.push(x);
          if (minStack.empty()) {
              minStack.push(x);
          } else {
  
              //最小值才入栈
  //            if (minStack.peek() > x) {
              //21111111111 保证两个栈的最小值数目一直 防止空指针
              if (minStack.peek() >= x) {
              minStack.push(x);
          }
          }
  
      }
  
      public void pop() {
  
  //            if (stack.pop() == minStack.peek())
              //这里main要删除  min只取值 而且  Character pop = matchStack.pop(); 对象比较用equals
              //这里返回的是一个包装类对象！！！！！！！！
  //            if ((stack.pop()).equals(minStack.peek())) {
  
  //                minStack.pop();
  //            }
              
             //改进
             if (!now.empty() && !min.empty() &&) {
  
                  if (top() == getMin()) {
  
                      min.pop();
                  }
                  
              now.pop();
          }
  
      }
  
      public int top() {
  
         return  stack.peek();
      }
  
      public int getMin() {
  
        return  minStack.peek();
  
      }
  }
  
  /**
   * Your MinStack object will be instantiated and called as such:
   * MinStack obj = new MinStack();
   * obj.push(x);
   * obj.pop();
   * int param_3 = obj.top();
   * int param_4 = obj.getMin();
   */
  //leetcode submit region end(Prohibit modification and deletion)
  
  }
  
  ```
