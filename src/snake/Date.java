package snake;

import javax.swing.*;
import java.net.URL;

//存放外部数据
public class Date {

    //头部的图片  url
    public static URL headerURL = Date.class.getResource("/statics/header.png");//定位到资源
    public static ImageIcon header = new ImageIcon(headerURL);
    //小蛇头部：
    public static URL upURL = Date.class.getResource("/statics/up.png");
    public static URL downURL = Date.class.getResource("/statics/down.png");
    public static URL leftURL = Date.class.getResource("/statics/left.png");
    public static URL rightURL = Date.class.getResource("/statics/right.png");
    public static ImageIcon up = new ImageIcon(upURL);
    public static ImageIcon down = new ImageIcon(downURL);
    public static ImageIcon left = new ImageIcon(leftURL);
    public static ImageIcon right = new ImageIcon(rightURL);
    //小蛇身体：
    public static URL bodyURL = Date.class.getResource("/statics/body.png");
    public static ImageIcon body = new ImageIcon(bodyURL);
    //食物：
    public static URL foodURL = Date.class.getResource("/statics/food.png");
    public static ImageIcon food = new ImageIcon(foodURL);

}
