public class Hand {
    //Attributes are self-explanatory
    //Cards: list of the 5 cards
    //Hand type: what type hand is
    //Bid Amount: The bid amount
    private String handType;
    private String[] cards;
    private int bidAmount;

    public Hand(String[] cards, int bidAmount) {
        this.cards = cards;
        this.bidAmount = bidAmount;
        handType = findHandType();
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

    public String getHandType() {
        return handType;
    }

    public String[] getCards() {
        return cards;
    }

    public int getBidAmount() {
        return bidAmount;
    }
}
