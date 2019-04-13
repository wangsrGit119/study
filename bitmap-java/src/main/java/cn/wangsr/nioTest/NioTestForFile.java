package cn.wangsr.nioTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Random;

public class NioTestForFile {

    public static void main(String[] args) throws Exception{

        FileInputStream fileInputStream=new FileInputStream(new File("indata.txt"));
        FileOutputStream fileOutputStream=new FileOutputStream(new File("data.txt"));
        FileChannel inChannel=fileInputStream.getChannel();
        FileChannel outChannel= fileOutputStream.getChannel();
        ByteBuffer buffer=ByteBuffer.allocate(512);
        while (true){
            buffer.clear();
            int read=inChannel.read(buffer);
            if(-1==read){break;}
            buffer.flip();
            outChannel.write(buffer);
        }

        inChannel.close();
        outChannel.close();

    }
}
