import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        File f = new File("src/txt");
        ArrayList<Hand> hands = new ArrayList<Hand>();
        String fileData = "";
        try {
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                String currentLine = s.nextLine();
                fileData += currentLine + "\n";
            }
            String[] fileArray = fileData.split("\n");


            //Make all the hand objects and put them in a list
            for (int i = 0; i < fileArray.length; i ++) {
                String[] cards = fileArray[i].substring(0, fileArray[i].indexOf("|")).split(",");
                int bidAmount = Integer.parseInt(fileArray[i].substring(fileArray[i].indexOf("|") + 1));
                Hand hand = new Hand(cards, bidAmount);
                hands.add(hand);
            }


        } catch (FileNotFoundException fe) {
            System.out.println("File was not found");
            System.exit(1);
        }

        //Plain Poker is my class that handles all the logic
        PlainPoker plainPoker = new PlainPoker(hands);
        plainPoker.printHandTypes();
        System.out.println("Bidding Amount: " + plainPoker.calculateBiddingAmount());
    }
}