package com.luckystars.tests;


import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.junit.Test;

import java.math.BigDecimal;
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
        walkByLevelTest(4);

        levelOrderTest(4);

    }
    public static void levelOrderTest(int deep){
        Tree root = new Tree();
        root.gen(deep);
        root.levelOrder();
        System.out.println(root.print());
    }

    public static void walkByLevelTest(int deep){
        Tree root = new Tree();
        root.gen(deep);
        root.walkByLevel();
        System.out.println(root.print());
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

    //层级遍历
    public void walkByLevel(){
        Queue<Tree> q = new LinkedList<>();
        q.add(this);
        int i = 1;
        Tree node = q.poll();
        while (node!=null){
            node.setValue(i);
            i++;
            if(node!=null&&node.leftChild!=null){
                q.add(node.leftChild);
                q.add(node.rightChild);
            }
            node = q.poll();
        }

    }



    public List<List<Tree>> levelOrder() {
        List<List<Tree>> result = new ArrayList<>();
        levelTraverse(this, 0, result);
        int i = 1;
        for(List<Tree> level : result){
            for(Tree node : level){
                node.setValue(i);
                i++;
            }
        }
        return result;
    }

    private void levelTraverse(Tree node, int level, List<List<Tree>> result) {
        if (node == null) {
            return;
        }
        // 如果当前层数已经超过结果列表的大小，说明是新的一层
        if (level >= result.size()) {
            result.add(new ArrayList<>());
        }
        result.get(level).add(node); // 将当前节点的值添加到对应层级的列表中
        levelTraverse(node.leftChild, level + 1, result); // 递归遍历左子树
        levelTraverse(node.rightChild, level + 1, result); // 递归遍历右子树
    }

    @Test
    public void inOrderWalkTests() {
        Tree root = new Tree();
        root.gen(5);
        root.inOrderWalk(1);
        System.out.println(root.print());
    }

    //中序遍历
    public Integer inOrderWalk(Integer start){

        if(this.leftChild!=null){
            start = this.leftChild.inOrderWalk(start);
            this.setValue( ++start);
            start = this.rightChild.inOrderWalk(++start);
        }else {
            this.setValue( start);
        }
        return start;
    }

    @Test
    public void preOrderWalkTests() {
        Tree root = new Tree();
        root.gen(5);
        root.preOrderWalk(1);
        System.out.println(root.print());
    }
    //先序遍历
    public Integer preOrderWalk(Integer start){
        this.setValue( start);
        if(this.leftChild!=null){
            start = this.leftChild.preOrderWalk(++start);
            start = this.rightChild.preOrderWalk(++start);
        }
        return start;
    }

    public String print(){
        StringBuilder sb = new StringBuilder();
        //进行层次遍历，并将结果存入levelList
        List<List<Tree>> levelList = new ArrayList<>();
        levelTraverse(this, 0, levelList);
        int i = 1;
        // 获取树的最大层级
        int maxLv = levelList.size();
        // 计算最大层级的数字长度，用于后续的对齐
        int maxNumLength = String.valueOf(1L<<(maxLv)).length();

        int levelNumberLength = String.valueOf(levelList.size()).length();
        for(List<Tree> level : levelList){

            String title  = "level["+StringUtils.leftPad(String.valueOf(i),levelNumberLength,"0")+"]:";

            StringBuilder nextLine = new StringBuilder();
            sb.append(title);
            nextLine.append(getSpace(title.length()));

            int leftNums = 1<<(maxLv-i);
            // 计算当前层节点间的空格数
            int padSpace =(1<<(maxLv-i))*(maxNumLength+2)-maxNumLength;

            for(Tree node : level){
                // 获取节点值的字符串，并进行对齐
                String val = padSpace(new BigDecimal(node.getValue()), maxNumLength);
                sb.append(val).append(getSpace(padSpace));
                // 如果不是最后一层，构建下一层的连接线
                if(i<maxLv){
                    nextLine.append(padSpace("|", maxNumLength));
                    // 计算下一层节点间的空格数
                    int nexLevelPadSpace  =(1<<(maxLv-i-1))*(maxNumLength+2)-maxNumLength;
                    nextLine.append(getDash(nexLevelPadSpace));
                    nextLine.append(StringUtils.leftPad("|", maxNumLength, '-'));
                    nextLine.append(getSpace(padSpace-nexLevelPadSpace-maxNumLength));
                }

            }
            sb.append("\r\n");
            sb.append(nextLine.toString());
            sb.append("\r\n");
            i++;
        }

        return sb.toString();

    }


    private String getSpace(int count){
        return new String(new char[count]).replace("\0", " ");
    }
    private String getDash(int count){
        if(count<=0){
            return "";
        }
        return new String(new char[count]).replace("\0", "-");
    }

    /**
     * 用0补全指定长度
     * @param num    需要补全的BigDecimal数值
     * @param length 补全后的总长度
     * @return 补全0后的字符串表示
     */
    public static String padSpace(Object num, int length) {
        String str = num.toString(); // 将BigDecimal转换为字符串
        int indexOfDot = str.indexOf(".");
        //获取小数位数
        int decimalLength = indexOfDot>=0? str.length() - indexOfDot : 0;
        //将整数位数补全
        return StringUtils.leftPad(str, length+decimalLength, ' ');
    }
    public static String padDash(Object num, int length) {
        String str = num.toString(); // 将BigDecimal转换为字符串
        int indexOfDot = str.indexOf(".");
        //获取小数位数
        int decimalLength = indexOfDot>=0? str.length() - indexOfDot : 0;
        //将整数位数补全
        return StringUtils.leftPad(str, length+decimalLength, '-');
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
