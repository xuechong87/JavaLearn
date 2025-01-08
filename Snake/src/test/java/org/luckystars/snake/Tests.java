package org.luckystars.snake;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.junit.Test;

public class Tests {

    @Test
    public void test1(){
        BaseGroundImpl ground = new BaseGroundImpl(null);
        Vector2D food = new Vector2D(2,0);
        ground.setFood(food);
        Snake snake = new Snake(ground);
        snake.printSnake();
        snake.move();
        snake.printSnake();

        snake.move();
        snake.printSnake();
        ground.setFood( new Vector2D(3,0));;
        snake.move();
        snake.printSnake();

        snake.turnUp();
        snake.move();
        snake.printSnake();
        snake.move();
        snake.printSnake();
    }


}
