package Bot;

import Bot.listeners.HelperListener;
import Bot.listeners.Listener;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;

public class DiscordBot {

    public static void main(String... args){
        DiscordApi api = new DiscordApiBuilder().setToken(Token.token).login().join();
        api.addListener(new Listener());
        api.addListener(new HelperListener());
        HelperListener.helped = false;
    }
}
