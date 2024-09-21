import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class WallServiceTest {
    @Before
    fun clearBeforeTest() {
        WallService.clear()
    }
    private var posts = emptyArray<Post>()
    private var postId = 0
    @Test
    fun WallService.add(post: Post) {
        posts += post.copy(id = ++postId, likes = post.likes.copy())
        val result = add(postId)
        assertNotEquals(0,result)
    }
    @Test
    fun WallService.updateTrue(newPost: Boolean){
        val likes = Likes(100)
        WallService.add(Post(1, 3,"name", "content", 254, likes = likes ,
            true, true, true, true))
        WallService.add(Post(1, 3,"name", "content2", 254, likes = likes ,
            true, true, true, true))
        println(WallService.update(Post(1, 3,"name", "content3", 254, likes = likes ,
            true, true, true, true)))
        WallService.printPosts()
        val result = updateTrue(true)
        assertEquals(true, result)
    }
    @Test
    fun WallService.updateFalse(newPost: Boolean){
        val likes = Likes(100)
        WallService.add(Post(1, 3,"name", "content", 254, likes = likes ,
            true, true, true, true))
        WallService.add(Post(1, 3,"name", "content2", 254, likes = likes ,
            true, true, true, true))
        println(WallService.update(Post(1, 3,"name", "content3", 254, likes = likes ,
            true, true, true, true)))
        WallService.printPosts()
        val result = updateFalse(false)
        assertEquals(false, result)
    }
}