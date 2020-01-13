package com.xf.algorithm.tophot;

import com.sun.istack.internal.NotNull;
import com.xf.test.Test;
import com.xf.test.TypeInt;
import com.xf.test.TypeInts;
import com.xf.test.TypeString;
import com.xf.utils.Utils;

import java.util.*;

/**
 * @author xfwaaang
 * @create 2020-01-07 20:34
 */
public class Solution {
    public static void main(String[] args){
        try {
            Test.test(new Solution());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
     *
     * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/two-sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for(int i=0; i<nums.length; i++){
            if(map.containsKey(target - nums[i])){
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }

        throw new IllegalArgumentException();
    }

    /**
     * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
     *
     * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
     *
     * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     *
     * 示例：
     *
     * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
     * 输出：7 -> 0 -> 8
     * 原因：342 + 465 = 807
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/add-two-numbers
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int sum = 0;
        int carry = 0;
        ListNode root = new ListNode(-1);
        ListNode curr = root;

        while(l1 != null &&  l2 != null){
            sum = l1.val + l2.val + carry;
            carry = sum / 10;

            ListNode node = new ListNode(sum % 10);

            curr.next = node;
            curr = node;


            l1 = l1.next;
            l2 = l2.next;
        }

        while(l1 != null){
            sum = l1.val + carry;
            carry = sum / 10;

            ListNode node = new ListNode(sum % 10);

            curr.next = node;
            curr = node;

            l1 = l1.next;
        }

        while(l2 != null){
            sum = l2.val + carry;
            carry = sum / 10;

            ListNode node = new ListNode(sum % 10);

            curr.next = node;
            curr = node;

            l2 = l2.next;
        }

        if(carry != 0){
            ListNode node = new ListNode(carry);
            curr.next = node;
        }

        return root.next;
    }

    /**
     * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
     *
     * 示例 1:
     *
     * 输入: "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     * 示例 2:
     *
     * 输入: "bbbbb"
     * 输出: 1
     * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
     * 示例 3:
     *
     * 输入: "pwwkew"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param s
     * @return
     */
    @TypeString("bbbbb")
    public int lengthOfLongestSubstring(String s) {
        if(s.length() == 1) return 1;

        int ret = 0;

        int i = 0, j = 1;

        while(j < s.length()){
            int pos = s.substring(i, j).indexOf(s.charAt(j));
            i = i + pos + 1;
            j++;
            ret = Math.max(ret, j-i);
        }

        return ret;
    }

    /**
     * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
     *
     * 示例 1：
     *
     * 输入: "babad"
     * 输出: "bab"
     * 注意: "aba" 也是一个有效答案。
     * 示例 2：
     *
     * 输入: "cbbd"
     * 输出: "bb"
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param s
     * @return
     */
    @TypeString("babad")
    public String longestPalindrome(String s) {
        if(s == null || s.length() == 0)     return "";

        int start = 0, end = 0;
        for(int i=0; i<s.length(); i++){
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i+1);
            int len = Math.max(len1, len2);
            if(len > end - start){
                start = i - (len-1)/2;
                end = i + len / 2;
            }
        }

        return s.substring(start, end+1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)){
            left--;
            right++;
        }
        return right - left - 1;
    }

    @TypeInt(1234)
    public int reverse(int x) {
        long ret = 0;

        while(x != 0){
            ret = ret * 10 + x % 10;
            x /= 10;
        }

        if(ret > Integer.MAX_VALUE || ret < Integer.MIN_VALUE)  return 0;
        return (int) ret;
    }

