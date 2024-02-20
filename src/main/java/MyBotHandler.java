import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

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
        sendMessage.setText("выберите рассу");
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
//            BotDB.save(
//                    update.getCallbackQuery().getMessage().getChatId(),
//                    "hf",
//                    callBackAnswerMessage
//            );
//            System.out.println(callBackAnswerMessage); vjt
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
            return sendMessage;
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

