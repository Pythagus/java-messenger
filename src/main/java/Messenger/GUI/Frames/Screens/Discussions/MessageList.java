package Messenger.GUI.Frames.Screens.Discussions;

import java.awt.*;
import javax.swing.*;
import Messenger.GUI.Frames.Utils.MyJList;
import Messenger.Foundation.Models.Messages.Message;
import Messenger.GUI.Frames.Screens.DiscussionScreen;

/**
 * @author Damien MOLINA
 */
public class MessageList extends MyJList<MessageListItem> {

    /**
     * Discussion screen instance.
     */
    private final DiscussionScreen screen ;

    /**
     * Make a message list instance.
     *
     * @param screen : discussion screen.
     */
    public MessageList(DiscussionScreen screen) {
        super() ;

        this.screen = screen ;

        this.initializeGraphics() ;
    }

    /**
     * Graphically initialize the screen.
     */
    private void initializeGraphics() {
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION) ;
        this.setLayoutOrientation(JList.VERTICAL) ;
        this.setVisibleRowCount(10) ;
        this.setModel(this.model) ;

        // Add the rendering listener.
        this.setCellRenderer(new ItemRenderer()) ;
    }

    /**
     * Render the component.
     *
     * @param g : graphic instance.
     */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    /**
     * Add the given item to the list.
     *
     * @param item : item to add.
     */
    public void addItem(MessageListItem item) {
        super.addItem(item) ;

        // Scroll to the bottom of the pane.
        // TODO : scroll to the very bottom. The added element is still not visible.
        JScrollBar vertical = this.screen.getListScrollPane().getVerticalScrollBar() ;
        vertical.setValue(vertical.getMaximum()) ;
    }

    /**
     * Add the message in the screen.
     *
     * @param message : message instance.
     */
    public void addItem(Message message) {
        System.out.println(message.getData().getText());
        this.addItem(new MessageListItem(this, message)) ;
    }

    /**
     * UserListItem renderer. This is responsible
     * of the rendering of the item.
     *
     * @author Damien MOLINA
     */
    private static class ItemRenderer implements ListCellRenderer<MessageListItem> {

        /**
         * Render the panel.
         *
         * @param list : list.
         * @param o : object to render.
         * @param i : object's index in the list.
         * @param selected : is selected.
         * @param b1 : is focused.
         * @return the rendered element.
         */
        public Component getListCellRendererComponent(JList<? extends MessageListItem> list, MessageListItem o, int i, boolean selected, boolean b1) {
            return o ;
        }
    }

}