    /**
     * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
     *
     * 例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.
     *
     * 与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/3sum-closest
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest(int[] nums, int target) {
        int ret = 0;
        int min_dis = Integer.MAX_VALUE;
        int dis;
        Arrays.sort(nums);
        for(int k=1; k<nums.length; k++){
            int i = 0, j = nums.length - 1;
            while (i < k && j > k){
                int sum = nums[i] + nums[k] + nums[j];
                dis = Math.abs(sum - target);
                if(dis == 0)    return sum;
                if(dis < min_dis){
                    min_dis = dis;
                    ret = sum;
                }
                if(sum > target){
                    j--;
                }else if(sum < target){
                    i++;
                }
            }
        }

        return ret;
    }

    /**
     * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，
     * 使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
     *
     * 注意：
     *
     * 答案中不可以包含重复的四元组。
     *
     * 示例：
     *
     * 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
     *
     * 满足要求的四元组集合为：
     * [
     *   [-1,  0, 0, 1],
     *   [-2, -1, 1, 2],
     *   [-2,  0, 0, 2]
     * ]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/4sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        if(nums.length < 4)     return new ArrayList<>();

        Arrays.sort(nums);
        List<List<Integer>> ret = new ArrayList<>();
        Set<List<Integer>> set = new HashSet<>();

        for(int i=1; i<nums.length-2; i++){
            for(int j=i+1; j<nums.length-1; j++){
                int m = 0, n = nums.length - 1;
                while(m < i && n > j){
                    int sum = nums[m] + nums[i] + nums[j] + nums[n];
                    if(sum > target){
                        n--;
                    }else if(sum < target){
                        m++;
                    }else
                    {
                        List<Integer> temp = new ArrayList<>();
                        temp.add(nums[m]);
                        temp.add(nums[i]);
                        temp.add(nums[j]);
                        temp.add(nums[n]);
                        if(!set.contains(temp)){
                            set.add(temp);
                            ret.add(temp);
                        }
                        m++;
                        n--;
                    }
                }
            }
        }

        return ret;
    }

    /**
     * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
     *
     * 示例：
     *
     * 给定一个链表: 1->2->3->4->5, 和 n = 2.
     *
     * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
     * 说明：
     *
     * 给定的 n 保证是有效的。
     *
     * 进阶：
     *
     * 你能尝试使用一趟扫描实现吗？
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null)    return null;

        ListNode p1 = head, p2 = head;

        while (n-- > -1 && p2 != null){
            p2 = p2.next;
        }

        while(p2 != null){
            p1 = p1.next;
            p2 = p2.next;
        }

        if(n >= -1)  return head.next;

        if(p1 == null || p1.next == null)  return null;

        p1.next = p1.next.next;

        return head;
    }

    /**
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
     *
     * 有效字符串需满足：
     *
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     * 注意空字符串可被认为是有效字符串。
     *
     * 示例 1:
     *
     * 输入: "()"
     * 输出: true
     * 示例 2:
     *
     * 输入: "()[]{}"
     * 输出: true
     * 示例 3:
     *
     * 输入: "(]"
     * 输出: false
     * 示例 4:
     *
     * 输入: "([)]"
     * 输出: false
     * 示例 5:
     *
     * 输入: "{[]}"
     * 输出: true
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/valid-parentheses
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param s
     * @return
     */
    @TypeString("{[]}]")
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        char[] chs = s.toCharArray();
        for (char ch : chs) {
            if(ch == '(' || ch == '[' || ch == '{'){
                stack.push(ch);
            }else{
                if(stack.empty())   return false;

                char ch1 = stack.pop();

                if(ch == ')'){
                    if(ch1 != '(')  return false;
                }else if(ch == ']'){
                    if(ch1 != '[')  return false;
                }else if(ch == '}'){
                    if(ch1 != '{')  return false;
                }

            }
        }

        if(!stack.empty())  return false;

