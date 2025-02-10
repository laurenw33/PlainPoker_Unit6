import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
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

        PlainPoker plainPoker = new PlainPoker(hands);
        plainPoker.printHandTypes();
        System.out.println("Total Bid Value: " + plainPoker.getNormBiddingAmt());
        System.out.println("Total Bid Value With Jacks Wild: " + plainPoker.getWildJacksBiddingAmt());
    }
}