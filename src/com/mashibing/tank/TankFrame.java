package com.mashibing.tank;

import java.awt.Frame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Graphics;

public class TankFrame extends Frame {
    int x = 200, y = 200;
    Dir dir =  Dir.DOWN;
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
    public void paint(Graphics g){
        g.fillRect(x,y,50,50);
    }

    //内部类
    class MyKeyListener extends KeyAdapter{

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            //System.out.println("keyPressed");
            switch (key){
                case KeyEvent.VK_UP:
                    y -= 10;
                    break;
                case KeyEvent.VK_DOWN:
                    y += 10;
                    break;
                case KeyEvent.VK_LEFT:
                    x -= 10;
                    break;
                case KeyEvent.VK_RIGHT:
                    x += 10;
                    break;
            }
            repaint();  //不能调用paint，画笔不在你手里，在系统手里
        }

        @Override
        public void keyReleased(KeyEvent e) {
            //System.out.println("keyReleased");
        }
    }
}
