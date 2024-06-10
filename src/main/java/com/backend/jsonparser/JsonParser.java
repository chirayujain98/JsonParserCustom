package com.backend.jsonparser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 */
@Component
public class JsonParser {


    private JsonLineValidity lineValidity;

    @Autowired
    public JsonParser(JsonLineValidity lineValidity){
        this.lineValidity  = lineValidity;
    }

    /**
     *
     */
    public void checkJsonElements() {
        String filePath = "/Users/chirayujain/Downloads/step3/valid.json";

        try(Scanner fileScanner = new Scanner(new File(filePath))){
            StringBuilder line = new StringBuilder();
            while(checkNextLine(fileScanner)) {
                // The idea is to get all the data of the file in one line
                line.append(removeWhiteSpace(fileScanner.nextLine()));
            }
            System.out.println(line);
            try {
                System.out.println(lineValidity.isValidLine(line.toString()));
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private boolean checkNextLine(Scanner fileScanner) {
        return fileScanner.hasNextLine();
    }

    public String removeWhiteSpace(String line) {
        return line.replace(" ","");
    }

    /**
     *
     * @param line Input line of json
     * @return true or false
     */

    public boolean checkParenthesis(String line){
        Stack<Character> stack = new Stack<>();
        boolean atLeastOneElement = false;
        for(char c :  line.toCharArray()){
            if(c == '{'){
                atLeastOneElement = true;
                stack.push('{');
            }
            if(c == '}'){
                stack.pop();
            }
        }
        if(stack.isEmpty() && atLeastOneElement){
            return true;
        }else{
            return false;
        }
    }
}
