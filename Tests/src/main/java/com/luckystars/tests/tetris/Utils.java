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
}
