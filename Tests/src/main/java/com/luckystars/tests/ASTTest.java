package com.luckystars.tests;

import org.junit.Test;

import java.util.*;

public class ASTTest {

    Map<Character,Character> keys = new HashMap<Character, Character>(3){{
        put('{','}');
        put('(',')');
        put('[',']');
        put('<','>');
    }};

    public boolean valid(String input){
        boolean result = false;
        if (input==null||input.length()<=0||input.length()%2!=0){
            return false;
        }
        Set<Character> starts = keys.keySet();

        Stack<Character> endsStack = new Stack<>();
        for (Character c :input.toCharArray()){
            if(starts.contains(c)){
                endsStack.push(keys.get(c));
            }else{
                if(endsStack.empty()){
                    result= false;
                    break;
                }
                if(endsStack.pop()!=c){
                    result= false;
                    break;
                }
                result= true;
            }
        }
        return result;
    }

    public boolean valid2(String input){
        if (input==null||input.length()<=0){
            return false;
        }
        Set<Character> starts = keys.keySet();
        Set<Character> ends = new HashSet<>(keys.values());
        Stack<Character> endsStack = new Stack<>();
        for (Character c :input.toCharArray()){

            if(starts.contains(c)){
                endsStack.push(keys.get(c));

            }else if(ends.contains(c)){
                if(endsStack.empty()){
                    return false;
                }
                if(endsStack.pop()!=c){
                    return false;
                }
            }
            //System.out.println(endsStack);
        }
        return endsStack.empty();
    }

    @Test
    public void test1(){
        String inputStr = "{)";
        System.out.println("Test1:" + valid(inputStr));

        String inputStr2 = "[{[]}()]";
        System.out.println("Test2:" + valid(inputStr2));

        String inputStr3 = "{}[]{}()({[])}";
        System.out.println("Test3:" + valid(inputStr3));

        String inputStr4 = "Stack<Character> endsStack = new Stack<Character>();\n" +
                "        for (Character c :input.toCharArray()){\n" +
                "            if(starts.contains(c)){\n" +
                "                endsStack.push(keys.get(c));\n" +
                "            }else{\n" +
                "                if(endsStack.empty()){\n" +
                "                    result= false;\n" +
                "                    break;\n" +
                "                }\n" +
                "                if(endsStack.pop()!=c){\n" +
                "                    result= false;\n" +
                "                    break;\n" +
                "                }\n" +
                "                result= true;\n" +
                "            }\n" +
                "        }";

        System.out.println("Test4:" + valid2(inputStr4));
    }





}
