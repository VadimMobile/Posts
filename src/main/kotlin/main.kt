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
var attachments = emptyArray<Attachment>(arrayOf(AudioAttachment(Audio(TODO())), PhotoAttachment(Photo(TODO())),
VideoAttachment(Video(TODO())), DocAttachment(Doc(TODO())), LincAttachment(Linc(TODO()))))

class Audio {
    val id = 1
    val owner_id = 2
    val artist = "artist"
    val title = "title"
    val duration = 3
}
 class AudioAttachment(val audio: Audio) : Attachment("audio")

 class Photo{
     val id = 1
     val album_id = 2
     val owner_id = 3
     val user_id = 4
     val text = "text"
}
 class PhotoAttachment(val photo: Photo) : Attachment("photo")

 class Video{
     val id = 1
     val owner_id = 2
     val description = "description"
     val title = "title"
     val views = 3
}
 class VideoAttachment(val video: Video) : Attachment("video")

 class Doc{
     val id = 1
     val owner_id = 2
     val title = "title"
     val size = 3
     val ext = "ext"
}
 class DocAttachment(val doc: Doc) : Attachment("doc")

 class Linc{
     val url = "url"
     val title = "title"
     val description = "description"
     val caption = "caption"
     val preview_page = "preview_page"
    }
 class LincAttachment(val linc: Linc) : Attachment("linc")


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