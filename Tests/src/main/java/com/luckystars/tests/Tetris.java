package com.luckystars.tests;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Tetris  extends JFrame {

        private JPanel gamePanel;

        public Tetris() {
            setTitle("Tetris");
            setSize(300, 600);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            gamePanel = new JPanel() {
                @Override
                public void paint(Graphics g) {
                    super.paint(g);
                    // 在这里绘制游戏元素
                    g.setColor(Color.RED);
                    g.fillRect(50, 50, 20, 20); // 绘制一个简单的方块
                }
            };

            gamePanel.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {}

                @Override
                public void keyPressed(KeyEvent e) {
                    // 处理按键事件
                    if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                        System.out.println("Left");
                    }
                }

                @Override
                public void keyReleased(KeyEvent e) {}
            });
            gamePanel.setFocusable(true); // 使JPanel获得焦点，才能接收键盘事件
            add(gamePanel);

            setVisible(true);
        }



        public static void main(String[] args) {
            new Tetris();
        }
}
