package util;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class InlineButtonUtil {

    public static InlineKeyboardButton button(String text, String callbackData){

        InlineKeyboardButton menubutton=new InlineKeyboardButton();
        menubutton.setText(text);
        menubutton.setCallbackData(callbackData);

        return menubutton;
    }

    public static List<InlineKeyboardButton> row(InlineKeyboardButton... inlineKeyboardButtons){
        List<InlineKeyboardButton> row1=new LinkedList<>();
        row1.addAll(Arrays.asList(inlineKeyboardButtons));


        return row1;
    }

    public static List<List<InlineKeyboardButton>> rowCollection(List<InlineKeyboardButton>...rows){
        List<List<InlineKeyboardButton>> rowCollection=new LinkedList<>();
        rowCollection.addAll(Arrays.asList(rows));
        return rowCollection;
    }

    public static InlineKeyboardMarkup readyKeyboard(List<List<InlineKeyboardButton>> rowCollection){
        InlineKeyboardMarkup inlineKeyboardMarkup=new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(rowCollection);

        return inlineKeyboardMarkup;
    }



}
