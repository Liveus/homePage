package com.liveus.letcode;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Desc:
 * @author: Lenovo
 * @Time: 2020/3/2 10:50
 * @Copyright: © Liveus
 * @Warning: for fun
 */
public class Letcode {
    public static void main(String[] args) {
        Letcode letcode = new Letcode();
        //字符串url化
        /*System.out.println(letcode.replaceSpaces("Mr John Smith    ",13));
        System.out.println(letcode.replaceSpaces("               ", 5));*/

        // 打家劫舍
        /*System.out.println(letcode.massage(new int[]{2,1,4,5,3,1,1,3}));*/

        //最多人存活的年份
        /*int[] birth = new int[]{1900, 1901, 1950};
        int[] death = new int[]{1948, 1951, 2000};
        System.out.println(letcode.maxAliveYear(birth,death));*/

        //单链反转
        /*ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        listNode5.next = null;
        ListNode listNode = letcode.reverseList(listNode1);
        while (listNode.next!=null){
            System.out.print(listNode.val+"->");
            listNode =listNode.next;
        }
        System.out.print(listNode.val+"->");*/

        //合并排序的数组
        /*int[] a = new int[]{1,2,3,0,0,0};
        int[] b = new int[]{2,5,6};

        int[] a = new int[]{2, 0};
        int[] b = new int[]{1};

        int[] a = new int[]{4,5,6,0,0,0};
        int[] b = new int[]{1,2,3};
        letcode.merge(a, 3, b, 3);*/

        // 压缩字符串
        /*System.out.println(letcode.compressString("a"));*/

        /*String J = "aA", S = "aAAbbbb";
        System.out.println(letcode.numJewelsInStones(J,S));*/

        // 相邻感染
        int[][] a = new int[][]{{2,1,1},{1,1,0},{0,1,1}};
        System.out.println(letcode.orangesRotting(a));

        /*int candies = 34;
        int num_people = 2;
        int[] result = letcode.distributeCandies(candies,num_people);
        for(int a:result){
            System.out.println(a);
        }*/
    }

    public String replaceSpaces(String S, int length) {
        char[] chars = S.toCharArray();
        StringBuilder newStr = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            if (i < length) {
                if (chars[i] == ' ') {
                    newStr.append("%20");
                } else {
                    newStr.append(chars[i]);
                }
            }
        }
        return newStr.toString();
    }

    public int massage(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int twoMax = Math.max(nums[0], nums[1]);
        if (nums.length == 2) {
            return twoMax;
        }

        int[] dp = new int[nums.length + 1];
        dp[0] = 0;
        dp[1] = nums[0];
        dp[2] = twoMax;
        for (int i = 2; i < nums.length + 1; i++) {
            // 判断dp[i]是dp[i-1]，还是dp[i-2]+新的值比较大
            dp[i] = Math.max(dp[i - 2] + nums[i - 1], dp[i - 1]);
        }
        return dp[nums.length];
    }

    public int maxAliveYear(int[] birth, int[] death) {
        int maxAlive = 0;
        int year = 0;
        for (int i = 1900; i < 2001; i++) {
            int singleCount = 0;
            for (int j = 0; j < birth.length; j++) {
                if (birth[j] <= i && death[j] >= i)
                    singleCount++;
            }
            if (singleCount > maxAlive) {
                year = i;
                maxAlive = singleCount;
            }
        }
        return year;
    }

    public ListNode reverseList(ListNode head) {
        ListNode listNode = new ListNode(0);
        listNode.next = null;
        listNode.val = head.val;
        ListNode pa = new ListNode(0);
        while (head.next != null) {
            ListNode node = new ListNode(0);
            node.val = listNode.val;
            node.next = listNode.next;
            pa.val = head.next.val;
            pa.next = node;
            listNode = pa;
            head = head.next;
        }
        return listNode;
    }

    public void merge(int[] A, int m, int[] B, int n) {
        if (n > 0) {
            int mark = 0;
            int[] result = new int[m + n];
            int sign = 0;
            boolean down = false;
            for (int i = 0; i < m; i++) {
                if(!down){
                    while (A[i] > B[sign]) {
                        result[mark] = B[sign];
                        mark++;
                        if(sign+1==B.length){
                            down = true;
                            break;
                        }
                        sign++;
                    }
                }
                result[mark] = A[i];
                mark++;
            }
            if (mark < m + n) {
                for (int i = sign; i < B.length; i++) {
                    result[mark] = B[i];
                    mark++;
                }
            }
            System.arraycopy(result, 0, A, 0, result.length);
        }
    }

    public String compressString(String S) {
        if(S.equals("")){
            return "";
        }
        if(S.length()==1){
            return S;
        }
        StringBuilder result = new StringBuilder();
        char[] chars = S.toCharArray();
        char s  = chars[0];
        int sum = 1;
        for (int i = 1;i<chars.length;i++){
            if(s==chars[i]){
                sum++;
            }else{
                result.append(s).append(sum);
                s = chars[i];
                sum = 1;
            }
            if(i+1==chars.length){
                result.append(s).append(sum);
            }
        }
        return result.toString().toCharArray().length<S.toCharArray().length?result.toString():S;
    }

    public int numJewelsInStones(String J, String S) {
        int sum = 0;
        char[] chars = S.toCharArray();
        for (char c:chars){
            if(J.contains(String.valueOf(c))){
                sum++;
            }
        }
        return sum;
    }

    public int orangesRotting(int[][] grid) {
        // 返回值
        int result = -1;
        //循环是否结束
        boolean down = false;
        // 用时，初始会执行一次，所以设置为-1
        int min = -1;

        do{
            // 循环一次用时+1
            min++;
            down = true;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    if(grid[i][j]==2){
                        if(i+1<grid.length&&grid[i+1][j]==1){
                            grid[i+1][j]=1;
                            // 如果有变化则循环继续
                            down =  false;
                        }
                        if(i-1>0&&grid[i-1][j]==1){
                            grid[i-1][j]=1;
                            down =  false;
                        }
                        if(j+1<grid[i].length&&grid[i][j+1]==1){
                            grid[i][j+1]=1;
                            down =  false;
                        }
                        if(j-1>0&&grid[i][j-1]==1){
                            grid[i][j-1]=1;
                            down =  false;
                        }
                    }
                }
            }


        }while (!down);

        
        return result;
    }

    public int[] distributeCandies(int candies, int num_people) {
        int[] a = new int[num_people];
        int round = 0;
        int n = num_people;
        while(n*(n+1)/2<=candies){
            round++;
            n = n+num_people;
        }
        n  = n-num_people;
        int remain = candies-n*(n+1)/2;
        // 第n+1轮第一个人应该发给的
        int single = round*num_people+1;
        for (int i = 0; i < num_people; i++) {
            // 不够分发
            if(single>remain){
                a[i] = round*(round-1)*num_people/2+round*(i+1)+
                        remain;
                remain = 0;
            //够分发
            }else{
                a[i] = round*(round-1)*num_people/2+round*(i+1)+
                        single;
                remain = remain-single;
                single++;
            }
        }
        return a;
    }
}
