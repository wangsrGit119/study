package cn.wangsr.netty;

import java.security.SecureRandom;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLamda   {



    public static void main(String[] args){

// ====================================================设定初始线程池大小==========================================

        ExecutorService executorService= Executors.newFixedThreadPool(5);

        for(int j=1;j<=10;j++){
            try {
                Thread.sleep( 1000);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }

// ===============================Runable  是接口  里面只有一个抽象方法所以可以实现函数式写法===========================
//            Runnable ruTest=()->{
//                System.out.println("线程：：" + Thread.currentThread().getName());
//            };
//            ruTest.run();
// ======================================================================================================================
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("线程：：" + Thread.currentThread().getName());
                }
            });

//            executorService.execute(new Thread(() -> {
//                System.out.println("线程：：" + Thread.currentThread().getName());
//            }));

        }




   //=====================一次性创建十个线程=================================
        for( int i=1;i<=10;i++){
            new Thread(()->System.out.println(new SecureRandom().nextInt(78)+"线程::"+Thread.currentThread().getName())).start();
        }

        System.out.println(new SecureRandom().nextInt(78)+"线程::"+Thread.currentThread().getName());










    }

}
