package se.sjolinder.advent25;

import java.util.HashMap;
import java.util.Scanner;

public class TuringMachine {
    public static void main(String[] args) {
        String input = "Begin in state A.\n" +
                "Perform a diagnostic checksum after 12399302 steps.\n" +
                "\n" +
                "In state A:\n" +
                "  If the current value is 0:\n" +
                "    - Write the value 1.\n" +
                "    - Move one slot to the right.\n" +
                "    - Continue with state B.\n" +
                "  If the current value is 1:\n" +
                "    - Write the value 0.\n" +
                "    - Move one slot to the right.\n" +
                "    - Continue with state C.\n" +
                "\n" +
                "In state B:\n" +
                "  If the current value is 0:\n" +
                "    - Write the value 0.\n" +
                "    - Move one slot to the left.\n" +
                "    - Continue with state A.\n" +
                "  If the current value is 1:\n" +
                "    - Write the value 0.\n" +
                "    - Move one slot to the right.\n" +
                "    - Continue with state D.\n" +
                "\n" +
                "In state C:\n" +
                "  If the current value is 0:\n" +
                "    - Write the value 1.\n" +
                "    - Move one slot to the right.\n" +
                "    - Continue with state D.\n" +
                "  If the current value is 1:\n" +
                "    - Write the value 1.\n" +
                "    - Move one slot to the right.\n" +
                "    - Continue with state A.\n" +
                "\n" +
                "In state D:\n" +
                "  If the current value is 0:\n" +
                "    - Write the value 1.\n" +
                "    - Move one slot to the left.\n" +
                "    - Continue with state E.\n" +
                "  If the current value is 1:\n" +
                "    - Write the value 0.\n" +
                "    - Move one slot to the left.\n" +
                "    - Continue with state D.\n" +
                "\n" +
                "In state E:\n" +
                "  If the current value is 0:\n" +
                "    - Write the value 1.\n" +
                "    - Move one slot to the right.\n" +
                "    - Continue with state F.\n" +
                "  If the current value is 1:\n" +
                "    - Write the value 1.\n" +
                "    - Move one slot to the left.\n" +
                "    - Continue with state B.\n" +
                "\n" +
                "In state F:\n" +
                "  If the current value is 0:\n" +
                "    - Write the value 1.\n" +
                "    - Move one slot to the right.\n" +
                "    - Continue with state A.\n" +
                "  If the current value is 1:\n" +
                "    - Write the value 1.\n" +
                "    - Move one slot to the right.\n" +
                "    - Continue with state E.";
        System.err.println("Advent25: part 1: " + part1(input, 12399302));

    }

    public static int part1(String input, int steps) {
        HashMap<Character, State> states = parseInput(input);
        char currentState = 'A';
        int currentPosition = 0;
        HashMap<Integer, Integer> tape = new HashMap<>();

        for (int i = 0; i < steps; i++) {
            State state = states.get(currentState);
            StateChange stateChange;
            if (tape.containsKey(currentPosition)) {
                stateChange = state.nextIfOne;

            } else
                stateChange = state.nextIfZero;
            if (stateChange.nextValue == 0)
                tape.remove(currentPosition);
            else
                tape.put(currentPosition, stateChange.nextValue);

            currentPosition += stateChange.nextCurrentPosition;
            currentState = stateChange.nextState;

        }
        return tape.keySet().size();
    }

    private static HashMap<Character, State> parseInput(String input) {
        HashMap<Character, State> states = new HashMap<>();
        Scanner scanner = new Scanner(input);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            if (line.startsWith("In state")) {
                State state = new State();
                states.put(line.charAt(9), state);

                scanner.nextLine();
                line = scanner.nextLine();
                state.nextIfZero = new StateChange();
                state.nextIfZero.nextValue = Integer.parseInt("" + line.charAt(22));
                line = scanner.nextLine();
                state.nextIfZero.nextCurrentPosition = line.contains("right") ? 1 : -1;
                line = scanner.nextLine();
                state.nextIfZero.nextState = line.charAt(26);

                scanner.nextLine();
                line = scanner.nextLine();
                state.nextIfOne = new StateChange();
                state.nextIfOne.nextValue = Integer.parseInt("" + line.charAt(22));
                line = scanner.nextLine();
                state.nextIfOne.nextCurrentPosition = line.contains("right") ? 1 : -1;
                line = scanner.nextLine();
                state.nextIfOne.nextState = line.charAt(26);
            }
        }

        return states;
    }
}

class StateChange {
    int nextValue;
    int nextCurrentPosition;
    char nextState;
}

class State {
    StateChange nextIfZero;
    StateChange nextIfOne;

    @Override
    public boolean equals(Object obj) {
        int value = (int) obj;
        if (value == 0) {

        }
        return false;
    }
}
