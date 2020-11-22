package Messenger.GUI.Layout.Items;

import java.awt.*;
import javax.swing.*;
import java.util.Arrays;

/**
 * @author Damien MOLINA
 */
public class uiDiscussionBar extends JPanel {

    // Bar main color.
    public static final Color backgroundColor = new Color(245, 246, 245) ;

    // List of the discussions.
    private final JPanel list ;

    /**
     * Make a new instance of the conversation bar.
     */
    public uiDiscussionBar() {
        this.list = this.graphicList() ;

        this.initializeComponentGraphics() ;
    }

    /**
     * Initialize graphically the component
     */
    private void initializeComponentGraphics() {
        this.setLayout(new BorderLayout()) ;

        // Add the header.
        this.add(this.graphicHeader(), BorderLayout.PAGE_START) ;

        // Add the list.
        this.add(this.graphicScrollPane(), BorderLayout.CENTER) ;
    }

    /**
     * Add the given item to the discussion items list.
     *
     * @param item : uiDiscussionItem instance.
     */
    public void addDiscussionItem(uiDiscussionItem item) {
        System.out.println("here");
        this.list.add(item) ;

        this.list.revalidate() ;
        this.list.repaint() ;
    }

    /**
     * Make the conversation header.
     *
     * @return the JPanel generated.
     */
    private JPanel graphicHeader() {
        JPanel header = new JPanel() ;

        header.setBackground(uiDiscussionBar.backgroundColor) ;
        header.setPreferredSize(new Dimension(300, 80)) ;
        header.setLayout(new GridLayout(1, 0)) ;

        // Add the label.
        header.add(this.graphicHeaderTitle()) ;

        return header ;
    }

    /**
     * Make the conversation header title.
     *
     * @return the JLabel generated.
     */
    private JLabel graphicHeaderTitle() {
        JLabel title = new JLabel() ;

        title.setHorizontalAlignment(SwingConstants.CENTER) ;
        title.setText("Discussions") ;
        title.setHorizontalTextPosition(SwingConstants.CENTER) ;
        title.setPreferredSize(new Dimension(490, 50)) ;

        Font f = new Font(null, Font.PLAIN, 24) ;
        title.setFont(f.deriveFont(Font.BOLD)) ;

        return title ;
    }

    /**
     * Make the conversation scroll pane.
     *
     * @return the JScrollPane generated.
     */
    private JScrollPane graphicScrollPane() {
        JScrollPane pane = new JScrollPane() ;

        pane.setAutoscrolls(true) ;
        pane.setPreferredSize(new Dimension(490, 100)) ;
        pane.setBorder(null) ;
        pane.setBackground(uiDiscussionBar.backgroundColor) ;

        // Add the panel.
        pane.setViewportView(this.list) ;

        return pane ;
    }

    /**
     * Make the conversation list.
     *
     * @return the JPanel generated.
     */
    private JPanel graphicList() {
        JPanel list = new JPanel() ;

        list.setPreferredSize(new Dimension(42, 705)) ;
        list.setBorder(null) ;
        list.setBackground(uiDiscussionBar.backgroundColor) ;

        return list ;
    }

}
