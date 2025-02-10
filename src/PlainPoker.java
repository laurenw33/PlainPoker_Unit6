import java.util.ArrayList;
import java.util.Arrays;

public class PlainPoker {
    ArrayList<Hand> highCards = new ArrayList<Hand>();
    ArrayList<Hand> onePairs = new ArrayList<Hand>();
    ArrayList<Hand> twoPairs = new ArrayList<Hand>();
    ArrayList<Hand> threeOfAKinds = new ArrayList<Hand>();
    ArrayList<Hand> fullHouses = new ArrayList<Hand>();
    ArrayList<Hand> fourOfAKinds = new ArrayList<Hand>();
    ArrayList<Hand> fiveOfAKinds = new ArrayList<Hand>();
    ArrayList<Hand> hands;
    private final int normBiddingAmt;
    private final int wildJacksBiddingAmt;

    public PlainPoker(ArrayList<Hand> hands) {
        this.hands = hands;
        wildJacksBiddingAmt = sortHands(true);
        normBiddingAmt = sortHands(false);
    }

    private int sortHands(Boolean wildJacks) {
        clearArrays();
        for (Hand hand: hands) {
            String handType = "";
            if (wildJacks) {
                handType = hand.getJackHandType();
            }
            else {
                handType = hand.getHandType();
            }
            if (handType.equals("high card")) {
                if (highCards.isEmpty()) {
                    highCards.add(hand);
                }
                else {
                    placeHand(highCards, hand, wildJacks);
                }
            }
            else if (handType.equals("one pair")) {
                if (onePairs.isEmpty()) {
                    onePairs.add(hand);
                }
                else {
                    placeHand(onePairs, hand, wildJacks);
                }
            }
            else if (handType.equals("two pair")) {
                if (twoPairs.isEmpty()) {
                    twoPairs.add(hand);
                }
                else {
                    placeHand(twoPairs, hand, wildJacks);
                }
            }
            else if (handType.equals("three of a kind")) {
                if (threeOfAKinds.isEmpty()) {
                    threeOfAKinds.add(hand);
                }
                else {
                    placeHand(threeOfAKinds, hand, wildJacks);
                }
            }
            else if (handType.equals("full house")) {
                if (fullHouses.isEmpty()) {
                    fullHouses.add(hand);
                }
                else {
                    placeHand(fullHouses, hand, wildJacks);
                }
            }
            else if (handType.equals("four of a kind")) {
                if (fourOfAKinds.isEmpty()) {
                    fourOfAKinds.add(hand);
                }
                else {
                    placeHand(fourOfAKinds, hand, wildJacks);
                }
            }
            else if (handType.equals("five of a kind")) {
                if (fiveOfAKinds.isEmpty()) {
                    fiveOfAKinds.add(hand);
                }
                else {
                    placeHand(fiveOfAKinds, hand, wildJacks);
                }
            }
        }
        return calculateBiddingAmount();
    }

    private void placeHand(ArrayList<Hand> handTypeArray, Hand hand, Boolean wildJacks) {
        boolean handPlaced = false;
        for (int i = 0; i < handTypeArray.size(); i ++) {
            if (!compareHands(hand, handTypeArray.get(i), wildJacks)) {
                handTypeArray.add(i, hand);
                handPlaced = true;
                break;
            }
        }
        if (!handPlaced) {
            handTypeArray.add(hand);
        }
    }

    private boolean compareHands(Hand hand1, Hand hand2, Boolean wildJacks) {
        int[] hand1Cards = makeNumericOnly(hand1.getCards(), wildJacks);
        int[] hand2Cards = makeNumericOnly(hand2.getCards(), wildJacks);
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

    private static int[] makeNumericOnly(String[] cards, boolean wildJacks) {
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
                if (wildJacks) {
                    value = 1;
                }
                else {
                    value = 11;
                }
            }
            else {
                value = Integer.parseInt(cards[i]);
            }
            modifiedCards[i] = value;
        }
        return modifiedCards;
    }

    private int calculateBiddingAmount() {
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

    private void clearArrays() {
        highCards.clear();
        onePairs.clear();
        twoPairs.clear();
        threeOfAKinds.clear();
        fullHouses.clear();
        fourOfAKinds.clear();
        fiveOfAKinds.clear();
    }

    public void printHandTypes() {
        System.out.println("Number of five of a kind hands: " + fiveOfAKinds.size());
        System.out.println("Number of full house hands: " + fullHouses.size());
        System.out.println("Number of four of a kind hands: " + fourOfAKinds.size());
        System.out.println("Number of three of a kind hands: " + threeOfAKinds.size());
        System.out.println("Number of two pair hands: " + twoPairs.size());
        System.out.println("Number of one pair hands: " + onePairs.size());
        System.out.println("Number of high card hands: " + highCards.size());
    }

    public int getNormBiddingAmt() {
        return normBiddingAmt;
    }

    public int getWildJacksBiddingAmt() {
        return wildJacksBiddingAmt;
    }
}