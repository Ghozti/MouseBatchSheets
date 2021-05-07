package Bot;

import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

public class Listener implements MessageCreateListener {


    @Override
    public void onMessageCreate(MessageCreateEvent event) {

        if (event.getMessageContent().equals("$help")){
            event.getChannel().sendMessage("Hello, ".concat(event.getMessageAuthor().getDisplayName().concat("! how may i help?")));
            //TODO display data
        }
    }
}
