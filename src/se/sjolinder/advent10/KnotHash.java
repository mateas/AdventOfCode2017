package se.sjolinder.advent10;

import java.util.ArrayList;

public class KnotHash {
    public static void main(String[] args) {
        String input = "14,58,0,116,179,16,1,104,2,254,167,86,255,55,122,244";
        System.err.println("Advent10: part1 1:" + part1(input, 256));
        System.err.println("Advent10: part1 1:" + part2(input, 256));
    }

    public static String part2(String input, int listSize) {
        int[] size = parseInput2(input);
        int[] list = new int[listSize];
        for (int i = 0; i < listSize; i++) {
            list[i] = i;
        }

        int position = 0;
        int skip = 0;
        for (int k = 0; k < 64; k++) {

            for (int i = 0; i < size.length; i++) {
                int[] subList = new int[size[i]];
                for (int j = 0; j < size[i]; j++) {
                    subList[j] = list[(position + j) % list.length];
                }
                for (int j = 0; j < size[i]; j++) {
                    list[(position + j) % list.length] = subList[size[i] - 1 - j];
                }
                position += (size[i] + skip++) % list.length;
            }
        }

        int xorResult = 0;
        int[] denseHash = new int[listSize / 16];
        String hex = "";
        for (int i = 0; i < list.length; i++) {
            denseHash[i / 16] = denseHash[i / 16] ^ list[i];
            if (i % 16 == 15) {
                hex+=Integer.toHexString(0x100 |  denseHash[i / 16]).substring(1);
            }
        }

        return hex;
    }

    public static int part1(String input, int listSize) {
        int[] size = parseInput(input);
        int[] list = new int[listSize];
        for (int i = 0; i < listSize; i++) {
            list[i] = i;
        }

        int position = 0;
        int skip = 0;
        for (int i = 0; i < size.length; i++) {
            int[] subList = new int[size[i]];
            for (int j = 0; j < size[i]; j++) {
                subList[j] = list[(position + j) % list.length];
            }
            for (int j = 0; j < size[i]; j++) {
                list[(position + j) % list.length] = subList[size[i] - 1 - j];
            }
            position += (size[i] + skip++) % list.length;
        }


        return list[0] * list[1];
    }

    private static int[] parseInput(String input) {
        String[] values = input.split(",\\s*");
        int[] output = new int[values.length];
        for (int i = 0; i < values.length; i++) {
            output[i] = Integer.parseInt(values[i]);
        }

        return output;
    }

    public static int[] parseInput2(String input) {
        char[] chars = input.toCharArray();
        int[] output = new int[chars.length + 5];
        for (int i = 0; i < chars.length; i++) {
            output[i] = (int) chars[i];
        }
        int[] suffix = {17, 31, 73, 47, 23};
        for (int i = 0; i < suffix.length; i++) {
            output[chars.length + i] = suffix[i];
        }
        return output;
    }
}
