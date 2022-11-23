public class Inversion {

    // Naive sorting: brute force
    static double [] testNums1 = {3,2,1};
    static double [] testNums2 = {5,1,4,2,3,6,7,9,8};

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
}
