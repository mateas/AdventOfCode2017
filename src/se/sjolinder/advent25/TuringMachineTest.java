package se.sjolinder.advent25;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TuringMachineTest {

    @Test
    void part1() {
        String input = "Begin in state A.\n" +
                "Perform a diagnostic checksum after 6 steps.\n" +
                "\n" +
                "In state A:\n" +
                "  If the current value is 0:\n" +
                "    - Write the value 1.\n" +
                "    - Move one slot to the right.\n" +
                "    - Continue with state B.\n" +
                "  If the current value is 1:\n" +
                "    - Write the value 0.\n" +
                "    - Move one slot to the left.\n" +
                "    - Continue with state B.\n" +
                "\n" +
                "In state B:\n" +
                "  If the current value is 0:\n" +
                "    - Write the value 1.\n" +
                "    - Move one slot to the left.\n" +
                "    - Continue with state A.\n" +
                "  If the current value is 1:\n" +
                "    - Write the value 1.\n" +
                "    - Move one slot to the right.\n" +
                "    - Continue with state A.\n";
        assertEquals(3, TuringMachine.part1(input, 6));
    }
}