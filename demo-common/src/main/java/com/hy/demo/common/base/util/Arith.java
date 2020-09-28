package com.hy.demo.common.base.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/** 
 * 进行BigDecimal对象的加减乘除，四舍五入等运算的工具类 
 * @author linw 
 * 
 */  
public class Arith {   

    //默认除法运算精度
    private static final int DEF_DIV_SCALE = 10;   
          
    //这个类不能实例化   
    private Arith() {
    }
  
    /**
     * 提供精确的加法运算。
     * @param v1 加数
     * @param v2 被加数
     * @return 和
     */
    public static double add(double v1, double v2) {
        return add(BigDecimal.valueOf(v1), BigDecimal.valueOf(v2)).doubleValue();
    }

    public static BigDecimal add(BigDecimal b1, BigDecimal b2) {
        return b1.add(b2);
    }


    /**  
     * 提供精确的减法运算。
     * @param v1 减数
     * @param v2 被减数
     * @return 差
     */   
    public static double sub(double v1 ,double v2) {
        return sub(BigDecimal.valueOf(v1), BigDecimal.valueOf(v2)).doubleValue();
    }

    public static BigDecimal sub(BigDecimal v1, BigDecimal v2) {
        return v1.subtract(v2);
    }
      
    /**  
     * 提供精确的乘法运算。  
     * @param v1 乘数
     * @param v2 被乘数
     * @return 两个参数的积  
     */   
    public static double mul(double v1, double v2) {
        return mul(BigDecimal.valueOf(v1), BigDecimal.valueOf(v2)).doubleValue();
    }

    public static BigDecimal mul(BigDecimal v1, BigDecimal v2) {
        return v1.multiply(v2);
    }
  
    /**  
     * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到
     * 小数点以后10位，以后的数字四舍五入。
     * @param v1 除数
     * @param v2 被除数
     * @return 商
     */   
    public static double div(double v1, double v2) {
        return div(v1, v2, DEF_DIV_SCALE);
    }   

    public static double div(double v1, double v2, int scale) {
        return div(BigDecimal.valueOf(v1), BigDecimal.valueOf(v2), scale).doubleValue();
    }

    public static BigDecimal div(BigDecimal v1, BigDecimal v2) {
        return div(v1, v2, DEF_DIV_SCALE);
    }

    public static BigDecimal div(BigDecimal v1, BigDecimal v2, int scale) {
        return div(v1, v2, scale, RoundingMode.HALF_UP);
    }


    /**  
     * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到
     * 小数点以后10位，有小数点，向前一位加
     * @param v1 除数
     * @param v2 被除数
     * @return 商
     */   
    public static double divUp(double v1, double v2) {
        return divUp(v1, v2, DEF_DIV_SCALE);
    }   

    public static double divUp(double v1, double v2, int scale) {
        return divUp(BigDecimal.valueOf(v1), BigDecimal.valueOf(v2), scale).doubleValue();
    }

    public static BigDecimal divUp(BigDecimal v1, BigDecimal v2) {
        return divUp(v1, v2, DEF_DIV_SCALE);
    }

    public static BigDecimal divUp(BigDecimal v1, BigDecimal v2, int scale) {
        return div(v1, v2, scale, RoundingMode.UP);
    }
    
    /**  
     * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到
     * 小数点以后10位，以后的数字，直接舍去
     * @param v1 除数
     * @param v2 被除数
     * @return 商
     */   
    public static double divDown(double v1, double v2) {
        return divDown(v1, v2, DEF_DIV_SCALE);
    }   

    public static double divDown(double v1, double v2, int scale) {
        return divDown(BigDecimal.valueOf(v1), BigDecimal.valueOf(v2), scale).doubleValue();
    }

    public static BigDecimal divDown(BigDecimal v1, BigDecimal v2) {
        return divDown(v1, v2, DEF_DIV_SCALE);
    }

