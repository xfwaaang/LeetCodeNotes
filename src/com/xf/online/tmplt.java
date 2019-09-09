package com.xf.online;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

/**
 * @author xfwaaang
 * @create 2019-09-08 13:28
 * 在线笔试模板
 */
public class tmplt {
    //全局变量
    static int a = 0;
    private static void tmplt(){
        //队列
        Queue<String> queue = new LinkedList<>();
        queue.isEmpty();
        queue.size();
        queue.offer("hhh");
        String s = queue.poll();

        //栈
        Stack<String> stack = new Stack<>();
        stack.empty();
        stack.size();
        stack.push("hhh");
        String top = stack.pop();

        //输入输出
        int n;
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        while(scanner.hasNext()){}

        int[] arr = new int[n];
        int len = arr.length;
    }

    public static void main(String[] args){

    }
}
