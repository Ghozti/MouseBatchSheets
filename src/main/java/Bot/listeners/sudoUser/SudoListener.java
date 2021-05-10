package Bot.listeners.sudoUser;

import Bot.listeners.user.Listener;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

public class SudoListener implements MessageCreateListener {

    public static boolean accessGranted = false;

    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        if (event.getMessage().getAuthor().isBotOwner()){
            if (event.getMessage().equals("$sudo -add")){
                accessGranted = true;
            }
        }
    }
}
