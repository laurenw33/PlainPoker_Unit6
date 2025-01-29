public class PlainPoker {

    private int bidValue;
    private int[] cardNum;
    private String[] cardString;
    private String[] combined;

    public PlainPoker(String[] num, String[] s, String[] initial){
        combined = initial;
        int len = num.length;
        cardNum = new int[len];
        for (int i = 0; i < num.length - 1; i++) {
            cardNum[i] = Integer.parseInt(num[i]);
        }
        cardString = s;

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
    public String handType(int[] counts) {
        int fiveOfKind = 0;
        int fourOfKind = 0;
        int fullHouse = 0;
        int threeOfKind = 0;
        int twoPair = 0;
        int onePair = 0;
        int highCard = 0;
        int pairs = 0;

        for (int count : counts) {
            if (count == 5) {
                fiveOfKind++;
            } else if (count == 4) {
                fourOfKind++;
            } else if (count == 3) {
                threeOfKind++;
            } else if (count == 2) {
                pairs++;
            }
        }

        if (fiveOfKind == 1) {
            fiveOfKind++;
        }
        if (fourOfKind == 1) {
            fourOfKind++;
        }
        if (threeOfKind == 1 && pairs == 1) {
            fullHouse++;
        }
        if (threeOfKind == 1) {
            threeOfKind++;
        }
        if (pairs == 2) {
            twoPair++;
        }
        if (pairs == 1) {
            onePair++;
        }
        highCard++;

        return "Number of five of a kind hands:" + fiveOfKind + "\n";
        return "Number of full house hands:" + fullHouse + "\n";
        return "Number of four of a kind hands: " + fourOfKind+ "\n";
        return "Number of three of a kind hands: " + threeOfKind+ "\n";
        return "Number of two pair hands: " + twoPair+ "\n";
        return "Number of one pair hands: " + onePair+ "\n";
        return "Number of high card hands: " + highCard + "\n";

    }
}
