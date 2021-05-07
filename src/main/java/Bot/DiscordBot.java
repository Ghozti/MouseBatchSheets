package Bot;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;

public class DiscordBot {

    public static void main(String... args){
        Redacted.addAll();
        DiscordApi api = new DiscordApiBuilder().setToken(Token.token).login().join();
        api.addListener(new Listener());
    }
}
