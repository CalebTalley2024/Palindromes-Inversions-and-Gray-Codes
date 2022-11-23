import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class GreyCode {


    //Generates recursively the binary reflected Gray code of order n
    //Input: A positive integer n
    //Output: A list of all bit strings of length n composing the Gray code
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
            System.out.println("This is L2: " + L2);
//            L1.foreach
            // add both lists to L
            L = L1;
            L.addAll(L2);

        }return L;

    }

    public static ArrayList<String> BaRGCNmes (ArrayList<String> sequence){
        // make an array for names
        // compare the first and last elements
        // see where the difference is
        // the place of the differnce deteremines the String you put in your array
        // return your number
    }
//    public static ArrayList<String> BaRGCNmesGreaterThanFour (ArrayList<String> sequence)






}
