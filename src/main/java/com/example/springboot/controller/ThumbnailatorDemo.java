package com.example.springboot.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

import com.example.springboot.utils.FileUtils;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.apache.commons.lang3.ArrayUtils;

/**
 * Created by vin on 2018/8/22.
 */
public class ThumbnailatorDemo {

    /**
     * @param args
     * @throws IOException
     */
//    public static void main(String[] args) throws IOException {
//        ThumbnailatorDemo thumbnailatorTest = new ThumbnailatorDemo();
//        //thumbnailatorTest.test1();//指定大小进行缩放
//        thumbnailatorTest.test2();//按照比例进行缩放
////        thumbnailatorTest.test3();
////        thumbnailatorTest.test4();
////        thumbnailatorTest.test5();
////        thumbnailatorTest.test6();
////        thumbnailatorTest.test7();
////        thumbnailatorTest.test8();
////        thumbnailatorTest.test9();
//    }

    /**
     * 指定大小进行缩放
     *
     * @throws IOException
     */
    private void test1() throws IOException {
        /*
         * size(width,height) 若图片横比200小，高比300小，不变
         * 若图片横比200小，高比300大，高缩小到300，图片比例不变 若图片横比200大，高比300小，横缩小到200，图片比例不变
         * 若图片横比200大，高比300大，图片按比例缩小，横为200或高为300
         */
        Thumbnails.of("images/test.jpg").size(200, 300).toFile("C:/image_200x300.jpg");
        Thumbnails.of("images/test.jpg").size(2560, 2048).toFile("C:/image_2560x2048.jpg");
    }

    /*
     * 函数名：getFile
     * 作用：使用递归，输出指定文件夹内的所有文件
     * 参数：path：文件夹路径   deep：表示文件的层次深度，控制前置空格的个数
     * 前置空格缩进，显示文件层次结构
     */
    private static void getFile(String path, int deep) {
        // 获得指定文件对象
        File file = new File(path);
        // 获得该文件夹内的所有文件
        File[] array = file.listFiles();
        if (null == array) return;

        //追加到数组内
        filePaths = ArrayUtils.addAll(filePaths, array);
        for (int i = 0; i < array.length; i++) {
            if (array[i].isFile())//如果是文件
            {
//                for (int j = 0; j < deep; j++)//输出前置空格
//                    System.out.print(" ");
                // 只输出文件名字
                System.out.println(array[i].getName());
                // 输出当前文件的完整路径
                // System.out.println("#####" + array[i]);
                // 同样输出当前文件的完整路径   大家可以去掉注释 测试一下
                // System.out.println(array[i].getPath());
            } else if (array[i].isDirectory())//如果是文件夹
            {
//                for (int j = 0; j < deep; j++)//输出前置空格
//                    System.out.print(" ");

                System.out.println(array[i].getName());
                //System.out.println(array[i].getPath());
                //文件夹需要调用递归 ，深度+1
                getFile(array[i].getPath(), deep + 1);
            }
        }
    }

    static File[] filePaths = new File[0];

    /**
     * 按照比例进行缩放
     *
     * @throws IOException
     */
    private void test2() throws IOException {
        /**
         * scale(比例)
         */
        String sPath = "/Users/liutong/Desktop/ThumbnailatorDemo/";

        getFile(sPath, 10);

        //        imageFile.list();
//        List<String> files = FileUtils.findChildrenList(imageFile, true);


        for (int i = 0; i < filePaths.length; i++) {
            String filePath = filePaths[i].toString();
            if (filePath.endsWith(".jpg")) {
                Thumbnails.of(filePath).scale(0.30f).toFile(filePath);
            }
        }

        //Thumbnails.of("/Users/liutong/Desktop/ThumbnailatorDemo/12471247766293.jpg").scale(0.20f).toFile("/Users/liutong/Desktop/ThumbnailatorDemo/12471247766293.jpg");
//        Thumbnails.of("/Users/liutong/Desktop/ThumbnailatorDemo/12471247766293.jpg").scale(0.30f).toFile("/Users/liutong/Desktop/ThumbnailatorDemo/12471247766293_30%.jpg");
//        Thumbnails.of("/Users/liutong/Desktop/ThumbnailatorDemo/12471247766293.jpg").scale(0.35f).toFile("/Users/liutong/Desktop/ThumbnailatorDemo/12471247766293_35%.jpg");
//        Thumbnails.of("/Users/liutong/Desktop/ThumbnailatorDemo/12471247766293.jpg").scale(0.40f).toFile("/Users/liutong/Desktop/ThumbnailatorDemo/12471247766293_40%.jpg");
    }

