import java.util.ArrayList;
import java.util.Arrays;

public class Hand {
    private String handType;
    private String[] cards;
    private int bidAmount;
    private String jackHandType;
    private boolean hasJack;

    public Hand(String[] cards, int bidAmount) {
        this.cards = cards;
        this.bidAmount = bidAmount;
        handType = findHandType(this.cards);
        jackHandType = findJackHandType();
    }

    private String findHandType(String[] cards) {
        boolean onePair = false;
        boolean twoPair = false;
        boolean threeOfAKind = false;
        int matching = 1;
        for (int x = 0; x < cards.length; x ++) {
            for (int y = x; y < cards.length; y ++) {
                if (x != y) {
                    if (cards[x].equals(cards[y])) {
                        matching++;
                    }
                }
            }
            if (matching == 5) {
                return "five of a kind";
            }
            if (matching == 4) {
                return "four of a kind";
            }
            if (matching == 3) {
                threeOfAKind = true;
            }
            if (matching == 2) {
                if (onePair) {
                    twoPair = true;
                }
                else {
                    onePair = true;
                }
            }
            matching = 1;
        }
        if (twoPair && threeOfAKind) {
            return "full house";
        }
        else if (threeOfAKind) {
            return "three of a kind";
        }
        else if (twoPair) {
            return "two pair";
        }
        else if (onePair) {
            return "one pair";
        }
        else {
            return "high card";
        }
    }

    private String findJackHandType() {
        ArrayList<String> jackTypes = new ArrayList<String>();
        jackTypes.add(cards[0]);
        for (String card: cards) {
            boolean add = true;
            for (String jackTypesCard: jackTypes) {
                if (card.equals(jackTypesCard)) {
                    add = false;
                }
            }
            if (add) {
                jackTypes.add(card);
            }
        }

        int max = 0;
        for (String jackType: jackTypes) {
            String[] cards = this.cards.clone();
            for (int i = 0; i < cards.length; i ++) {
                if (cards[i].equals("Jack")) {
                    cards[i] = jackType;
                }
            }
            int currJackHandType = makeHandTypeNumeric(findHandType(cards));
            if (currJackHandType > max) {
                max = currJackHandType;
            }
        }

        return makehandTypeString(max);
    }

    private int makeHandTypeNumeric(String handType) {
        if (handType.equals("high card")) {
            return 1;
        }
        else if (handType.equals("one pair")) {
            return 2;
        }
        else if (handType.equals("two pair")) {
            return 3;
        }
        else if (handType.equals("three of a kind")) {
            return 4;
        }
        else if (handType.equals("full house")) {
            return 5;
        }
        else if (handType.equals("four of a kind")) {
            return 6;
        }
        else {
            return 7;
        }
    }

    private String makehandTypeString(int handType) {
        if (handType == 1) {
            return "high card";
        }
        else if (handType == 2) {
            return "one pair";
        }
        else if (handType == 3) {
            return "two pair";
        }
        else if (handType == 4) {
            return "three of a kind";
        }
        else if (handType == 5) {
            return "full house";
        }
        else if (handType == 6) {
            return "four of a kind";
        }
        else {
            return "five of a kind";
        }
    }

    public String getHandType() {
        return handType;
    }

    public String getJackHandType() {
        return jackHandType;
    }

    public String[] getCards() {
        return cards;
    }

    public int getBidAmount() {
        return bidAmount;
    }
}
