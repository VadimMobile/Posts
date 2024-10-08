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
            true, true, true, true, attachments)
        WallService.add(Post(1, 3,"name", "content2", 254, likes = likes ,
            true, true, true, true, attachments))
        val result = Post(1, 3,"name", "content2", 254, likes = likes ,
            true, true, true, true, attachments)
        assertNotEquals(null,result)
    }
    @Test
    fun updateTrue(){
        WallService.add(Post(1, 3,"name", "content", 254, likes = likes ,
            true, true, true, true, attachments))
        WallService.add(Post(2, 3,"name", "content2", 254, likes = likes ,
            true, true, true, true, attachments))
        println(WallService.update(Post(1, 3,"name", "content3", 254, likes = likes ,
            true, true, true, true, attachments)))
        val result =  WallService.update(Post(1, 3,"name", "content", 254, likes = likes ,
            true, true, true, true, attachments))
        assertTrue(result)
    }

   @Test(expected = PostNotFoundException::class)
   fun shouldThrow() {
       WallService.add(Post(1, 3,"name", "content", 254, likes = likes ,
           true, true, true, true, attachments))
       WallService.add(Post(2, 3,"name", "content2", 254, likes = likes ,
           true, true, true, true, attachments))
       WallService.add(Post(3, 3,"name", "content2", 254, likes = likes ,
           true, true, true, true, attachments))
       WallService.createComment(4, Comment(1,5,3,"4"))
   }
    @Test
    fun createComment() {
        WallService.add(Post(1, 3,"name", "content", 254, likes = likes ,
            true, true, true, true, attachments))
        WallService.add(Post(2, 3,"name", "content2", 254, likes = likes ,
            true, true, true, true, attachments))
        WallService.add(Post(3, 3,"name", "content2", 254, likes = likes ,
            true, true, true, true, attachments))
        val result = WallService.createComment(3,Comment(1,5,3,"4"))
        assertNotEquals(null,result)
    }

}


