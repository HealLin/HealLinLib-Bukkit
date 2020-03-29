package com.zeyilinxin.heallinlib.chat;



/**
 * @author 择忆霖心
 * @version 1.0
 * @date 2020/3/28 18:20
 */
public interface CoreChatMessage {

    static CoreChatMessage getInstance(String meesage){
        return new ChatMessage(meesage);
    }
}
