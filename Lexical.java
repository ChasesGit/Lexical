//Chase Hunter
//Assignment1 : Simple Lexicon
//Class: 530
//Date: 2/15/2022
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;

public class Lexical {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Please input a file name: ");
        int i;
        //the initialization of character and word to store
        String character = "";
        String word = "";
        List<String> list = new ArrayList<>();
        int Counter = 0;
        int LineCount =1;
        String term = "";
        try {
            FileReader file = new FileReader(scan.next());
            //read the file
            while ((i= file.read())!= -1) {
               //the condition for word to see if it's a digit or letter
                if(Character.isDigit((char)i)|| Character.isAlphabetic((char)i)) {
                    word += (char) i;
                    Counter++;
                }

                else if (i == '.') {
                    if (!word.matches("([a-zA-Z])")) {
                        word += (char) i;
                        Counter++;
                    }
                    else {
                            word += (char) i;
                            //adds the error word to the list
                            list.add("Line" + LineCount+": " + Counter +" " + term + " " + word);
                            word = "";
                            Counter++;
                    }
                }
                // more conditionals to check if the string is constant
                else if ((char) i == '"') {
                    word += (char) i;
                    Counter++;
                }

                else {

                    if (word != "") {
                        //where I define what the words should be viewed as
                        if (Character.isAlphabetic(word.charAt(0)) && !word.contains("int") && !word.contains("double") && !word.contains("String")) {
                            term = "identifier:";
                        } else if (word.matches(".*\\d.*") && !word.contains(".")) {
                            term = "int constant:";
                        } else if (word.matches(".*\\d.*") && word.contains(".")) {
                            term = "double constant:";
                        } else if (word.equals("int") || word.equals("double") || word.equals("String")) {
                            term = "keyword:";
                        } else if (word.startsWith("\"") && word.endsWith("\"")) {
                            term = "String Constant:";
                        }
                        if (word.length() != 1) {
                            list.add("Line" + LineCount + ": " + (Counter - (word.length() - 1)) + " " + term + " " + word);
                            word = "";
                        } else {
                            list.add("Line" + LineCount + ": " + Counter + " " + term + " " + word);
                            word = "";
                        }
                    }
                        //spaces add to the count
                        if (i == ' ') {
                            Counter++;
                        }
                        //checks to see if it's not a letter or digit
                        else if (!Character.isDigit((char) i) && !Character.isAlphabetic((char) i) && i >= 33 && i <= 126) {

                            character += (char) i;
                            Counter++;
                            //condition for setting the operator given the parameters in the assignment
                            if (character.equals("=") || character.equals("(") || character.equals(")") || character.equals("+")
                                    || character.equals("-") || character.equals("*") || character.equals("/") || character.equals(",") || character.equals(";")) {
                                term = "operator:";
                            } else {
                                term = "error:";
                            }//Add the character string to the list
                            list.add("Line" + LineCount + ": " + Counter + " " + term + " " + character);
                            //sets term back to error for the default so if no else condition catches it defaults to error
                            term = "error:";
                            character = "";
                        }


                }//Counting the lines in the text file and resetting the counter each time
                if (i == '\n') {
                    LineCount += 1;
                    Counter = 0;
                }

            } // end of while loop

            if(word != "") {
                //Add words that are longer than length 1 to the list
                if (word.length() > 1) {
                    list.add("Line" + LineCount + ": "+ (Counter - (word.length()-1)) + " " + term + " " + word);
                }
                //Add words that aren't longer than length 1 to the list along with the term and Line count
                else {
                    list.add("Line" + LineCount + ": "+ (Counter) + " " +term + " " + word);
                }
            }
        //prints every element from the list
        for(String words: list) {
            System.out.println(words);
        }
        } //end of try

        catch (IOException e) {
            e.printStackTrace();
        }

    } //end of main
} // end of class