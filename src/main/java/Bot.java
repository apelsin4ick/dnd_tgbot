import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Bot extends TelegramLongPollingBot {

    public Bot(String botToken) {
        super(botToken);
    }

    @Override
    public void onUpdateReceived(Update update) {

        try{
            if (update.hasCallbackQuery()){
                SendMessage sendMessage = new SendMessage();
                String callBackAnswerMessage = update.getCallbackQuery().getData();
                sendMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());
                sendMessage.setText("Вы выбрали " + callBackAnswerMessage);
                execute(sendMessage);
                return;
            }

            ReplyKeyboardMarkup keyboardMarkup= new ReplyKeyboardMarkup();
            KeyboardButton button11=new KeyboardButton("создать персонажа");
            KeyboardButton button22= new KeyboardButton("мои персонажи");
            KeyboardButton button33= new KeyboardButton("настройки");

            KeyboardRow row1= new KeyboardRow(List.of(button11));
            KeyboardRow row2= new KeyboardRow(List.of(button22));
            KeyboardRow row3= new KeyboardRow(List.of(button33));
            keyboardMarkup.setKeyboard(new ArrayList<>(List.of(row1,row2,row3)));

            String message= update.getMessage().getText();
            SendMessage sendMessage=new SendMessage();
            sendMessage.setChatId(update.getMessage().getChatId());
            sendMessage.setReplyMarkup(keyboardMarkup);

            if(message.equals("/inline") && update.getMessage().isCommand()) {
                sendMessage.setText("да или нет?");

                execute(sendMessage);

            } else if (message.equals("создать персонажа") && update.getMessage().isUserMessage()) {
                sendMessage.setText("Выбыерите редакцию");
                InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
                List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
                InlineKeyboardButton button1 = new InlineKeyboardButton("днд 5 редакции");
                InlineKeyboardButton button2 = new InlineKeyboardButton("pathfinder");
                button1.setCallbackData("1");
                button2.setCallbackData("2");
                rowsInline.add(new ArrayList<>(List.of(button1, button2)));
                markupInline.setKeyboard(rowsInline);
                sendMessage.setReplyMarkup(markupInline);
                execute(sendMessage);

            } else if(message.equals("/photo")&&update.getMessage().isCommand()){
                SendPhoto sendPhoto = new SendPhoto();
                sendPhoto.setChatId(update.getMessage().getChatId());
                sendPhoto.setPhoto(new InputFile(new File("")));
                execute(sendPhoto);

            }else {
                sendMessage.setText("wat?");
                execute(sendMessage);
            }
        }catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "Zxctggood_bot";
    }
}
