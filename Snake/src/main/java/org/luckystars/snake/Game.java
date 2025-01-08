package org.luckystars.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game extends JFrame {

    private final JPanel gamePanel;
    private  Timer timer;

    private volatile boolean isRunning = true;

    private BaseGround ground;
    private Snake snake;

    public Game() {
        setTitle("Snake");
        this.gamePanel = new  JPanel(){
            @Override
            public void paint(Graphics g) {
                g.setColor(Color.GREEN);
                super.paint(g);
                // 在这里绘制游戏元素
                ground.drawGround(g);
                snake.drawSnake(g);
            }
        };

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gamePanel.setBackground(Color.BLACK);

        this.ground = new BaseGroundImpl(gamePanel);
        this.snake = new Snake(ground);

        gamePanel.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                synchronized (gamePanel) {

                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        System.out.println("space");
                        if (timer.isRunning()) {
                            isRunning = false;
                            timer.stop();
                        } else {
                            isRunning = true;
                            timer.restart();
                        }
                    }
                    // 暂停时不处理按键
                    if (!isRunning) {
                        return;
                    }
                    // 处理按键事件
                    if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                        System.out.println("Left");
                        snake.turnLeft();
                    }
                    if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                        System.out.println("Right");
                        snake.turnRight();
                    }
                    if (e.getKeyCode() == KeyEvent.VK_UP) {
                        System.out.println("UP");
                        snake.turnUp();
                    }
                    if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                        System.out.println("Down");
                        snake.turnDown();
                    }

                }
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        });


        gamePanel.setFocusable(true); // 使JPanel获得焦点，才能接收键盘事件
        add(gamePanel);

        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                synchronized (gamePanel) {
                    // 在这里处理游戏逻辑
                    snake.move();

                    repaint();
                }

            }
        };

        timer = new Timer(600, listener);
        timer.start();
    }

    public static void main(String[] args) {
        Game snake = new Game();
        snake.setVisible(true);
    }


}
