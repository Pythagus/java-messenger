package Messenger.Network.Models.Broadcast;

import java.util.regex.*;
import java.net.UnknownHostException;
import Messenger.Foundation.Models.User;
import Messenger.Foundation.System.Console.Console;

/**
 * @author Damien MOLINA
 */
public class BroadcastNotification  {

    /**
     * Broadcast type.
     */
    private BroadcastType type ;

    /**
     * User sent with the broadcast notification.
     */
    private User sender ;

    public BroadcastType getType(){return this.type; }

    public User getUser(){return this.sender; }


    /**
     * Make a new BroadcastNotification instance.
     *
     * @param type : broadcast type.
     * @param user : user sending the notification.
     */
    public BroadcastNotification(BroadcastType type, User user) throws UnknownHostException
    {
        this.type = type ;
        this.sender = user ;
    }


    /**
     * make a string out of a Broadcast notification
     * @return String with a standardized format
     */
    public String NotifToString()
    {
        String notifString = "##" + this.type + "#@#" + this.sender.getPseudo() + "@" + this.sender.getIdentifier() + "#" + this.sender.getAddress() + "###" ;
        return notifString ;
    }

    /**
     * make a broadcastNotification out of a string
     * @param notifString payload of a UPD packet
     * @throws Exception
     */
    public BroadcastNotification (String notifString) throws Exception
    {
        Pattern pType = Pattern.compile("##([A-Z]+)#");
        Matcher m = pType.matcher(notifString);
        if (m.find())
        {
            switch (m.group(1))
            {
                case "LOGIN":
                    this.type = BroadcastType.LOGIN;
                    break;
                case "LOGOUT":
                    this.type = BroadcastType.LOGOUT;
                    break;
                case "HASPSEUDO":
                    this.type = BroadcastType.HASPSEUDO;
                    break;
                case "CHANGEDPSEUDO":
                    this.type = BroadcastType.CHANGEDPSEUDO;
                    break;
            }
        } else
        {
            Console.comment("=> oups, string to broadcast failed");
        }

        //user information
        Pattern pName = Pattern.compile("#@#([A-Za-z0-9]+)#"); // group 1
        Matcher m1 = pName.matcher(notifString);
        Pattern pMAC = Pattern.compile("@(([A-Za-z0-9][A-Za-z0-9]:)+[A-Za-z0-9][A-Za-z0-9])"); //group 1
        Matcher m2 = pMAC.matcher(notifString);
        Pattern pIP = Pattern.compile("#([0-9]+.[0-9]+.[0-9]+.[0-9]+)###"); // group 1
        Matcher m3 = pIP.matcher(notifString);
        if (m1.find() & m2.find() & m3.find())
        {
            this.sender = new User(m1.group(1), m2.group(1), m3.group(1));
        } else
        {
            Console.comment("=> oups, string to broadcast failed");
        }
    }

}
