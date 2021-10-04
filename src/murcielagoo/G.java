
package murcielagoo;

import javax.swing.SwingUtilities;
public class G {
  public static void main(String[] args) {

    SwingUtilities.invokeLater(new Runnable() {
      private Paleta Pal;

      @Override
      public void run() {

        Pal = new Paleta();

      }
    });
  }
}
