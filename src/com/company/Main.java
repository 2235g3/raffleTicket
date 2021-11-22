package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        HashMap<Integer, String> raffleDict = new HashMap<>();
        boolean again = true;
        while (again) {
            System.out.println("Would you like to buy a ticket, check a ticket, or leave: B / C / L");
            switch (input().toLowerCase()) {
                case ("b"):
                    raffleDict = raffleBuy(raffleDict);
                    break;
                case ("c"):
                    checkTicket(raffleDict);
                    break;
                case ("l"):
                    again = false;
                    break;
                default:
                    System.out.println("Not a valid input, please try again\n");
            }
        }
    }
    public static String input() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            return br.readLine();
        } catch (Exception e) {
            return "";
        }
    }
    public static HashMap<Integer, String> raffleBuy(HashMap<Integer, String> raffleDict) {
        int userRaffle;
        while (true) {
            try {
                System.out.println("Enter the raffle number you want to buy:");
                userRaffle = Integer.parseInt(input());
                if (raffleDict.containsKey(userRaffle)) {
                    System.out.println("Someone already has that raffle number");
                } else {
                    System.out.println("You have bought raffle ticket #" + userRaffle);
                    break;
                }
            } catch (Exception e) {
                System.out.println("Not a valid input");
            }
        }
        System.out.println("Enter your name:");
        raffleDict.put(userRaffle, input());
        return raffleDict;
    }
    public static void checkTicket(HashMap<Integer, String> raffleDict) {
        int largeNum = 0;
        boolean numExists = false;
        boolean numPrime = true;
        if (! raffleDict.isEmpty()) {
            for (int i = 0; i < raffleDict.size(); i++) {
                if (Integer.parseInt(raffleDict.keySet().toArray()[i].toString()) > largeNum) {
                    largeNum = Integer.parseInt(raffleDict.keySet().toArray()[i].toString());
                }
            }
            System.out.println("Enter the number you want to check");
            int numToCheck = Integer.parseInt(input());
            for (int i = 0; i < raffleDict.size(); i++) {
                if (Integer.parseInt(raffleDict.keySet().toArray()[i].toString()) == numToCheck) {
                    numExists = true;
                    break;
                }
            }
            if (! numExists) {
                System.out.println("No one has bought that ticket yet");
            }
            else {
                System.out.println("Enter your name:");
                String nameToCheck = input();
                if (nameToCheck.equals(raffleDict.get(numToCheck))) {
                    for (int i = 1; i <= largeNum; i++) {
                        if (numToCheck % i == 0 && i != 1 && i != numToCheck) {
                            numPrime = false;
                            break;
                        }
                    }
                    if (! numPrime) {
                        System.out.println("You did not win anything");
                    }
                    else {
                        System.out.println("Well done " + nameToCheck + "!\nYou got a winning ticket!");
                    }
                    raffleDict.remove(numToCheck);
                }
                else {
                    System.out.println(nameToCheck + " didn't buy ticket #" + numToCheck);
                }
            }
        }
        else {
            System.out.println("No one has bought any tickets yet.");
        }
    }
}