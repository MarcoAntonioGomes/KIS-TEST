import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class main {


    public static void main(String[] args) {

        System.out.println("-------------- TESTS--------------------");
        //TDD - Tests
        testIfWordHasNoLetters();
        testMustTranslateSingleWord();
        testMustTranslateTwoWords();
        testMustTranslateSentenceWithPunctuation();
        testMustTranslateSentenceWithNunber();
        testMustTranslateSentenceWithDifferentPunctuation();

        System.out.println("---------------------------------------");

        String sentece = "Shoes";
        System.out.println("Translation of " + sentece + ": "+translateSentence(sentece));

    }

    /**
     * Method responsible for translate a group of words or a char sequence.
     * The method split sequence in a separate words and translate word by word
     * If a specific word has punctuation, the method remove that punctuation, to preserve it and add it after the word process translation
     * If a word has no letters, the method just add the word and continue the process
     *
     * @param sentece
     * @return
     */
    public static String translateSentence(String sentece) {
        List<String> words = List.of(sentece.split(" "));
        String sentenceTranslated = "";

        for (String word : words) {

            if(containsPunctuation(word)){
                String punctuation = word.substring(word.length() -1, word.length());
                sentenceTranslated = sentenceTranslated + " " +translateASingleWord(word.substring(0, word.length() -1)) + punctuation;
            }
            else if (!isAlpha(word)) {
                sentenceTranslated = sentenceTranslated + " " + word;
            }
            else {
                sentenceTranslated = sentenceTranslated + " " + translateASingleWord(word);
            }
        }

        return sentenceTranslated.trim();
    }


    /**
     * This is the core method responsible for translate a sigle word
     * that check if letter by letter to identify if the letter is vowel or no and create the prefix and stem.
     * If the word has no consonants the method will let prefix empty and stem be the word plus the word "yay".
     * If the word has consonants the method will apply the rule stem + prefix + "ay"
     *
     * @param word
     * @return
     */
    public static String translateASingleWord(String word) {

        List<String> vowels = new ArrayList<>(Arrays.asList("a", "o", "e", "i", "u", "y", "A", "O", "E", "I", "U", "Y"));
        String prefix = "";
        String stem = "";
        String wordTranslated = "";
        boolean noConsonants =  hasNoConsonants(word, vowels);

       if(!noConsonants){

           for (int i = 0; i < word.length(); i++) {
               String onlyChar = String.valueOf(word.charAt(i));

               if (!vowels.contains(onlyChar)) {
                   prefix = prefix + word.charAt(i);
               } else {
                   stem = word.substring(i, word.length());
                   break;
               }
           }
           prefix = prefix.toLowerCase();
           stem = stem.toLowerCase();
           wordTranslated = stem + prefix + "ay";

       }
       else{
           stem = word;
           wordTranslated = stem + "yay";
       }

        wordTranslated = capitalizeTheTranslatedSenteceIfThereIsInitialCapitalLetter(word, wordTranslated);
        return wordTranslated;
    }

    /**
     * Method to check if the word has no consonants
     *
     * @param word
     * @param vowels
     * @return
     */
    private static boolean hasNoConsonants(String word, List<String> vowels) {

        for (int i = 0; i < word.length(); i++) {
            String onlyChar = String.valueOf(word.charAt(i));
           if(vowels.contains(onlyChar)){
               return false;
            }
        }
        return true;
    }


    /**
     * This method capitalize the translated word if your respective word begin with a capital letter
     *
     * @param sentece
     * @param senteceTranslated
     * @return
     */

    private static String capitalizeTheTranslatedSenteceIfThereIsInitialCapitalLetter(String sentece, String senteceTranslated) {

        if (Character.isUpperCase(sentece.charAt(0))) {
            senteceTranslated = capitalize(senteceTranslated);
        }
        return senteceTranslated;
    }

    /**
     * Check if the word has letters or space
     * @param word
     * @return
     */
    public static boolean isAlpha(String word) {
        return word.matches("^[a-zA-Z ]*$");
    }

    /**
     * Transform the initial letter of any word in Upper Case
     * @param word
     * @return
     */
    public static String capitalize(String word) {
        if (word == null || word.length() <= 1) return word;
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }

    /**
     * Check if the word has punctuation
     * @param word
     * @return
     */
    public static boolean containsPunctuation(String word) {
        Pattern p = Pattern.compile("\\p{Punct}");
        Matcher m = p.matcher(word);
        return m.find();
    }

    public static void testIfWordHasNoLetters() {

        System.out.println("Has no Letters");
        if (!isAlpha("14")) {
            System.out.println("TEST PASSED!");
        } else {
            System.out.println("TEST FAILED!");
        }

    }



    public static void testMustTranslateSingleWord(){

        System.out.println("Must translate a single word");
        if (translateSentence("Stop").equals("Opstay")) {
            System.out.println("TEST PASSED!");
        } else {
            System.out.println("TEST FAILED!");
        }

    }

    public static void testMustTranslateTwoWords(){

        System.out.println("Must translate two words");
        if (translateSentence("No littering").equals("Onay itteringlay")) {
            System.out.println("TEST PASSED!");
        } else {
            System.out.println("TEST FAILED!");
        }

    }

    public static void testMustTranslateSentenceWithPunctuation(){

        System.out.println("Must translate sentence with punctuation");
        if (translateSentence("No shirts, no shoes, no service").equals("Onay irtsshay, onay oesshay, onay ervicesay")) {
            System.out.println("TEST PASSED!");
        } else {
            System.out.println("TEST FAILED!");
        }

    }


    public static void testMustTranslateSentenceWithNunber(){

        System.out.println("Must translate sentence with number");
        if (translateSentence("No persons under 14 admitted").equals("Onay ersonspay underay 14 admitteday")) {
            System.out.println("TEST PASSED!");
        } else {
            System.out.println("TEST FAILED!");
        }

    }

    public static void testMustTranslateSentenceWithDifferentPunctuation(){

        System.out.println("Must translate sentence with different punctuation");
        if (translateSentence("Hey buddy, get away from my car!").equals("Eyhay uddybay, etgay awayay omfray ymay arcay!")) {
            System.out.println("TEST PASSED!");
        } else {
            System.out.println("TEST FAILED!");
        }

    }


}
