package com.xf.algorithm.strmatharr;

import java.util.Scanner;

/**
 * @author xfwaaang
 * @create 2019-09-15 18:52
 */
public class Digit2RMB {
    static String HanDigiStr[] = new String[] { "零", "壹", "贰", "叁", "肆", "伍", "陆",
            "柒", "捌", "玖" };

    static String HanDiviStr[] = new String[] { "", "拾", "佰", "仟", "万", "拾", "佰", "仟",
            "亿", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", "佰", "仟", "万",
            "拾", "佰", "仟" };

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        double x = scanner.nextDouble();
        System.out.println(slt2(x));
    }

    private static String fun(String numberStr) {
        String RMBStr = "";
        boolean lastzero = false;
        boolean hasvalue = false; // 亿、万进位前有数值标记
        int len, n;
        len = numberStr.length();
        if (len > 15)   return " ";
        for (int i = len - 1; i >= 0; i--) {
            if (numberStr.charAt(len - i - 1) == ' ')
                continue;
            n = numberStr.charAt(len - i - 1) - '0';
            if (n < 0 || n > 9)
                return "error";
            if (n != 0) {
                if (lastzero)
                    RMBStr += HanDigiStr[0];

                RMBStr += HanDigiStr[n];
                RMBStr += HanDiviStr[i];
                hasvalue = true;

            } else {
                if ((i % 8) == 0 || ((i % 8) == 4 && hasvalue))
                    RMBStr += HanDiviStr[i];
            }
            if (i % 8 == 0)
                hasvalue = false; // 万进位前有值标记逢亿复位
            lastzero = (n == 0) && (i % 4 != 0);
        }
        if (RMBStr.length() == 0)
            return HanDigiStr[0]; // 输入空字符或"0"，返回"零"
        return RMBStr;
    }

    public static String slt2(double mny){
        String rmb = "人民币";
        String tailStr = "";
        long frac, intg; //小数，整数
        int jiao, fen;

        if (mny > 99999999999999.999)  return "";

        // 四舍五入到分
        long temp = Math.round(mny * 100);
        intg = temp / 100;
        frac = temp % 100;
        jiao = (int) frac / 10;
        fen = (int) frac % 10;
        if (jiao == 0 && fen == 0) {
            tailStr = "整";
        } else {
            tailStr = HanDigiStr[jiao];
            if (jiao != 0)
                tailStr += "角";
            if (intg == 0 && jiao == 0)
                tailStr = "";
            if (fen != 0)
                tailStr += HanDigiStr[fen] + "分";
        }

        String cnIntg = fun(String.valueOf(intg));

        return rmb + ( (mny >= 1) ? (cnIntg + "元" + tailStr) : tailStr );
    }
}
