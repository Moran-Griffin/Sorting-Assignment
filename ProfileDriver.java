import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that was created for the purpose of determining the ideal threshold for
 * adaptive mergesort. It preforms the adaptive merge with various thresholds and prints
 * them to a separate file to allow for easier analysis.
 */
public class ProfileDriver {
  /**
   * Main method.
   */
  public static void main(String[] args) throws FileNotFoundException {
    thresholdCalculate();
  }
  /**
   * Test method for calculating the threshold.
   */
  
  public static void thresholdCalculate() throws FileNotFoundException {
    // Create a SortProfiler object.
    // See the JavaDoc for an explanation of the parameters.
    SortProfiler sp = new SortProfiler(List.of(MergeSortImproved::mergeSortAdaptive),
                                       List.of("MergeSortAdaptive"),
                                       1000, 1000, 10000, 10,
                                       Generators::generateOrdered);

    ArrayList<String> outputs = new ArrayList<>();
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    // Run the profiler and send the output to stdout.
    MergeSortImproved.initThreshold = 70;
    for (int i = 70; i < 101; i += 5) {
      String output = "Results for threshold of " + i + "\n";
      sp.run(baos);
      output += baos.toString();
      outputs.add(output);
      baos.reset();
      MergeSortImproved.initThreshold += 5;
    }
    File file = new File("MergeThresholdSmall.txt");
    PrintWriter pw = new PrintWriter(file);
    
    for (String output : outputs) {
      pw.print(output);
      pw.flush();
      //System.out.print(output);
    }
    pw.close();
  }
}
