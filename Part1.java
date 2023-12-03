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
                ArrayList<String> characters = new ArrayList<>();
                try{
                    //If number
                    int number = Integer.parseInt(String.valueOf(input.get(row).charAt(character)));
                }
                catch (Exception e){
                    Pattern special = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
                    String temp = String.valueOf(input.get(row).charAt(character));
                    Matcher hasSpecial = special.matcher(temp);

                    //add character, row and column index
                    if(hasSpecial.matches()){
                        //System.out.println("found symbol: " + input.get(row).charAt(character));
                        characters.add(temp);
                        characters.add(String.valueOf(row));
                        characters.add(String.valueOf(character));
                        numberIndex.add(characters);
                    }
                }
            }
        }
    }
}
