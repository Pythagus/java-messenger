package Messenger.GUI.Layout.Items.Discussion;

import Messenger.Foundation.System.Assets.ImageAsset;
import Messenger.GUI.Factories.ButtonFactory;
import Messenger.Foundation.Observers.Listeners.SendMessageListener;
import Messenger.GUI.Subscreens.uiDiscussion;

import javax.swing.*;
import java.awt.*;

/**
 * @author Damien MOLINA
 */
public class uiDiscussionFooter extends JPanel {

    /**
     * uiDiscussion's input instance.
     */
    private final uiDiscussionInput input ;

    /**
     * Make a new instance of uiDiscussionFooter.
     */
    public uiDiscussionFooter() {
        this.input = new uiDiscussionInput() ;
        this.input.addListener(new SendMessageListener()) ;

        this.initializeComponentGraphics() ;
    }

    /**
     * Initialize graphically the component
     */
    private void initializeComponentGraphics() {
        this.setBackground(uiDiscussion.backgroundColor) ;
        this.setPreferredSize(new Dimension(678, 70)) ;
        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS)) ;

        // Add the component elements.
        this.add(this.input) ;
        this.add(this.graphicButtons()) ;
    }

    /**
     * Generate the buttons container.
     *
     * @return the buttons container instance.
     */
    private JPanel graphicButtons() {
        JPanel container = new JPanel() ;
        container.setBackground(uiDiscussion.backgroundColor) ;
        container.setPreferredSize(new Dimension(150, 70)) ;
        container.setLayout(new GridLayout()) ;

        // Add the component elements.
        container.add(this.graphicFileButton()) ;
        container.add(this.graphicSendingButton()) ;

        return container ;
    }

    /**
     * Generate the file button.
     *
     * @return the file button.
     */
    private JButton graphicFileButton() {
        JButton btn = ButtonFactory.withoutBorder(uiDiscussion.backgroundColor) ;
        btn.setIcon(
            ImageAsset.asImageIcon("discussion/file_button.png")
        ) ;

        return btn ;
    }

    /**
     * Generate the sending button.
     *
     * @return the sending button.
     */
    private JButton graphicSendingButton() {
        JButton btn = ButtonFactory.withoutBorder(uiDiscussion.backgroundColor) ;
        btn.setIcon(
            ImageAsset.asImageIcon("discussion/send_button.png")
        ) ;
        btn.addActionListener(Unused -> this.input.sendInputContent()) ;

        return btn ;
    }

}
