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
    private static int totalBidValueWithJacksWild = 0;

    public PlainPoker(String[] num, String[] s, String[] initial, int bidValue) {
        combined = initial;
        cardString = s;
        this.bidValue = new int[]{bidValue};

        cardNum = new int[num.length];
        for (int i = 0; i < num.length; i++) {
            try {
                cardNum[i] = Integer.parseInt(num[i]);
            } catch (Exception e) {
                cardNum[i] = 0;
            }
        }
        setRank(bidValue);
    }

    public int[] determineHandType() {
        int[] counts = new int[combined.length];

        for (int i = 0; i < combined.length; i++) {
            String currentCard = combined[i];
            int count = 1;

            for (int j = i + 1; j < combined.length; j++) {
                if (combined[j].equals(currentCard)) {
                    count++;
                    counts[j] = -1;
                }
            }

            if (counts[i] != -1) {
                counts[i] = count;
            }
        }
        return counts;
    }

    public void handType(int[] counts) {
        int pairs = 0;
        boolean hasThree = false;

        for (int count : counts) {
            if (count == 5) {
                fiveOfKind++;
            } else if (count == 4) {
                fourOfKind++;
            } else if (count == 3) {
                hasThree = true;
            } else if (count == 2) {
                pairs++;
            }
        }

        if (hasThree && pairs == 1) {
            fullHouse++;
            setRank(6);
        } else if (hasThree) {
            threeOfKind++;
            setRank(3);
        } else if (pairs == 2) {
            twoPair++;
            setRank(2);
        } else if (pairs == 1) {
            onePair++;
            setRank(1);
        } else {
            highCard++;
            setRank(0);
        }
        updateTotalBidValue(calculateTotalBidValue());
        updateTotalBidValueWithJacksWild(calculateTotalBidValueWithJacksWild());
    }
    public int calculateTotalBidValue() {
        return this.rank * bidValue[0];
    }

    public static int addWildBonus(int wildCards) {
        return wildCards * 100;
    }

    public void setRank(int newRank) {
        rank = newRank;
        totalBidValue = rank * bidValue[0];
    }

    public int calculateTotalBidValueWithJacksWild() {
        int wildCardValue = 0;

        for (int i = 0; i < combined.length; i++) {
            if (combined[i].equalsIgnoreCase("jack")) {
                wildCardValue++;
            }
        }

        return totalBidValue + (addWildBonus(wildCardValue));
    }

    public void updateTotalBidValue(int value) {
        totalBidValue += value;
    }

    public static void updateTotalBidValueWithJacksWild(int value) {
        totalBidValueWithJacksWild += value;
    }

    public int[] orderCardsNum() {
        for (int i = 0; i < cardNum.length - 1; i++) {
            for (int j = 0; j < cardNum.length - 1 - i; j++) {
                if (cardNum[j] > cardNum[j + 1]) {
                    int temp = cardNum[j];
                    cardNum[j] = cardNum[j + 1];
                    cardNum[j + 1] = temp;
                }
            }
        }
        return cardNum;
    }

    public int[] orderCardsString() {
        int[] temp = new int[cardString.length];

        for (int i = 0; i < cardString.length; i++) {
            String card = cardString[i].toLowerCase();

            if (card.equals("jack")) {
                temp[i] = 11;
            } else if (card.equals("queen")) {
                temp[i] = 12;
            } else if (card.equals("king")) {
                temp[i] = 13;
            } else if (card.equals("ace")) {
                temp[i] = 14;
            } else {
                try {
                    temp[i] = Integer.parseInt(cardString[i]);
                } catch (NumberFormatException e) {
                    temp[i] = 0;
                }
            }
        }

        for (int i = 0; i < temp.length - 1; i++) {
            for (int j = 0; j < temp.length - 1 - i; j++) {
                if (temp[j] > temp[j + 1]) {
                    int tempValue = temp[j];
                    temp[j] = temp[j + 1];
                    temp[j + 1] = tempValue;
                }
            }
        }

        return temp;
    }

//    public int[] combineCards() {
//        int[] stringCards = orderCardsString();
//        int[] combinedArray = new int[cardNum.length + stringCards.length];
//
//        for (int i = 0; i < cardNum.length; i++) {
//            combinedArray[i] = cardNum[i];
//        }
//        for (int i = 0; i < stringCards.length; i++) {
//            combinedArray[cardNum.length + i] = stringCards[i];
//        }
//
//        for (int i = 0; i < combinedArray.length - 1; i++) {
//            for (int j = 0; j < combinedArray.length - 1 - i; j++) {
//                if (combinedArray[j] > combinedArray[j + 1]) {
//                    int temp = combinedArray[j];
//                    combinedArray[j] = combinedArray[j + 1];
//                    combinedArray[j + 1] = temp;
//                }
//            }
//        }
//        return combinedArray;
//    }

    public String toString() {
        String line = "Number of five of a kind hands:" + fiveOfKind + "\n";
        line += "Number of full house hands:" + fullHouse + "\n";
        line += "Number of four of a kind hands: " + fourOfKind + "\n";
        line += "Number of three of a kind hands: " + threeOfKind + "\n";
        line += "Number of two pair hands: " + twoPair + "\n";
        line += "Number of one pair hands: " + onePair + "\n";
        line += "Number of high card hands: " + highCard + "\n";
        line += "Total Bid Value: " + totalBidValue + "\n";
        line += "Total Bid Value With Jacks Wild: " + calculateTotalBidValueWithJacksWild();
        return line;
    }
}
