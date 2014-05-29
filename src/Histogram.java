

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;


public class Histogram extends JPanel 
{

  private ArrayList<Integer> counter;
  private ArrayList<String> dates;
  /** Set the count and display histogram */
  public void showHistogram(ArrayList<Integer> counter, ArrayList<String> dates)
  {
	  System.out.println("reached");
    this.counter = counter;
    this.dates= dates;
    repaint();
  }
  /** Paint the histogram */
  protected void paintComponent(Graphics g) {
    if (counter == null) return; // No display if count is null
    super.paintComponent(g);
   // Find the panel size and bar width and interval dynamically
    int width = 600;
    int height = 300;
    int interval = (width - 10) / counter.size();
    int individualWidth = (int)(((width - 40) / 24) * 0.60);

    // Find the maximum count. The maximum count has the highest bar
    int maxCount = 0;
    for (int i = 0; i < counter.size(); i++) {
      if (maxCount < counter.get(i))
        maxCount = counter.get(i);
    }
   // x is the start position for the first bar in the histogram
    int x = 30;
    // Draw a horizontal base line
   g.drawLine(10, height - 45, width - 10, height - 45);
    for (int i = 0; i < counter.size(); i++) {
      // Find the bar height
      int barHeight =   (int)(((double)counter.get(i) / (double)maxCount) * (height - 55));
      // Display a bar (i.e. rectangle)
      g.drawRect(x, height - 45 - barHeight, individualWidth, barHeight);
      // Display a letter under the base line
      //g.drawString((char)(65 + i) + "", x, height - 30);
      g.drawString(dates.get(i)+"("+counter.get(i) + ")", x, height - 30);
      // Move x for displaying the next character
      x += interval;

    }

  }

  /** Override getPreferredSize */

  public Dimension getPreferredSize() {

    return new Dimension(300, 300);

  }

}
