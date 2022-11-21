import java.util.Scanner;

public class Palindrome {



    public static boolean palindromecheck (){
        String sentence = getInputString();
        String word = makeStringBasic(sentence);
        boolean bool = true;
        char[] fWord = word.toCharArray();
        int maxIndex = fWord.length - 1;

        bool = isPalindrome(0,fWord,maxIndex,bool);
        System.out.println(bool);
        return bool;
    }
    public static boolean isPalindrome (int i, char[] fWord, int maxIndex,boolean bool ){
        if(i<Math.ceil(maxIndex/2)){

//        if(bool != false) {break;}
            if(fWord[i]==fWord[maxIndex - i]){
                bool =  isPalindrome ( i+1,  fWord, maxIndex,bool);
            }
            else{bool = false;}

        }
        return bool;

    }
    public static String getInputString(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Type a word or phrase so see if you have a palindrome.");
        String sentence = scan.nextLine();
        System.out.println("you have picked: "+ sentence);
        return sentence;
    }

    public static String makeStringBasic(String sentence){
        // "\\p{Punct}" represents any punctuation
        // get rid of punctuation
        String word = sentence.replaceAll("\\p{Punct}","");
        // get rid of all spaces
        word = word.replaceAll(" ","");
        // get rid of capitals
        word = word.toLowerCase();
//        System.out.println(word);
        return word;
    }
    public static boolean palindromecheckNoRecursion (String word){
        boolean same = false;
        char[] fWord = word.toCharArray();
        String bWord = "";
        int maxIndexWord = fWord.length - 1;

        // make our "word" string backwards

        for (int i = maxIndexWord; i>=0; i--){
            bWord = bWord.concat(String.valueOf(fWord[i]));
        }

        // check to see if our word is the same as our backward string
        if(word.equals(bWord)){same = true;}

        // make a string that is the backwards version of our word
        System.out.println(same);

        // recursively check each character

//        // iterate on both sides till you are at the halfway point of the char array
//        for (int i = 0; i<=Math.ceil(maxIndex/2); i++){
//            if(fWord[i]!=fWord[maxIndex - i]){
//                bool = false;
//                // stops checking the left does not mirror the right
//                break;
//            }
////            else{bool = true;}
//        }
        return same;
    }
}
