import java.util.Arrays;

public class PlainPoker {

    public int[] bidValue;
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
    private static int count;

    public PlainPoker(String[] num, String[] s, String[] initial, String winning) {
        combined = initial;
        cardString = s;
        bidValue = new int[]{Integer.parseInt(winning)};

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

    public int handType(int[] counts) {
        int pairs = 0;
        int handType = 0;
        for (int count : counts) {
            if (count > 1) {
                if (count == 5) {
                    fiveOfKind++;
                    handType = 6;
                } else if (count == 4) {
                    fourOfKind++;
                    handType = 5;
                } else if (count == 3) {
                    threeOfKind++;
                    handType = 3;
                } else if (count == 2) {
                    pairs++;
                } else {
                    highCard++;
                    handType = 0;
                }
            }
        }

        if (pairs == 2) {
            twoPair++;
            handType = 2;
        } else if (pairs == 1) {
            onePair++;
            handType = 1;
        }
        if (pairs == 1 && threeOfKind == 1) {
            pairs--;
            threeOfKind--;
            fullHouse++;
            handType = 4;
        }
        return handType;
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
        int[] combined = new int[5];
        int numIndex = 0;
        int strIndex = 0;
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

    public static int addTypes() {
        count++;
        return count;
    }

//    public int[] counts(int type){
//        int[] counts = new int[count];
//        for (int i = 0; i < counts.length; i++) {
//            counts[i] += type;
//        }
//
//        return counts;
//    }

    public int[] getCardNum() {
        return cardNum;
    }

    public void setRank (int newRank) {
        rank = newRank;
        for (int val : bidValue) {
            totalBidValue += rank * val;
        }
    }

    public int compareTo(PlainPoker other) {
        int thisStrength = this.handType(determineHandType());
        int otherStrength = other.handType(determineHandType());

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


