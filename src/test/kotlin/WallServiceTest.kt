import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class WallServiceTest {
    @Before
    fun clearBeforeTest() {
        WallService.clear()
    }
    val likes = Likes(100)
    @Test

    fun add() {
        val post = Post(null, 3,"name", "content", 254, likes = likes ,
            true, true, true, true)
        WallService.add(Post(1, 3,"name", "content2", 254, likes = likes ,
            true, true, true, true))
        val result = Post(1, 3,"name", "content2", 254, likes = likes ,
            true, true, true, true)
        assertNotEquals(null,result)
    }
    @Test
    fun updateTrue(){
        WallService.add(Post(1, 3,"name", "content", 254, likes = likes ,
            true, true, true, true))
        WallService.add(Post(2, 3,"name", "content2", 254, likes = likes ,
            true, true, true, true))
        println(WallService.update(Post(1, 3,"name", "content3", 254, likes = likes ,
            true, true, true, true)))
        val result =  WallService.update(Post(1, 3,"name", "content", 254, likes = likes ,
            true, true, true, true))
        assertTrue(result)
    }
    @Test
    fun updateFalse(){
        WallService.add(Post(1, 3,"name", "content", 254, likes = likes ,
            true, true, true, true))
        WallService.add(Post(2, 3,"name", "content2", 254, likes = likes ,
            true, true, true, true))
        println(WallService.update(Post(3, 3,"name", "content3", 254, likes = likes ,
            true, true, true, true)))
        val result = WallService.update(Post(3, 3,"name", "content", 254, likes = likes ,
            true, true, true, true))
        assertFalse(result)
    }
}