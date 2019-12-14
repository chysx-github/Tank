package com.mashibing.tank;

import java.awt.Graphics;
import java.util.Random;

public class Tank {
    private int x, y;
    private Dir dir =  Dir.DOWN; //初始方向
    private static final int SPEED = 5; //坦克移速

    public static int WIDTH = ResourceMgr.goodTankD.getWidth();  //坦克长宽
    public static int HEIGHT = ResourceMgr.goodTankD.getHeight();

    private boolean moving = true; //地方坦克可以动了
    private boolean living = true;
    private Group group = Group.BAD;
    private TankFrame tf = null;

    private Random random = new Random();

    public Tank(int x, int y, Dir dir, Group group, TankFrame tf){
        super();
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tf = tf;
    } //构造方法

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public Group getGroup() {
        return group;
    }
    public void setGroup(Group group) {
        this.group = group;
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

    public void paint(Graphics g){
        if(!living){
            tf.tanks.remove(this);
            return;
        }
        switch (dir) {
            case LEFT:
                //g.drawImage(ResourceMgr.tankL, x, y, null);
                g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankL : ResourceMgr.badTankL, x, y, null);
                break;
            case RIGHT:
                g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankR : ResourceMgr.badTankR, x, y, null);
                break;
            case UP:
                g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankU : ResourceMgr.badTankU, x, y, null);
                break;
            case DOWN:
                g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankD : ResourceMgr.badTankD, x, y, null);
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
        if(this.group == Group.BAD && random.nextInt(100) > 95) this.fire();

        if(this.group == Group.BAD && random.nextInt(100) > 95) randomDir();

        boundsCheck();
    }

    private void boundsCheck() {
        if(this.x < 2) x = 2;
        if (this.y < 28) y = 28;
        if (this.x > TankFrame.GAME_WIDTH- Tank.WIDTH -2) x = TankFrame.GAME_WIDTH - Tank.WIDTH -2;
        if (this.y > TankFrame.GAME_HEIGHT - Tank.HEIGHT -2 ) y = TankFrame.GAME_HEIGHT -Tank.HEIGHT -2;
    }

    private void randomDir() {
        this.dir = Dir.values()[random.nextInt(4)];
    }

    public void fire() {
    //主坦克为什么在移动时会随机开火？
        int bX = this.x + Tank.WIDTH/2 - Bullet.WIDTH/2; //计算子弹生成的位置
        int bY = this.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;//还是偏了一些，上下不明显，左右明显
        tf.bullets.add(new Bullet(bX, bY, this.dir, this.group, this.tf));

        if(this.group == Group.GOOD) new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
    }

    public void die() {
        this.living = false;
    }
}
