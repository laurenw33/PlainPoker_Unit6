import java.sql.Array;
import java.util.ArrayList;

public class PlainPoker {
    /*Arraylist for each type of hand
    we will put in the hands in their respective type
     */
    ArrayList<Hand> highCards = new ArrayList<Hand>();
    ArrayList<Hand> onePairs = new ArrayList<Hand>();
    ArrayList<Hand> twoPairs = new ArrayList<Hand>();
    ArrayList<Hand> threeOfAKinds = new ArrayList<Hand>();
    ArrayList<Hand> fullHouses = new ArrayList<Hand>();
    ArrayList<Hand> fourOfAKinds = new ArrayList<Hand>();
    ArrayList<Hand> fiveOfAKinds = new ArrayList<Hand>();

    ArrayList<Hand> jackHigh = new ArrayList<Hand>();
    ArrayList<Hand> jackOnePair = new ArrayList<Hand>();
    ArrayList<Hand> jackTwoPair = new ArrayList<Hand>();
    ArrayList<Hand> jackThree = new ArrayList<Hand>();
    ArrayList<Hand> jackFull = new ArrayList<Hand>();
    ArrayList<Hand> jackFour = new ArrayList<Hand>();
    ArrayList<Hand> jackFive = new ArrayList<Hand>();

    ArrayList<Hand> hands;

    public PlainPoker(ArrayList<Hand> hands) {
        this.hands = hands;
        sortHands();
        sortJackHands();
    }

    //Does all the logic for sorting the hands
    private void sortHands() {
        for (Hand hand: hands) {
            if (hand.getHandType().equals("high card")) {
                if (highCards.isEmpty()) {
                    highCards.add(hand);
                }
                else {
                    placeHand(highCards, hand);
                }
            }
            else if (hand.getHandType().equals("one pair")) {
                if (onePairs.isEmpty()) {
                    onePairs.add(hand);
                }
                else {
                    placeHand(onePairs, hand);
                }
            }
            else if (hand.getHandType().equals("two pair")) {
                if (twoPairs.isEmpty()) {
                    twoPairs.add(hand);
                }
                else {
                    placeHand(twoPairs, hand);
                }
            }
            else if (hand.getHandType().equals("three of a kind")) {
                if (threeOfAKinds.isEmpty()) {
                    threeOfAKinds.add(hand);
                }
                else {
                    placeHand(threeOfAKinds, hand);
                }
            }
            else if (hand.getHandType().equals("full house")) {
                if (fullHouses.isEmpty()) {
                    fullHouses.add(hand);
                }
                else {
                    placeHand(fullHouses, hand);
                }
            }
            else if (hand.getHandType().equals("four of a kind")) {
                if (fourOfAKinds.isEmpty()) {
                    fourOfAKinds.add(hand);
                }
                else {
                    placeHand(fourOfAKinds, hand);
                }
            }
            else if (hand.getHandType().equals("five of a kind")) {
                if (fiveOfAKinds.isEmpty()) {
                    fiveOfAKinds.add(hand);
                }
                else {
                    placeHand(fiveOfAKinds, hand);
                }
            }

        }
    }

    private void sortJackHands() {
        for (Hand hand: hands) {
            if (hand.getJackHandType() == 1) {
                if (jackHigh.isEmpty()) {
                    jackHigh.add(hand);
                    String[] cards = hand.getCards();
                }
                else {
                    placeHand(jackHigh, hand);
                }
            }
            else if (hand.getJackHandType() == 2) {
                if (jackOnePair.isEmpty()) {
                    jackOnePair.add(hand);
                }
                else {
                    placeHand(jackOnePair, hand);
                }
            }
            else if (hand.getJackHandType() == 3) {
                if (jackTwoPair.isEmpty()) {
                    jackTwoPair.add(hand);
                }
                else {
                    placeHand(jackTwoPair, hand);
                }
            }
            else if (hand.getJackHandType() == 4) {
                if (jackThree.isEmpty()) {
                    jackThree.add(hand);
                }
                else {
                    placeHand(jackThree, hand);
                }
            }
            else if (hand.getJackHandType() == 5) {
                if (jackFull.isEmpty()) {
                    jackFull.add(hand);
                }
                else {
                    placeHand(jackFull, hand);
                }
            }
            else if (hand.getJackHandType() == 6) {
                if (jackFour.isEmpty()) {
                    jackFour.add(hand);
                }
                else {
                    placeHand(jackFour, hand);
                }
            }
            else if (hand.getJackHandType() == 7) {
                if (jackFive.isEmpty()) {
                    jackFive.add(hand);
                }
                else {
                    placeHand(jackFive, hand);
                }
            }

        }
    }


