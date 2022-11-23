import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class GreyCode {


    //Generates recursively the binary reflected Gray code of order n
    //Input: A positive integer n
    //Output: A list of all bit strings of length n composing the Gray code

//    public static void graycodesarefun();
    public static ArrayList<String> BRGC(int n){
        ArrayList<String> L = new ArrayList<>();
        if (n == 1){
            L = new ArrayList<>(Arrays.asList("0","1"));
        }
        else{
            ArrayList<String> L1 = BRGC(n-1);
//            ArrayList<String> L2 = (ArrayList<String>) L1; ////////////////Why do I need to make a copy??????
            ArrayList<String> L2 = (ArrayList<String>) L1.clone();
            // reverse order

            Collections.reverse(L2);

            // adds 0s and 1s to each bit string in L1 and L2 respectively\

            for( int i = 0; i< L1.size();i++) {
                L1.set(i,"0"+L1.get(i));
            }
            for( int i = 0; i< L2.size();i++) {
                L2.set(i,"1"+L2.get(i));
            }
//            System.out.println("This is L2: " + L2);
//            L1.foreach
            // add both lists to L
            L = L1;
            L.addAll(L2);

        }return L;

    }

    // takes in a BRGC sequence, returns a list of names that corresponds to the BRGC sequence
    public static ArrayList<String> BaRGCNmes (ArrayList<String> sequence){
        ArrayList<String> Names = new ArrayList<String>();

        Names.add("Nobody");//////////////////place holder

        for (int i = 0; i<sequence.size() - 1;i++){
            // compare the first and last elements23
            // see where the difference is
            int bitDiff =  getBStringDiff(sequence.get(i),sequence.get(i+1));
            String aName = nameDecider(bitDiff);
            // the place of the differnce deteremines the String you put in your array
            Names.add(aName);
        }return Names;
    }
    // helper: compares two strings and returns the index in which they differ
    public static int getBStringDiff (String bit1,String bit2){
        int num = 0;
        for (int i  = 0; i<bit1.length(); i++){
            if(bit1.charAt(i) != bit2.charAt(i)){
                num = i;
            }
        }return num;
    }
    ///// SO FAR THIS only works for n = 4
    public static String nameDecider(int index){
        String name = "";
        // depending on the index, pick the name
        switch(index){
            case 0:
                name = "Dylan";
                break;
            case 1:
                name = "Chris";
                break;
            case 2:
                name = "Bob";
                break;
            case 3:
                name = "Alice";
                break;

        }return name;

    }


// make a hashmap with each key corresponding to a different column of the table






}
