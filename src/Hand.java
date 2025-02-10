public class Hand {
    //Attributes are self-explanatory
    //Cards: list of the 5 cards
    //Hand type: what type hand is
    //Bid Amount: The bid amount
    private String handType;
    private int jackHandType;
    private String[] cards;
    private int bidAmount;

    public Hand(String[] cards, int bidAmount) {
        this.cards = cards;
        this.bidAmount = bidAmount;
        handType = findHandType();
        jackHandType = findJackHandType();
    }

    //This is just all of part one in a function
    private String findHandType() {
        boolean onePair = false;
        boolean twoPair = false;
        boolean threeOfAKind = false;
        int matching = 1;
        String [] cards = this.cards;
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

    private int findJackHandType(){
        boolean onePair = false;
        boolean twoPair = false;
        boolean threeOfAKind = false;
        int matching = 1;
        int type;
        String [] cards = this.cards;
        for (int x = 0; x < cards.length; x ++) {
            for (int y = x; y < cards.length; y ++) {
                if (x != y) {
                    if (cards[x].equals(cards[y])) {
                        matching++;
                    }
                }
            }
            if (matching == 5) {
                type = 7;
            }
            if (matching == 4) {
                type = 5;
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
            type = 6;
        }
        else if (threeOfAKind) {
            type = 4;
        }
        else if (twoPair) {
            type = 3;
        }
        else if (onePair) {
            type = 2;
        }
        else {
            type = 1;
        }
        int jackCount = 0;
        for (String c : cards) {
            if (c.equals("Jack")){
                jackCount++;
            }
        }
        return type + jackCount;
    }

    public String getHandType() {
        return handType;
    }

    public int getJackHandType() {
        return jackHandType;
    }

    public String[] getCards() {
        return cards;
    }

    public int getBidAmount() {
        return bidAmount;
    }
}
