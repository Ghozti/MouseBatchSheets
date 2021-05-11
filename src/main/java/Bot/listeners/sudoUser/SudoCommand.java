package Bot.listeners.sudoUser;

import Sheets.SheetsMain;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;
import java.io.IOException;
import java.security.GeneralSecurityException;

public class SudoCommand implements MessageCreateListener {

    @Override
    public void onMessageCreate(MessageCreateEvent event) {

        System.out.println(event.getMessageContent());

        String type = event.getMessageContent().substring(0,event.getMessageContent().indexOf(':')-1);
        String content = event.getMessageContent().substring(event.getMessageContent().indexOf(':')-1);

        try {
            SheetsMain.addToSheet(type,content);
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }
    }
}
