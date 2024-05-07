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

        try {
            long tgId = update.getMessage().getChatId();
            ArrayList<User> users = SQLiteUser.select(tgId);
            if (!users.isEmpty()) {
                User user = users.get(0);
                int id = SQLiteDND.insert(user.tgId, "", "", 0,0,0,0,0,0,"");
                sendMessage.setText("Персонаж #%d#. Выберите рассу".formatted(id));
                SQLiteUser.update(tgId, UserState.RACES, user.persNum);
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

                SQLiteUser.update(user.tgId, UserState.RACES, id);
            } else {
                int persId = SQLiteDND.insert(tgId, "", "", 0,0,0,0,0,0,"");
                SQLiteUser.insert(tgId, UserState.RACES, persId);

                sendMessage.setText("Персонаж #%d#. Выберите рассу".formatted(persId));
                SQLiteUser.update(tgId, UserState.RACES, persId);
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

                SQLiteUser.update(tgId, UserState.RACES, persId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sendMessage;
    }

    @TgCallback(name = "человек")
    public SendMessage commandPers(Update update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());
        String callBackAnswerMessage = update.getCallbackQuery().getData();
        sendMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());

        try {
            long tgId = update.getCallbackQuery().getMessage().getChatId();
            ArrayList<User> users = SQLiteUser.select(tgId);
            User user = users.get(0);

            SQLiteDND.update(user.persNum, "races", callBackAnswerMessage);
            SQLiteUser.update(tgId, UserState.CLASSES, user.persNum);
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

        try {
            long tgId = update.getCallbackQuery().getMessage().getChatId();
            ArrayList<User> users = SQLiteUser.select(tgId);
            User user = users.get(0);

            SQLiteDND.update(user.persNum, "races", callBackAnswerMessage);
            SQLiteUser.update(tgId, UserState.CLASSES, user.persNum);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return commandCreateClass(convert(update));
    }

    @TgCallback(name = "Выберете класс")
    public SendMessage commandCreateClass(Update update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId());

        try {
            long tgId = update.getMessage().getChatId();
            ArrayList<User> users = SQLiteUser.select(tgId);
            if (!users.isEmpty()) {
                User user = users.get(0);
                sendMessage.setText("Персонаж #%d#. Выберите класс".formatted(user.persNum));
                SQLiteUser.update(tgId, UserState.CLASSES, user.persNum);
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sendMessage;
    }

    @TgCallback(name = "плут")
    public SendMessage commandClass(Update update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());
        String callBackAnswerMessage = update.getCallbackQuery().getData();
        sendMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());

        try {
            long tgId = update.getCallbackQuery().getMessage().getChatId();
            ArrayList<User> users = SQLiteUser.select(tgId);
            User user = users.get(0);

            SQLiteDND.update(user.persNum, "classes", callBackAnswerMessage);
            SQLiteUser.update(tgId, UserState.STREINGHT, user.persNum);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return streinght(convert(update));
    }

    @TgCallback(name = "маг")
    public SendMessage commandClass1(Update update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());
        String callBackAnswerMessage = update.getCallbackQuery().getData();
        sendMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());

        try {
            long tgId = update.getCallbackQuery().getMessage().getChatId();
            ArrayList<User> users = SQLiteUser.select(tgId);
            User user = users.get(0);

            SQLiteDND.update(user.persNum, "classes", callBackAnswerMessage);
            SQLiteUser.update(tgId, UserState.STREINGHT, user.persNum);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return streinght(convert(update));
    }

    @TgCallback(name = "варвар")
    public SendMessage commandClass2(Update update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());
        String callBackAnswerMessage = update.getCallbackQuery().getData();
        sendMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());

        try {
            long tgId = update.getCallbackQuery().getMessage().getChatId();
            ArrayList<User> users = SQLiteUser.select(tgId);
            User user = users.get(0);
            SQLiteDND.update(user.persNum, "classes", callBackAnswerMessage);
            SQLiteUser.update(tgId, UserState.STREINGHT, user.persNum);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return streinght(convert(update));
    }

    @TgCommand(name = "Сила")
    public SendMessage streinght(Update update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId());

        try {
            long tgId = update.getMessage().getChatId();
            ArrayList<User> users = SQLiteUser.select(tgId);
            if (!users.isEmpty()) {
                User user = users.get(0);
                SQLiteUser.update(tgId, UserState.STREINGHT, user.persNum);
                sendMessage.setText("Персонаж #%d#. Впишите силу".formatted(user.persNum));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sendMessage;
    }
    @TgCommand(name = "Ловкость")
    public SendMessage dexterity(Update update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId());

        try {
            long tgId = update.getMessage().getChatId();
            ArrayList<User> users = SQLiteUser.select(tgId);
            if (!users.isEmpty()) {
                User user = users.get(0);
                SQLiteUser.update(tgId, UserState.DEXTERITY, user.persNum);
                sendMessage.setText("Персонаж #%d#. Впишите ловкость".formatted(user.persNum));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sendMessage;
    }
    @TgCommand(name = "Выносливость")
    public SendMessage endurance(Update update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId());

        try {
            long tgId = update.getMessage().getChatId();
            ArrayList<User> users = SQLiteUser.select(tgId);
            if (!users.isEmpty()) {
                User user = users.get(0);
                SQLiteUser.update(tgId, UserState.ENDURANCE, user.persNum);
                sendMessage.setText("Персонаж #%d#. Впишите выносливость".formatted(user.persNum));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sendMessage;
    }
    @TgCommand(name = "Интеллект")
    public SendMessage intellect(Update update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId());

        try {
            long tgId = update.getMessage().getChatId();
            ArrayList<User> users = SQLiteUser.select(tgId);
            if (!users.isEmpty()) {
                User user = users.get(0);
                SQLiteUser.update(tgId, UserState.INTELLECT, user.persNum);
                sendMessage.setText("Персонаж #%d#. Впишите интеллект".formatted(user.persNum));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sendMessage;
    }
    @TgCommand(name = "Мудрость")
    public SendMessage wisdom(Update update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId());

        try {
            long tgId = update.getMessage().getChatId();
            ArrayList<User> users = SQLiteUser.select(tgId);
            if (!users.isEmpty()) {
                User user = users.get(0);
                SQLiteUser.update(tgId, UserState.WISDOM, user.persNum);
                sendMessage.setText("Персонаж #%d#. Впишите мудрость".formatted(user.persNum));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sendMessage;
    }
    @TgCommand(name = "Харизма")
    public SendMessage charisma(Update update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId());

        try {
            long tgId = update.getMessage().getChatId();
            ArrayList<User> users = SQLiteUser.select(tgId);
            if (!users.isEmpty()) {
                User user = users.get(0);
                SQLiteUser.update(tgId, UserState.CHARISMA, user.persNum);
                sendMessage.setText("Персонаж #%d#. Впишите харизма".formatted(user.persNum));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sendMessage;
    }
    @TgCommand(name = "Имя")
    public SendMessage name(Update update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId());

        try {
            long tgId = update.getMessage().getChatId();
            ArrayList<User> users = SQLiteUser.select(tgId);
            if (!users.isEmpty()) {
                User user = users.get(0);
                SQLiteUser.update(tgId, UserState.DEFAULT, user.persNum);
                sendMessage.setText("Персонаж #%d#. Впишите имя".formatted(user.persNum));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sendMessage;
    }

    protected boolean isNumber(String s){
        int cnt = 0;
        for(int i = 0; i < s.length(); i++){
            if ('0' <= s.charAt(i) && s.charAt(i) <= '9'){
                cnt++;
            }
        }
        return cnt == s.length();
    }

    @Override
    public SendMessage commandDefault(Update update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId());

        long tgId = update.getMessage().getChatId();

        try {
            ArrayList<User> users = SQLiteUser.select(tgId);
            User user = users.get(0);
            String userMessage = update.getMessage().getText();

            if (user.state == UserState.STREINGHT) {
                SQLiteDND.update(user.persNum, "strength", update.getMessage().getText());
                SQLiteUser.update(tgId, UserState.DEXTERITY, user.persNum);
                return dexterity(update);
            } else {
                if (isNumber(userMessage)) {
                    if (user.state == UserState.DEXTERITY) {
                        SQLiteDND.update(user.persNum, "dexterity", update.getMessage().getText());
                        SQLiteUser.update(tgId, UserState.DEXTERITY, user.persNum);
                        return endurance(update);
                    } else {
                        if (user.state == UserState.ENDURANCE) {
                            SQLiteDND.update(user.persNum, "endurance", update.getMessage().getText());
                            SQLiteUser.update(tgId, UserState.ENDURANCE, user.persNum);
                            return intellect(update);
                        } else {
                            if (user.state == UserState.INTELLECT) {
                                SQLiteDND.update(user.persNum, "intellect", update.getMessage().getText());
                                SQLiteUser.update(tgId, UserState.ENDURANCE, user.persNum);
                                return wisdom(update);
                            } else {
                                if (user.state == UserState.WISDOM) {
                                    SQLiteDND.update(user.persNum, "wisdom", update.getMessage().getText());
                                    SQLiteUser.update(tgId, UserState.WISDOM, user.persNum);
                                    return charisma(update);
                                } else {
                                    if (user.state == UserState.CHARISMA) {
                                        SQLiteDND.update(user.persNum, "charisma", update.getMessage().getText());
                                        SQLiteUser.update(tgId, UserState.CHARISMA, user.persNum);
                                        return name(update);
                                        }else{
                                            if(user.state == UserState.DEFAULT){
                                                SQLiteDND.update(user.persNum, "name", update.getMessage().getText());
                                                SQLiteUser.update(tgId, UserState.DEFAULT, user.persNum);
                                                sendMessage.setText("конец");
                                                return sendMessage;
                                            }
                                    }
                                }
                            }
                        }
                    }
                }else {
                    sendMessage.setText("error");
                    return sendMessage;
                }
            }
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
}
