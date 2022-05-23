package com.txg.www.until;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

@WebServlet("/checkCode")
public class CheckCode extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //接收客户端请求，生成一个验证码图片，响应给客户端
        // 使用Java中提供的 awt 工具包动态生成一个验证码图片
        //1.创建一张图片
        //验证码宽度
        int width = 300;
        //验证码高度
        int height = 90;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        //2.绘制图片
        Graphics2D pen = image.createGraphics();
        //a.绘制背景
        pen.setColor(getRandomColor());
        //fillRect: 绘制实心矩形
        pen.fillRect(0, 0, width, height);
        //b.绘制验证码字符串
        //验证码图片上的字符的个数
        int letterNum = 4;
        // 验证码图片上两个字母之间的空隙
        int space = 20;
        //计算每个字母占据的宽度
        int letterWidth = (width - (letterNum + 1) * space) / letterNum;

        //for循环每循环一次，绘制一个字母  （小写字母的ascii码  97-122）
        Random random = new Random();
        String checkCode = "";
        for (int i = 0; i < letterNum; i++) {
            //随机生成一个小写字母：
            //97-122
            int ascii = random.nextInt(26) + 97;
            byte[] bs = {(byte) ascii};
            String letter = new String(bs);
            checkCode += letter;
            //drawString: 绘制字母
            pen.setColor(getRandomColor());
            pen.setFont(new Font("Gulim", Font.BOLD, 70));
            pen.drawString(letter, space + (letterWidth + space) * i, height - space);
        }
        HttpSession session = req.getSession();
        session.setAttribute("checkCode", checkCode);
        //图片绘制完成之后，将图片通过 response的输出流响应到客户端
        ImageIO.write(image, "png", resp.getOutputStream());
    }

    /**
     * 产生一种随机颜色
     */
    private Color getRandomColor() {
        Random random = new Random();
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        Color color = new Color(r, g, b);
        return color;
    }
}
