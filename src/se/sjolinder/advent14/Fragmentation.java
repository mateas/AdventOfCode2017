package se.sjolinder.advent14;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Fragmentation {
    public static void main(String[] args) {
        String input = "oundnydw";
        System.err.println("Advent14: part1 1: " + part1(input));
        System.err.println("Advent14: part1 2: " + part2(input));
    }

    public static int part1(String input) {
        int counter = 0;
        for (int i = 0; i < 128; i++) {
            String hashInput = input + "-" + i;
            String hash = hash(hashInput, 256);
            String bitHash = "";

            for (int j = 0; j < hash.length() - 1; j += 2) {
                String data = hash.substring(j, j + 2);
                int digit = Integer.parseInt(data, 16);

                bitHash += Integer.toBinaryString(digit);
            }
            counter += bitHash.replace("0", "").length();
        }
        return counter;
    }

    public static int part2(String input) {
        String[] memGrid = new String[128];
        for (int i = 0; i < 128; i++) {
            String hashInput = input + "-" + i;
            String hash = hash(hashInput, 256);
            String bitHash = "";

            for (int j = 0; j < hash.length() - 1; j += 2) {
                String data = hash.substring(j, j + 2);
                int digit = Integer.parseInt(data, 16);
                String bits = String.format("%8s", Integer.toBinaryString(digit)).replace(" ", "0");
                bitHash += bits;
            }
            memGrid[i] = bitHash;
        }

        Node[] nodes = new Node[128 * 128];

        for (int i = 0; i < 128 * 128; i++) {
            char k = memGrid[i / 128].charAt(i - 128 * (i / 128));

            if (k == '1') {
                //Group found
                if (nodes[i] == null)
                    nodes[i] = new Node(i);

                //Find adjacent
                int nextI = i + 1;
                if (nextI % 128 != 0) {
                    char nextK = memGrid[nextI / 128].charAt(nextI - 128 * (nextI / 128));
                    if (nextK == '1') {
                        if (nodes[nextI] == null)
                            nodes[nextI] = new Node(nextI);
                        if (!nodes[i].adjecantNodes.contains(nodes[nextI]))
                            nodes[i].adjecantNodes.add(nodes[nextI]);
                        if (!nodes[nextI].adjecantNodes.contains(nodes[i]))
                            nodes[nextI].adjecantNodes.add(nodes[i]);
                    }
                }

                nextI = i + 128;
                if (nextI < 128 * 128) {
                    char nextK = memGrid[nextI / 128].charAt(nextI - 128 * (nextI / 128));
                    if (nextK == '1') {
                        if (nodes[nextI] == null)
                            nodes[nextI] = new Node(nextI);
                        if (!nodes[i].adjecantNodes.contains(nodes[nextI]))
                            nodes[i].adjecantNodes.add(nodes[nextI]);
                        if (!nodes[nextI].adjecantNodes.contains(nodes[i]))
                            nodes[nextI].adjecantNodes.add(nodes[i]);
                    }
                }
            }

        }


        ArrayList<ArrayList<Node>> groups = new ArrayList<>();
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] != null){
                boolean newGroup = true;
                for (ArrayList<Node> group : groups) {
                    if (group.contains(nodes[i]))
                        newGroup = false;
                }
                if (newGroup) {

                    ArrayList<Node> reachedNodes = new ArrayList<>();
                    reachNodes(nodes[i], reachedNodes);
                    groups.add(reachedNodes);
                }
            }
        }
        return groups.size();
    }

    private static void reachNodes(Node node, ArrayList<Node> reachedNodes) {
        if (!reachedNodes.contains(node)) {
            reachedNodes.add(node);
            for (Node adjecantNode : node.adjecantNodes) {
                reachNodes(adjecantNode, reachedNodes);
            }
        }
    }

    public static String hash(String input, int listSize) {
        int[] size = parseHashInput(input);
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
                hex += Integer.toHexString(0x100 | denseHash[i / 16]).substring(1);
            }
        }

        return hex;
    }

    public static int[] parseHashInput(String input) {
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

    public static int traveller(String input) {
        String[] rows = input.split(System.getProperty("line.separator"));
        Node[] nodes = new Node[rows.length];
        Pattern p = Pattern.compile("^(\\d+)\\s<->\\s(.*)?");
        for (int i = 0; i < rows.length; i++) {
            Matcher matcher = p.matcher(rows[i]);
            matcher.find();
            int node = Integer.parseInt(matcher.group(1));
            if (nodes[node] == null)
                nodes[node] = new Node(node);


            String[] adjacentNodes = matcher.group(2).split(", ");
            for (String adjacentNode : adjacentNodes) {
                int adjecantNodeId = Integer.parseInt(adjacentNode);

                if (nodes[adjecantNodeId] == null)
                    nodes[adjecantNodeId] = new Node(adjecantNodeId);
                if (!nodes[node].adjecantNodes.contains(nodes[adjecantNodeId]))
                    nodes[node].adjecantNodes.add(nodes[adjecantNodeId]);
                if (!nodes[adjecantNodeId].adjecantNodes.contains(nodes[node]))
                    nodes[adjecantNodeId].adjecantNodes.add(nodes[node]);
            }
        }
        ArrayList<ArrayList<Node>> groups = new ArrayList<>();
        for (int i = 0; i < nodes.length; i++) {
            boolean newGroup = true;
            for (ArrayList<Node> group : groups) {
                if (group.contains(nodes[i]))
                    newGroup = false;
            }
            if (newGroup) {

                ArrayList<Node> reachedNodes = new ArrayList<>();
                reachNodes(nodes[i], reachedNodes);
                groups.add(reachedNodes);
            }
        }
        return groups.size();

    }
}

class Node {
    public int id;
    public ArrayList<Node> adjecantNodes = new ArrayList<>();

    public Node(int identifier) {
        id = identifier;
    }

}

