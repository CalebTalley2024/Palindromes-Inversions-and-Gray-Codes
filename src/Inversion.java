import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Inversion {

    // Naive sorting: brute force
    static ArrayList<Integer> testNums1 = new ArrayList<>(Arrays.asList(3,2,1));
    static ArrayList<Integer> testNums2 = new ArrayList<Integer>(Arrays.asList(8,8,6,7,5,1,3,2,4));

    // uses 2 for loops to find # of inversions

    public static int easyinversioncount(ArrayList<Integer> nums){
        int invertCounter = 0;
        // itterate throught the list using nested for loop
        for(int i = 0; i<nums.size();i++){
            for(int j = i+1; j<nums.size();j++){
                if(nums.get(i)>nums.get(j)){
                    invertCounter++;
                }
            }
        }
        System.out.println("Easy Count: for your array, you have " + invertCounter +" inversions");
        // if we have an inversion, then add 1 to the counter
        return invertCounter;
    }
    //
    // uses Merge sort divide and conquer method to find # of inversions
    public static int fastinversioncount(ArrayList<Integer> nums){

        int counter = 0;
        ArrayList<Object> numsWithCounter = new ArrayList<>();
        numsWithCounter.add(nums);
        numsWithCounter.add(counter);

        // use the merge sort algorithm to get a sorted list and the number of inversions
        numsWithCounter = mergeSort(numsWithCounter);

        counter = (int) numsWithCounter.get(1);
        System.out.println("Fast Count: for your array, you have "+counter+" inversions");
        return counter;
    }

    public static ArrayList<Object> mergeSort(ArrayList<Object> numsWithCounter){
        ArrayList<Integer> nums = (ArrayList<Integer>) numsWithCounter.get(0);
        int counter = (int) numsWithCounter.get(1);

        // if you just have one number in your nums list (or you have reached the deepest level)......
        //....just return your number

        if(nums.size() == 1){
            return numsWithCounter;
        }

        // divide your nums arraylist into two parts
        // nums1: nums from indexes - to the floor of the middle (size/2)
        int mid = (int) Math.ceil(nums.size()/2);
        ArrayList<Integer> nums1 = subArrayList(nums,0, mid);

        ArrayList<Integer> nums2 = subArrayList(nums, mid,nums.size());


        ArrayList<Object> numsWithCounter1 = new ArrayList<>();
        numsWithCounter1.add(nums1);
        numsWithCounter1.add(counter);
        ArrayList<Object> numsWithCounter2 = new ArrayList<>();
        numsWithCounter2.add(nums2);
        numsWithCounter2.add(counter);

        //recursive call to keep dividing our array till we get sizes of 1
        numsWithCounter1 = mergeSort(numsWithCounter1);
        numsWithCounter2 = mergeSort(numsWithCounter2);



        // return a merges array everytime the funciton is called
        // this will eventually give you the whole list sorted
        return merge(numsWithCounter1,numsWithCounter2);
    }

    public static ArrayList<Object> merge(ArrayList<Object> numsWithCounterA, ArrayList<Object> numsWithCounterB){
        // get the arraylist
        // I used A and B instead of 1 and 2 since A and B was easier to keep track of for me in this function
        ArrayList<Integer> numsA = (ArrayList<Integer>) numsWithCounterA.get(0);
        ArrayList<Integer> numsB = (ArrayList<Integer>) numsWithCounterB.get(0);
        //// new variables
//        ArrayList<Object> mergedNumsWithCounter = new ArrayList<>();
        ArrayList<Integer> mergedNums = new ArrayList<>();
        int newCounter = 0;
        // while both arrays have elements
        while ((numsA.size() !=0)&&(numsB.size() !=0)){
            // if the first element in numsA is greater than the one in numsB...
            if(numsA.get(0)> numsB.get(0)){
                // put numsA's first element in our mergedNums array
                mergedNums.add(numsB.get(0));
                //  add the size of numsA to our counter, since that is how many inveresions numsB's first element has)
                newCounter += numsA.size();
                // remove the element we placed in mergedNums from numsB
                numsB.remove(0);

            }
            // when numsA[0] is smaller than numsB[0], add the num to merged array and remove from numsA
            else{
                mergedNums.add(numsA.get(0));
                numsA.remove(0);

            }
        }
        // at this point, either numsA or numsB will still have left over numbers
        // we can just add these numbers in since we know that they will already be sorted
        while ((numsA.size() !=0)){
            // in loop, keep adding the first element till you dont have any anymore
            mergedNums.add(numsA.get(0));
            numsA.remove(0);
        }
        // same logic applies if numsB is the list that still contains numbers
        while((numsB.size() !=0)){
            mergedNums.add(numsB.get(0));
            numsB.remove(0);;

        }
        // add the counters from lists A and B to newCounter
        int counterA = (int) numsWithCounterA.get(1);
        int counterB = (int) numsWithCounterB.get(1);
        newCounter +=  (counterA + counterB);
        // make new ArrayList Object and place your new nums list and counter inside
        ArrayList<Object> mergedNumsWithCounter = new ArrayList<>();
        mergedNumsWithCounter.add(mergedNums);
        mergedNumsWithCounter.add(newCounter);

        // finally, return the mergedArray
        return mergedNumsWithCounter;

    }

    public static ArrayList<Integer> subArrayList(ArrayList<Integer> AList,int beginning, int end){
        ArrayList<Integer> subAList = new ArrayList<>();
        for (int i = beginning; i<end;i++){
            subAList.add(AList.get(i));
        }
        return subAList;

    }

}
