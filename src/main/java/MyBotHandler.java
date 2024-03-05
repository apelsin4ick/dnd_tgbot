import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

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
            id = MainSQLiteExample.insert(Integer.parseInt(sendMessage.getChatId()), "", "");
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
        sendMessage.setText("Вы выбрали " + callBackAnswerMessage + "a");

        String parentMessage = update.getCallbackQuery().getMessage().getText();
        int hashTagPos = parentMessage.indexOf('#');
        int hashTagPosLast = parentMessage.lastIndexOf('#');
        int characterId = Integer.parseInt(parentMessage.substring(hashTagPos + 1, hashTagPosLast));

        System.out.println("ерсонаж " + characterId);

        try {
            MainSQLiteExample.update(characterId, "races", callBackAnswerMessage);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commandCreateClass(convert(update));
    }

    @TgCallback(name = "эльф")
    public SendMessage commandPers1(Update update) {
        if (update.hasCallbackQuery()) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());
            String callBackAnswerMessage = update.getCallbackQuery().getData();
            sendMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());
            sendMessage.setText("Вы выбрали " + callBackAnswerMessage + "a");
            try {
                MainSQLiteExample.insert(Integer.parseInt(""), callBackAnswerMessage, "");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return commandCreateClass(convert(update));
        }
        return null;
    }


    @TgCommand(name = "Выберите класс")
    public SendMessage commandCreateClass(Update update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId());
        sendMessage.setText("выбор класса");
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        InlineKeyboardButton button1 = new InlineKeyboardButton("плут");
        InlineKeyboardButton button2 = new InlineKeyboardButton("маг");
        button1.setCallbackData("плут");
        button2.setCallbackData("маг");
        rowsInline.add(new ArrayList<>(List.of(
                button1, button2
        )));
        markupInline.setKeyboard(rowsInline);
        sendMessage.setReplyMarkup(markupInline);
        return sendMessage;
    }

    @TgCallback(name = "плут")
    public SendMessage commandClass(Update update) {
        if (update.hasCallbackQuery()) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());
            String callBackAnswerMessage = update.getCallbackQuery().getData();
            sendMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());
            sendMessage.setText("Вы выбрали " + callBackAnswerMessage);
            try {
                MainSQLiteExample.insert(Integer.parseInt(""), "", callBackAnswerMessage);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return sendMessage;
        }
        return null;
    }

    @TgCallback(name = "маг")
    public SendMessage commandClass1(Update update) {
        if (update.hasCallbackQuery()) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());
            String callBackAnswerMessage = update.getCallbackQuery().getData();
            sendMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());
            sendMessage.setText("Вы выбрали " + callBackAnswerMessage);
            try {
                MainSQLiteExample.insert(Integer.parseInt(""), "", callBackAnswerMessage);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return sendMessage;
        }
        return null;
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
        sendMessage.setText("error");
        return sendMessage;
    }
}

