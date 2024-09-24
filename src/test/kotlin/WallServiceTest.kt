import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class WallServiceTest {
    @Before
    fun clearBeforeTest() {
        WallService.clear()
    }
    private var postId = 0
    @Test

    fun add() {
        val likes = Likes(100)
        val post = Post(0, 3,"name", "content", 254, likes = likes ,
            true, true, true, true)
        WallService.add(post)
        val result = postId
        assertNotEquals(0,result)
    }
    @Test
    fun updateTrue(){
        val likes = Likes(100)
        WallService.add(Post(1, 3,"name", "content", 254, likes = likes ,
            true, true, true, true))
        WallService.add(Post(2, 3,"name", "content2", 254, likes = likes ,
            true, true, true, true))
        println(WallService.update(Post(1, 3,"name", "content3", 254, likes = likes ,
            true, true, true, true)))
        WallService.printPosts()
        val result = true
        assertEquals(true, result)
    }
    @Test
    fun updateFalse(){
        val likes = Likes(100)
        WallService.add(Post(1, 3,"name", "content", 254, likes = likes ,
            true, true, true, true))
        WallService.add(Post(2, 3,"name", "content2", 254, likes = likes ,
            true, true, true, true))
        println(WallService.update(Post(3, 3,"name", "content3", 254, likes = likes ,
            true, true, true, true)))
        WallService.printPosts()
        val result = false
        assertEquals(false, result)
    }
}