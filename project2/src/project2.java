public class project2 {
    public static int findMaxSumSubarray(int[] arr) {
        if (arr==null||arr.length==0) {
            throw new IllegalArgumentException("Array cant be null or empty");
        }

        int localMaximum = arr[0];
        int globalMaximum = arr[0];

        for (int i=1;i<arr.length;i++) {
            localMaximum= Math.max(localMaximum+arr[i],arr[i]);
            globalMaximum= Math.max(globalMaximum,localMaximum);
        }

        return globalMaximum;
    }

    public static void main(String[] args) {
        int[] arr = {-2,1,-3,4,-1,2,1,-5,4};
        int maxSum = findMaxSumSubarray(arr);
        System.out.println("Maximum Sum Subarray: "+maxSum);
    }
}

//xrisimopioume mono 2 metavlites (localMax,globalMax) gia ka8e stixio tou arr epilegoume
//an 8a 3ekinisoume kainourgio ipopinaka apo to arr i 8a sinexisoume apo ton proigoumeno
//an i localMax einai megaliteri apo tin globalMax kanoume globalMax=max(gkibakNax,localMax)
// kai i globalMax sto telos 8a exei to a8risma