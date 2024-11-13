data class Likes(val count: Int = 0)
data class Post(
    val id: Int?, val authorId: Int?, val authorName: String?, val content: String?, val published: Long?,
    val likes: Likes = Likes(), val canPin: Boolean, val canDelete: Boolean, val canEdit: Boolean,
    val isPinned: Boolean, var attachments: Array<Attachment>
)
data class Note(val id: Int, var title: String, var text: String)
data class CommentWithNotes(val id: Int, val text: String, val noteId: Int, var isDeleted: Boolean = false)

data class Message(val text: String, var read: Boolean = false)
data class Chat(val messages: MutableList<Message> = mutableListOf())

object ChatService{
private var chats = mutableMapOf(10 to Chat())

    fun addMessage(userId: Int, message: Message){
        chats.getOrPut(userId) { Chat() }.messages += message
    }

    fun getUnreadChatsCount() = chats.values.count { chat -> chat.messages.any { !it.read } }

    fun getLastMessage() = chats.values.map { it.messages.lastOrNull()?.text ?: "No messages"}

    fun getMessage(userId: Int, count: Int): List <Message>{
        val chat = chats[userId] ?: throw NoSuchChatException()
        return chat.messages.takeLast(count).onEach { it.read = true }
    }

    fun deleteMessage(message: Message) {
        chats.values.forEach { chat -> chat.messages.remove(message) }
    }

    fun addChat(userId: Int){
        chats.getOrPut(userId) { Chat() }
    }


    fun deleteChat(userId: Int) = chats.remove(userId)

    fun printChats(){
        println(chats)
    }
}
class NoSuchChatException : Throwable()

class NotesService(var items: MutableList<Note>) {
    private var noteIdCounter = 0
    private var commentIdCounter = 0
    private var comments = emptyArray<CommentWithNotes>()

    fun clear() {
        items.clear()
        commentIdCounter = 0
        comments = emptyArray()
        noteIdCounter = 0
    }

    fun add(title: String, text: String): Note {
        val note = Note(++noteIdCounter, title, text)
        items += note
        return note
    }

    fun createComment(noteId: Int, message: String): CommentWithNotes {
        if (items.any { it.id == noteId }) {
            val comment = CommentWithNotes(++commentIdCounter, message, noteId, false)
            comments += comment
            return comment
        } else {
            throw NoteNotFoundException("Note with ID $noteId not found")
        }
    }

    fun delete(noteId: Int): Boolean {
        for (item in items) {
            if (item.id == noteId) {
                items.remove(item)
                return true
            }
        }
        return false
    }



    fun deleteComment(commentId: Int): Boolean {
        val comment = comments.find { it.id == commentId }
        comment?.let {
            it.isDeleted = true
            return true
        }
        return false
    }

    fun edit(noteId: Int, title: String, text: String): Note? {
        val note = items.find { it.id == noteId }
        return note?.let {
            val updatedNote = it.copy(title = title, text = text)
            items[items.indexOf(note)] = updatedNote
            updatedNote
        }
    }


    fun editComment(commentId: Int, text: String): CommentWithNotes? {
        val comment = comments.find { it.id == commentId }
        return comment?.let {
            val updatedComment = it.copy(text = text)
            comments[comments.indexOf(comment)] = updatedComment
            updatedComment
        }
    }
    fun getAll(): List<Note> = items.toList()

    fun getById(noteId: Int): Note? {
        for (item in items) {
            if (item.id == noteId) {
                return item
            }
        }
        return null
    }

    fun getComments(noteId: Int): List<CommentWithNotes> {
        val commentsResult = mutableListOf<CommentWithNotes>()
        for (comment in comments) {
            if (comment.noteId == noteId) {
                commentsResult.add(comment)
            }
        }
        return comments.toList()
    }

    fun restoreComment(commentId: Int): Boolean {
        val comment = comments.find { it.id == commentId }
        comment?.let {
            it.isDeleted = false
            return true
        }
        return false
    }
}

object WallService {
    private var posts = emptyArray<Post>()
    private var comments = emptyArray<CommentWithPosts>()
    private var postId = 0
    fun createComment(postId: Int, comment: CommentWithPosts): CommentWithPosts {
            for ((index, post) in posts.withIndex()) {
                if (posts[index].id == postId) {
                    comments += comment
                    return comments.last()
                }
            }
        throw PostNotFoundException ("Пост не найден")
    }

    fun clear() {
        posts = emptyArray()
        postId = 0
    }

    fun add(post: Post): Post {
        posts += post.copy(id = ++postId, likes = post.likes.copy())
        return posts.last()
    }

    fun printPosts() {
        for (post in posts)
            println(post)
    }

    fun update(newPost: Post): Boolean {
        for ((index, post) in posts.withIndex()) {
            if (posts[index].id == newPost.id) {
                posts[index] = newPost.copy(likes = post.likes.copy())
                return true
            }
        }
        return false
    }
}

abstract class Attachment (val type: String)
var attachments = arrayOf(AudioAttachment(Audio(1,2,"artist","title",3)),
    PhotoAttachment(Photo(1,2,3,4, "text")),
    VideoAttachment(Video(1,2,"description", "title", 3)),
    DocAttachment(Doc(1,2, "title", 3, "ext")),
    LincAttachment(Linc("url", "title", "description", "caption", "preview_page")))

class Audio (
    val id: Int,
    val owner_id: Int,
    val artist: String,
    val title: String,
    val duration: Int,
)
 class AudioAttachment(val audio: Audio) : Attachment("audio")

 class Photo(
     val id: Int,
     val album_id: Int,
     val owner_id: Int,
     val user_id: Int,
     val text: String
 )
 class PhotoAttachment(val photo: Photo) : Attachment("photo")

 class Video(
     val id: Int,
     val owner_id: Int,
     val description: String,
     val title: String,
     val views: Int,
 )
 class VideoAttachment(val video: Video) : Attachment("video")

 class Doc(
     val id: Int,
     val owner_id: Int,
     val title: String,
     val size: Int,
     val ext: String
     )
 class DocAttachment(val doc: Doc) : Attachment("doc")

 class Linc(
     val url: String,
     val title: String,
     val description: String,
     val caption: String,
     val preview_page: String
     )
 class LincAttachment(val linc: Linc) : Attachment("linc")

class CommentWithPosts (val id: Int?, val from_id: Int?, val date: Int, val text: String)

class PostNotFoundException (message: String) : Exception(message)
class NoteNotFoundException (message: String) : Exception(message)

fun main() {
    val likes = Likes(100)
    WallService.add(Post(1, 3,"name", "content", 254, likes = likes ,
        true, true, true, true, attachments))
    WallService.add(Post(1, 3,"name", "content2", 254, likes = likes ,
        true, true, true, true, attachments))
    WallService.printPosts()
    println(WallService.update(Post(1, 3,"name", "content3", 254, likes = likes ,
        true, true, true, true, attachments)))
    WallService.printPosts()

    ChatService.addMessage(1,Message("Hello"))
    ChatService.addMessage(2,Message("Hi"))
    ChatService.addMessage(2,Message("How are you?"))

    ChatService.printChats()
    println(ChatService.getUnreadChatsCount())
println(ChatService.getLastMessage())
    println(ChatService.getMessage(2,1))
    ChatService.printChats()
}
