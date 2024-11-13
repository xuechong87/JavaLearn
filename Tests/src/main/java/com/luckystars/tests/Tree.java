package com.luckystars.tests;


import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Tree {

    private Integer value;
    private Tree parent;
    private Tree leftChild;
    private Tree rightChild;
    private Integer deep;


    public static void main(String[] args) {
//        walkByLevelTest();

        levelOrderTest();

    }

    public static void walkByLevelTest(){
        Tree root = new Tree();
        root.gen(4);
        root.walkByLevel();
        System.out.println(root);
    }

    //层级遍历
    public void walkByLevel(){
        Queue<Tree> q = new LinkedList<>();
        q.add(this);

        int i = 1;
        Tree node = q.poll();
        while (node!=null){
            node.setVal(i);
            i++;
            if(node!=null&&node.leftChild!=null){
                q.add(node.leftChild);
                q.add(node.rightChild);
            }
            node = q.poll();
        }

    }

    public static void levelOrderTest(){
        Tree root = new Tree();
        root.gen(4);
        root.levelOrder();
        System.out.println(root);
    }

    public List<List<Tree>> levelOrder() {
        List<List<Tree>> result = new ArrayList<>();
        traverse(this, 0, result);
        int i = 1;
        for(List<Tree> level : result){
            for(Tree node : level){
                node.setVal(i);
                i++;
            }
        }
        return result;
    }

    private void traverse(Tree node, int level, List<List<Tree>> result) {
        if (node == null) {
            return;
        }
        // 如果当前层数已经超过结果列表的大小，说明是新的一层
        if (level >= result.size()) {
            result.add(new ArrayList<>());
        }
        result.get(level).add(node); // 将当前节点的值添加到对应层级的列表中
        traverse(node.leftChild, level + 1, result); // 递归遍历左子树
        traverse(node.rightChild, level + 1, result); // 递归遍历右子树
    }



    //中序
    public Integer walk(Integer start){

        if(this.leftChild!=null){
            start = this.leftChild.setVal(start);
            this.setValue( ++start);
            start = this.rightChild.setVal(++start);
        }else {
            this.setValue( start);
        }
        return start;
    }

    //生成全二叉树
    public void  gen (int deep){
        this.deep = deep;
        if(deep>1){
            this.leftChild = new Tree();
            this.leftChild.parent = this;
            this.leftChild.gen(deep -1);
            this.rightChild = new Tree();
            this.rightChild.parent = this;
            this.rightChild.gen(deep -1);
        }

    }
    //先序
    public Integer setVal(Integer start){
        this.setValue( start);
        if(this.leftChild!=null){
            start = this.leftChild.setVal(++start);
            start = this.rightChild.setVal(++start);
        }
        return start;
    }




    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Tree getParent() {
        return parent;
    }

    public void setParent(Tree parent) {
        this.parent = parent;
    }

    public Tree getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Tree leftChild) {
        this.leftChild = leftChild;
    }

    public Tree getRightChild() {
        return rightChild;
    }

    public void setRightChild(Tree rightChild) {
        this.rightChild = rightChild;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE, false);
    }
}
