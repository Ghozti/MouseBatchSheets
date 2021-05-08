package Bot;

import Sheets.SheetsMain;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public class Listener implements MessageCreateListener {


    @Override
    public void onMessageCreate(MessageCreateEvent event) {

        if (event.getMessageContent().equals("$help")){
            event.getChannel().sendMessage("Hello, ".concat(event.getMessageAuthor().getDisplayName().concat("! how may i help?")));

            try {
                if(SheetsMain.getValues() == null || SheetsMain.getValues().isEmpty()){
                    System.out.println("no data");
                }else {
                    int i = 1;
                    for (List row : SheetsMain.getValues()) {
                        event.getChannel().sendMessage(String.valueOf(i).concat(". ".concat(row.get(0).toString())));
                        i++;
                    }
                    i = 1;
                }
            } catch (GeneralSecurityException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
