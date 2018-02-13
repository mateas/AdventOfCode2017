package se.sjolinder.advent07;

import java.util.ArrayList;

public class Node {
    public int weight;
    public Node parent;
    public String name;
    public String[] childNames = new String[0];
    public ArrayList<Node> childs = new ArrayList<>();

    public int sumChilds() {
        int sum = 0;
        for (Node child : childs) {
            sum += child.weight;
        }
        return sum;
    }

    public int sum() {
        return weight + sumChilds();
    }

}