package com.company;

import java.util.Scanner;

/**
 * @author Gonzalo Ortiz <gonzalo.ortizbianco@cs.tamk.fi>
 * @version 2016.1220
 * @since 1.8
 */
public class Helpers {

    /**
     * Used to request and safely handle an integer input.
     *
     * @return integer input given by the user..
     */
    static int inputInt() {
        Scanner scan = new Scanner(System.in);
        boolean bool = true;
        int number = 0;
        int count = 0;
        while (bool == true) {
            if (count >= 1) {
                scan.nextLine();
            }
            try {
                count++;
                number = scan.nextInt();
                bool = false;
            } catch (Exception e) {
                System.out.println("Please input an integer");
            }
        }
        return number;
    }

    /**
     * Used to ask the user for an input
     *
     * @return returns a String-type input.
     */
    static String inputString() {
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }
}
