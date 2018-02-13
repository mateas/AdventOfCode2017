package se.sjolinder.advent13;

import java.util.regex.Pattern;

public class FirewallSecurity {
    public static void main(String[] args) {
        String input = "0: 3\n" +
                "1: 2\n" +
                "2: 4\n" +
                "4: 8\n" +
                "6: 5\n" +
                "8: 6\n" +
                "10: 6\n" +
                "12: 4\n" +
                "14: 6\n" +
                "16: 6\n" +
                "18: 9\n" +
                "20: 8\n" +
                "22: 8\n" +
                "24: 8\n" +
                "26: 8\n" +
                "28: 10\n" +
                "30: 8\n" +
                "32: 12\n" +
                "34: 10\n" +
                "36: 14\n" +
                "38: 12\n" +
                "40: 12\n" +
                "42: 12\n" +
                "44: 12\n" +
                "46: 12\n" +
                "48: 12\n" +
                "50: 14\n" +
                "52: 12\n" +
                "54: 14\n" +
                "56: 12\n" +
                "58: 12\n" +
                "60: 14\n" +
                "62: 18\n" +
                "64: 14\n" +
                "68: 14\n" +
                "70: 14\n" +
                "72: 14\n" +
                "74: 14\n" +
                "78: 14\n" +
                "80: 20\n" +
                "82: 14\n" +
                "84: 14\n" +
                "90: 17";
        System.err.println("Advent13: part1 1: " + part1(input));
        System.err.println("Advent13: part1 2: " + part2(input));
    }

    public static int part1(String input) {

        String[] rows = input.split(System.getProperty("line.separator"));
        int[] layers = null;
        for (int i = rows.length - 1; i >= 0; i--) {
            String[] values = rows[i].split(": ");
            int layer = Integer.parseInt(values[0]);
            int depth = Integer.parseInt(values[1]);
            if (layers == null)
                layers = new int[layer + 1];
            layers[layer] = depth;
        }


        int severity = 0;
        for (int picosec = 0; picosec < layers.length; picosec++) {
            if (picosec % ((layers[picosec] - 1) * 2) == 0)
                severity += picosec * layers[picosec];
        }

        return severity;
    }

    public static int part2(String input) {

        String[] rows = input.split(System.getProperty("line.separator"));
        int[] layers = null;
        for (int i = rows.length - 1; i >= 0; i--) {
            String[] values = rows[i].split(": ");
            int layer = Integer.parseInt(values[0]);
            int depth = Integer.parseInt(values[1]);
            if (layers == null)
                layers = new int[layer + 1];
            layers[layer] = depth;
        }

        for (int delay = 0; delay < Integer.MAX_VALUE; delay++) {
            boolean passed = true;
            for (int picosec = 0; picosec < layers.length; picosec++) {
                int layersDepth = (layers[picosec] - 1) * 2;
                if (layers[picosec] > 0 && (picosec + delay) % layersDepth == 0) {
                    passed = false;
                    break;
                }
            }
            if (passed)
                return delay;

        }

        return 0;
    }
}
