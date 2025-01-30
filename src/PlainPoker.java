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
    public void handType(int[] counts) {
        int pairs = 0;
        for (int count : counts) {
            if (count > 1) {
                if (count == 5){
                    fiveOfKind++;
                }
                else if (count == 4) {
                    fourOfKind++;
                }
                else if (count == 3){
                    threeOfKind++;
                }
                else if (count == 2){
                    pairs++;
                }
                else {
                    highCard++;
                }
            }
        }

        if (pairs == 2) {
            twoPair++;
        }
        else if (pairs == 1) {
            onePair++;
        }
        if (pairs == 1 && threeOfKind == 1) {
            pairs--;
            threeOfKind--;
            fullHouse++;
        }
    }

    public String toString(){
        String line = "Number of five of a kind hands:" + fiveOfKind + "\n";
        line += "Number of full house hands:" + fullHouse + "\n";
        line += "Number of four of a kind hands: " + fourOfKind+ "\n";
        line += "Number of three of a kind hands: " + threeOfKind+ "\n";
        line += "Number of two pair hands: " + twoPair+ "\n";
        line += "Number of one pair hands: " + onePair+ "\n";
        line += "Number of high card hands: " + highCard + "\n";
        return line;
    }

}
