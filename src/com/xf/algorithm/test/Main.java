package com.xf.algorithm.test;

import com.xf.utils.Utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author xfwaaang
 * @create 2019-09-06 14:44
 *
 * 利用注解测试solution
 */
public class Main {
    public static void main(String[] args){
        Utils.println("############ Test start ################");

        com.xf.algorithm.exam.Solution slt_exam = new com.xf.algorithm.exam.Solution();
        com.xf.algorithm.bitmanipulation.Solution slt_bit = new com.xf.algorithm.bitmanipulation.Solution();

        try {
            test(slt_bit);
            test(slt_exam);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Utils.println("############ Test finished ################");

    }

    private static void test(Object obj) throws Exception {
        Utils.println("--- Test " + obj.getClass().getName() + " start---");
        for (Method method : obj.getClass().getDeclaredMethods()) {
            Annotation[] annos = method.getDeclaredAnnotations();
            if (annos.length == 0)  continue;

            String method_name = method.getName() + "(";
            String args_str = "";

            Object[] args = new Object[annos.length];
            for (int i = 0; i < annos.length; i++) {
                String type = "";
                String arg = "";
                if(annos[i].annotationType() == TypeInt.class)  {
                    args[i] = ((TypeInt)annos[i]).value();
                    type = "int";
                    arg =  "" + args[i];
                }
                if(annos[i].annotationType() == TypeInts.class)  {
                    args[i] = ((TypeInts)annos[i]).value();
                    type = "int[]";
                    arg = Arrays.toString((int[])args[i]);
                }
                if(annos[i].annotationType() == TypeString.class)  {
                    args[i] = ((TypeString)annos[i]).value();
                    type = "String";
                    arg = "" + args[i];
                }
                if(annos[i].annotationType() == TypeStrings.class)  {
                    args[i] = ((TypeStrings)annos[i]).value();
                    type = "String[]";
                    arg = "[";
                    for (int i1 = 0; i1 < ((String[]) args[i]).length; i1++) {
                        if(i1 == 0)    arg += ((String[]) args[i])[i1];
                        else   arg += ", " + ((String[]) args[i])[i1];
                    }
                    arg += "]";
                }
                if(i == 0){
                    method_name += type;
                    args_str += arg;
                }else{
                    method_name += ", " + type;
                    args_str += ", " + arg;
                }
            }


            Utils.println("method: " + method_name + ")");
            Utils.println("args: " + args_str);
            Utils.println("result: " + method.invoke(obj, args));
        }

        Utils.println("--- Test " + obj.getClass().getName() + " finished ---");
    }

}