    //Place hand will find out where to put the hand in the typeArray
    private void placeHand(ArrayList<Hand> handTypeArray, Hand hand) {
        boolean handPlaced = false;
        for (int i = 0; i < handTypeArray.size(); i ++) {
            if (!compareHands(hand, handTypeArray.get(i))) {
                handTypeArray.add(i, hand);
                handPlaced = true;
                break;
            }
        }
        if (!handPlaced) {
            handTypeArray.add(hand);
        }
    }

    //is called by placeHand(), will compare the card types of the two hands
    private boolean compareHands(Hand hand1, Hand hand2) {
        int[] hand1Cards = convertNumeric(hand1.getCards());
        int[] hand2Cards = convertNumeric(hand2.getCards());
        for (int i = 0; i < hand1Cards.length; i++) {
            if (hand1Cards[i] > hand2Cards[i]) {
                return true;
            }
            else if (hand1Cards[i] < hand2Cards[i]) {
                return false;
            }
        }
        return false;
    }

    //is called by compareHands(), will convert all string cards (Ace, King, Queen, Jack) into numeric values for easy comparison
    private static int[] convertNumeric(String[] cards) {
        int[] modifiedCards = new int[5];
        for (int i = 0; i < cards.length; i ++) {
            int value = 0;
            if (cards[i].equals("Ace")) {
                value= 14;
            }
            else if (cards[i].equals("King")) {
                value = 13;
            }
            else if (cards[i].equals("Queen")) {
                value = 12;
            }
            else if (cards[i].equals("Jack")) {
                value = 11;
            }
            else {
                value = Integer.parseInt(cards[i]);
            }
            modifiedCards[i] = value;
        }
        return modifiedCards;
    }

    //public function, iterates through each of the hands in the hand type arrays, starts at the lowest, incrementing the rank by one every time
    public int calculateBiddingAmount() {
        int rank = 1;
        int total = 0;
        for (Hand highCard: highCards) {
            total += highCard.getBidAmount() * rank;
            rank ++;
        }
        for (Hand onePair: onePairs) {
            total += onePair.getBidAmount() * rank;
            rank ++;
        }
        for (Hand twoPair: twoPairs) {
            total += twoPair.getBidAmount() * rank;
            rank ++;
        }
        for (Hand threeOfAKind: threeOfAKinds) {
            total += threeOfAKind.getBidAmount() * rank;
            rank ++;
        }
        for (Hand fullHouse: fullHouses) {
            total += fullHouse.getBidAmount() * rank;
            rank ++;
        }
        for (Hand fourOfAKind: fourOfAKinds) {
            total += fourOfAKind.getBidAmount() * rank;
            rank ++;
        }
        for (Hand fiveOfAKind: fiveOfAKinds) {
            total += fiveOfAKind.getBidAmount() * rank;
            rank ++;
        }
        return total;
    }

    public int calculateJackBiddingAmount() {
        int rank = 1;
        int total = 0;
        for (Hand highCard: jackHigh) {
            total += highCard.getBidAmount() * rank;
            rank ++;
        }
        for (Hand onePair: jackOnePair) {
            total += onePair.getBidAmount() * rank;
            rank ++;
        }
        for (Hand twoPair: jackTwoPair) {
            total += twoPair.getBidAmount() * rank;
            rank ++;
        }
        for (Hand threeOfAKind: jackThree) {
            total += threeOfAKind.getBidAmount() * rank;
            rank ++;
        }
        for (Hand fullHouse: jackFull) {
            total += fullHouse.getBidAmount() * rank;
            rank ++;
        }
        for (Hand fourOfAKind: jackFour) {
            total += fourOfAKind.getBidAmount() * rank;
            rank ++;
        }
        for (Hand fiveOfAKind: jackFive) {
            total += fiveOfAKind.getBidAmount() * rank;
            rank ++;
        }
        return total;
    }

    //js prints how many of each kind
    public void printHandTypes() {
        System.out.println("Number of five of a kind hands: " + fiveOfAKinds.size());
        System.out.println("Number of four of a kind hands: " + fourOfAKinds.size());
        System.out.println("Number of full house hands: " + fullHouses.size());
        System.out.println("Number of three of a kind hands: " + threeOfAKinds.size());
        System.out.println("Number of two pair hands: " + twoPairs.size());
        System.out.println("Number of one pair hands: " + onePairs.size());
        System.out.println("Number of high card hands: " + highCards.size());
    }
}
