package com.mashibing.tank;

import java.awt.Graphics;

public class Tank {
    private int x = 200, y = 200;
    private Dir dir =  Dir.DOWN; //初始方向
    private static final int SPEED = 10; //坦克移速

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public Tank(int x, int y, Dir dir){
        super();
        this.x = x;
        this.y = y;
        this.dir = dir;
    }


    public void paint(Graphics g){
        //根据方向画图

        g.fillRect(x,y,50,50);

        switch (dir){
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            case LEFT:
                x -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
        }
    }
}
