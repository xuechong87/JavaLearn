package com.luckystars.tests;

import com.sun.javafx.sg.prism.NGExternalNode;

public class Linklist <T>{

    private T value;
    private Linklist<T> parent;
    private Linklist<T> leftChild;
    private Linklist<T> rightChild;


    public static void main(String[] args) {



        Linklist<Integer> root = new Linklist();
        root.gen(5);
        root.setVal(1);
        int deep = 1;
    }


    public void  gen (int deep){


        if(deep>1){
            this.leftChild = new Linklist<>();
            this.leftChild.parent = this;
            this.leftChild.gen(deep -1);
            this.rightChild = new Linklist<>();
            this.rightChild.parent = this;
            this.rightChild.gen(deep -1);
        }

    }

    public Integer setVal(Integer start){
        this.setValue((T) start);
        if(this.leftChild!=null){
            this.leftChild.setValue((T) (++start));
            this.rightChild.setValue((T) (++start));



            start = this.leftChild.setVal(start);
            start = this.rightChild.setVal(start);
        }
        start ++;
        return start;
    }




    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Linklist<T> getParent() {
        return parent;
    }

    public void setParent(Linklist<T> parent) {
        this.parent = parent;
    }

    public Linklist<T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Linklist<T> leftChild) {
        this.leftChild = leftChild;
    }

    public Linklist<T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(Linklist<T> rightChild) {
        this.rightChild = rightChild;
    }
}
