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
 * 可重复注解需要定义一个注解容器，如：TypeIntC.class
 * 可重复注解：同一类型的注解有多个则类型为注解对应得注解容器，同一类型的注解只有一个则类型为注解本身
 */
public class Test {
    public static void main(String[] args){
        Utils.println("############ Test start ################");

        com.xf.algorithm.exam.Solution slt_exam = new com.xf.algorithm.exam.Solution();
        com.xf.algorithm.bitmanipulation.Solution slt_bit = new com.xf.algorithm.bitmanipulation.Solution();
        Solution slt_dac = new Solution();
        com.xf.algorithm.strmatharr.Solution slt_sma = new com.xf.algorithm.strmatharr.Solution();
        com.xf.algorithm.pointer.Solution slt_ptr = new com.xf.algorithm.pointer.Solution();


        try {
            test(slt_bit);
//            test(slt_exam);
//            test(slt_dac);
//            test(slt_sma);
//            test(slt_ptr);
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

            Annotation annos[] = method.getDeclaredAnnotations();
            for(Annotation anno : annos){
                String annoName = anno.annotationType().getName();
                annoName = annoName.substring(annoName.lastIndexOf(".") + 1);
                switch (annoName){
                    case "TypeInt":
                        args.add(((TypeInt)anno).value());
                        break;
                    case "TypeInts":
                        args.add(((TypeInts)anno).value());
                        break;
                    case "TypeString":
                        args.add(((TypeString)anno).value());
                        break;
                    case "TypeStrings":
                        args.add(((TypeStrings)anno).value());
                        break;
                    case "TypeIntC":
                        for (TypeInt typeInt : ((TypeIntC) anno).value()) {
                            args.add(typeInt.value());
                        }
                        break;
                    case "TypeIntsC":
                        for (TypeInts typeInts : ((TypeIntsC) anno).value()) {
                            args.add(typeInts.value());
                        }
                        break;
                    case "TypeStringC":
                        for (TypeString typeString : ((TypeStringC) anno).value()) {
                            args.add(typeString.value());
                        }
                        break;
                    case "TypeStringsC":
                        for (TypeStrings typeStrings : ((TypeStringsC) anno).value()) {
                            args.add(typeStrings.value());
                        }
                        break;
                }

            }

            //获取函数形参类型，并转为字符串
            StringBuilder paramTypeStr = new StringBuilder();
            Type[] paramTypes = method.getGenericParameterTypes();
            for (int i = 0; i < paramTypes.length; i++) {
                if (i == 0){
                    paramTypeStr.append(paramTypes[i].getTypeName());
                }else{
                    paramTypeStr.append(", ").append(paramTypes[i].getTypeName());
                }
            }

            //获取函数实参值的字符串表示
            StringBuilder argsStr = new StringBuilder();
            String argStr;
            for (int i = 0; i < args.size(); i++) {
                argStr = Utils.toString(args.get(i));
                if (i == 0){
                    argsStr.append(argStr);
                }else {
                    argsStr.append(", ").append(argStr);
                }
            }

            //获取函数返回值的字符串表示
            Object res = method.invoke(obj, args.toArray());
            String resStr = Utils.toString(res);

            Utils.println("method: " + method.getName() + "(" + paramTypeStr.toString() + ")");
            Utils.println("args: " + argsStr.toString());
            Utils.println("result: \n" + resStr + "\n");
        }

        Utils.println("--- Test " + obj.getClass().getName() + " finished ---");
    }



}
