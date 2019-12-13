package com.mashibing.tank;

import java.awt.Color;
import java.awt.Graphics;

public class Tank {
    private int x, y;
    private Dir dir =  Dir.DOWN; //初始方向
    private static final int SPEED = 5; //坦克移速

    public static int WIDTH = ResourceMgr.tankD.getWidth();  //坦克长宽
    public static int HEIGHT = ResourceMgr.tankD.getHeight();

    private boolean moving = false; //坦克是否处于运动状态

    private boolean living = true;

    private TankFrame tf = null;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

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

    public Tank(int x, int y, Dir dir, TankFrame tf){
        super();
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }


    public void paint(Graphics g){
        if(!living){
            tf.tanks.remove(this);
            return;
        }
        switch (dir) {
            case LEFT:
                g.drawImage(ResourceMgr.tankL, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.tankR, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.tankU, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.tankD, x, y, null);
                break;
        }

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

    public void fire() {

        int bX = this.x + Tank.WIDTH/2 - Bullet.WIDTH/2; //计算子弹生成的位置
        int bY = this.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;//还是偏了一些，上下不明显，左右明显
        tf.bullets.add(new Bullet(bX, bY, this.dir, this.tf));
    }

    public void die() {
        this.living = false;
    }
}
