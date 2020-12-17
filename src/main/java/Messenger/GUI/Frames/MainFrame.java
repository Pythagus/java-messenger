package Messenger.GUI.Frames;

import java.awt.*;
import javax.swing.*;
import Messenger.GUI.GraphicInterface;
import Messenger.GUI.Frames.Items.Sidebar;
import Messenger.GUI.Frames.Utils.GridBagUtil;
import Messenger.GUI.Frames.Screens.Utils.UserBar;
import Messenger.GUI.Frames.Screens.Utils.BarType;
import Messenger.GUI.Frames.Screens.WelcomeScreen;
import Messenger.GUI.Frames.Screens.SettingsScreen;
import Messenger.GUI.Frames.Screens.Utils.CardPanel;
import Messenger.GUI.Frames.Screens.DiscussionScreen;
import Messenger.GUI.Frames.Screens.Utils.ContentType;
import Messenger.GUI.Frames.Screens.Utils.ContentScreen;
import Messenger.GUI.Frames.Screens.Contacts.ContactBar;
import Messenger.GUI.Frames.Screens.Discussions.DiscussionBar;
import Messenger.Foundation.Observers.Listeners.UserListUpdated;

/**
 * @author Damien MOLINA
 */
public class MainFrame extends Frame {

    /**
     * Main frame background color.
     */
    public static final Color backgroundColor = new Color(230, 230, 230) ;

    /**
     * Card layout for the bars.
     */
    private final JPanel barContainer ;

    /**
     * Right side content container.
     */
    private final JPanel contentContainer ;


    /**
     * Make a new MainFrame instance.
     */
    public MainFrame() {
        super("The Java Internal Network") ;

        this.barContainer     = new JPanel() ;
        this.contentContainer = new JPanel() ;

        this.initializeGraphics() ;
    }

    /**
     * Start the main frame.
     */
    public static void start() {
        GraphicInterface.instance().start(MainFrame.class) ;
        GraphicInterface.instance().notifyWhenRendered(UserListUpdated::updateUI) ;
    }

    /**
     * Graphically initialize the frame.
     */
    private void initializeGraphics() {
        this.setResizable(false) ;
        this.setExtendedState(JFrame.MAXIMIZED_BOTH) ;
        this.setMinimumSize(new Dimension(800, 500)) ;
        this.getContentPane().setLayout(new GridBagLayout());

        // Bar container.
        this.barContainer.setLayout(new CardLayout()) ;
        this.barContainer.add(BarType.DISCUSSION.toString(), new DiscussionBar(this)) ;
        this.barContainer.add(BarType.CONTACTS.toString(), new ContactBar(this)) ;

        // Right side container.
        this.contentContainer.setLayout(new CardLayout()) ;
        this.addCardPanel(new WelcomeScreen()) ;
        this.addCardPanel(new SettingsScreen()) ;
        this.addCardPanel(new DiscussionScreen()) ;

        // Add the frame columns.
        this.addColumn(new Sidebar(this), 1, 3) ;
        this.addColumn(this.barContainer, 2, 22) ;
        this.addColumn(this.contentContainer, 3, 75) ;
    }

    /**
     * Add the given component as a column.
     *
     * @param component : column
     * @param rank : column rank.
     * @param percentageSize : size (in percentage) of the column in the frame.
     */
    private void addColumn(JComponent component, int rank, int percentageSize) {
        GridBagUtil.addColumn(
            this.getContentPane(), component, rank, percentageSize
        ) ;
    }

    /**
     * Add the given card to the container.
     *
     * @param card : card to add.
     */
    private void addCardPanel(CardPanel card) {
        if(card instanceof ContentScreen) {
            this.contentContainer.add(card.getName(), card) ;
        } else if(card instanceof UserBar) {
            this.barContainer.add(card.getName(), card) ;
        }
    }

    /**
     * Show the given bar type.
     *
     * @param type : bar type.
     */
    public void showBar(BarType type) {
        this.showCard(this.barContainer, type.toString()) ;
    }

    /**
     * Get the bar identifier by the given
     * type.
     *
     * @param type : bar type.
     * @return the bar instance.
     */
    public UserBar getBar(BarType type) {
        return (UserBar) this.getCardItem(this.barContainer, type.toString()) ;
    }

    /**
     * Show the given content type.
     *
     * @param type : content type.
     */
    public void showContent(ContentType type) {
        this.showCard(this.contentContainer, type.toString()) ;
    }

    /**
     * Get the content type.
     *
     * @param type : content type.
     * @return the content instance.
     */
    public ContentScreen getContent(ContentType type) {
        return (ContentScreen) this.getCardItem(this.contentContainer, type.toString()) ;
    }

    /**
     * Show the given card content.
     *
     * @param container : card container.
     * @param type : card type.
     */
    private void showCard(JPanel container, String type) {
        CardLayout layout = (CardLayout) container.getLayout() ;
        layout.show(container, type) ;
    }

    /**
     * Get the cart item identified by the type.
     *
     * @param container : component container.
     * @param type : item type.
     * @return the item if found, null otherwise.
     */
    private JPanel getCardItem(JPanel container, String type) {
        for (Component comp : container.getComponents()) {
            if (comp.getName().equals(type)) {
                return (JPanel) comp ;
            }
        }

        return null ;
    }

}
