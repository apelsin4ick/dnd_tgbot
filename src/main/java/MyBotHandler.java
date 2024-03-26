import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MyBotHandler extends CommandHandler {
    public MyBotHandler() {
        super();
    }

    public Update convert(Update update) {
        return new Update(
                update.getUpdateId(),
                update.getCallbackQuery().getMessage(),
                update.getInlineQuery(),
                update.getChosenInlineQuery(),
                null,
                update.getEditedMessage(),
                update.getChannelPost(),
                update.getEditedChannelPost(),
                update.getShippingQuery(),
                update.getPreCheckoutQuery(),
                update.getPoll(),
                update.getPollAnswer(),
                update.getMyChatMember(),
                update.getChatMember(),
                update.getChatJoinRequest()
        );
    }

    @TgCommand(name = "/start")
    public SendMessage commandStart(Update update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId());
        sendMessage.setText("тгбдндсп");
        sendMessage.setReplyMarkup(new MainKeyboard());

        return sendMessage;
    }

    @TgCommand(name = "Создать персонажа")
    public SendMessage createCratePers(Update update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId());
        int id = 0;
        try {
            id = SQLiteDND.insert(Integer.parseInt(sendMessage.getChatId()), "", "", Integer.parseInt(sendMessage.getChatId()));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        sendMessage.setText("Персонаж #%d#. Выберите рассу".formatted(id));
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        InlineKeyboardButton button1 = new InlineKeyboardButton("человек");
        InlineKeyboardButton button2 = new InlineKeyboardButton("эльф");
        button1.setCallbackData("человек");
        button2.setCallbackData("эльф");
        rowsInline.add(new ArrayList<>(List.of(
                button1, button2
        )));
        markupInline.setKeyboard(rowsInline);
        sendMessage.setReplyMarkup(markupInline);

        return sendMessage;
    }


    @TgCallback(name = "человек")
    public SendMessage commandPers(Update update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());
        String callBackAnswerMessage = update.getCallbackQuery().getData();
        sendMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());

        String parentMessage = update.getCallbackQuery().getMessage().getText();
        int hashTagPos = parentMessage.indexOf('#');
        int hashTagPosLast = parentMessage.lastIndexOf('#');
        int characterId = Integer.parseInt(parentMessage.substring(hashTagPos + 1, hashTagPosLast));

        try {
            SQLiteDND.update(characterId, "races", callBackAnswerMessage);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commandCreateClass(convert(update));
    }

    @TgCallback(name = "эльф")
    public SendMessage commandPers1(Update update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());
        String callBackAnswerMessage = update.getCallbackQuery().getData();
        sendMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());

        String parentMessage = update.getCallbackQuery().getMessage().getText();
        int hashTagPos = parentMessage.indexOf('#');
        int hashTagPosLast = parentMessage.lastIndexOf('#');
        int characterId = Integer.parseInt(parentMessage.substring(hashTagPos + 1, hashTagPosLast));

        try {
            SQLiteDND.update(characterId, "races", callBackAnswerMessage);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commandCreateClass(convert(update));
    }


    @TgCallback(name = "Выберете класс")
    public SendMessage commandCreateClass(Update update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId());
        String parentMessage = update.getMessage().getText();
        int id = getCharacterIdFromMessage(parentMessage);

        sendMessage.setText("Персонаж #%d#. Выберите класс".formatted(id));
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        InlineKeyboardButton button1 = new InlineKeyboardButton("плут");
        InlineKeyboardButton button2 = new InlineKeyboardButton("маг");
        InlineKeyboardButton button3 = new InlineKeyboardButton("варвар");
        button1.setCallbackData("плут");
        button2.setCallbackData("маг");
        button3.setCallbackData("варвар");
        rowsInline.add(new ArrayList<>(List.of(
                button1, button2, button3
        )));
        markupInline.setKeyboard(rowsInline);
        sendMessage.setReplyMarkup(markupInline);

        return sendMessage;
    }

    protected int getCharacterIdFromMessage(String message) {
        int hashTagPos = message.indexOf('#');
        int hashTagPosLast = message.lastIndexOf('#');
        return Integer.parseInt(message.substring(hashTagPos + 1, hashTagPosLast));
    }

    @TgCallback(name = "плут")
    public SendMessage commandClass(Update update) {

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());
        String callBackAnswerMessage = update.getCallbackQuery().getData();
        sendMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());

        String parentMessage = update.getCallbackQuery().getMessage().getText();

        try {
            SQLiteDND.update(getCharacterIdFromMessage(parentMessage), "classes", callBackAnswerMessage);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commandClass1(update);
    }

    @TgCallback(name = "маг")
    public SendMessage commandClass1(Update update) {

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());
        String callBackAnswerMessage = update.getCallbackQuery().getData();
        sendMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());

        String parentMessage = update.getCallbackQuery().getMessage().getText();

        try {
            SQLiteDND.update(getCharacterIdFromMessage(parentMessage), "classes", callBackAnswerMessage);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return commandClass1(convert(update));
    }

    @TgCallback(name = "варвар")
    public SendMessage commandClass2(Update update) {

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());
        String callBackAnswerMessage = update.getCallbackQuery().getData();
        sendMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());

        String parentMessage = update.getCallbackQuery().getMessage().getText();

        try {
            SQLiteDND.update(getCharacterIdFromMessage(parentMessage), "classes", callBackAnswerMessage);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return streinght(convert(update));
    }

    @TgCommand(name = "Сила")
    public SendMessage streinght(Update update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId());
        String parentMessage = update.getMessage().getText();
        int id = getCharacterIdFromMessage(parentMessage);

        long tg = update.getMessage().getChatId();
        long state = UserState.STREINGHT;
        try {
            SQLiteUser.insert(tg, state);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        sendMessage.setText("Персонаж #%d#. Впишите количество силы:".formatted(id));
        return sendMessage;
    }

    @TgCommand(name = "Мои персонажи")
    public SendMessage myPers(Update update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId());
        long id = update.getMessage().getChatId();
        try {
            ArrayList<Personazhi> personazhisList = SQLiteDND.select(id);

            sendMessage.setText(personazhisList.toString().substring(1, personazhisList.toString().length() - 2).replace(",", "\n"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sendMessage;
    }


    @Override
    public SendMessage callbackDefault(Update update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());
        sendMessage.setText("error");
        return sendMessage;
    }

    @Override
    public SendMessage commandDefault(Update update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId());

        String data = update.getMessage().getText();

        long tg = update.getMessage().getChatId();

        try {
            ArrayList<User> users = SQLiteUser.select(tg);
            if (!users.isEmpty()) {
                User user = users.get(0);
                if (user.state == UserState.STREINGHT) {

                } else {
                    sendMessage.setText("error");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sendMessage;
    }
}

