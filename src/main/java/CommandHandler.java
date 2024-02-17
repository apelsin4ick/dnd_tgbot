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
    private final Map<String, Command> callbackMap = new HashMap<>();

    protected abstract SendMessage commandDefault(Update update);
    protected abstract SendMessage callbackDefault(Update update);


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
            if (method.isAnnotationPresent(TgCallback.class)) {
                String key = method.getAnnotation(TgCallback.class).name();
                callbackMap.put(key, new Command() {
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
        if (update.hasCallbackQuery()){
            String message = update.getCallbackQuery().getData();;
            if (callbackMap.containsKey(message.toLowerCase())) {
                Command command = callbackMap.get(message);
                return command.doId(update);
            }
            return callbackDefault(update);
        }

        String message = update.getMessage().getText();
        if (commandMap.containsKey(message.toLowerCase())) {
            Command command = commandMap.get(message);
            return command.doId(update);
        } else {
            return commandDefault(update);
        }
    }
}