        return true;
    }

    /**
     * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
     *
     * 示例：
     *
     * 输入：1->2->4, 1->3->4
     * 输出：1->1->2->3->4->4
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/merge-two-sorted-lists
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(-1);
        ListNode curr = head;

        while(l1 != null && l2 != null){
            if(l1.val < l2.val){
                curr.next = l1;
                l1 = l1.next;
            }else{
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }

        while(l1 != null){
            curr.next = l1;
            l1 = l1.next;
            curr = curr.next;
        }

        while(l2 != null){
            curr.next = l2;
            l2 = l2.next;
            curr = curr.next;
        }

        return head.next;
    }

    /**
     * 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
     *
     * 例如，给出 n = 3，生成结果为：
     *
     * [
     *   "((()))",
     *   "(()())",
     *   "(())()",
     *   "()(())",
     *   "()()()"
     * ]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/generate-parentheses
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param n
     * @return
     */
    @TypeInt(3)
    public List<String> generateParenthesis(int n) {
        List<String> ret = new ArrayList<>();

        generateParenthesisHelper(n, 0, 0, ret, "");

        return ret;
    }

    private void generateParenthesisHelper(int n, int left, int right, List<String> list, String s) {
        if(n == left && n == right){
            list.add(s);
            return;
        }else if(left > n || right > n){
            return;
        }

        if(left > right){
            generateParenthesisHelper(n, left, right+1, list, s+")");
        }
        generateParenthesisHelper(n, left+1, right, list, s+"(");
    }

    /**
     * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
     *
     * 示例:
     *
     * 输入:
     * [
     *   1->4->5,
     *   1->3->4,
     *   2->6
     * ]
     * 输出: 1->1->2->3->4->4->5->6
     * 分治法
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/merge-k-sorted-lists
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        return mergeKListsHelper(lists, 0, lists.length-1);
    }

    private ListNode mergeKListsHelper(ListNode[] lists, int s, int e) {
        if(s > e)   return null;
        if(s == e)  return lists[s];
        int m = (s + e) / 2;
        ListNode node1 = mergeKListsHelper(lists, s, m);
        ListNode node2 = mergeKListsHelper(lists, m+1, e);
        return mergeTwoLists(node1, node2);
    }

    /**
     * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
     *
     * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
     *
     *  
     *
     * 示例:
     *
     * 给定 1->2->3->4, 你应该返回 2->1->4->3.
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/swap-nodes-in-pairs
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null)   return head;

        ListNode tmp = head.next;
        head.next = tmp.next;
        tmp.next = head;
        head = tmp;

        ListNode p = head.next;
        while (p != null){
            if(p.next != null && p.next.next != null){
                tmp = p.next;
                ListNode tmp1 = p.next.next.next;
                p.next = tmp.next;
                tmp.next.next = tmp;
                tmp.next = tmp1;

                p = p.next.next;
            }else{
                break;
            }
        }

        return head;
    }

    /**
     * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
     *
     * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
     *
     * 示例 1:
     *
     * 给定数组 nums = [1,1,2],
     *
     * 函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
     *
     * 你不需要考虑数组中超出新长度后面的元素。
     * 示例 2:
     *
     * 给定 nums = [0,0,1,1,1,2,2,3,3,4],
     *
     * 函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
     *
     * 你不需要考虑数组中超出新长度后面的元素。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        int i = 0, j = 1;

        while(j < nums.length){
            if(nums[j] == nums[i]){
                j++;
            }else{
                nums[++i] = nums[j++];
            }
        }

        return i + 1;
    }

    /**
     * 给定一个数组 nums 和一个值 val，你需要原地移除所有数值等于 val 的元素，返回移除后数组的新长度。
     *
     * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
     *
     * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
     *
     * 示例 1:
     *
     * 给定 nums = [3,2,2,3], val = 3,
     *
     * 函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。
     *
     * 你不需要考虑数组中超出新长度后面的元素。
     * 示例 2:
     *
     * 给定 nums = [0,1,2,2,3,0,4,2], val = 2,
     *
     * 函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。
     *
     * 注意这五个元素可为任意顺序。
     *
     * 你不需要考虑数组中超出新长度后面的元素。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/remove-element
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        int i = -1, j = 0;

        while(j < nums.length){
            if(nums[j] == val){
                j++;
            }else{
                nums[++i] = nums[j++];
            }
        }

        return i + 1;
    }

    /**
     * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
     *
     * 示例 1:
     *
     * 输入: haystack = "hello", needle = "ll"
     * 输出: 2
     * 示例 2:
     *
     * 输入: haystack = "aaaaa", needle = "bba"
     * 输出: -1
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/implement-strstr
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param haystack
     * @param needle
     * @return
     */
    @TypeString("hello")
    @TypeString("llw")
    public int strStr(String haystack, String needle) {
        int i = 0, j = 0;
        char[] hay = haystack.toCharArray();
        char[] nee = needle.toCharArray();

        while(i < hay.length && j < nee.length){
            if(hay[i] == nee[j]){
                i++;
                j++;
            }else{
                i = i - j + 1;
                j = 0;
            }
        }

        if(j == nee.length)     return i - j;

        return -1;
    }

    /**
     * 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
     *
     * 返回被除数 dividend 除以除数 divisor 得到的商。
     *
     * 示例 1:
     *
     * 输入: dividend = 10, divisor = 3
     * 输出: 3
     * 示例 2:
     *
     * 输入: dividend = 7, divisor = -3
     * 输出: -2
     * 说明:
     *
     * 被除数和除数均为 32 位有符号整数。
     * 除数不为 0。
     * 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−2^31,  2^31 − 1]。本题中，如果除法结果溢出，则返回 231 − 1。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/divide-two-integers
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param dividend
     * @param divisor
     * @return
     */
    public int divide(int dividend, int divisor) {
        return -1;
    }

    /**
     * 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
     * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
     * 必须原地修改，只允许使用额外常数空间。
     * 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
     * 1,2,3 → 1,3,2
     * 3,2,1 → 1,2,3
     * 1,1,5 → 1,5,1
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/next-permutation
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param nums
     */
    @TypeInts({1,3,2})
    public void nextPermutation(int[] nums) {
        int n = nums.length;

        if(n < 2)    return;

        int k = n - 1;
        while((k-1) >= 0 && nums[k] > nums[k-1]) k--;
        if(k != n - 1)
        {
            int temp = nums[n-1];
            nums[n-1] = nums[n-2];
            nums[n-2] = temp;
            return;
        }

        k = n - 1;
        while((k-1) >= 0 && nums[k] <= nums[k-1]) k--;
        if(k == 0)
        {
            Arrays.sort(nums);
        }
        else if(k != n - 1)
        {
            int temp = nums[k-1];
            Arrays.sort(nums, k-1, n);

            int i = k;
            for(; i<n; i++)
            {
                if(nums[i] > temp)
                {
                    break;
                }
            }

            temp = nums[i];
            for(int j=i; j>k-1; j--)
            {
                nums[j] = nums[j-1];
            }
            nums[k-1] = temp;
        }
    }

    /**
     * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
     * 示例 1:
     * 输入: "(()"
     * 输出: 2
     * 解释: 最长有效括号子串为 "()"
     * 示例 2:
     * 输入: ")()())"
     * 输出: 4
     * 解释: 最长有效括号子串为 "()()"
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/longest-valid-parentheses
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param s
     * @return
     */
    @TypeString(")()())")
    public int longestValidParentheses(String s) {
        int n = s.length();

        if(n < 2)   return 0;

        char[] chs = s.toCharArray();

        boolean[][] dp = new boolean[n+1][n+1];

        int ret = 0;

        for(int i=0; i<=n-2; i++){
            if(chs[i] == '(' && chs[i+1] == ')') {
                dp[i+1][i+2] = true;
                ret = 2;
            }
        }

        for(int k=2; k<=n-1; k++){
            for(int i=1; i<=n-k; i++){
                int j = i + k;
                if(chs[i-1] == '(' && chs[j-1] == ')'){
                    if(dp[i+1][j-1]){
                        dp[i][j] = true;
                    }else{
                        dp[i][j] = false;
                        for(int x=i+1; x<j-1; x++){
                            if(dp[i][x] && dp[x+1][j]){
                                dp[i][j] = true;
                                break;
                            }
                        }
                    }
                    if(dp[i][j]){
                        ret = Math.max(ret, j-i+1);
                    }
                }else{
                    dp[i][j] = false;
                }
            }
        }

        return ret;
    }

    private boolean longestValidParenthesesHelper(int i, int j, char[] chs) {
        Stack<Character> stack = new Stack<>();

        for(int k=i; k<=j; k++){
            if(chs[k] == ')'){
                if(stack.empty())   return false;
                stack.pop();
            }else{
                stack.push(chs[k]);
            }
        }

        if(!stack.empty())  return false;

        return true;
    }

    /**
     * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
     * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
     * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
     * 你可以假设数组中不存在重复的元素。
     * 你的算法时间复杂度必须是 O(log n) 级别。
     * 示例 1:
     * 输入: nums = [4,5,6,7,0,1,2], target = 0
     * 输出: 4
     * 示例 2:
     * 输入: nums = [4,5,6,7,0,1,2], target = 3
     * 输出: -1
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int low = 0, high = nums.length - 1;

        while(low < high){
            int mid = (low + high) / 2;
            if(target >= nums[mid] && target <= nums[nums.length-1]){

            }
        }

        return -1;
    }

}
