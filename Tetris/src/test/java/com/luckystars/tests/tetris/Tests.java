package com.luckystars.tests.tetris;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

import static com.luckystars.tests.tetris.Constants.*;

public class Tests {

    @Test
    public void removeLines() throws Throwable {
        BaseGroundImpl ground = new BaseGroundImpl(null);
        fillGround(ground);

        Random r = new Random(System.nanoTime());
        int index = r.nextInt(HEIGHT);
        Thread.sleep(100);
        int index2 = r.nextInt(HEIGHT);
        for (int i = 0; i < HEIGHT; i++) {
            if (i == index||i == index2)  {
                Arrays.fill(ground.getGround()[i], 1);
            }
        }
        ground.printGround();
        System.out.println(ground.getFullLines());
        ground.removeFullLines();
        System.out.println(ground.getFullLines());
        ground.printGround();
    }


    private static void fillGround(BaseGround ground){
        int[][] groundData = ground.getGround();
        for (int i = 0; i < HEIGHT; i++) {
//            int x = (WIDTH-i%(WIDTH)-1);
            int x = i%(2*WIDTH);
            if(x>=WIDTH){
                x =  2*WIDTH -x -1;
            }
//            int x = i<WIDTH?i:(WIDTH-i%(WIDTH-1) -1);
//            int x = i < WIDTH ? i : WIDTH - 1 - (i - WIDTH );
//            System.out.println(x);
            groundData[i][x] = 1;
        }
    }
}
