package com.mashibing.tank;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Graphics;

public class TankFrame extends Frame {
    Tank myTank = new Tank(200,200,Dir.DOWN);
    Bullet b = new Bullet(300,300,Dir.DOWN);

    static final int GAME_WIDTH = 800, GAME_HEIGHT = 600;
    //构造方法
    public TankFrame(){
        // 下面不写this也可以
        this.setSize(GAME_WIDTH,GAME_HEIGHT);
        this.setResizable(false);//不可改变窗口大小
        this.setTitle("tank war");
        this.setVisible(true);

        this.addWindowListener(new WindowAdapter(){ //使用监听器、匿名内部类，点×可以关闭了

            @Override
            public void windowClosing(WindowEvent e){ //点×时发生windowClosing事件
                System.exit(0);
            }
        });

        this.addKeyListener(new MyKeyListener()); //键盘监听器+匿名内部类

    }

    //双缓冲解决闪烁
    Image offScreenImage = null;
    @Override
    public void update(Graphics g) {
        if(offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    @Override
    public void paint(Graphics g) {

        myTank.paint(g);
        b.paint(g);
    }

    //内部类,键盘监听类
    class MyKeyListener extends KeyAdapter{
        //记录方向键的状态
        boolean bU = false;
        boolean bD = false;
        boolean bL = false;
        boolean bR = false;

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key){
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
            }

            setMainTankDir();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key){
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
            }
            setMainTankDir();
        }

        public void setMainTankDir(){

            if(!bU && !bD && !bL && !bR) myTank.setMoving(false);
            else{
                myTank.setMoving(true);
                if(bU) myTank.setDir(Dir.UP);
                if(bD) myTank.setDir(Dir.DOWN);
                if(bL) myTank.setDir(Dir.LEFT);
                if(bR) myTank.setDir(Dir.RIGHT);
            }

        }
    }
}
