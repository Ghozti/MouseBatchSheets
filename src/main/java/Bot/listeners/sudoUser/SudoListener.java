package Bot.listeners.sudoUser;

import Bot.DiscordBot;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

public class SudoListener implements MessageCreateListener {

    @Override
    public void onMessageCreate(MessageCreateEvent event) {

        if (event.getMessageContent().equals("$sudo -addP") && event.getMessage().getAuthor().isBotOwner()){
            event.getChannel().sendMessage("Enter data:");
        }
    }
}
