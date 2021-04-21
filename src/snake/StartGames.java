package snake;

import javax.swing.*;

public class StartGames {
    public static void main(String[] args) {
        //1 绘制一个静态窗口  JFrame
        JFrame frame = new JFrame("贪吃蛇小程序");
        //设置界面的大小
        frame.setBounds(10, 10,900,720);//设置界面大小
        frame.setResizable(false);//窗口大小不可改变
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置关闭事件，游戏可以关闭了

        //2 面板 Jpanel   可以加入到 JFrame窗口中

        frame.add(new GamePanel());

        frame.setVisible(true);//让窗口能够展示出来



    }
}
