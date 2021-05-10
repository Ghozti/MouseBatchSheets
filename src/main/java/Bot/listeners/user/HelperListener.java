package Bot.listeners.user;

import Sheets.SheetsMain;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public class HelperListener implements MessageCreateListener {

    public static boolean helped = false;

    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        if (event.getMessageContent().equals("$quit")){
            helped = true;
        }else {
            if (helped){
                return;
            }
            try {
                for (List row : SheetsMain.getValues()) {
                   if(event.getMessageContent().equals(row.get(0).toString())){
                       event.getChannel().sendMessage(row.get(1).toString());
                       helped = true;
                       break;
                   }
                }
            } catch (GeneralSecurityException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
