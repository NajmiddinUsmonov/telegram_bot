package controller;

import dto.CodeMessage;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import service.FileInfoService;

import java.util.LinkedList;
import java.util.List;

public class Maincontroller extends TelegramLongPollingBot {
    private GeneralController generalController;
    private FileInfoService fileInfoService;



    public Maincontroller() {
        this.generalController = new GeneralController();
        this.fileInfoService=new FileInfoService();
    }


    @Override
    public void onUpdateReceived(Update update) {

        Message message = update.getMessage();//narsalar solish uchun karobga

        if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            String data = callbackQuery.getData();
            User user1 = callbackQuery.getFrom();
//            Message message1 = callbackQuery.getMessage();

            if (data.equals("menu")) {
                sendMes(this.generalController.handle(data, message.getChatId(), message.getMessageId()));

            }
            } else if (message != null) {
                String text = message.getText();


                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(message.getChatId().toString());
                Integer messageId = message.getMessageId();




                if (text!=null){

                    if (text.equals("/start") || text.equals("/help") || text.equals("/setting")) {
                        sendMes(this.generalController.handle(text, message.getChatId(), messageId));

                    }else{
                        sendMessage.setText("Mavjud emas");
                        this.sendMes(sendMessage);
                    }
                }else{

                  this.sendMes(this.fileInfoService.getFileInfo(message));
                }



            User user = message.getFrom();

//                System.out.println("MessageId= " + messageId + " User_name= " + user.getFirstName() + " User_username= " + user.getUserName() + " Message= " + text);



            }
        }






        public void sendMes(SendMessage sendMessage){
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

        public void sendMes(CodeMessage codeMessage){
            try {
                switch (codeMessage.getType()){
                    case MESSAGE:
                        execute(codeMessage.getSendMessage());
                        break;
                    case EDIT:
                        execute(codeMessage.getEditMessageText());
                        break;
                    default:
                        break;
                }
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

        }


        @Override
        public String getBotUsername () {
            return "najosman_bot";
        }

        @Override
        public String getBotToken () {
            return "1673144248:AAH8VFkIz2im9uHoXaLSfR3J1wkXDuQmXm0";
        }



}