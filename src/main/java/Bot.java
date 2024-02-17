import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class Bot extends TelegramLongPollingBot {
    CommandHandler commandHandler;

    public Bot(String token) {
        super("6615041363:AAGJeLPjEW0bmrOeZ6ZdRd8MDvQHBckUUSo");
        commandHandler = new MyBotHandler();
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            SendMessage sendMessage = commandHandler.handle(update);
            System.out.println(sendMessage);
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }
    @Override
    public String getBotUsername() {
        return "Zxctggood_bot";
    }
}

//public class Bot extends TelegramLongPollingBot {
//    CommandHandler commandHandler;
//
//    public Bot(String token) {
//        super(token);
//        commandHandler = new CommandHandler();
//    }
//
//    @Override
//    public void onUpdateReceived(Update update) {
//        try {
//            SendMessage sendMessage=commandHandler.handle(update);
//        }catch (TelegramApiException e){
//            e.printStackTrace();
//
//        }


//        try{
//            if (update.hasCallbackQuery()){
//                SendMessage sendMessage = new SendMessage();
//                String callBackAnswerMessage = update.getCallbackQuery().getData();
//                sendMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());
//                sendMessage.setText("Вы выбрали " + callBackAnswerMessage);
//                execute(sendMessage);
//                return;
//            }
//
//            ReplyKeyboardMarkup keyboardMarkup= new ReplyKeyboardMarkup();
//            KeyboardButton button11=new KeyboardButton("создать персонажа");
//            KeyboardButton button22= new KeyboardButton("мои персонажи");
//            KeyboardButton button33= new KeyboardButton("настройки");
//
//            KeyboardRow row1= new KeyboardRow(List.of(button11));
//            KeyboardRow row2= new KeyboardRow(List.of(button22));
//            KeyboardRow row3= new KeyboardRow(List.of(button33));
//            keyboardMarkup.setKeyboard(new ArrayList<>(List.of(row1,row2,row3)));
//
//            String message= update.getMessage().getText();
//            SendMessage sendMessage=new SendMessage();
//            sendMessage.setChatId(update.getMessage().getChatId());
//            sendMessage.setReplyMarkup(keyboardMarkup);
//
//            if (message.equals("создать персонажа") && update.getMessage().isUserMessage()) {
//                sendMessage.setText("Выбыерите редакцию");
//                InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
//                List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
//                InlineKeyboardButton button1 = new InlineKeyboardButton("5 редакции");
//                InlineKeyboardButton button2 = new InlineKeyboardButton("pathfinder");
//                button1.setCallbackData("5 редакции");
//                button2.setCallbackData("pathfinder");
//                rowsInline.add(new ArrayList<>(List.of(button1, button2)));
//                markupInline.setKeyboard(rowsInline);
//                sendMessage.setReplyMarkup(markupInline);
//                execute(sendMessage);
//
//            } else if((update.hasCallbackQuery())){
//                String callBackAnswerMessage = update.getCallbackQuery().getData();
//                sendMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());
//                sendMessage.setText("Выбыерите рассу");
//                InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
//                List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
//                InlineKeyboardButton button1 = new InlineKeyboardButton("эльф");
//                InlineKeyboardButton button2 = new InlineKeyboardButton("человек");
//                button1.setCallbackData("эльф");
//                button2.setCallbackData("человек");
//                rowsInline.add(new ArrayList<>(List.of(button1, button2)));
//                markupInline.setKeyboard(rowsInline);
//                sendMessage.setText(callBackAnswerMessage);
//                execute(sendMessage);
//
//            }else {
//                sendMessage.setText("wat?");
//                execute(sendMessage);
//            }
//        }catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
