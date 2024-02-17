import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class MainKeyboard extends ReplyKeyboardMarkup {
    public MainKeyboard() {
        KeyboardButton button1 = new KeyboardButton("Создать персонажа");
        KeyboardButton button2 = new KeyboardButton("Мои персонажи");
        KeyboardButton button3 = new KeyboardButton("Настройки");

        KeyboardRow row1 = new KeyboardRow(List.of(button1));
        KeyboardRow row2 = new KeyboardRow(List.of(button2));
        KeyboardRow row3 = new KeyboardRow(List.of(button3));
        this.setKeyboard(new ArrayList<>(List.of(row1, row2, row3)));
    }
}

