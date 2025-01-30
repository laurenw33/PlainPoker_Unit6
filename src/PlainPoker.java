public class PlainPoker {

    private int bidValue;
    private int[] cardNum;
    private String[] cardString;
    private String[] combined;
    private static int fiveOfKind = 0;
    private static int fourOfKind = 0;
    private static int fullHouse = 0;
    private static int threeOfKind = 0;
    private static int twoPair = 0;
    private static int onePair = 0;
    private static int highCard = 0;

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

    public String toString() {
        String line = "Number of five of a kind hands:" + fiveOfKind + "\n";
        line += "Number of full house hands:" + fullHouse + "\n";
        line += "Number of four of a kind hands: " + fourOfKind + "\n";
        line += "Number of three of a kind hands: " + threeOfKind + "\n";
        line += "Number of two pair hands: " + twoPair + "\n";
        line += "Number of one pair hands: " + onePair + "\n";
        line += "Number of high card hands: " + highCard + "\n";
        return line;
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

    public int[] combineCards(){
        int[] stringCards = orderCardsString();
        int[] combined = new int[stringCards.length + cardNum.length];

        for (int i = 0; i < cardNum.length; i++) {
            combined[i] = cardNum[i];
        }
        for (int i = 0; i < stringCards.length; i++) {
            combined[cardNum.length + i] = stringCards[i];
        }

        return combined;
    }



    public int[] getCardNum() {
        return cardNum;
    }
}


