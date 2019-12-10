package com.mashibing.tank;

import java.awt.Frame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Graphics;

public class TankFrame extends Frame {
    Tank myTank = new Tank(200,200,Dir.DOWN);
    //构造方法
    public TankFrame(){
        // 下面不写this也可以
        this.setSize(800,600);
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

    @Override
    public void paint(Graphics g) {
        myTank.paint(g);
    }

    //内部类,键盘监听类
    class MyKeyListener extends KeyAdapter{

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
