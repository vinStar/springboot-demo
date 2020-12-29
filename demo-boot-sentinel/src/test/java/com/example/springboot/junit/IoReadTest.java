package com.example.springboot.junit;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import java.io.*;

/**
 * @author lt
 * @date 2020/3/13
 */
public class IoReadTest {


    public static void main(String[] args) throws Exception {

        // such as image data. For reading streams of characters,
        // 测试test
        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\xx\\Desktop\\test.txt");

        int length = -1;
        int byteData = -1;
        byte[] dataArray = new byte[1];
        byteData = fileInputStream.read();
        length = fileInputStream.read(dataArray);
        System.out.println("test1 " + new String(dataArray));
        System.out.println("test2 " + (char) byteData);
        // 测试test
        FileReader fileReader = new FileReader("C:\\Users\\xx\\Desktop\\test.txt");
        // read by two byte
        int characterData = fileReader.read();
        System.out.println("test2 " + (char) characterData);
        characterData = fileReader.read();
        System.out.println("test2 " + (char) characterData);
        characterData = fileReader.read();
        System.out.println("test2 " + (char) characterData);
        characterData = fileReader.read();
        System.out.println("test2 " + (char) characterData);

        fileInputStream = new FileInputStream("C:\\Users\\xx\\Desktop\\sb.jpg");
        // read by two byte
        characterData = fileInputStream.read();
        System.out.println("sb.jpg " + Integer.toHexString(characterData) + "  binary " + Integer.toBinaryString(characterData));
        characterData = fileInputStream.read();
        System.out.println("sb.jpg " + Integer.toHexString(characterData) + "  binary " + Integer.toBinaryString(characterData));


        ImageInputStream imageInputStream = ImageIO.createImageInputStream(fileInputStream);
        imageInputStream.getBitOffset();


        //region begin--------------system.out----------------
//        FileOutputStream fileOutputStream = new FileOutputStream(FileDescriptor.out);
//        fileOutputStream.write('A');
//
//        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
//        outputStreamWriter.write("A");
//
//        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
//        bufferedWriter.write("test");
//
//        bufferedWriter.close();
//        fileOutputStream.close();
//        outputStreamWriter.close();
        //endregion end--------------system.out----------------

        UseTimeTool.getInstance().start();
        //2,279,426 字节
        FileInputStream fis = new FileInputStream("D:\\CloudMusic\\进货订单20200312150918.zip");
        FileOutputStream fos = new FileOutputStream("D:\\CloudMusic\\copy\\copy-20200312150918.zip");

        System.out.println("len : " + fis.available());
        System.out.println("len : " + fis.getFD());


        //region begin io  read  byte by byte
        //        int len = -1, i = 0;

//        while ((len = fis.read()) != -1) {
//            //这里就不是长度的问题了,而是读取的字节"内容",读到一个写一个,相当慢.
//            System.out.println("len : " + len);
//            fos.write(len);
//            i++;
//            System.out.println("byte size " + i);
//        }
        //endregion


        /*--------------使用缓冲--------------*/
        //缓冲方法复制歌曲用了不到20"毫秒"
//        //创建一个长度为1024的字节数组,每次都读取5kb,目的是缓存,如果不用缓冲区,用fis.read(),就会效率低,一个一个读字节,缓冲区是一次读5000个
//        byte[] bytes = new byte[1024*8];
//        //每次都是从读取流中读取(5k)长度的数据,然后再写到文件去(5k的)数据,注意,每次读取read都会不同,是获取到下一个,直到后面最后一个.
//        while (fis.read(bytes)!=-1) {
//            //write是最追加到文件后面,所以直接每次添5K.
//            fos.write(bytes);
//        }

        /*--------------解释len--------------*/
        //告诉你为什么用len
        byte[] bytes = new byte[1024 * 5];
        int len = -1;
        //解释这个fis.read(bytes)的意思:从读取流"读取数组长度"的数据(打印len可知),并放入数组
        while ((len = fis.read(bytes, 0, 1024)) != -1) {
            //虽然数组长度的*5,但是这里我们设置了1024所以每次输出1024
            System.out.println("len : " + len);
            //因为每次得到的是新的数组,所以每次都是新数组的"0-len"
            //解决最后一次可能与实际大小不一样的问题
            fos.write(bytes, 0, len);
        }


        UseTimeTool.getInstance().stop();

    }


}

class UseTimeTool {
    private static UseTimeTool utt = new UseTimeTool();

    private UseTimeTool() {
    }

    public static UseTimeTool getInstance() {
        return utt;
    }

    private long start;

    public void start() {
        start = System.currentTimeMillis();
    }

    public void stop() {
        long end = System.currentTimeMillis();
        System.out.println("所用時間 : " + (end - start) + "毫秒");
    }
}

