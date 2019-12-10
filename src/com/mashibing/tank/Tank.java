package com.mashibing.tank;

import java.awt.Color;
import java.awt.Graphics;

public class Tank {
    private int x = 200, y = 200;
    private Dir dir =  Dir.DOWN; //初始方向
    private static final int SPEED = 5; //坦克移速
    private boolean moving = false; //坦克是否处于运动状态

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

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

        Color c = g.getColor();
        g.setColor(Color.YELLOW);
        g.fillRect(x,y,50,50);
        g.setColor(c);

        move();

    }
    public void move(){

        if(!moving) return;

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
