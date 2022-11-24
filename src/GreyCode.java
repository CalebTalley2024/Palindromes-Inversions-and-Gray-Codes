import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Stream;

public class GreyCode {

    //graycodesarefun:
    //Input: A positive integer n
    // Output: Table
    public static void graycodesarefun(int order){
        ArrayList<Integer> indexes = Indexes(order);
        ArrayList<String> grayCodes = BRGC(order);
        ArrayList<String> childrenInPhotos = childrenInPhoto(grayCodes);
        ArrayList<String> actions = Actions(grayCodes);
        // sout the labels (use \t\t for 10 spaces)
        System.out.println("Index \t\t Gray Code\t\t Child(ren) in Photo\t\t\t\t\t\t\t\t\t\t Action");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------");
        for (int i = 0; i<Math.pow(2,order);i++){
            int index = indexes.get(i);
            String aGrayCode = grayCodes.get(i);
            String childrenInPhoto = childrenInPhotos.get(i);
            String anAction = actions.get(i);
            System.out.println(index+ "\t\t\t\t"+ aGrayCode + "\t\t\t\t "+ childrenInPhoto + "\t\t\t\t\t\t\t\t\t\t\t\t"+anAction);
        }

    }
    public static ArrayList<String> BRGC(int n){
        ArrayList<String> L = new ArrayList<>();
        if (n == 1){
            L = new ArrayList<>(Arrays.asList("0","1"));
        }
        else{
            ArrayList<String> L1 = BRGC(n-1);
//            ArrayList<String> L2 = (ArrayList<String>) L1;
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
    public static ArrayList<String> Actions(ArrayList<String> sequence){
        ArrayList<String> getNames = getNames(sequence);
        ArrayList<String> getActions = getActions(sequence);
        // add names and actions into one list
        ArrayList<String> Actions = new ArrayList<>();
        for (int i = 0; i< getNames.size();i++){
            String anAction = getNames.get(i)+" "+getActions.get(i);
            Actions.add(anAction);

        }

        return Actions;
    }

    public static ArrayList<String> childrenInPhoto(ArrayList<String> sequence){
        // get the array form of all of the arrays of children
        ArrayList<ArrayList<String>> childrenInPhotoArrays = childrenInPhotoArrays( sequence);
        ArrayList<String> groups = new ArrayList<>();
        // do "Name" + "+" for all but the last name, then after the for loop, add the last name
        groups.add("Nobody");

        for (int i = 1; i<childrenInPhotoArrays.size();i++){
            ArrayList<String>aChildArray = childrenInPhotoArrays.get(i);
            // reverse the list order to match order in the assignment pdf
            Collections.reverse(aChildArray);
//            System.out.println(aChildArray);
            // aGroup: group for each grey code number
            String aGroup = "";
            // iterating through names
            for(int j = 0; j<aChildArray.size();j++){
                aGroup+=aChildArray.get(j)+" & ";
//                System.out.println(aGroup);
            }
            // get rid of the last "& "
            aGroup = aGroup.substring(0,aGroup.length()-2);
            groups.add(aGroup);
        }
        return groups;

    }
    public static ArrayList<Integer> Indexes(int order){
        int largestIndex = (int)Math.pow(2,order);
        ArrayList<Integer> Indexes = new ArrayList<>();
        for (int i = 0; i< largestIndex;i++){
            Indexes.add(i);
        }return Indexes;

    }

    /////// Helpers
    public static ArrayList<ArrayList<String>> childrenInPhotoArrays(ArrayList<String> sequence){
        ArrayList<ArrayList<String>> childrenInPhoto = new ArrayList<>();
        // get all indexes, for each bitSting, that contain a 1
        for (int i = 0; i<sequence.size();i++){
            // get the indexies that contian a 1
        ArrayList<Integer> numberForKids =  getChildNumbers(sequence.get(i));
//            System.out.println(numberForKids);
        // make a list for the kids in each bitString
        ArrayList<String> kidsPerIndex = new ArrayList<>();
        // put names in kidsPerIndex using the indexes that have kids
            for(int j  = 0; j<numberForKids.size();j++){
                kidsPerIndex.add(nameDecider((numberForKids.get(j))));
                // reverse the list to fit the table format
            }
            childrenInPhoto.add(kidsPerIndex);
        }

        return childrenInPhoto;
    }
// gets how many children there are using a bitString
    public static ArrayList<Integer> getChildNumbers(String bitString){
        ArrayList<Integer>  ChildIndexes = new ArrayList<>();
        for (int i = bitString.length()-1;i>=0;i--){
            if(bitString.charAt(i) == '1'){
                int index = bitString.length() - i;
                ChildIndexes.add(index);
            }
        }return ChildIndexes;
    }

    // takes in a BRGC sequence, returns a list of names that corresponds to the BRGC sequence
    public static ArrayList<String> getNames (ArrayList<String> sequence){
        ArrayList<String> Names = new ArrayList<String>();

        Names.add("Do");//////////////////place holder

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
    public static ArrayList<String> getActions(ArrayList<String> sequence){
        ArrayList<String> Actions = new ArrayList<String>();

        Actions.add("Nothing");//////////////////place holder

        for (int i = 0; i<sequence.size() - 1;i++){
            // compare the first and last elements23
            // see where the difference is
            int bitDiff =  getBStringDiff(sequence.get(i),sequence.get(i+1));
            String anAction = actionDecider(bitDiff,sequence.get(i),sequence.get(i+1));
            // the place of the differnce deteremines the String you put in your array
            Actions.add(anAction);
        }return Actions;

    }
    // Action: combines names and actions

    public static int getBStringDiff (String bit1,String bit2){
        int num = 0;

        for (int i  = bit1.length()-1; i>=0; i--){
            if(bit1.charAt(i) != bit2.charAt(i)){
                // this will make the count be from right to left (43210)instead of left right to right (0123)
                num = bit1.length()-i;


            }
        }return num;
    }
    public static String nameDecider(int index){
        String name = "";
        // depending on the index, pick the name
        switch(index){
            case 1:
                name = "Alice";
                break;
            case 2:
                name = "Bob";
                break;
            case 3:
                name = "Chris";
                break;
            case 4:
                name = "Dylan";
                break;
            case 5:
                name = " Eve";
                break;
            case 6:
                name = "Felix";
                break;
            case 7:
                name = "Gerry";
                break;
            case 8:
                name = "Helen";
                break;
            case 9:
                name = " Ian";
            case 10:
                name = " Jane";
            case 11:
                name = " Karl";


        }return name;
    }
    public static String actionDecider(int bitDiff, String bit1,String bit2){
        String action = "";
        // get the index where there is a difference btw the strings
        int i = bit1.length()-bitDiff;
        // check if, at the index where there is a difference btw the two bitStrings...
        //.... if the first string at that index has a zero, we return "In",

        char zero = '0';
        if (bit1.charAt(i) == zero){
            action = "In";

        }
        //.... Otherwise(the second string has an index of zero),we can say out
        else{
            action = "Out";
        }

        return action;
    }


// make a hashmap with each key corresponding to a different column of the table






}
