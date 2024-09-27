data class Likes(val count: Int = 0)
data class Post(
    val id: Int?, val authorId: Int?, val authorName: String?, val content: String?, val published: Long?,
    val likes: Likes = Likes(), val canPin: Boolean, val canDelete: Boolean, val canEdit: Boolean, val isPinned: Boolean,
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
abstract class Attachment () {

}

class Audio
class AudioAttachment(val audio: Audio) : Attachment()

class Photo
class PhotoAttachment(val audio: Photo) : Attachment()

class Video
class VideoAttachment(val audio: Video) : Attachment()

class Doc
class DocAttachment(val audio: Doc) : Attachment()

class Linc
class LincAttachment(val audio: Linc) : Attachment()


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