
/**
 * An improved implementation of the merge stort algorithm that
 * only copies half of the pre-sorted array into a temporary array
 * which causes overhead storage to go from n to n/2.
 * 
 * This implementation switches to insertion sort once
 * an input size threshold is reached which was calculated using
 * the ProfileDriver class.
 * 
 * @author Griffin Moran
 * 
 * @version V1
 */

public class MergeSortImproved {
  static int initThreshold = 70;
  /**
   * Merge sort the provided array using an improved merge operation.
   */
  
  public static <T extends Comparable<T>> void mergeSortHalfSpace(T[] items) {
    // TODO
    @SuppressWarnings("unchecked")
    T[] temp = (T[]) new Comparable[(items.length / 2) + 1];
    mergeSortHalfSpace(items, temp, 0, items.length - 1);
  }
  /**
   * Merge sort the provided array using an improved merge operation
   * that only uses half size temp arrays.
   */
  
  public static <T extends Comparable<T>> void mergeSortHalfSpace(
      T[] items, T[] temp, int start, int end) {  
    if (start >= end) { // List has one record
      return;
    }
    int mid = (start + end) / 2; // Select midpoint
      
    mergeSortHalfSpace(items, temp, start, mid);
    mergeSortHalfSpace(items, temp, mid + 1, end);
      
    merge(items, temp, start, mid, end);
  }


  /**
   * Merge sort the provided array by using an improved merge operation and
   * switching to insertion sort for small sub-arrays.
   */
  
  public static <T extends Comparable<T>> void mergeSortAdaptive(T[] items) { //uncomment if needed
    //@SuppressWarnings("unchecked")
    //T[] temp = (T[]) new Comparable[(items.length / 2) + 1];
    //mergeSubsortAdaptive(items, temp, 0, items.length - 1); 
    mergeSubsortAdaptive(items, 0, items.length - 1);
  }
  
  /**
   * Merge sort the provided sub-array using our improved merge sort. This is the
   * fallback method used by introspective sort.
   */
  public static <T extends Comparable<T>> void mergeSubsortAdaptive(T[] items, int start, int end) {
    @SuppressWarnings("unchecked")
    T[] temp = (T[]) new Comparable[(items.length / 2) + 1];
    
    if (end - start < 85) {
      BasicSorts.insertionSubsort(items, start, end);
    } else {
      int mid = (start + end) / 2; // Select midpoint
      
      mergeSubsortAdaptive(items, temp, start, mid);
      mergeSubsortAdaptive(items, temp, mid + 1, end);
      
      merge(items, temp, start, mid, end);
    }

  }

  /**
   * Merge sort the provided sub-array using our improved merge sort. This is the
   * fallback method used by introspective sort.
   */
  
  public static <T extends Comparable<T>> void mergeSubsortAdaptive(//currently not being used
      T[] items, T[] temp, int start, int end) {
    
    if (end - start < 90) {
      BasicSorts.insertionSubsort(items, start, end);
    } else {
      int mid = (start + end) / 2; // Select midpoint
      
      mergeSubsortAdaptive(items, temp, start, mid);
      mergeSubsortAdaptive(items, temp, mid + 1, end);
      
      merge(items, temp, start, mid, end);
    }
  }
  
  private static <T extends Comparable<T>> void merge(T[] items, T[] temp, int left, int mid,
      int right) {
    
    for (int i = 0; i <= mid - left; i++) {
      temp[i] = items[i + left];
    }
    
    int i1 = 0;
    int i2 = mid + 1;
    for (int cur = left; cur <= right; cur++) {
      if (i1 > mid - left) {
        items[cur] = items[i2++];
      } else if (i2 > right) {
        items[cur] = temp[i1++];
      } else if (temp[i1].compareTo(items[i2]) <= 0) {
        items[cur] = temp[i1++];
      } else {
        items[cur] = items[i2++];
      }
    }
  }
  
  /**
   * Personal test method that makes use of a global threshold variable. 
   * This is the method I used to calculate the ideal threshold for the adaptive merge.
   */
  
  public static <T extends Comparable<T>> void mergeSortHalfSpaceTest(T[] items) {
    // TODO
    @SuppressWarnings("unchecked")
    T[] temp = (T[]) new Comparable[(items.length / 2) + 1];
    mergeSubsortAdaptiveTest(items, temp, 0, items.length - 1, initThreshold);
  }
  
  /**
   * Personal test method that makes use of a global threshold variable. 
   * This is the method I used to calculate the ideal threshold for the adaptive merge.
   */
   
  public static <T extends Comparable<T>> void mergeSubsortAdaptiveTest(
      T[] items, T[] temp, int start, int end, int threshold) {
    if (start >= end) { // List has one record
      return;
    }
    if (end - start < initThreshold) {
      BasicSorts.insertionSubsort(items, start, end);
    } else {
      int mid = (start + end) / 2; // Select midpoint
      
      mergeSubsortAdaptiveTest(items, temp, start, mid, threshold);
      mergeSubsortAdaptiveTest(items, temp, mid + 1, end, threshold);
      
      merge(items, temp, start, mid, end);
    }
  }
}
