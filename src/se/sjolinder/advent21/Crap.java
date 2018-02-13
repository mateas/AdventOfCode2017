package se.sjolinder.advent21;

public class Crap {
    public static void main(String[] args) {
        String input = "";

        System.err.println(ClosestEnemyII(new String[]{"00000", "10000", "00000", "00002", "02002"}));
        System.err.println(ClosestEnemyII(new String[]{"0000000", "0001000", "0000000", "0000000", "0000000", "2000000", "0000000"}));
    }

   /* public static String FirstReverse(String str) {

        // code goes here
    *//* Note: In Java the return type of a function and the
       parameter types being passed are defined, so this return
       call must match the return type of the function.
       You are free to modify the return type. *//*
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String vowel = "aouei";


        char[] chars = str.toCharArray();
        for (int i = 0; i < str.length(); i++) {
            if (i == 0 || chars[i] = 0 ' '){
                chars[i + 1] = Character.toUpperCase(chars[i + 1]);

            }

        }
        return new String(chars);

    }*/



    public static String ClosestEnemyII(String[] strArr) {

        // code goes here
    /* Note: In Java the return type of a function and the
       parameter types being passed are defined, so this return
       call must match the return type of the function.
       You are free to modify the return type. */
        int mex = 0;
        int mey = 0;
        int enDist = Integer.MAX_VALUE;
        boolean stopLoop = false;
        for (; mey < strArr.length; mey++) {
            for (mex = 0; mex < strArr[0].length(); mex++) {
                if (strArr[mey].charAt(mex) == '1') {
                    stopLoop = true;
                    break;
                }
            }
            if (stopLoop)
                break;
        }
        for (int y = 0; y < strArr.length; y++) {
            for (int x = 0; x < strArr[0].length(); x++) {
                if (strArr[y].charAt(x) == '2') {
                    int dist = Math.abs(x - mex) + Math.abs(y - mey);
                    if (dist < enDist)
                        enDist = dist;
                }
            }
        }
        if (enDist == Integer.MAX_VALUE) {
            enDist = 0;
        }
        return "" + enDist;

    }


}
