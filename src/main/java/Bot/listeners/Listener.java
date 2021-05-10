package Bot.listeners;

import Sheets.SheetsMain;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;
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
                    StringBuilder list = new StringBuilder();
                    for (List row : SheetsMain.getValues()) {
                        list.append(String.valueOf(i).concat(". ***".concat(row.get(0).toString())).concat("***\n"));
                        i++;
                    }
                    event.getChannel().sendMessage(list.toString());
                    event.getChannel().sendMessage("This is all I currently have in my database, for other issues please ask the human staff.");
                    event.getChannel().sendMessage("Please type **$quit** to quit now, or type in your problem as written in the list");
                }
            } catch (GeneralSecurityException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
