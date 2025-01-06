package com.luckystars.tests.tetris;

public class Utils {
    // 深拷贝多维数组的方法
    public static int[][] deepCopy(int[][] original) {
        if (original == null) {
            return null;
        }

        int[][] copy = new int[original.length][];
        for (int i = 0; i < original.length; i++) {
            copy[i] = new int[original[i].length];
            System.arraycopy(original[i], 0, copy[i], 0, original[i].length);
        }
        return copy;
    }

    public static void printShape(int[][] shape){
        System.out.println("print Shape start");
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                if(shape[i][j]==0){
                    System.out.print("□ ");
                }else{
                    System.out.print("■ ");
                }
//                System.out.print(shape[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("print Shape end");
    }
}
