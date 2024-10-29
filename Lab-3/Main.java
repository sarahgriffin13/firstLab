public class Main {
  public static void main(String[] args) {
    String text = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    
    // Best case: match occurs at the beginning of the text
    String bestCase = "A"; 

    // Initialize startTime for best case
    long startTime = System.nanoTime();
    int index = match(text, bestCase);
    // Initialize endTime and calculate elapsed time for best case
    long endTime = System.nanoTime();
    long elapsedTime = endTime - startTime;
    
    if (index >= 0)
      System.out.println("Best-case input matched at index " + index);
    else
      System.out.println("Best-case input unmatched");   
    System.out.println("Best-case elapsed time: " + elapsedTime + " ns");

    // Worst case: match occurs at the end of the text
    String worstCase = "9"; 
    
    // Initialize startTime for worst case
    startTime = System.nanoTime();
    index = match(text, worstCase);
    // Initialize endTime and calculate elapsed time for worst case
    endTime = System.nanoTime();
    elapsedTime = endTime - startTime;
    
    if (index >= 0)
      System.out.println("Worst-case input matched at index " + index);
    else
      System.out.println("Worst-case input unmatched");  
    System.out.println("Worst-case elapsed time: " + elapsedTime + " ns");
  }

  // Return the index of the first match. -1 otherwise.
  public static int match(String text, String pattern) {
    for (int i = 0; i < text.length() - pattern.length() + 1; i++) {
      if (isMatched(i, text, pattern))
        return i;
    }
    return -1;
  }
  
  // Test if pattern matches text starting at index i
  private static boolean isMatched(int i, String text, String pattern) {
    for (int k = 0; k < pattern.length(); k++) {
      if (pattern.charAt(k) != text.charAt(i + k)) {
        return false;
      }
    }
    return true;
  }
}
