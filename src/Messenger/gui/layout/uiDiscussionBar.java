package Messenger.gui.layout;

import javax.swing.*;
import java.awt.*;

/**
 * @author Damien MOLINA
 */
public class uiDiscussionBar extends JPanel {

    // Bar main color.
    public static final Color backgroundColor = new Color(245, 246, 245) ;

    /**
     * Make a new instance of the conversation bar.
     */
    public uiDiscussionBar() {
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
        pane.setViewportView(this.graphicList()) ;

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
        
        uiDiscussionItem conv = new uiDiscussionItem(42, "francois_hollande.png", "Damien Molina",
                "Hey, comment tu vas ? Je vais bien, merci. Je veux juste savoir si l'overflow se passe bien") ;
        uiDiscussionItem conv2 = new uiDiscussionItem(43, "francois_hollande.png", "Damien Molina", "Hey, comment tu vas ?") ;

        list.add(conv) ;
        list.add(conv2) ;

        return list ;
    }

}
