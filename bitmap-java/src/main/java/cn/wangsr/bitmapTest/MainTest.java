package cn.wangsr.bitmapTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.*;


/**
 * wjl  测试
 */
public class MainTest {
    //数据量  视情况而定
    private static final int Capacity=1000;
    private static final String lineSeparator = "\n";


    /**
     * 生成指定容量数据
     * @param data  数据容量
     */
    public static void generateData(int data){
        Random random = new Random();
        FileChannel outChannel=null;
        try{
            FileOutputStream fileOutputStream=new FileOutputStream(new File("data.txt"));
             outChannel= fileOutputStream.getChannel();
            ByteBuffer byteBuffer=ByteBuffer.allocate(2048);
            for(int i=0;i<data;i++){
                byteBuffer.put(String.valueOf(random.nextInt(data)).getBytes());
                byteBuffer.put(lineSeparator.getBytes()); //换行
                byteBuffer.flip();   //翻转  limit初始位置在capacity   翻转后limit变到position    position到0
                outChannel.write(byteBuffer);   //文件中写入数据
                byteBuffer.clear(); //清空 position位置为0，limit=capacity
            }
            outChannel.close();
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 数据读取后存入数组
     */
    public static int[] readData(){

        //提取数据
        List list=new LinkedList<>();

        try{
            FileInputStream fileInputStream=new FileInputStream(new File("data.txt"));
            FileChannel inChannel=fileInputStream.getChannel();
            ByteBuffer byteBuffer=ByteBuffer.allocate(2048);
            while (true){
                int read=inChannel.read(byteBuffer);
                if(-1==read){break;}
                byteBuffer.flip();
                while (byteBuffer.hasRemaining()){
                    String line = (Charset.defaultCharset().decode(byteBuffer).toString());
                    Arrays.stream(line.split("\n")).forEach(x->{
                        list.add(Integer.parseInt(x));
                    });
                }
                byteBuffer.clear();
            }
            inChannel.close();


        }catch (Exception e){
            throw new RuntimeException(e);
        }
        //存入数组
        int[] arr=new int[list.size()];
        list.forEach(y->{
            arr[list.indexOf(y)]=(int)y;
        });
        //返回数组
        return  arr;
    }


    public static void main(String[] args) throws Exception{

        //  生成数据      generateData(Capacity);


        //读取数据    readData()   并存入数据数组
        //初始化原始数据数组
        int[] arrOrigin=null;
         arrOrigin= readData();

         //初始化bitmap
         BitMapFiTest.initBitMap(Capacity);


         //插入数据

        Arrays.stream(arrOrigin).forEach(x->{
            BitMapFiTest.insertBitMap(x);
        });

        //判断存在
        BitMapFiTest.isExist(7896);


        //数据生成容量
        System.out.println(arrOrigin.length);

    }
}
