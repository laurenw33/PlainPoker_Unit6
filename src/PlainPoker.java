public class PlainPoker {

    public int bidValue;
    private int[] cardNum;
    private String[] cardString;
    public String[] combined;
    private static int fiveOfKind = 0;
    private static int fourOfKind = 0;
    private static int fullHouse = 0;
    private static int threeOfKind = 0;
    private static int twoPair = 0;
    private static int onePair = 0;
    private static int highCard = 0;
    private int rank;
    private static int totalBidValue = 0;

    public PlainPoker(String[] num, String[] s, String[] initial, String winning) {
        combined = initial;
        cardString = s;
        bidValue = Integer.parseInt(winning);

        cardNum = new int[num.length];
        for (int i = 0; i < num.length; i++) {
            try {
                int number = Integer.parseInt(num[i]);
                cardNum[i] = number;
            }
            catch (Exception e) {}
        }

    }

    public int[] determineHandType() {
        int len = combined.length;
        int[] counts = new int[len];

        for (int x = 0; x < len; x++) {
            String currentCard = combined[x];
            int count = 1;

            for (int i = x + 1; i < len; i++) {
                if (combined[i].equals(currentCard)) {
                    count++;
                    counts[i] = -1;
                }
            }

            if (counts[x] != -1) {
                counts[x] = count;
            }
        }
        return counts;
    }

    public void handType(int[] counts) {
        int pairs = 0;
        for (int count : counts) {
            if (count > 1) {
                if (count == 5) {
                    fiveOfKind++;
                } else if (count == 4) {
                    fourOfKind++;
                } else if (count == 3) {
                    threeOfKind++;
                } else if (count == 2) {
                    pairs++;
                } else {
                    highCard++;
                }
            }
        }

        if (pairs == 2) {
            twoPair++;
        } else if (pairs == 1) {
            onePair++;
        }
        if (pairs == 1 && threeOfKind == 1) {
            pairs--;
            threeOfKind--;
            fullHouse++;
        }
    }

    public int getCardValue() {
        int total = 0;

        for (String card : cardString) {
            if (card.equalsIgnoreCase("ACE")) {
                total += 14;
            }

            if (card.equalsIgnoreCase("KING")) {
                total += 13;
            }
            if (card.equalsIgnoreCase("QUEEN")) {
                total += 12;
            }
            if (card.equalsIgnoreCase("JACK")) {
                total += 11;
            }
        }

        for (int i : cardNum) {
            total += i;
        }
        return total;
    }

    public void orderCardsNum() {
        int len = cardNum.length;

        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - 1 - i; j++) {
                if (cardNum[j] > cardNum[j + 1]) {

                    int temp = cardNum[j];
                    cardNum[j] = cardNum[j + 1];
                    cardNum[j + 1] = temp;
                }
            }
        }

    }

    public int[] orderCardsString(){
        int len = cardString.length;
        int[] temp = new int[cardString.length];

        for (int i = 0; i < len; i++) {
            String card = cardString[i];

            if (card.equalsIgnoreCase("jack")) {
                temp[i] = 11;
            }
            else if (card.equalsIgnoreCase("queen")) {
                temp[i] = 12;
            }
            else if (card.equalsIgnoreCase("king")) {
                temp[i] = 13;
            }
            else if (card.equalsIgnoreCase("ace")) {
                temp[i] = 14;
            }
        }
        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - 1 - i; j++) {
                if (temp[j] > temp[j + 1]) {

                    int tempValue = temp[j];
                    temp[j] = temp[j + 1];
                    temp[j + 1] = tempValue;
                }
            }
        }

        return temp;
    }

//    public int[] combineCards(){
//        int[] stringCards = orderCardsString();
//        int[] combined = new int[stringCards.length + cardNum.length];
//
//        for (int i = 0; i < cardNum.length; i++) {
//            combined[i] = cardNum[i];
//        }
//        for (int i = 0; i < stringCards.length; i++) {
//            combined[cardNum.length + i] = stringCards[i];
//        }
//
//        return combined;
//    }



    public int[] combineCards() {
        int[] combined = new int[5]; // Always 5 cards in a poker hand
        int numIndex = 0;
        int strIndex = 0;

        // Convert the original combined array to numeric values
        for (int i = 0; i < this.combined.length; i++) {
            String card = this.combined[i];
            if (card.equalsIgnoreCase("ace")) {
                combined[i] = 14;
            } else if (card.equalsIgnoreCase("king")) {
                combined[i] = 13;
            } else if (card.equalsIgnoreCase("queen")) {
                combined[i] = 12;
            } else if (card.equalsIgnoreCase("jack")) {
                combined[i] = 11;
            } else {
                try {
                    combined[i] = Integer.parseInt(card);
                } catch (NumberFormatException e) {
                }
            }
        }

        for (int i = 0; i < combined.length - 1; i++) {
            for (int j = 0; j < combined.length - 1 - i; j++) {
                if (combined[j] > combined[j + 1]) {
                    int temp = combined[j];
                    combined[j] = combined[j + 1];
                    combined[j + 1] = temp;
                }
            }
        }

        return combined;
    }

    public int[] getCardNum() {
        return cardNum;
    }

    public int getHandStrength() {
        int[] counts = determineHandType();
        int pairs = 0;
        int threes = 0;

        for (int count : counts) {
            if (count == 5) return 7;
            if (count == 4) return 6;
            if (count == 3) threes++;
            if (count == 2) pairs++;
        }

        if (threes == 1 && pairs == 1) return 5;
        if (threes == 1) return 4;
        if (pairs == 2) return 3;
        if (pairs == 1) return 2;
        return 1;
    }

    public void setRank (int newRank) {
        rank = newRank;
        totalBidValue += rank * bidValue;
    }

    public int compareTo(PlainPoker other) {
        int thisStrength = this.getHandStrength();
        int otherStrength = other.getHandStrength();

        if (thisStrength != otherStrength) {
            return Integer.compare(thisStrength, otherStrength);
        }

        int[] thisCombined = this.combineCards();
        int[] otherCombined = other.combineCards();

        for (int i = 0; i < thisCombined.length; i++) {
            if (thisCombined[i] != otherCombined[i]) {
                return Integer.compare(thisCombined[i], otherCombined[i]);
            }
        }
        return 0;
    }

    public String toString() {
        String line = "Number of five of a kind hands:" + fiveOfKind + "\n";
        line += "Number of full house hands:" + fullHouse + "\n";
        line += "Number of four of a kind hands: " + fourOfKind + "\n";
        line += "Number of three of a kind hands: " + threeOfKind + "\n";
        line += "Number of two pair hands: " + twoPair + "\n";
        line += "Number of one pair hands: " + onePair + "\n";
        line += "Number of high card hands: " + highCard + "\n";
        line += "Total Bid Value: " + totalBidValue;
        return line;
    }
    }


