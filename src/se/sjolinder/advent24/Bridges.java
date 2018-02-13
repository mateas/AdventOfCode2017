package se.sjolinder.advent24;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Bridges {
    public static void main(String[] args) {
        String input = "48/5\n" +
                "25/10\n" +
                "35/49\n" +
                "34/41\n" +
                "35/35\n" +
                "47/35\n" +
                "34/46\n" +
                "47/23\n" +
                "28/8\n" +
                "27/21\n" +
                "40/11\n" +
                "22/50\n" +
                "48/42\n" +
                "38/17\n" +
                "50/33\n" +
                "13/13\n" +
                "22/33\n" +
                "17/29\n" +
                "50/0\n" +
                "20/47\n" +
                "28/0\n" +
                "42/4\n" +
                "46/22\n" +
                "19/35\n" +
                "17/22\n" +
                "33/37\n" +
                "47/7\n" +
                "35/20\n" +
                "8/36\n" +
                "24/34\n" +
                "6/7\n" +
                "7/43\n" +
                "45/37\n" +
                "21/31\n" +
                "37/26\n" +
                "16/5\n" +
                "11/14\n" +
                "7/23\n" +
                "2/23\n" +
                "3/25\n" +
                "20/20\n" +
                "18/20\n" +
                "19/34\n" +
                "25/46\n" +
                "41/24\n" +
                "0/33\n" +
                "3/7\n" +
                "49/38\n" +
                "47/22\n" +
                "44/15\n" +
                "24/21\n" +
                "10/35\n" +
                "6/21\n" +
                "14/50";
        System.err.println("Advent24: part 1: " + part1(input));
        System.err.println("Advent24: part 1: " + part2(input));
    }

    public static int part1(String input) {
        String[] split = input.split(System.getProperty("line.separator"));
        ArrayList<String> comp = new ArrayList<String>(Arrays.asList(split));
        int strength = findStrongestBridge(0, comp);
        return strength;
    }

    public static int part2(String input) {
        String[] split = input.split(System.getProperty("line.separator"));
        ArrayList<String> comp = new ArrayList<String>(Arrays.asList(split));
        HashMap<Integer, Integer> depthes = new HashMap<>();
        findStrongestBridge(0, 0, 0, comp, depthes);

        return 0;
    }

    private static int findStrongestBridge(int startId, ArrayList<String> components) {
        int strength = 0;
        //System.err.print(startId + "/");

        for (int i = 0; i < components.size(); i++) {
            String[] edges = components.get(i).split("/");
            int nextStartId = -1;
            ArrayList<String> nextArray = new ArrayList<>(components);
            if (Integer.parseInt(edges[0]) == startId) {
                nextStartId = Integer.parseInt(edges[1]);
                nextArray.remove(components.get(i));
            } else if (Integer.parseInt(edges[1]) == startId) {
                nextStartId = Integer.parseInt(edges[0]);
                nextArray.remove(components.get(i));
            }
            if (nextStartId != -1) {
                int tmpStrength = startId + findStrongestBridge(nextStartId, nextArray);
                if (tmpStrength > strength) {
                    strength = tmpStrength;
                }
            }
        }
        strength += startId;
        //System.err.println("(" + strength + ")");

        return strength;
    }

    private static void findStrongestBridge(int startId, int depth, int strength, ArrayList<String> components, HashMap<Integer, Integer> depthNStrength) {
        for (int i = 0; i < components.size(); i++) {
            String[] edges = components.get(i).split("/");
            int nextStartId = -1;
            ArrayList<String> nextArray = new ArrayList<>(components);
            if (Integer.parseInt(edges[0]) == startId) {
                nextStartId = Integer.parseInt(edges[1]);
                nextArray.remove(components.get(i));
            } else if (Integer.parseInt(edges[1]) == startId) {
                nextStartId = Integer.parseInt(edges[0]);
                nextArray.remove(components.get(i));
            }
            if (nextStartId != -1) {
                int newStrength = strength + startId + nextStartId;

                if (!depthNStrength.containsKey(depth)) {
                    depthNStrength.put(depth, 0);
                }
                if (newStrength > depthNStrength.get(depth)) {
                    depthNStrength.put(depth, newStrength);
                }

                findStrongestBridge(nextStartId, depth + 1, newStrength, nextArray, depthNStrength);

            }
        }
    }
}
