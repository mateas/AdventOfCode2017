package se.sjolinder.advent23;

import se.sjolinder.advent08.Instructions;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Coprocessor {
    public static void main(String[] args) {
        String input = "set b 84\n" +
                "set c b\n" +
                "jnz a 2\n" +
                "jnz 1 5\n" +
                "mul b 100\n" +
                "sub b -100000\n" +
                "set c b\n" +
                "sub c -17000\n" +
                "set f 1\n" +
                "set d 2\n" +
                "set e 2\n" +
                "set g d\n" +
                "mul g e\n" +
                "sub g b\n" +
                "jnz g 2\n" +
                "set f 0\n" +
                "sub e -1\n" +
                "set g e\n" +
                "sub g b\n" +
                "jnz g -8\n" +
                "sub d -1\n" +
                "set g d\n" +
                "sub g b\n" +
                "jnz g -13\n" +
                "jnz f 2\n" +
                "sub h -1\n" +
                "set g b\n" +
                "sub g c\n" +
                "jnz g 2\n" +
                "jnz 1 3\n" +
                "sub b -17\n" +
                "jnz 1 -23";
        String inputOpt ="set b 84\n" +
                "set c b\n" +
                "jnz a 2\n" +
                "jnz 1 5\n" +
                "mul b 100\n" +
                "sub b -100000\n" +
                "set c b\n" +
                "sub c -17000\n" +
                "set f 1\n" +
                "set d 2\n" +
                "set e 2\n" +
                "set g d\n" +
                "mul g e\n" +
                "sub g b\n" +
                "jnz g 3\n" +
                "set f 0\n" +
                "jnz 1 9\n" +
                "sub e -1\n" +
                "set g e\n" +
                "sub g b\n" +
                "jnz g -9\n" +
                "sub d -1\n" +
                "set g d\n" +
                "sub g b\n" +
                "jnz g -14\n" +
                "jnz f 2\n" +
                "sub h -1\n" +
                "set g b\n" +
                "sub g c\n" +
                "jnz g 2\n" +
                "jnz 1 3\n" +
                "sub b -17\n" +
                "jnz 1 -24";
        System.err.println("Advent23: part 1: " + part1(input));
        System.err.println("Advent23: part 1: " + countPrime());


        System.err.println("Advent23: part 2: " + part2(inputOpt));
    }

    private static int countPrime() {
        int counter =0;
        for (int i = 108400; i < 125400; i = i+17) {
            if (isPrime(i))
                counter++;
        }
        return counter;
    }
    private static boolean isPrime(int num) {
        if (num < 2) return false;
        if (num == 2) return true;
        if (num % 2 == 0) return false;
        for (int i = 3; i * i <= num; i += 2)
            if (num % i == 0) return false;
        return true;
    }

    public static int part1(String input) {
        String[] instructions = input.split(System.getProperty("line.separator"));
        HashMap<String, Long> registers = new HashMap<>();
        long lastPlayedValue = 0;
        int counter = 0;
        for (int i = 0; i < instructions.length; i++) {
            String[] instruction = instructions[i].split(" ");
            if (!registers.containsKey(instruction[1]))
                registers.put(instruction[1], (long) 0);

            long value = 0;
            if (instruction.length > 2)
                value = getValue(registers, instruction[2]);
            boolean isRecovered = false;
            switch (instruction[0]) {
                    /*case "snd":
                        lastPlayedValue = registers.get(instruction[1]);
                        break;*/
                case "set":
                    registers.put(instruction[1], value);
                    break;
                    /*case "add":
                       long oldValue = getValue(registers, instruction[1]);
                        registers.put(instruction[1], oldValue + value);
                        break;*/
                case "sub":
                    long oldValue = getValue(registers, instruction[1]);
                    registers.put(instruction[1], oldValue - value);
                    break;
                case "mul":
                    oldValue = getValue(registers, instruction[1]);
                    registers.put(instruction[1], oldValue * value);
                    counter++;
                    break;
                case "mod":
                    oldValue = getValue(registers, instruction[1]);
                    registers.put(instruction[1], oldValue % value);
                    break;
                case "rcv":
                    oldValue = registers.get(instruction[1]);
                    if (oldValue != 0) {
                        isRecovered = true;
                    }
                    break;
                case "jgz":
                    oldValue = getValue(registers, instruction[1]);
                    if (oldValue > 0) {
                        i = i - 1 + (int) value;
                    }
                    break;
                case "jnz":
                    oldValue = getValue(registers, instruction[1]);
                    if (oldValue != 0) {
                        i = i - 1 + (int) value;
                    }
                    break;

            }
            if (isRecovered)
                break;

        }
        return counter;
    }

    public static int part2(String input) {
        String[] instructions = input.split(System.getProperty("line.separator"));
        HashMap<String, Long> registers = new HashMap<>();
        registers.put("a", 1L);
        int counter = 0;
        for (int i = 0; i < instructions.length; i++) {
            System.err.println("i=" + i + ", " + instructions[i]);
            String[] instruction = instructions[i].split(" ");
            if (!registers.containsKey(instruction[1]))
                registers.put(instruction[1], (long) 0);

            long value = 0;
            if (instruction.length > 2)
                value = getValue(registers, instruction[2]);
            boolean isRecovered = false;

            switch (instruction[0]) {
                    /*case "snd":
                        lastPlayedValue = registers.get(instruction[1]);
                        break;*/
                case "set":
                    registers.put(instruction[1], value);
                    break;
                    /*case "add":
                       long oldValue = getValue(registers, instruction[1]);
                        registers.put(instruction[1], oldValue + value);
                        break;*/
                case "sub":
                    long oldValue = getValue(registers, instruction[1]);
                    registers.put(instruction[1], oldValue - value);
                    break;
                case "mul":
                    oldValue = getValue(registers, instruction[1]);
                    registers.put(instruction[1], oldValue * value);
                    break;
                case "mod":
                    oldValue = getValue(registers, instruction[1]);
                    registers.put(instruction[1], oldValue % value);
                    break;
                case "rcv":
                    oldValue = registers.get(instruction[1]);
                    if (oldValue != 0) {
                        isRecovered = true;
                    }
                    break;
                case "jgz":
                    oldValue = getValue(registers, instruction[1]);
                    if (oldValue > 0) {
                        i = i - 1 + (int) value;
                    }
                    break;
                case "jnz":
                    oldValue = getValue(registers, instruction[1]);
                    if (oldValue != 0) {
                        System.err.println("------JNZ---------");


                        i = i - 1 + (int) value;
                    }

                    break;

            }
            if (isRecovered)
                break;
            if (registers.containsKey("e") && registers.get("e") == 54200L) {
                System.err.println("nowﬁ");
            }

            if (true) {
                Set<Map.Entry<String, Long>> entries = registers.entrySet();
                Iterator<Map.Entry<String, Long>> iterator1 = registers.entrySet().iterator();
                while (iterator1.hasNext()) {
                    Map.Entry<String, Long> next = iterator1.next();
                    System.err.print(next.getKey() + "=" + next.getValue());
                    if (iterator1.hasNext())
                        System.err.print(", \t");
                    else
                        System.err.println("");

                }
            }
            counter++;

        }
        return counter;
    }

    private static long getValue(HashMap<String, Long> register, String s) {
        if (isNumeric(s)) {
            return Long.parseLong(s);
        }
        return register.get(s);
    }


    private static boolean isNumeric(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        } catch (NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }
}
