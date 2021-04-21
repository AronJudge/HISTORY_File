package snake;

import sun.security.mscapi.CPublicKey;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Time;
import java.util.Random;

public class GamePanel extends JPanel implements KeyListener, ActionListener {

    //画一条静态的小蛇
    //蛇的长度
    int lenth;
    //积分
    int score;
    //蛇的身体坐标数组
    int[] snakex = new int[850];
    int[] snakey = new int[600];
    //头的方向
    String fx = "R";  //上 ： U 下 ：D 左 ：L 右 ：R

    //游戏是否开始
    boolean isStart = false;

    //定时器 ： 帧
    Timer timer = new Timer(100,this);

    //定义一个食物
    int foodx;
    int foody;
    Random random = new Random();

    //死亡判断
    boolean isFail = false;

    //构造器 调用初始化 方法  new 的时候自动初始化
    public GamePanel(){
        init();
        //获取键盘的监听事件
        this.setFocusable(true);
        //添加一个监听
        this.addKeyListener(this);
        timer.start();//让时间动起来
    }

    //蛇的初始化
    public void init(){
        lenth = 3;
        //头部坐标
        snakex[0] = 100;
        snakey[0] = 100;
        //第一个身体坐标
        snakex[1] = 75;
        snakey[1] = 100;
        //第二个身体坐标
        snakex[2] = 50;
        snakey[2] = 100;

        //食物初始化
        foodx = 25 + 25 * random.nextInt(34);
        foody = 25 + 25 * random.nextInt(24);
    }

    //画面板 ： 画界面 画蛇
    //Graphics : 画笔  通过 G 绘画上去
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);//清屏
        this.setBackground(Color.WHITE);//设置背景颜色

        //绘制头部广告栏
        Date.header.paintIcon(this,g,25,11);

        //绘制游戏区域
        g.fillRect(25,70,850,600);

        //画一头静态的小蛇
        if(fx.equals("R")){
            Date.right.paintIcon(this,g,snakex[0],snakey[0]);
        }else if (fx.equals("L")){
            Date.left.paintIcon(this,g,snakex[0],snakey[0]);
        }else if (fx.equals("U")){
            Date.up.paintIcon(this,g,snakex[0],snakey[0]);
        }else if (fx.equals("D")){
            Date.down.paintIcon(this,g,snakex[0],snakey[0]);
        }

        //画积分
        g.setColor(Color.white);
        g.setFont(new Font("微软雅黑",Font.BOLD,18));
        g.drawString("长度" +lenth,750,35);
        g.drawString("分数" +score,750,50);

        //画食物
        Date.food.paintIcon(this,g,foodx,foody);

        //蛇的身体长度 通过 lenth 控制
        for (int i = 1; i < lenth; i++) {
            Date.body.paintIcon(this,g,snakex[i],snakey[i]);
        }

        //游戏提示：是否开始
        if(isStart == false){
            //画一个文字：String
            //设置画笔的颜色 字体
            g.setColor(Color.white);
            g.setFont(new Font("微软雅黑",Font.BOLD,40));
            g.drawString("按下空格开始游戏",300,300);
        }

        //失败提醒
        if(isFail == true){
            g.setColor(Color.red);
            g.setFont(new Font("微软雅黑",Font.BOLD,40));
            g.drawString("游戏失败,按下空格重新开始",200,300);
        }

    }

    //接收键盘的输入：监听

    //键盘按下，弹起
    @Override
    public void keyTyped(KeyEvent e) {
    }

    //定时器：监听时间流动
    //执行定时操作
    @Override
    public void actionPerformed(ActionEvent e) {
        //如果游戏处于开始状态，并且游戏没有结束，
        if(isStart && isFail==false){
            //右移
            //身体右移 除了脑袋 身体向前移动
            for(int i = lenth-1; i > 0; i--){
                snakex[i] = snakex[i-1];
                snakey[i] = snakey[i-1];
            }
            //通过控制方向，让头部移动
            if(fx.equals("R")){
                snakex[0] = snakex[0] + 25; //头部右移
                //边界判断
                if(snakex[0]>850){ snakex[0] = 25; }
            }else if(fx.equals("L")){
                snakex[0] = snakex[0] - 25; //头部右移
                //边界判断
                if(snakex[0]<25){ snakex[0] = 850; }
            }else if(fx.equals("U")){
                snakey[0] = snakey[0] - 25; //头部右移
                //边界判断
                if(snakey[0]<75){ snakey[0] = 650; }
            }else if(fx.equals("D")){
                snakey[0] = snakey[0] + 25; //头部右移
                //边界判断
                if(snakey[0]>650){ snakey[0] = 75 ; }
            }

            //吃食物 如果 食物和 蛇头坐标重合
            if(snakex[0] == foodx && snakey[0] == foody){
                //长度加1
                lenth++;
                //积分加10
                score += 10;
                //重新生产食物
                foodx = 25 + 25 * random.nextInt(34);
                foody = 25 + 25 * random.nextInt(24);
            }

            //结束判断： 遍历身体 头和身体重合
            for (int i = 1; i < lenth; i++) {
                if(snakex[0] == snakex[i] && snakey[0] == snakey[i]){
                    isFail = true;
                }
            }

            //刷新界面
            repaint();
        }
        timer.start();//让时间动起来

    }


    //键盘按下 未释放
    @Override
    public void keyPressed(KeyEvent e) {
        //接收键盘的输入， 获取按下的是那个按键
        int keyCode = e.getKeyCode();
        //如果按下 VK_SPACE 游戏 开始 或 结束
        if(keyCode == KeyEvent.VK_SPACE){
            if(isFail) {
                //失败 游戏重新开始
                isFail = false;
                //重新初始化游戏
                init();
            }else {
                isStart = !isStart;
            }
            //刷新界面
            repaint();
        }

        //键盘控制走向
        if (keyCode==KeyEvent.VK_LEFT){
            fx = "L";
        }else if (keyCode==KeyEvent.VK_RIGHT){
            fx = "R";
        }else if (keyCode==KeyEvent.VK_UP){
            fx = "U";
        }else if (keyCode==KeyEvent.VK_DOWN){
            fx = "D";
        }
    }
    //释放
    @Override
    public void keyReleased(KeyEvent e) {}

}
