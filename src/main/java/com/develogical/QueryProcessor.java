package com.develogical;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class QueryProcessor {

    public String process(String query) {

        if (query.toLowerCase().contains("shakespeare")) {
            return "William Shakespeare (26 April 1564 - 23 April 1616) was an " +
                    "English poet, playwright, and actor, widely regarded as the greatest " +
                    "writer in the English language and the world's pre-eminent dramatist.";
        }

        if (query.contains("your name")) {
            return "Monish";
        }

        if (query.contains("Which of the following numbers is the largest:")) {
            Pattern pattern = Pattern.compile("\\d+");
            Matcher matcher = pattern.matcher(query);
            List<Integer> numbers = new ArrayList<>();

            while (matcher.find()) {
                numbers.add(Integer.parseInt(matcher.group()));
            }

            if (!numbers.isEmpty()) {
                int largest = Collections.max(numbers);
                return String.valueOf(largest);
            }
        }

        if (query.contains("What is") && query.contains("plus")) {
            // What is 62 plus 40?
            Pattern pattern = Pattern.compile("\\d+");
            Matcher matcher = pattern.matcher(query);
            List<Integer> numbers = new ArrayList<>();
            
            while (matcher.find()) {
                numbers.add(Integer.parseInt(matcher.group()));
            }
            
            if (numbers.size() >= 2) {
                return String.valueOf(numbers.get(0) + numbers.get(1));
            }
            return "";
        }

        if (query.contains("multiplied by") || query.contains("multiplied")) {
            // Handle multiplication
            Pattern pattern = Pattern.compile("\\d+");
            Matcher matcher = pattern.matcher(query);
            List<Integer> numbers = new ArrayList<>();
            
            while (matcher.find()) {
                numbers.add(Integer.parseInt(matcher.group()));
            }
            
            if (numbers.size() >= 2) {
                return String.valueOf(numbers.get(0) * numbers.get(1));
            }
        }
        if (query.contains("Which of the following numbers is both a square and a cube:")) {
            // Which of the following numbers is both a square and a cube: 2416, 1936, 3160, 4508, 4290, 2744, 1?
            Pattern pattern = Pattern.compile("\\d+");
            Matcher matcher = pattern.matcher(query);
            List<Integer> numbers = new ArrayList<>();
            
            while (matcher.find()) {
                numbers.add(Integer.parseInt(matcher.group()));
            }
    
            List<Integer> validNumbers = new ArrayList<>();
            for (int number : numbers) {
                if (checkIfNumberIsCube(number) && checkIfNumberIsSquare(number)) {
                    validNumbers.add(number);
                }
            }
    
            if (!validNumbers.isEmpty()) {
                return validNumbers.stream().map(String::valueOf).collect(Collectors.joining(","));
            }
        }

        return "";
    }

    
    private boolean checkIfNumberIsCube(int number) {
        return Math.cbrt(number) % 1 == 0;
    }

    private boolean checkIfNumberIsSquare(int number) {
        return Math.sqrt(number) % 1 == 0;
    }
}