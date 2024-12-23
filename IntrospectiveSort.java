/**
 * An implementation of introspective sort which begins by using quicksort but then switches
 * to MergeSortImproved once it appears that the preformance will be Theta(n squared) thus
 * making the worst case Theta(nlogn).
 * 
 * @author Griffin Moran
 * 
 * @version V1
 */

public class IntrospectiveSort {
  /**
   * Sort the provided items using introspective sort.
   */
  public static <T extends Comparable<T>> void introspectiveSort(T[] items) {
    int depthLimit = (2 * (int) (Math.log(items.length) / Math.log(2)));
    introspectiveSort(items, 0, items.length - 1, depthLimit);
  }
  
  /**
   * Sort the provided items using introspective sort.
   */
  public static <T extends Comparable<T>> void introspectiveSort(
      T[] items, int left, int right, int depthLimit) {
    if (depthLimit == 0) {
      MergeSortImproved.mergeSubsortAdaptive(items, left, right);
    } else if (items.length == 0 || items.length == 1) {
      return;
    } else {
      int pivotindex = QuickSort.findPivot(items, left, right);

      // curr will be the final position of the pivot item.
      int curr = QuickSort.partition(items, left, right, pivotindex);

      if ((curr - left) > 1) {
        introspectiveSort(items, left, curr - 1, depthLimit - 1); // Sort left partition
      }
      if ((right - curr) > 1) {
        introspectiveSort(items, curr + 1, right, depthLimit - 1); // Sort right partition
      }    
    }
  }
  

}
