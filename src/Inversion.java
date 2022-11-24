import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Inversion {

    // Naive sorting: brute force
    static double [] testNums1 = {3,2,1};
    static double [] testNums2 = {5,1,4,2,3,6,7,9,8};
    static double [] testNums3 = {8,6,7,5,1,3,2,4};
    static double [] testNums4 = {4,6,7,5,1,3,2,8};

    static ArrayList<Double> testNums5 = new ArrayList<Double>(Arrays.asList(2.0,5.0,3.0,6.0,5.0,9.0));
    public static int easyinversioncount(double[] nums){
        int invertCounter = 0;
    // itterate throught the list using nested for loop
        for(int i = 0; i<nums.length;i++){
            for(int j = i+1; j<nums.length;j++){
                if(nums[i]>nums[j]){
                    invertCounter++;
                }
            }
        }
        System.out.println(" for your array, you have " + invertCounter +" inversions");
    // if we have an inversion, then add 1 to the counter

        return invertCounter;
    }
    public static int fastinversioncount(ArrayList<Double> nums){
        int counter = 0;
        mergeSort(nums);
        return counter;
    }
    public static ArrayList<Double> mergeSort(ArrayList<Double> nums){
        // if you just have one number in your nums list (or you have reached the deepest level)......
        //....just return your number
        if(nums.size() == 1){
            return nums;
        }
        // divide your nums arraylist into two parts
        // nums1: nums from indexes - to the floor of the middle (size/2)
        int mid = (int) Math.ceil(nums.size()/2);
        ArrayList<Double> nums1 = subArrayList(nums,0, mid);
        ArrayList<Double> nums2 = subArrayList(nums, mid,nums.size());

        //recursive call to keep dividing our array till we get sizes of 1
        nums1 = mergeSort(nums1);
        nums2 = mergeSort(nums2);

        // return a merges array everytime the funciton is called
        // this will eventually give you the whole list sorted
        return merge(nums1,nums2);
    }
    public static ArrayList<Double> merge(ArrayList<Double> numsA, ArrayList<Double> numsB){
       ArrayList<Double> mergedNums = new ArrayList<>();

       // while both arrays have elements
       while ((numsA.size() !=0)&&(numsB.size() !=0)){
           // if the first element in numsA is greater than the one in numsB...
           if(numsA.get(0)> numsB.get(0)){
               // put numsA's first element in our mergedNums array
               mergedNums.add(numsB.get(0));
               // (in the future, add the size of numsA to our counter, since that is how many inveresions numsB's first element has)

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
        // finally, return the mergedArray
        return mergedNums;

    }

    public static ArrayList<Double> subArrayList(ArrayList<Double> AList,int beginning, int end){
        ArrayList<Double> subAList = new ArrayList<>();
        for (int i = beginning; i<end;i++){
            subAList.add(AList.get(i));
        }
        return subAList;

    }

}