    /**
     * 不按照比例，指定大小进行缩放
     *
     * @throws IOException
     */
    private void test3() throws IOException {
        /**
         * keepAspectRatio(false) 默认是按照比例缩放的
         */
        Thumbnails.of("images/test.jpg").size(120, 120).keepAspectRatio(false).toFile("C:/image_120x120.jpg");
    }

    /**
     * 旋转
     *
     * @throws IOException
     */
    private void test4() throws IOException {
        /**
         * rotate(角度),正数：顺时针 负数：逆时针
         */
        Thumbnails.of("images/test.jpg").size(1280, 1024).rotate(90).toFile("C:/image+90.jpg");
        Thumbnails.of("images/test.jpg").size(1280, 1024).rotate(-90).toFile("C:/iamge-90.jpg");
    }

    /**
     * 水印
     *
     * @throws IOException
     */
    private void test5() throws IOException {
        /**
         * watermark(位置，水印图，透明度)
         */
        Thumbnails.of("images/test.jpg").size(1280, 1024).watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File("images/watermark.png")), 0.5f)
                .outputQuality(0.8f).toFile("C:/image_watermark_bottom_right.jpg");
        Thumbnails.of("images/test.jpg").size(1280, 1024).watermark(Positions.CENTER, ImageIO.read(new File("images/watermark.png")), 0.5f)
                .outputQuality(0.8f).toFile("C:/image_watermark_center.jpg");
    }

    /**
     * 裁剪
     *
     * @throws IOException
     */
    private void test6() throws IOException {
        /**
         * 图片中心400*400的区域
         */
        Thumbnails.of("images/test.jpg").sourceRegion(Positions.CENTER, 400, 400).size(200, 200).keepAspectRatio(false)
                .toFile("C:/image_region_center.jpg");
        /**
         * 图片右下400*400的区域
         */
        Thumbnails.of("images/test.jpg").sourceRegion(Positions.BOTTOM_RIGHT, 400, 400).size(200, 200).keepAspectRatio(false)
                .toFile("C:/image_region_bootom_right.jpg");
        /**
         * 指定坐标
         */
        Thumbnails.of("images/test.jpg").sourceRegion(600, 500, 400, 400).size(200, 200).keepAspectRatio(false).toFile("C:/image_region_coord.jpg");
    }

    /**
     * 转化图像格式
     *
     * @throws IOException
     */
    private void test7() throws IOException {
        /**
         * outputFormat(图像格式)
         */
        Thumbnails.of("images/test.jpg").size(1280, 1024).outputFormat("png").toFile("C:/image_1280x1024.png");
        Thumbnails.of("images/test.jpg").size(1280, 1024).outputFormat("gif").toFile("C:/image_1280x1024.gif");
    }

    /**
     * 输出到OutputStream
     *
     * @throws IOException
     */
    private void test8() throws IOException {
        /**
         * toOutputStream(流对象)
         */
        OutputStream os = new FileOutputStream("C:/image_1280x1024_OutputStream.png");
        Thumbnails.of("images/test.jpg").size(1280, 1024).toOutputStream(os);
    }

    /**
     * 输出到BufferedImage
     *
     * @throws IOException
     */
    private void test9() throws IOException {
        /**
         * asBufferedImage() 返回BufferedImage
         */
        BufferedImage thumbnail = Thumbnails.of("images/test.jpg").size(1280, 1024).asBufferedImage();
        ImageIO.write(thumbnail, "jpg", new File("C:/image_1280x1024_BufferedImage.jpg"));
    }
}
