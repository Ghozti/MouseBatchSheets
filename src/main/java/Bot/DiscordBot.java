package Bot;

import Bot.listeners.sudoUser.SudoListener;
import Bot.listeners.user.HelperListener;
import Bot.listeners.user.Listener;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;

public class DiscordBot {

    public static void main(String... args){
        DiscordApi api = new DiscordApiBuilder().setToken(Token.token).login().join();
        //api.addListener(new SudoListener());
        api.addListener(new Listener());
        api.addListener(new HelperListener());
        HelperListener.helped = false;
    }
}
