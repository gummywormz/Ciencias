
package Ciencias.Managers;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 *
 * @author homecomputer
 */


class ItemChangeListener implements ItemListener{
    @Override
    public void itemStateChanged(ItemEvent event) {
       if (event.getStateChange() == ItemEvent.SELECTED) {
          Object item = event.getItem();
          // do something with object
       }
    }       
}