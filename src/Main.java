//import java.io.File;
//import java.io.FileNotFoundException;
//import java.util.Objects;
//import java.util.Scanner;
//import java.util.Arrays;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//public class Main {
//
//    public static void main(String[] args) {
//
//        File f = new File("src/txt");
//        List<PlainPoker> hands = new ArrayList<>();
//
//        String fileData = "";
//        try {
//            Scanner s = new Scanner(f);
//            while (s.hasNextLine()) {
//                String currentLine = s.nextLine();
//                fileData += currentLine + "\n";
//            }
//
//            String[] fileArray = fileData.split("\n");
//
//            String names;
//            String numbers;
//            String[] combined;
//            PlainPoker poker = null;
//            for (int i = 0; i < fileArray.length; i++) {
//                String stringNumbers = fileArray[i];
//                int idx = stringNumbers.indexOf("\\|");
//                int len = stringNumbers.length();
//                String winning = stringNumbers.substring(idx, len);
//                String[] x = stringNumbers.split("\\|");
//                String[] y = x[0].split(",");
//                combined = y;
//
//                numbers = "";
//                names = "";
//                for (int p = 0; p < y.length; p++) {
//                    String list = y[p];
//                    if (list.equals("1")) {
//                        numbers += "1 ";
//                    } else if (list.equals("2")) {
//                        numbers += "2 ";
//                    } else if (list.equals("3")) {
//                        numbers += "3 ";
//                    } else if (list.equals("4")) {
//                        numbers += "4 ";
//                    } else if (list.equals("5")) {
//                        numbers += "5 ";
//                    } else if (list.equals("6")) {
//                        numbers += "6 ";
//                    } else if (list.equals("7")) {
//                        numbers += "7 ";
//                    } else if (list.equals("8")) {
//                        numbers += "8 ";
//                    } else if (list.equals("9")) {
//                        numbers += "9 ";
//                    } else if (list.equals("10")) {
//                        numbers += "10 ";
//                    }
//                }
//                for (int p = 0; p < y.length; p++) {
//                    String list = y[p];
//                    if (list.equalsIgnoreCase("ace")) {
//                        names += "Ace ";
//                    } else if (list.equalsIgnoreCase("king")) {
//                        names += "King ";
//                    } else if (list.equalsIgnoreCase("queen")) {
//                        names += "Queen ";
//                    } else if (list.equalsIgnoreCase("jack")) {
//                        names += "Jack ";
//                    }
//                }
//                String[] num = numbers.split(" ");
//                String[] name = names.split(" ");
//
//                poker = new PlainPoker(num, name, combined, winning);
//                poker.determineHandType();
//                poker.handType(poker.determineHandType());
//                hands.add(poker);
//
//                System.out.println(poker.getCardValue());
//                poker.orderCardsNum();
////                System.out.println(Arrays.toString(poker.getCardNum()));
////                System.out.println(Arrays.toString(poker.orderCardsString()));
//                System.out.println(Arrays.toString(poker.combineCards()));
//                Collections.sort(hands, (h1, h2) -> h1.compareTo(h2));
//
//            }
//
//            Collections.sort(hands, (h1, h2) -> h1.compareTo(h2));
//            for (int i = 0; i < hands.size(); i++) {
//                hands.get(i).setRank(i + 1);
//            }
//
//            poker = hands.get(hands.size() - 1);
//            System.out.println(poker.toString());
//        }
//        catch (FileNotFoundException fe) {
//            System.out.println("File was not found");
//            System.exit(1);
//        }
//
//    }
//
//
//}



import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        File f = new File("src/txt");
        List<PlainPoker> hands = new ArrayList<>();

        try {
            Scanner s = new Scanner(f);
            StringBuilder fileData = new StringBuilder();
            while (s.hasNextLine()) {
                fileData.append(s.nextLine()).append("\n");
            }

            String[] fileArray = fileData.toString().split("\n");
            PlainPoker poker = null;

            for (String line : fileArray) {
                String[] parts = line.split("\\|");
                if (parts.length != 2) {
                    continue;
                }

                String[] cards = parts[0].split(",");
                String bidValue = parts[1].trim();

                StringBuilder numbers = new StringBuilder();
                StringBuilder names = new StringBuilder();

                for (String card : cards) {
                    card = card.trim();
                    try {
                        int num = Integer.parseInt(card);
                        numbers.append(num).append(" ");
                    } catch (NumberFormatException e) {
                        if (card.equalsIgnoreCase("ace") ||
                                card.equalsIgnoreCase("king") ||
                                card.equalsIgnoreCase("queen") ||
                                card.equalsIgnoreCase("jack")) {
                            names.append(card).append(" ");
                        }
                    }
                }

                String[] num = numbers.toString().trim().split(" ");
                String[] name = names.toString().trim().split(" ");

                poker = new PlainPoker(num, name, cards, bidValue);
                poker.determineHandType();
                poker.handType(poker.determineHandType());
                hands.add(poker);
            }

            Collections.sort(hands, (h1, h2) -> h1.compareTo(h2));
            for (int i = 0; i < hands.size(); i++) {
                hands.get(i).setRank(i + 1);
            }

            if (!hands.isEmpty()) {
                System.out.println(hands.get(hands.size() - 1).toString());
            }

        } catch (FileNotFoundException fe) {
            System.out.println("File was not found");
            System.exit(1);
        }
    }
}

