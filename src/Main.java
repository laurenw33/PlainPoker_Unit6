import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        // step 1: create a file object
        File f = new File("src/txt");


        String fileData = "";
        try {
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                String currentLine = s.nextLine();
                fileData += currentLine + "\n";
            }

            // a String array where every item in the array is a line from the file
            String[] fileArray = fileData.split("\n");

            String numbers;
            for (int i = 0; i < fileArray.length; i++) {
                String stringNumbers = fileArray[i];
                String[] x = stringNumbers.split("\\|");
                String[] y = x[0].split(",");
                System.out.println(Arrays.toString(y));

                numbers = "";
                for (int p = 0; p < y.length; p++){
                    String list = y[p];
                    if (list.equals("1")){
                        numbers += "1" + " ";
                    }
                    else if (list.equals("2")){
                        numbers += "2" + " ";
                    }
                    else if (list.equals("3")){
                        numbers += "3" + " ";
                    }
                    else if (list.equals("4")){
                        numbers += "4" + " ";
                    }
                    else if (list.equals("5")){
                        numbers += "5" + " ";
                    }
                    else if (list.equals("6")){
                        numbers += "6" + " ";
                    }
                    else if (list.equals("7")){
                        numbers += "7" + " ";
                    }
                    else if (list.equals("8")){
                        numbers += "8" + " ";
                    }
                    else if (list.equals("9")){
                        numbers += "9" + " ";
                    }
                    else if (list.equals("10")){
                        numbers += "10" + " ";
                    }
                }
                System.out.println(numbers);
            }
        }
        catch (FileNotFoundException fe) {
            System.out.println("File was not found");
            System.exit(1);
        }

    }


}

