package Messenger.GUI.Frames.Utils;

import java.awt.*;
import javax.swing.*;

/**
 * @author Damien MOLINA
 */
final public class GridBagUtil {

    /**
     * Add the given component as a column.
     *
     * @param container : new component container.
     * @param component : column
     * @param rank : column rank.
     * @param percentageSize : size (in percentage) of the column in the frame.
     */
    public static void addColumn(Container container, JComponent component, int rank, int percentageSize) {
        GridBagConstraints c = new GridBagConstraints() ;
        c.gridx   = rank;
        c.weightx = (double) percentageSize / 100 ;
        c.weighty = 1 ;
        c.fill    = GridBagConstraints.BOTH;

        container.add(component, c) ;
    }

}
