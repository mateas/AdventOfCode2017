package se.sjolinder.advent18;

import java.util.HashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Duet {
    public static void main(String[] args) {
        String input = "set i 31\n" +
                "set a 1\n" +
                "mul p 17\n" +
                "jgz p p\n" +
                "mul a 2\n" +
                "add i -1\n" +
                "jgz i -2\n" +
                "add a -1\n" +
                "set i 127\n" +
                "set p 316\n" +
                "mul p 8505\n" +
                "mod p a\n" +
                "mul p 129749\n" +
                "add p 12345\n" +
                "mod p a\n" +
                "set b p\n" +
                "mod b 10000\n" +
                "snd b\n" +
                "add i -1\n" +
                "jgz i -9\n" +
                "jgz a 3\n" +
                "rcv b\n" +
                "jgz b -1\n" +
                "set f 0\n" +
                "set i 126\n" +
                "rcv a\n" +
                "rcv b\n" +
                "set p a\n" +
                "mul p -1\n" +
                "add p b\n" +
                "jgz p 4\n" +
                "snd a\n" +
                "set a b\n" +
                "jgz 1 3\n" +
                "snd b\n" +
                "set f 1\n" +
                "add i -1\n" +
                "jgz i -11\n" +
                "snd a\n" +
                "jgz f -16\n" +
                "jgz a -19";
        System.err.println("Advent18: part 1: " + part1(input));
        part2(input);
    }

    public static int part1(String input) {
        String[] instructions = input.split(System.getProperty("line.separator"));
        HashMap<String, Long> registers = new HashMap<>();
        long lastPlayedValue = 0;
        for (int i = 0; i < instructions.length; i++) {
            String[] instruction = instructions[i].split(" ");
            if (!registers.containsKey(instruction[1]))
                registers.put(instruction[1], (long) 0);

            long value = 0;
            if (instruction.length > 2)
                if (isNumeric(instruction[2])) {
                    value = Integer.parseInt(instruction[2]);
                } else {
                    value = registers.get(instruction[2]);
                }
            boolean isRecovered = false;
            switch (instruction[0]) {
                case "snd":
                    lastPlayedValue = registers.get(instruction[1]);
                    break;
                case "set":
                    registers.put(instruction[1], value);
                    break;
                case "add":
                    long oldValue = registers.get(instruction[1]);
                    registers.put(instruction[1], oldValue + value);
                    break;
                case "mul":
                    oldValue = registers.get(instruction[1]);
                    registers.put(instruction[1], oldValue * value);
                    break;
                case "mod":
                    oldValue = registers.get(instruction[1]);
                    registers.put(instruction[1], oldValue % value);
                    break;
                case "rcv":
                    oldValue = registers.get(instruction[1]);
                    if (oldValue != 0) {
                        isRecovered = true;
                    }
                    break;
                case "jgz":
                    oldValue = registers.get(instruction[1]);
                    if (oldValue > 0) {
                        i = i - 1 + (int) value;
                    }
                    break;

            }
            if (isRecovered)
                break;

        }
        return (int) lastPlayedValue;
    }

    public static void part2(String input) {
        LinkedBlockingQueue<Long> queueA = new LinkedBlockingQueue<>();
        LinkedBlockingQueue<Long> queueB = new LinkedBlockingQueue<>();
        Runnable programA = new Runnable() {
            @Override
            public void run() {
                Duet.program(input, 0, queueA, queueB);
            }
        };
        Runnable programB = new Runnable() {
            @Override
            public void run() {
                Duet.program(input, 1, queueB, queueA);
            }
        };
        Thread threadB = new Thread(programB);
        threadB.start();
        programA.run();
    }

    public static int program(String input, int programId, LinkedBlockingQueue<Long> sendinginQueue, LinkedBlockingQueue<Long> receivingQueue) {
        String[] instructions = input.split(System.getProperty("line.separator"));
        HashMap<String, Long> registers = new HashMap<>();
        registers.put("p", (long) programId);
        long countSend = 0;
        for (int i = 0; i < instructions.length; i++) {
            String[] instruction = instructions[i].split(" ");
            if (!instruction[0].equals("snd") && !registers.containsKey(instruction[1]))
                registers.put(instruction[1], (long) 0);

            long value = 0;
            if (instruction.length > 2)
                value = getValue(registers, instruction[2]);


            boolean isRecovered = false;
            switch (instruction[0]) {
                case "snd":
                    long valueToSend;
                    if (isNumeric(instruction[1])) {
                        valueToSend = Integer.parseInt(instruction[1]);
                    } else {
                        valueToSend = registers.get(instruction[1]);
                    }

                    sendinginQueue.add(valueToSend);
                    System.err.println("Program " + programId + ": -> " + valueToSend);
                    countSend++;
                    break;
                case "set":
                    registers.put(instruction[1], value);
                    break;
                case "add":
                    long oldValue = registers.get(instruction[1]);
                    registers.put(instruction[1], oldValue + value);
                    break;
                case "mul":
                    oldValue = registers.get(instruction[1]);
                    registers.put(instruction[1], oldValue * value);
                    break;
                case "mod":
                    oldValue = registers.get(instruction[1]);
                    registers.put(instruction[1], oldValue % value);
                    break;
                case "rcv":
                    try {
                        Long newValue = receivingQueue.poll(3, TimeUnit.SECONDS);
                        if (newValue == null)
                            isRecovered = true;
                        else {
                            registers.put(instruction[1], newValue);
                            System.err.println("Program " + programId + ": <- " + newValue);

                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                case "jgz":
                    oldValue = getValue(registers, instruction[1]);
                    if (oldValue > 0) {
                        i = i - 1 + (int) value;
                    }
                    break;

            }
            if (isRecovered)
                break;

        }
        System.err.println("Program " + programId + " ended with " + countSend + " sends");

        return (int) countSend;
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
