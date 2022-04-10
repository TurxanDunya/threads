package main.paralellization;

class MergeSort {

    private final int[] nums;
    private final int[] tempArray;

    public MergeSort(int[] nums) {
        this.nums = nums;
        this.tempArray = new int[nums.length];
    }

    public void parallelMergeSort(int low, int high, int numOfThreads) {
        if (numOfThreads <= 1) {
            mergeSort(low, high); //Then we will use sequential sort
            return;
        }

        int middleIndex = (low + high) / 2;

        Thread leftSorter = createThread(low, middleIndex, numOfThreads);
        Thread rightSorter = createThread(middleIndex + 1, high, numOfThreads);

        leftSorter.start();
        rightSorter.start();

        try {
            leftSorter.join();
            rightSorter.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        merge(low, middleIndex, high);
    }

    public Thread createThread(int low, int high, int numOfThreads) {
        return new Thread() {
            @Override
            public void run() {
                parallelMergeSort(low, high, numOfThreads / 2);
            }
        };
    }

    public void mergeSort(int low, int high) {
        if (low >= high) {
            return;
        }

        int middle = (low + high) / 2;

        mergeSort(low, middle);
        mergeSort(middle + 1, high);
        merge(low, middle, high);
    }

    public void showResult() {
        for (int i = 0; i < nums.length; ++i) {
            System.out.println(nums[i] + " ");
        }
    }

    private void merge(int low, int middle, int high) {
        for (int i = low; i <= high; i++) {
            tempArray[i] = nums[i];
        }

        int i = low;
        int j = middle + 1;
        int k = low;

        //We consider the temp array and copy the items into the nums
        while (i <= middle && j <= high) {
            if (tempArray[i] < tempArray[j]) {
                nums[k] = tempArray[j];
                ++i;
            } else {
                nums[k] = tempArray[j];
                ++j;
            }

            ++k;
        }

        //We have to copy the items from the left sub-array (If there are any)
        while (i <= middle) {
            nums[k] = tempArray[i];
            ++k;
            ++i;
        }

        //We have to copy yhe items from the right sub-array (If there are any)
        while (j <= high) {
            nums[k] = tempArray[j];
            ++k;
            ++j;
        }
    }
}

public class ParallelMergeSort {
    public static void main(String[] args) {
        int[] nums = {5, -1, 0, 7, 2, 3, 2, 1, 0, 1, 2};

        MergeSort sort = new MergeSort(nums);
//        sort.sort();
//        sort.showArray;
    }
}