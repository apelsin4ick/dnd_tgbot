import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
public abstract class CommandHandler {
    private interface Command {
        SendMessage doId(Update update);
    }
    private final Map<String, Command> commandMap = new HashMap<>();
    protected abstract SendMessage commandDefault(Update update);
    CommandHandler() {
        for (Method method : this.getClass().getMethods()) {
            if (method.isAnnotationPresent(TgCommand.class)) {
                String key = method.getAnnotation(TgCommand.class).name();
                commandMap.put(key, new Command() {

                    @Override
                    public SendMessage doId(Update update) {
                        try {
                            return (SendMessage) method.invoke(CommandHandler.this, update);
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                });
            }
        }
    }

    public SendMessage handle(Update update) {
        String message = update.getMessage().getText();
        if (commandMap.containsKey(message.toLowerCase())) {
            Command command = commandMap.get(message);
            return command.doId(update);
        } else {
            return commandDefault(update);
        }
    }
}