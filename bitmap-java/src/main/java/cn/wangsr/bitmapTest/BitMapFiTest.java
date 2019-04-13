package cn.wangsr.bitmapTest;


import java.util.Random;

/**
 * wjl
 * 大数据bitmap索引
 * 用int存值   数据上限：2^31
 */
public class BitMapFiTest {


     private static int[] bitsMap;

     //初始化bitmap容量
     public static void initBitMap(int bitData){
         bitsMap=new int[1+bitData/32];
     }

   //原始数据放入bitmap
     public static void insertBitMap(int num){
         int bitIndex=getBitIndex(num);
         int offSet=getBitOffset(num);
         bitsMap[bitIndex] |= 1<< offSet;
     }

     //判断数据是否存在该数据集中
     public static boolean isExist(int num){
         int bitIndex=getBitIndex(num);
         int offSet=getBitOffset(num);
         int bits =  bitsMap[bitIndex];
         return  (bits &  1<< offSet) !=0;
     }

    //2^5 = 32  求出在bit数组的哪个索引   0~Capacity (bit数组容量)
     public static  int  getBitIndex(int num){
         return num >> 5;
     }
    //求出在当前索引下具体位置  0~31  （32位）
    public static  int  getBitOffset(int num){
        return num  & 32;
    }


}
