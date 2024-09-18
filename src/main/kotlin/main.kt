data class Post(
    val id: Int, val authorId: Int, val authorName: String, val content: String, val published: Long,
    val likes: Int, val canPin: Boolean, val canDelete: Boolean, val canEdit: Boolean, val isPinned: Boolean
)

object WallService{
    private var posts = emptyArray<Post>()
    private var postId = 0
    fun add (post: Post): Post{
        for ((index, post) in posts.withIndex())
        posts += post
        postId++
        return posts.last()
    }

    fun printPosts(){
        for (post in posts)
            print(postId)
        println(posts)
    }
fun update(post: Post): Boolean {
    if (postId == post.id)
        return true
    else
    return false
}
    }
fun main() {
    val post = Post(
        1, 2, "name", "content", 3,
        543, true, true, true, true
    )
    val liked = post.copy(likes = post.likes + 10)
    val (_,_,_,_,_,likes) = post
    println(likes)
    println(liked)
    println(printPosts)
}