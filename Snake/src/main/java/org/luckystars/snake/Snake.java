package org.luckystars.snake;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import java.awt.*;
import java.util.LinkedList;

public class Snake {

    private Vector2D head;

    private LinkedList<Vector2D> body;

    private Vector2D direction;

    private BaseGround ground;

    private volatile boolean isDead = false;

    public Snake(BaseGround ground) {
        this.direction = new Vector2D(1, 0);
        head = new Vector2D(0, 0);
        body = new LinkedList<>();
        body.addFirst(head);
        this.ground = ground;
    }

    public synchronized void eat(Vector2D food) {
        body.addFirst(food);
        ground.createNewFood();
        this.head = food;

        move();
    }

    public synchronized void move() {
        Vector2D newHead = head.add(direction);
        checkDead(newHead);
        if (isDead) {
            return;
        }
        if (ground.getFood().equals(newHead)) {
            eat(ground.getFood());
        } else {
            body.addFirst(newHead);
            body.removeLast();
            this.head = newHead;
        }

    }

    private void checkDead(Vector2D newHead)  {
        if (newHead.getX() < 0
                || newHead.getX() >= Constants.WIDTH
                || newHead.getY() < 0
                || newHead.getY() >= Constants.HEIGHT) {
            isDead = true;
        }
        for (Vector2D v : body) {
            if (v.equals(newHead)) {
                isDead = true;
            }
        }
    }


    public synchronized void turnLeft() {
        Vector2D newDirect = new Vector2D(-1, 0);
        if (!checkDirect(newDirect)) {
            return;
        }
        this.direction = newDirect;
    }

    public synchronized void turnRight() {
        Vector2D newDirect = new Vector2D(1, 0);
        if (!checkDirect(newDirect)) {
            return;
        }
        this.direction = newDirect;
    }

    public synchronized void turnUp() {
        Vector2D newDirect = new Vector2D(0, 1);
        if (!checkDirect(newDirect)) {
            return;
        }
        this.direction = newDirect;
    }

    public synchronized void turnDown() {
        Vector2D newDirect = new Vector2D(0, -1);
        if (!checkDirect(newDirect)) {
            return;
        }
        this.direction = newDirect;
    }

    private boolean checkDirect(Vector2D newDirect) {
        if (newDirect.equals(direction)) {
            return false;
        }
        if (newDirect.add(direction).equals(Vector2D.ZERO)) {
            return false;
        }
        return true;
    }

    public void drawSnake(Graphics g) {
        g.setColor(Color.GREEN);
        for (Vector2D v : body) {
            g.fillRect((int) v.getX(), (int) v.getY(), Constants.PIC_UNIT, Constants.PIC_UNIT);
        }
    }

    public void printSnake() {
        System.out.println("=============== printSnake ===============");
        int[][] groundData = Utils.deepCopy(this.ground.getGround());
        for (Vector2D v : body) {
            groundData[(int) v.getY()][(int) v.getX()] = 1;
        }
        Vector2D curFood = this.ground.getFood();
        System.out.println("food location ：" + curFood);
        System.out.println("is dead ：" + isDead);
        groundData[(int) curFood.getY()][(int) curFood.getX()] = 1;
        Utils.printShape(groundData);
        System.out.println("=============== printSnake END ===============");
    }

    public boolean isDead() {
        return isDead;
    }

}