    public static BigDecimal divDown(BigDecimal v1, BigDecimal v2, int scale) {
        return div(v1, v2, scale, RoundingMode.DOWN);
    }

    public static BigDecimal div(BigDecimal v1, BigDecimal v2, int scale, RoundingMode roundingMode) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        return v1.divide(v2, scale, roundingMode);
    }

  
    /**  
     * 提供精确的小数位四舍五入处理。  
     * @param v 需要四舍五入的数字  
     * @param scale 小数点后保留几位  
     * @return 四舍五入后的结果  
     */   
    public static double round(double v, int scale) {
        return round(v, scale, RoundingMode.HALF_UP);
    }   
    
    /**  
     * 提供精确的小数位向上舍入处理。  
     * @param v 需要向上舍入的数字  
     * @param scale 小数点后保留几位  
     * @return 向上舍入后的结果  
     */   
    public static double roundUp(double v, int scale) {
        return round(v, scale, RoundingMode.UP);
    }   
    
    /**  
     * 提供精确的小数位向下舍去处理。  
     * @param v 需要向下舍去的数字  
     * @param scale 小数点后舍去几位  
     * @return 向下舍去后的结果  
     */   
    public static double roundDown(double v, int scale) {
        return round(v, scale, RoundingMode.DOWN);
    }

    public static double round(double v, int scale, RoundingMode roundingMode) {
        return round(BigDecimal.valueOf(v), scale, roundingMode).doubleValue();
    }

    public static BigDecimal round(BigDecimal v, int scale, RoundingMode roundingMode) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        return v.divide(BigDecimal.ONE, scale, roundingMode);
    }

    /**
    * 提供精确的类型转换(Float)  
    * @param v 需要被转换的数字  
    * @return 返回转换结果  
    */   
    public static float convertsToFloat(double v) {
        return BigDecimal.valueOf(v).floatValue();
    }   
      
    /**  
    * 提供精确的类型转换(Int)不进行四舍五入  
    * @param v 需要被转换的数字  
    * @return 返回转换结果  
    */   
    public static int convertsToInt(double v) {
        return BigDecimal.valueOf(v).intValue();
    }   
  
    /**  
    * 提供精确的类型转换(Long)  
    * @param v 需要被转换的数字  
    * @return 返回转换结果  
    */   
    public static long convertsToLong(double v) {
        return BigDecimal.valueOf(v).longValue();
    }

    /**  
    * 返回两个数中大的一个值  
    * @param v1 需要被对比的第一个数  
    * @param v2 需要被对比的第二个数  
    * @return 返回两个数中大的一个值  
    */   
    public static double max(double v1, double v2) {
        return max(BigDecimal.valueOf(v1), BigDecimal.valueOf(v2)).doubleValue();
    }

    public static BigDecimal max(BigDecimal v1, BigDecimal v2) {
        return v1.max(v2);
    }
  
    /**  
    * 返回两个数中小的一个值  
    * @param v1 需要被对比的第一个数  
    * @param v2 需要被对比的第二个数  
    * @return 返回两个数中小的一个值  
    */   
    public static double min(double v1, double v2) {
        return min(BigDecimal.valueOf(v1), BigDecimal.valueOf(v2)).doubleValue();
    }

    public static BigDecimal min(BigDecimal v1, BigDecimal v2) {
        return v1.min(v2);
    }
  
    /**  
    * 精确对比两个数字  
    * @param v1 需要被对比的第一个数  
    * @param v2 需要被对比的第二个数  
    * @return 如果两个数一样则返回0，如果第一个数比第二个数大则返回1，反之返回-1  
    */   
	public static int compareTo(double v1, double v2) {
        return compareTo(BigDecimal.valueOf(v1), BigDecimal.valueOf(v2));
    }

    public static int compareTo(BigDecimal v1, BigDecimal v2) {
        return v1.compareTo(v2);
    }

    public static void main(String[] args) {
        System.out.println(div(3D, 10D));
    }
}