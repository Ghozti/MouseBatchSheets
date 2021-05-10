package Bot.listeners.sudoUser;

import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

public class SudoCommand implements MessageCreateListener {

    public static int commandPort;

    private String type,content;

    @Override
    public void onMessageCreate(MessageCreateEvent event) {

        type = event.getMessageContent().substring(0,event.getMessageContent().indexOf(':')-1);
        content = event.getMessageContent().substring(event.getMessageContent().indexOf(':')-1);

        switch (commandPort){
            case 1:
                //TODO add stuff to the spreadsheets
                //sheets add row 0 type, sheets add row 1 content
                break;

        }
    }
}
