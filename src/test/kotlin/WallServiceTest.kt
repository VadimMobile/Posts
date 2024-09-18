import org.junit.Test

import org.junit.Assert.*

class WallServiceTest {
    private var posts = emptyArray<Post>()
    private var postId = 0
    @Test
    fun add() {
        for ((index, post) in posts.withIndex())
            posts += post
        postId++
        val result = add(postId)
        assertEquals(1,result)
    }
    @Test
    fun update(){
        if (postId == post.id)
            return true
        else
            return false
    }
}