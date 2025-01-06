package com.luckystars.tests.tetris;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Random;
import static com.luckystars.tests.tetris.Constants.*;
import static com.luckystars.tests.tetris.Utils.deepCopy;

public class BlockImpl implements Block{

    protected int[][] shape;
    protected int x;
    protected int y;
    private JPanel gamePanel;
    protected BaseGround ground;

    private BlockImpl(){
        super();
        x=0;
        y=0;
    }

    @Override
    public void printShape(){
        Utils.printShape(this.shape);
    }

    @Override
    public void turn() {
        int[][] newShape = new int[4][4];
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                newShape[i][j] = shape[shape.length - 1 - j][i];
            }
        }
        shape = newShape;
    }

    @Override
    public void moveLeft() {
        this.x -= 1;
    }
    @Override
    public void moveRight() {
        this.x += 1;
    }
    @Override
    public void moveDown() {
        this.y += 1;
    }

    @Override
    public void drawBlock(Graphics g) {
        g.setColor(Color.GREEN);
        int[][] shape = this.shape;
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                if (shape[i][j] == 1) {
                    g.fillRect( (j +this.x)* PIC_UNIT , (i + this.y) * PIC_UNIT , PIC_UNIT, PIC_UNIT);
                }
            }
        }
    }

    @Override
    public void eraseBlock(Graphics g) {
        int[][] shape = this.shape;
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                if (shape[i][j] == 1) {
                    g.clearRect( (j +this.x)* PIC_UNIT ,  (i + this.y) * PIC_UNIT , PIC_UNIT, PIC_UNIT);
                }
            }
        }
    }

    //俄罗斯方块的基本图形
    public static int[][] getRandomShape(){
        Random r = new Random(System.nanoTime());
        int index = r.nextInt(TETRIS.length) ;
        int[][] shape = deepCopy(TETRIS[index]);
        System.arraycopy(TETRIS[index], 0, shape, 0, 4);
        return shape;
    }

    public static Block getRandomBlock(JPanel panel,BaseGround ground){
        BlockImpl b = new BlockImpl();
        b.shape = getRandomShape();
        b.gamePanel = panel;
        BlockProxy blockProxy = new BlockProxy(b);
        Block proxyBlock = (Block) Proxy.newProxyInstance(
                Block.class.getClassLoader(),
                new Class<?>[] { Block.class },
                blockProxy
        );
        b.gamePanel.repaint();
        b.ground = ground;
        b.printShape();
        return proxyBlock;
    }

    public static class BlockProxy implements InvocationHandler {

        private BlockImpl block;

        public BlockProxy(BlockImpl target) {
            this.block = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            //为方块的方法创建代理
            if ("turn".equals(method.getName())
                    ||"moveLeft".equals(method.getName())
                    ||"moveRight".equals(method.getName())
                    ||"moveDown".equals(method.getName())) {
                Object result = method.invoke(block, args);
                block.gamePanel.repaint();
                return result;
            } else {
                return method.invoke(block, args);
            }
        }
    }



    @Override
    public int[][] getShape() {
        return shape;
    }

    public void setShape(int[][] shape) {
        this.shape = shape;
    }

    @Override
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public JPanel getGamePanel() {
        return gamePanel;
    }

    public void setGamePanel(JPanel gamePanel) {
        this.gamePanel = gamePanel;
    }
}
