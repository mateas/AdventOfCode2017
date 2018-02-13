package se.sjolinder.advent06;

import java.util.ArrayList;

public class Memory {
    public static void main(String[] args) {

        String input = "4	10	4	1	8	4	9	14	5	1	14	15	0	15	3	5";
        System.out.println("Advent07: part1 1: " + part1(input));
        System.out.println("Advent07: part1 2: " + part2(input));
    }

    public static int part1(String input) {
        int[] digits = parseList(input);

        ArrayList<String> sampler = new ArrayList<String>();
        sampler.add(getPrint(digits));
        int c = 1;
        for (; c < Integer.MAX_VALUE; c++) {
            int largestValueIndex = digits.length - 1;
            for (int i = digits.length - 1; i >= 0; i--) {
                if (digits[i] >= digits[largestValueIndex])
                    largestValueIndex = i;
            }

            int num = digits[largestValueIndex];
            digits[largestValueIndex] = 0;
            for (int j = 1; j <= num; j++) {
                digits[(largestValueIndex + j) % digits.length] += 1;
            }


            if (sampler.contains(getPrint(digits))) {
                break;
            } else
                sampler.add(getPrint(digits));
        }
        return c;


    }

    private static String getPrint(int[] digits) {
        String result = "";
        for (int d : digits) {
            result += "_" + d;
        }
        return result;
    }


    public static int part2(String input) {
        int[] digits = parseList(input);

        ArrayList<String> sampler = new ArrayList<String>();
        sampler.add(getPrint(digits));
        int c = 1;
        for (; c < Integer.MAX_VALUE; c++) {
            int largestValueIndex = digits.length - 1;
            for (int i = digits.length - 1; i >= 0; i--) {
                if (digits[i] >= digits[largestValueIndex])
                    largestValueIndex = i;
            }

            int num = digits[largestValueIndex];
            digits[largestValueIndex] = 0;
            for (int j = 1; j <= num; j++) {
                digits[(largestValueIndex + j) % digits.length] += 1;
            }


            if (sampler.contains(getPrint(digits))) {
                break;
            } else
                sampler.add(getPrint(digits));
        }

        return sampler.size() -  sampler.indexOf(getPrint(digits));
        //return c;


    }


    private static int[] parseList(String list) {
        String[] rows = list.split("\\s+");
        int[] digits = new int[rows.length];
        for (int i = 0; i < rows.length; i++) {
            digits[i] = Integer.parseInt(rows[i]);
        }

        return digits;
    }


}
