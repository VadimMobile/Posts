data class Likes(val count: Int = 0)
data class Post(
    val id: Int?, val authorId: Int?, val authorName: String?, val content: String?, val published: Long?,
    val likes: Likes = Likes(), val canPin: Boolean, val canDelete: Boolean, val canEdit: Boolean,
    val isPinned: Boolean, var attachments: Array<Attachment>
)
data class Notes <T>(
    add(val title: String, val text: String, val privacy: Integer, val comment_privacy: Integer,
    val privacy_view: String, val privacy_comment: String),
    createComment(val note_id: String, val message: String, val guid: String),
    delete(val note_id: String),
    deleteComment(val comment_id: Positive),
    edit((val title: String, val text: String, val note_id: String),
    editComment(val comment_id: Positive),
    get(val note_ids: String, val user_id: Positive, val offset: Positive, val count: Positive, val sort: Positive),
    getById(val note_id: Positive),
    getComments(val note_id: Positive),
    restoreComment(val comment_id: Positive)
)

object WallService {
    private var posts = emptyArray<Post>()
    private var comments = emptyArray<Comment>()
    private var postId = 0
    fun createComment(postId: Int, comment: Comment): Comment {
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
var attachments =arrayOf(AudioAttachment(Audio(1,2,"artist","title",3)),
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

class Comment (val id: Int?, val from_id: Int?, val date: Int, val text: String)

class PostNotFoundException (message: String) : Exception(message)


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
}