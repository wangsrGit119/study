package cn.wangsr.example02;

public class Test01Lamda {

    public static void main(String[] args){


//=====================匿名内部类=================================
        Test01Inteface ts= new Test01Inteface(){
            @Override
            public void getInfo(String st) {
                System.out.println(st);
            }
        };
       ts.getInfo("hhhhhh");


//==================函数式实现==========================
        Test01Inteface ts02= (param) -> {
            System.out.println(param+"：：lamda");
        };

        ts02.getInfo("lalll");




    }

}
