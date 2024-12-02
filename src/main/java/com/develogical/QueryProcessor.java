package com.develogical;

import java.math.BigInteger;
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
            // What is 24 plus 15 plus 2?
            Pattern pattern = Pattern.compile("\\d+");
            Matcher matcher = pattern.matcher(query);
            List<Integer> numbers = new ArrayList<>();
            while (matcher.find()) {
                numbers.add(Integer.parseInt(matcher.group()));
            }

            int sum = 0;
            for (int number : numbers) {
                sum += number;
            }
            return String.valueOf(sum);
        }

        if (query.contains("What is") && query.contains("minus")) {
            Pattern pattern = Pattern.compile("\\d+");
            Matcher matcher = pattern.matcher(query);
            List<Integer> numbers = new ArrayList<>();
            
            while (matcher.find()) {
                numbers.add(Integer.parseInt(matcher.group()));
            }
            
            if (numbers.size() >= 2) {
                return String.valueOf(numbers.get(0) - numbers.get(1));
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
                if (validNumbers.size() == 1) {
                    return String.valueOf(validNumbers.get(0));
                } else {
                    return validNumbers.stream().map(String::valueOf).collect(Collectors.joining(","));
                }
            }
        }

        if (query.toLowerCase().contains("are primes:")) {
            // Example query:
            // Which of the following numbers are primes: 40, 36, 95, 71, 29?
            Pattern pattern = Pattern.compile("\\d+");
            Matcher matcher = pattern.matcher(query);
            List<Integer> numbers = new ArrayList<>();
        
            while (matcher.find()) {
                numbers.add(Integer.parseInt(matcher.group()));
            }
        
            List<Integer> primeNumbers = new ArrayList<>();
            for (Integer num : numbers) {
                if (isPrime(num)) {
                    primeNumbers.add(num);
                }
            }
        
            if (!primeNumbers.isEmpty()) {
                // Convert the list of prime numbers to a comma-separated string
                StringBuilder result = new StringBuilder("Prime numbers: ");
                for (int i = 0; i < primeNumbers.size(); i++) {
                    result.append(primeNumbers.get(i));
                    if (i < primeNumbers.size() - 1) {
                        result.append(", ");
                    }
                }
                return result.toString();
            }
        
            return "No prime numbers found in the list.";
        }

        if (query.contains("What is") && query.contains("to the power of")) {
            // Example query:
            // What is 8 to the power of 66?
            Pattern pattern = Pattern.compile("\\d+");
            Matcher matcher = pattern.matcher(query);
            List<BigInteger> numbers = new ArrayList<>();
        
            while (matcher.find()) {
                numbers.add(new BigInteger(matcher.group()));
            }
        
            if (numbers.size() >= 2) {
                BigInteger base = numbers.get(0);
                int exponent = numbers.get(1).intValue();
                BigInteger result = computePower(base, exponent);
                return result.toString();
            }
            return "";
        }

        return "";
    }

    private BigInteger computePower(BigInteger base, int exponent) {
        return base.pow(exponent);
    }

    private boolean checkIfNumberIsCube(int number) {
      int t1 = number;
      double t2 = Math.cbrt(number);
      return  t1 == (t2 * t2 * t2);
    }
  
    private boolean checkIfNumberIsSquare(int number) {
        int t1 = number;
        double t2 = Math.sqrt(number);
        return  t1 == (t2 * t2);
    }

    private boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }
        if (num == 2) {
            return true;
        }
        if (num % 2 == 0) {
            return false;
        }
        int sqrt = (int) Math.sqrt(num);
        for (int i = 3; i <= sqrt; i += 2) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}