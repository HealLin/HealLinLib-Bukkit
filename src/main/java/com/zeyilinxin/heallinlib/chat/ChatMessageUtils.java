package com.zeyilinxin.heallinlib.chat;

import lombok.Getter;

import java.util.Iterator;
import java.util.List;

/**
 * @author 择忆霖心
 * @version 1.0
 * @date 2020/3/29 18:02
 */
public class ChatMessageUtils {

    private Iterator<String> list;

    public ChatMessageUtils(List<String> list){
        this.list = list.iterator();
    }

    public boolean has(){
        return this.list.hasNext();
    }

    public Chat next(){
        String data = this.list.next();
        if (data.split("`").length == 1){
            return new Chat(data , false , "");
        }
        return new Chat(data.split("`")[0] , true , data.split("`")[1]);
    }

    public class Chat{

        @Getter
        private String name;
        @Getter
        private boolean isChat;
        @Getter
        private String move = "";

        public Chat(String name , boolean chat , String move){
            this.name = name;
            this.isChat = chat;
            this.move = move;
        }
    }
}
