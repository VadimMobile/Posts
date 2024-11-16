import org.junit.Test

import org.junit.Assert.*

class ChatServiceTest {

    @Test
    fun addMessage() {
        val result = ChatService.addMessage(1, Message("text", false))
        assertNotEquals(1, result)
    }

    @Test
    fun getUnreadChatsCount() {
         ChatService.addMessage(1, Message("text", false))
         ChatService.addMessage(2, Message("text", true))
         ChatService.addMessage(3, Message("text", false))
        val result = ChatService.getUnreadChatsCount()
        assertEquals(2,result)
    }

    @Test
    fun getLastMessage() {
        ChatService.addMessage(1, Message("text", false))
        val result = ChatService.getLastMessage()
        assertEquals(Message("text"),result)
    }

    @Test
    fun noGetLastMessage() {
        ChatService.addMessage(1, Message("text", false))
        ChatService.addMessage(2, Message("text", true))
        ChatService.addMessage(3, Message("text", false))
        val result = ChatService.getLastMessage()
        assertEquals("No messages",result)
    }

    @Test
    fun getMessage() {
        ChatService.addMessage(1, Message("text", false))
        ChatService.addMessage(2, Message("text", true))
        ChatService.addMessage(3, Message("text", false))
        val result = ChatService.getMessage(1,1)
        assertEquals(1, result.size)
    }

    @Test
    fun deleteMessage() {
        ChatService.addMessage(1, Message("text", false))
        ChatService.addMessage(2, Message("text", true))
        ChatService.addMessage(3, Message("text", false))
        val result = ChatService.deleteMessage(Message("text"))
        assertNull(result)
    }

    @Test
    fun addChat() {
        ChatService.addMessage(1, Message("text", false))
        val result = ChatService.addChat(1)
        assertNotNull(result)
    }

    @Test
    fun deleteChat() {
        ChatService.addMessage(1, Message("text", false))
        ChatService.addMessage(2, Message("text", true))
        ChatService.addMessage(3, Message("text", false))
        val result = ChatService.deleteChat(1)
        assertNull(result)
    }
}