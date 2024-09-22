package com.etjava.util;

import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * 图片相关工具类
 */
public class ImageUtil {

    /**
     * 指定的图片上面添加文字
     * @param filePath 模板图片路径 具体到某个图片
     * @param word 需要添加的文字
     * @param targetPath 生成的新图片目标位置
     * @return 返回图片名称
     */
    public static String addFont(String filePath,String word,String targetPath){
        try{
            File file = new File(filePath);
            if(!file.exists()){
                return "";
            }
            InputStream is = new FileInputStream(filePath);

            //BufferedImage bi = ImageIO.read(is);
            //通过JPEG图象流创建JPEG数据流解码器
            //JPEGImageDecoder jpegDecoder = JPEGCodec.createJPEGDecoder(is);
            //解码当前JPEG数据流，返回BufferedImage对象
            //BufferedImage buffImg = jpegDecoder.decodeAsBufferedImage();
            BufferedImage buffImg = ImageIO.read(is);
            //得到画笔对象
            Graphics g = buffImg.getGraphics();
            //设置文字颜色。
            g.setColor(Color.WHITE);
            //最后一个参数用来设置字体的大小
            int len = word.length();
            Font f = null;
            if(len<=12){
                f = new Font("微软雅黑",Font.BOLD,50);
                g.setFont(f);
                //60,160 表示这段文字在图片上的位置(x,y) .第一个是你设置的内容。
                g.drawString(word,45,160);
            }else{
                f = new Font("微软雅黑",Font.BOLD,20);
                g.setFont(f);
                //60,160 表示这段文字在图片上的位置(x,y) .第一个是你设置的内容。
                g.drawString(word,60,160);
            }


            g.dispose();
            OutputStream os;
            String imageName = System.currentTimeMillis() + ".jpg";
            targetPath = targetPath + imageName;
            File file1 = new File(targetPath);
            if(!file1.exists()){
                file1.createNewFile();
            }
            os = new FileOutputStream(targetPath);
            ImageIO.write(buffImg, "jpg", os);
            //en.encode(buffImg);
            is.close();
            os.close();

            return imageName;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String s = addFont("D:\\classroomImages\\coursemodel.jpg", "SpringSecurity+Vue3通用权限系统视频教程", "D:/");
        System.out.println(s);
    }
}
