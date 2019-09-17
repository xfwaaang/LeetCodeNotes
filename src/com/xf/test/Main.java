package com.xf.test;

import com.xf.algorithm.dac.Solution;
import com.xf.utils.Utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author xfwaaang
 * @create 2019-09-06 14:44
 *
 * 利用注解测试solution
 * 可重复注解：同一类型的注解有多个则类型为注解对应得注解容器，同一类型的注解只有一个则类型为注解本身
 * 使用时，需按照 TypeInt, TypeInts, TypeString, TypeStrings 的顺序放置
 */
public class Main {
    public static void main(String[] args){
        Utils.println("############ Test start ################");

        com.xf.algorithm.exam.Solution slt_exam = new com.xf.algorithm.exam.Solution();
        com.xf.algorithm.bitmanipulation.Solution slt_bit = new com.xf.algorithm.bitmanipulation.Solution();
        Solution slt_dac = new Solution();
        com.xf.algorithm.strmatharr.Solution slt_sma = new com.xf.algorithm.strmatharr.Solution();
        com.xf.algorithm.pointer.Solution slt_ptr = new com.xf.algorithm.pointer.Solution();


        try {
//            test(slt_bit);
//            test(slt_exam);
//            test(slt_dac);
//            test(slt_sma);
            test(slt_ptr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Utils.println("############ Test finished ################");

    }

    /**
     * 使用可重复注解（注解容器）
     * 相同类型的注解需要相邻放置
     * @param obj
     * @throws Exception
     */
    public static void test(Object obj) throws Exception{
        Utils.println("--- Test " + obj.getClass().getName() + " start---");

        for (Method method : obj.getClass().getDeclaredMethods()) {
            if (method.getAnnotations().length == 0)    continue;

            //获取函数实参值
            List<Object> args = new ArrayList<>();

            TypeInt[] typeIntArr = method.getAnnotationsByType(TypeInt.class);
            TypeInts[] typeIntsArr = method.getAnnotationsByType(TypeInts.class);
            TypeString[] typeStringArr = method.getAnnotationsByType(TypeString.class);
            TypeStrings[] typeStringsArr = method.getAnnotationsByType(TypeStrings.class);

            for (TypeInt typeInt : typeIntArr)    args.add(typeInt.value());
            for (TypeInts typeInts : typeIntsArr)    args.add(typeInts.value());
            for (TypeString typeString : typeStringArr)    args.add(typeString.value());
            for (TypeStrings typeStrings : typeStringsArr)    args.add(typeStrings.value());

            //获取函数形参类型，并转为字符串
            StringBuilder paramTypeStr = new StringBuilder();
            Type[] paramTypes = method.getGenericParameterTypes();
            for (int i = 0; i < paramTypes.length; i++) {
                if (i == 0)  paramTypeStr.append(paramTypes[i].getTypeName());
                else    paramTypeStr.append(", ").append(paramTypes[i].getTypeName());
            }

            //获取函数实参值的字符串表示
            StringBuilder argsStr = new StringBuilder();
            StringBuilder argStr;
            for (int i = 0; i < args.size(); i++) {
                argStr = new StringBuilder();
                Object arg = args.get(i);
                if (arg instanceof Integer){
                    argStr.append(args.get(0));
                }else if (arg instanceof int[]){
                    argStr.append(Arrays.toString((int[])arg));
                }else if (arg instanceof String){
                    argStr.append(arg);
                }else if (arg instanceof String[]){
                    argStr.append(Arrays.toString((String[])arg));
                }

                if (i == 0){
                    argsStr.append(argStr);
                }else {
                    argsStr.append(", ").append(argStr);
                }
            }

            //获取函数返回值的字符串表示
            Object res = method.invoke(obj, args.toArray());
            String resStr;
            if(res instanceof int[]){
                resStr = Arrays.toString((int[])res);
            }else if (res instanceof String[]){
                resStr = Arrays.toString((String[])res);
            }else {
                resStr = res.toString();
            }

            Utils.println("method: " + method.getName() + "(" + paramTypeStr.toString() + ")");
            Utils.println("args: " + argsStr.toString());
            Utils.println("result: \n" + resStr + "\n");
        }

        Utils.println("--- Test " + obj.getClass().getName() + " finished ---");
    }

    private static void test1(Object obj) throws Exception {
        Utils.println("--- Test " + obj.getClass().getName() + " start---");
        for (Method method : obj.getClass().getDeclaredMethods()) {
            Annotation[] annos = method.getDeclaredAnnotations();
            if (annos.length == 0)  continue;

            String method_name = method.getName() + "(";
            String args_str = "";

            Utils.println(annos[0].annotationType());

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
            Utils.println("result: \n" + method.invoke(obj, args));
        }

        Utils.println("--- Test " + obj.getClass().getName() + " finished ---");
    }

}
