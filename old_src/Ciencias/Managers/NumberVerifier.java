package Ciencias.Managers;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JTextField;

/**
 * Verifies that input is a number.
 * @author Paul Alves
 */


public class NumberVerifier extends InputVerifier {
         @Override
         public boolean verify(JComponent input) {
             JTextField tf = (JTextField) input;
             try{Integer.parseInt(tf.getText());
             return true;
             }
             catch(java.lang.NumberFormatException e){return false;}
         }
     }
