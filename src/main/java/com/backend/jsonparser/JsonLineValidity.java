package com.backend.jsonparser;

import com.backend.jsonparser.Constants.State;
import org.springframework.stereotype.Component;

@Component
public class JsonLineValidity {

    /**
     * Logic to check the line
     * @param line Input line for the json
     * @return return true of false if line is valid in json
     */
    public boolean isValidLine(String line) {
        State currentState = State.OPEN;
        boolean stop = false;
        int index = 0;
        while(index < line.length()) {
            char currChar = line.charAt(index);
            switch (currentState) {
                case OPEN:
                    if(currChar != '{') {
                        return false;
                        // WRONG RESULT
                    }
                    currentState = State.KEY;
                    break;
                case KEY:
                    if(currChar != '"') {
                        return false;
                        // WRONG RESULT
                    }

                    index++;
                    index = extractString(index, line);
                    currentState = State.VALUE;
                    index++;
                    char nextChar = line.charAt(index);
                    if(nextChar != ':') {

                        return false;
                        // WRONG RESULT
                    }
                    break;
                case VALUE:
                    switch (currChar) {
                        // We have 3 cases in here if '{' this means new object of for value
                        // '"' this means the value associated with key
                        // otherwise this is wrong result.
                        case '"':
                            index++;
                            index = extractString(index, line);
                            break;
                        case '{':
                            isValidLine(line);
                            break;
                        default:
                            throw new RuntimeException();
                    }

                    index++;
                    char nextValue  = line.charAt(index);
                    switch(nextValue) {
                        case ',':
                            // Multiple key value pairs in this { }
                            currentState = State.KEY;
                            break;
                        case '}':
                            currentState = State.CLOSE;
                            break;
                        default:
                            return false;
                    }
                    break;

                case CLOSE:
                   stop = true;
                   break;

            }
            if(stop) {
                break;
            }
            index++;
        }
        return true;
    }

    private int extractString(int index, String line) {
        while(index < line.length() && line.charAt(index) != '"') {
            index++;
        }
        return index;
    }

}
