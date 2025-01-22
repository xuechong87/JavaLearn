package com.luckystars.tests;

import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.junit.Test;

public class MatrixTests {

    @Test
    public void test1 (){
        RealMatrix matrix = MatrixUtils.createRealMatrix(new double[][]{
                {1,2,3},
                {3,4,5}});
        System.out.println(matrix);
        RealMatrix matrix2 = MatrixUtils.createRealMatrix(new double[][]{
                {1,1,1},
                {2,2,2}});
        RealMatrix matrix3 = matrix.add(matrix2);
        System.out.println(matrix3);

        RealMatrix matrix4 = MatrixUtils.createRealMatrix(new double[][]{
                {2},{2},{2}});
        System.out.println(matrix3.multiply(matrix4));

        System.out.println(matrix3.scalarMultiply(3));
        RealMatrix matrix5 = MatrixUtils.createRealMatrix(new double[][]{
                {3,0,0},
                {0,3,0},
                {0,0,3}});
        System.out.println(matrix3.multiply(matrix5));

    }
}
