package Misc;

public class ConvertBaseAToB {
    private String inBuilt(String number, int sourceBase, int destinationBase) {
        return Integer.toString(Integer.parseInt(number, sourceBase), destinationBase);
    }

    private static String convertBase2To6(String num) {
        // convert from 2 to 10 first
        int baseTen = 0;

        for (int i = 0; i < num.length(); i++) {
            baseTen += (num.charAt(i) - '0' * (int) Math.pow(2, num.length() - i - 1));
        }

        // convert from 10 to 6
        StringBuilder baseSix = new StringBuilder();

        while (baseTen > 0) {
            baseSix.insert(0, baseTen % 6);
            baseTen /= 6;
        }

        return baseSix.toString();
    }

    public static void main(String[] args) {
        System.out.println(convertBase2To6("1101001"));
        System.out.println(convertBase2To6("1100"));
    }
}
