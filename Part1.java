import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {
    ArrayList<String> input = new ArrayList<String>();

    public Part1(){
        readFile();
        checker();
    }
    private void readFile(){
        try{
            File file1 = new File("input.txt");
            Scanner sc1 = new Scanner(file1);
            while(sc1.hasNextLine()){
                input.add(sc1.nextLine());
            }
        }
        catch (Exception e){
            System.err.println("File not found");
        }
    }

    private void checker(){
        //array for number/symbol and x/y coords
        ArrayList<ArrayList<String>> numberIndex = new ArrayList<>();

        for(int row = 0; row < input.size(); row++){

            for(int character = 0 ; character < input.get(row).length(); character++){
                //ArrayList<String> characters = new ArrayList<>();
                ArrayList<Integer> values = new ArrayList<>();

                try{
                    //If number
                    int number = Integer.parseInt(String.valueOf(input.get(row).charAt(character)));

                    Pattern special = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
                    String charPlus1 = String.valueOf(input.get(row).charAt(character+1));
                    Matcher hasSpecialCharPlus1 = special.matcher(charPlus1);
                    String charMinus1 = String.valueOf(input.get(row).charAt(character-1));
                    Matcher hasSpecialCharMinus1 = special.matcher(charMinus1);
                    String rowPlus1 = String.valueOf(input.get(row+1).charAt(character));
                    Matcher hasSpecialRowPlus1 = special.matcher(rowPlus1);
                    String rowMinus1 = String.valueOf(input.get(row-1).charAt(character));
                    Matcher hasSpecialRowMinus1 = special.matcher(rowMinus1);
                    String diagLeftUp = String.valueOf(input.get(row-1).charAt(character-1));
                    Matcher hasSpecialDiagLeftUp = special.matcher(diagLeftUp);
                    String diagLeftDown = String.valueOf(input.get(row+1).charAt(character-1));
                    Matcher hasSpecialDiagLeftDown = special.matcher(diagLeftDown);
                    String diagRightUp = String.valueOf(input.get(row-1).charAt(character+1));
                    Matcher hasSpecialDiagRightUp = special.matcher(diagRightUp);
                    String diagRightDown = String.valueOf(input.get(row+1).charAt(character+1));
                    Matcher hasSpecialDiagRightDown = special.matcher(diagRightDown);

                    //If touching a special character
                    if(hasSpecialRowPlus1.find() || hasSpecialRowMinus1.find() || hasSpecialCharMinus1.find() || hasSpecialCharMinus1.find()
                    || hasSpecialDiagRightUp.find() || hasSpecialDiagRightDown.find() || hasSpecialDiagLeftDown.find() || hasSpecialDiagLeftUp.find()) {
                        String numberStr = String.valueOf(number);

                        //If previous not a number
                        try {
                            //floored: if prev not added, removes anyway
                            int charmin1 = Integer.parseInt(charMinus1);
                            int prev = values.remove(values.size()-1);
                            values.add(Integer.parseInt(String.valueOf(prev) + String.valueOf(charmin1)));
                        }
                        catch(Exception e) {
                            try {
                                for (int index = character + 1; index < input.get(row).length(); index++) {
                                    numberStr += String.valueOf(Integer.parseInt(input.get(index)));
                                }
                            } catch (Exception f) {
                            }
                            values.add(Integer.parseInt(numberStr));
                        }
                    }
                }
                catch (Exception e) {

//                    Pattern special = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
//                    String temp = String.valueOf(input.get(row).charAt(character));
//                    Matcher hasSpecial = special.matcher(temp);
//
//                    //add character, row and column index
//                    if(hasSpecial.matches()){
//                        //System.out.println("found symbol: " + input.get(row).charAt(character));
//                        characters.add(temp);
//                        characters.add(String.valueOf(row));
//                        characters.add(String.valueOf(character));
//                        numberIndex.add(characters);
//                    }
                }
            }
        }
    }
}
