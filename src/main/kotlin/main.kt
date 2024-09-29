import Audio

data class Likes(val count: Int = 0)
data class Post(
    val id: Int?, val authorId: Int?, val authorName: String?, val content: String?, val published: Long?,
    val likes: Likes = Likes(), val canPin: Boolean, val canDelete: Boolean, val canEdit: Boolean,
    val isPinned: Boolean, var attachments: Array<Attachment>
)

object WallService {
    private var posts = emptyArray<Post>()
    private var postId = 0

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
var attachments = emptyArray<Attachment>(arrayOf(AudioAttachment(Audio(...)), PhotoAttachment(Photo(...)),
VideoAttachment(Video(...)), DocAttachment(Doc(...)), LincAttachment(Linc(...))))

abstract class Audio {
    abstract val id: Int
    abstract val owner_id: Int
    abstract val artist: String
    abstract val title: String
    abstract val duration: Int
}
abstract class AudioAttachment(val audio: Audio) : Attachment("audio")

abstract class Photo{
    abstract val id: Int
    abstract val album_id: Int
    abstract val owner_id: Int
    abstract val user_id: Int
    abstract val text: String
}
abstract class PhotoAttachment(val photo: Photo) : Attachment("photo")

abstract class Video{
    abstract val id: Int
    abstract val owner_id: Int
    abstract val description: String
    abstract val title: String
    abstract val views: Int
}
abstract class VideoAttachment(val video: Video) : Attachment("video")

abstract class Doc{
    abstract val id: Int
    abstract val owner_id: Int
    abstract val title: String
    abstract val size: Int
    abstract val ext: String
}
abstract class DocAttachment(val doc: Doc) : Attachment("doc")

abstract class Linc{
    abstract val url: String
    abstract val title: String
    abstract val description: String
    abstract val caption: String
    abstract val preview_page: String
    }
abstract class LincAttachment(val linc: Linc) : Attachment("linc")


fun main() {
    val likes = Likes(100)
    WallService.add(Post(1, 3,"name", "content", 254, likes = likes ,
        true, true, true, true))
    WallService.add(Post(1, 3,"name", "content2", 254, likes = likes ,
        true, true, true, true))
    WallService.printPosts()
    println(WallService.update(Post(1, 3,"name", "content3", 254, likes = likes ,
        true, true, true, true)))
    WallService.printPosts()
}