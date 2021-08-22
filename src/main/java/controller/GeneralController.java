package controller;

import dto.CodeMessage;
import enums.CodeMessageType;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import util.InlineButtonUtil;

import java.util.LinkedList;
import java.util.List;

public class GeneralController {

    public CodeMessage handle(String text, Long chatId, Integer messageId){
        CodeMessage codeMessage=new CodeMessage();



        SendMessage sendMessage=new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));


        codeMessage.setSendMessage(sendMessage);



        if (text.equals("/start")){
            sendMessage.setText("*Welcome to TO-DO-LIST*");
            sendMessage.setParseMode("Markdown");

            InlineKeyboardButton menu= InlineButtonUtil.button("Go to menu","menu");

            List<InlineKeyboardButton> row=InlineButtonUtil.row(menu);

            List<List<InlineKeyboardButton>> rowCollection=InlineButtonUtil.rowCollection(row);

            InlineKeyboardMarkup keyboard=InlineButtonUtil.readyKeyboard(rowCollection);

            sendMessage.setReplyMarkup(keyboard);


            /////BUTTON
            codeMessage.setType(CodeMessageType.MESSAGE);

        }else if (text.equals("/help")){
            String msg="*TO-DO-LIST*\n You can make your daily to-do-list by using this bot.\n"+
                    "Watch this video [youtube channel](https://www.youtube.com/watch?v=vmrBqWO2IIo) " +
                    "to get information";

            sendMessage.setText(msg);
            sendMessage.setParseMode("Markdown");
            sendMessage.disableWebPagePreview();
            codeMessage.setSendMessage(sendMessage);
            codeMessage.setType(CodeMessageType.MESSAGE);

            ///video

        }else if (text.equals("/setting")){

            sendMessage.setText("*Waiting.....*");
            sendMessage.setParseMode("Markdown");
            codeMessage.setType(CodeMessageType.MESSAGE);




        }else if (text.equals("menu")){

            EditMessageText editMessageText=new EditMessageText();
            editMessageText.setText("*Siz menu tugmasini bosdingiz!*");
            editMessageText.setParseMode("Markdown");
            editMessageText.setChatId(String.valueOf(chatId));
            editMessageText.setMessageId(messageId);

            InlineKeyboardButton button=InlineButtonUtil.button("To-Do-List","/todo/list");
            InlineKeyboardButton button1=InlineButtonUtil.button("Create New","/todo/create");
            List<InlineKeyboardButton> row=InlineButtonUtil.row(button,button1);
            List<List<InlineKeyboardButton>> collection=InlineButtonUtil.rowCollection(row);
            InlineKeyboardMarkup keyboard=InlineButtonUtil.readyKeyboard(collection);
            sendMessage.setReplyMarkup(keyboard);
            codeMessage.setType(CodeMessageType.EDIT);
            codeMessage.setEditMessageText(editMessageText);
            return codeMessage;
        }


        return codeMessage;
    }

}
