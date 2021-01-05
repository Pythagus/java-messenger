package fr.insa.messenger.client.ui.screens.settings;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import fr.insa.messenger.client.utils.ColorUtils;
import fr.insa.messenger.tools.models.UserStatus;
import fr.insa.messenger.client.http.PresenceResponse;
import fr.insa.messenger.client.http.PresenceInterface;
import fr.insa.messenger.client.system.console.Console;
import fr.insa.messenger.client.ui.factories.FontFactory;
import fr.insa.messenger.client.ui.screens.SettingsScreen;

/**
 * @author Damien MOLINA
 */
public class StatusContainer extends JPanel implements ActionListener {

    /**
     * Status select instance.
     */
    private final JComboBox<String> select ;

    /**
     * Status parent screen.
     */
    private final SettingsScreen screen ;

    /**
     * Make a new screen instance.
     */
    public StatusContainer(SettingsScreen screen) {
        this.screen = screen ;
        this.select = new JComboBox<>(UserStatus.allTranslatedKeys()) ;

        this.initializeGraphics() ;
    }

    /**
     * Graphically initialize the screen.
     */
    private void initializeGraphics() {
        this.setLayout(new BorderLayout()) ;
        this.setBackground(ColorUtils.TRANSPARENT) ;
        this.setBorder(
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ) ;

        this.select.getModel().setSelectedItem(UserStatus.CONNECTED.translate()) ;
        this.select.addActionListener(this) ;

        // Pseudo label.
        JLabel status = new JLabel("Statut") ;
        status.setFont(FontFactory.normal(14)) ;
        status.setBorder(
            BorderFactory.createEmptyBorder(0, 0, 10, 0)
        ) ;

        // Add the container components.
        this.add(status, BorderLayout.NORTH) ;
        this.add(this.select, BorderLayout.SOUTH) ;
    }

    /**
     * When a select option is selected.
     *
     * @param event : select event.
     */
    public void actionPerformed(ActionEvent event) {
        try {
            UserStatus status = UserStatus.translate((String) this.select.getSelectedItem()) ;

            PresenceResponse response = PresenceInterface.publish(status) ;

            if(response.isSuccessful()) {
                Console.comment("[SUCCESS] Status changed : " + status.toString()) ;
            } else {
                // Generate an error to read the catch block.
                throw new Exception() ;
            }
        } catch (Exception e) {
            Console.comment("[ERROR] Status change failed") ;
            JOptionPane.showMessageDialog(
                this.screen, "Une erreur est survenue", "Erreur", JOptionPane.ERROR_MESSAGE
            ) ;
        }
    }

}
