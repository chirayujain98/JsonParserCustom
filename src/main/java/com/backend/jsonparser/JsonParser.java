package com.backend.jsonparser;

import java.io.File;
import java.util.Scanner;
import java.util.Stack;

public class JsonParser {

    public void checkJsonElements() {
        String filePath = "/Users/chirayujain/Downloads/step1/valid.json";

        try(Scanner fileScanner = new Scanner(new File(filePath))){
            String line;
            Stack<Character> stack = new Stack<>();
            boolean atLeastOneElement = false;
            while(fileScanner.hasNextLine() && !(line = fileScanner.next()).isEmpty()) {

                for(char c :  line.toCharArray()){
                    if(c == '{'){
                        atLeastOneElement = true;
                        stack.push('{');
                    }
                    if(c == '}'){
                        stack.pop();
                    }
                }
            }
            if(stack.isEmpty() && atLeastOneElement){
                System.out.println("0");
            }else{
                System.out.println("1");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}


//                try(Scanner lineScanner = new Scanner(line)){
//                    String word = lineScanner.next();
//                    System.out.println(word);
//                }catch (Exception e){
//                    System.out.println(e.getMessage());
//                }
