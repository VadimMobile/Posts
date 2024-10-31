import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class NotesServiceTest {
    @Before
    fun clearBeforeTest() {
        NotesService.clear()
    }
    val NotesService = NotesService(mutableListOf())

    @Test
    fun add() {
        NotesService.add("title", "text")
        val result = Note(1,"title", "text")
        assertNotEquals(null,result)
    }

    @Test
    fun createComment() {
        NotesService.add("title", "text")
        NotesService.add("title", "text")
        NotesService.add("title", "text")
        val result = NotesService.createComment(1,"message")
            assertNotEquals(null, result)
    }

    @Test(expected = NoteNotFoundException::class)
    fun shouldThrow() {
            NotesService.add("title", "text")
            NotesService.add("title", "text")
            NotesService.add("title", "text")
        NotesService.createComment(4,"message")
    }

    @Test
    fun deleteTrue() {
        NotesService.add("title", "text")
        assertTrue(NotesService.delete(1))
    }

    @Test
    fun deleteFalse() {
        NotesService.add("title", "text")
        assertFalse(NotesService.delete(2))
    }

    @Test
    fun deleteCommentTrue() {
        NotesService.add("title", "text")
        NotesService.createComment(1,"message")
        assertTrue(NotesService.deleteComment(1))
    }

    @Test
    fun deleteCommentFalse() {
        NotesService.add("title", "text")
        NotesService.createComment(1,"message")
        assertFalse(NotesService.deleteComment(2))
    }

    @Test
    fun edit() {
        NotesService.add("title", "text")
        val result = NotesService.edit(1,"title2", "text2")
        assertEquals(NotesService.edit(1,"title2", "text2"),result)
    }

    @Test
    fun editComment() {
        NotesService.add("title", "text")
        NotesService.createComment(1,"message")
        val result = NotesService.editComment(1,"text2")
        assertEquals(NotesService.editComment(1,"text2"), result)
    }

    @Test
    fun getAll() {
        NotesService.add("title", "text")
        NotesService.add("title", "text")
        NotesService.add("title", "text")
        var result = NotesService.getAll()
        assertNotEquals(null,NotesService)
    }

    @Test
    fun getById() {
        NotesService.add("title", "text")
        val result = NotesService.getById(1)
        assertEquals(1, result?.id)
    }

    @Test
    fun getComments() {
        NotesService.add("title", "text")
        NotesService.createComment(1,"message")
        val result = NotesService.getComments(1)
        assertEquals(1, result.size)
    }

    @Test
    fun restoreCommentTrue() {
        NotesService.add("title", "text")
        NotesService.createComment(1,"message")
        NotesService.deleteComment(1)
        assertTrue(NotesService.restoreComment(1))
    }

    @Test
    fun restoreCommentFalse() {
        NotesService.add("title", "text")
        NotesService.createComment(1,"message")
        NotesService.deleteComment(1)
        assertFalse(NotesService.restoreComment(2))
    }
}

