import org.junit.Test

import org.junit.Assert.*

class notesServiceTest {
    val notesService = notesService(mutableListOf())
    @Test
    fun add() {
        notesService.add("title", "text")
        val result = Note(1,"title", "text")
        assertNotEquals(null,result)
    }

    @Test
    fun createComment() {
        notesService.add("title", "text")
        notesService.add("title", "text")
        notesService.add("title", "text")
        val result = notesService.createComment(1,"message")
            assertNotEquals(null, 3)
    }
    @Test(expected = NoteNotFoundException::class)
    fun shouldThrow() {
            notesService.add("title", "text")
            notesService.add("title", "text")
            notesService.add("title", "text")
        notesService.createComment(4,"message")
    }

    @Test
    fun deleteTrue() {
        notesService.add("title", "text")
        notesService.delete(1)
        assertTrue(notesService.delete(1))
    }
    @Test
    fun deleteFalse() {
        notesService.add("title", "text")
        notesService.delete(1)
        assertFalse(notesService.delete(2))
    }

    @Test
    fun deleteCommentTrue() {
        notesService.add("title", "text")
        notesService.createComment(1,"message")
        assertTrue(notesService.deleteComment(1))
    }

    @Test
    fun deleteCommentFalse() {
        notesService.add("title", "text")
        notesService.createComment(1,"message")
        assertFalse(notesService.deleteComment(2))
    }

    @Test
    fun edit() {
        notesService.add("title", "text")
        val result = notesService.edit(1,"title2", "text2")
        assertEquals(notesService.edit(1,"title2", "text2"),result)
    }

    @Test
    fun editComment() {
        notesService.add("title", "text")
        notesService.createComment(1,"message")
        val result = notesService.editComment(1,"text2")
        assertEquals(notesService.editComment(1,"text2"), result)
    }

    @Test
    fun getAll() {
        notesService.add("title", "text")
        notesService.add("title", "text")
        notesService.add("title", "text")
        var result = notesService.getAll()
        assertNotEquals(null,notesService)
    }

    @Test
    fun getById() {
        notesService.add("title", "text")
        val result = notesService.getById(1)
       assertEquals(1, result)
    }

    @Test
    fun getComments() {
        notesService.add("title", "text")
        val result = notesService.getComments(1)
        assertEquals(1, result)
    }

    @Test
    fun restoreCommentTrue() {
        notesService.add("title", "text")
        notesService.createComment(1,"message")
        notesService.deleteComment(1)
        assertTrue(notesService.restoreComment(1))
    }
    @Test
    fun restoreCommentFalse() {
        notesService.add("title", "text")
        notesService.createComment(1,"message")
        notesService.deleteComment(1)
        assertFalse(notesService.restoreComment(2))
    }
}

