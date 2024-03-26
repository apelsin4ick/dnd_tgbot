import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class More {
    @TgCommand(name = "Поробнее")
    public SendMessage more(Update update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId());
        int id = 0;
        try {
            id = SQLiteDND.insert(Integer.parseInt(sendMessage.getChatId()), "", "", Integer.parseInt(sendMessage.getChatId()));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        sendMessage.setText("Подрбнее о: ".formatted(id));
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        InlineKeyboardButton button1 = new InlineKeyboardButton("классы");
        InlineKeyboardButton button2 = new InlineKeyboardButton("рассы");
        button1.setCallbackData("классы");
        button2.setCallbackData("рассы");
        rowsInline.add(new ArrayList<>(List.of(
                button1, button2
        )));
        markupInline.setKeyboard(rowsInline);
        sendMessage.setReplyMarkup(markupInline);

        return sendMessage;
    }
    @TgCallback(name = "классы")
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
        return moreClass(update);
    }

    @TgCallback(name = "Подробнее о: ")
    public SendMessage moreClass(Update update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId());
        String parentMessage = update.getMessage().getText();

        sendMessage.setText("Выберите класс");
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
}
