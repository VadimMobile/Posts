import org.junit.Test

import org.junit.Assert.*

class NotesServiceTest {

    @Test
    fun add() {
        NotesService.add(Note(1,"title", "text"))
        val result = Note(1,"title", "text")
        assertNotEquals(null,result)
    }

    @Test
    fun createComment() {
        NotesService.add(Note(1,"title", "text"))
        NotesService.add(Note(2,"title", "text"))
        NotesService.add(Note(3,"title", "text"))
        val result = NotesService.createComment(1,"message")
            assertNotEquals(null, 3)
    }
    @Test(expected = NoteNotFoundException::class)
    fun shouldThrow() {
            NotesService.add(Note(1,"title", "text"))
            NotesService.add(Note(2,"title", "text"))
            NotesService.add(Note(3,"title", "text"))
        NotesService.createComment(4,"message")
    }

    @Test
    fun deleteTrue() {
        NotesService.add(Note(1,"title", "text"))
        NotesService.delete(1)
        assertTrue(NotesService.delete(1))
    }
    @Test
    fun deleteFalse() {
        NotesService.add(Note(1,"title", "text"))
        NotesService.delete(1)
        assertFalse(NotesService.delete(2))
    }

    @Test
    fun deleteCommentTrue() {
        NotesService.add(Note(1,"title", "text"))
        NotesService.createComment(1,"message")
        assertTrue(NotesService.deleteComment(1))
    }

    @Test
    fun deleteCommentFalse() {
        NotesService.add(Note(1,"title", "text"))
        NotesService.createComment(1,"message")
        assertFalse(NotesService.deleteComment(2))
    }

    @Test
    fun edit() {
        NotesService.add(Note(1,"title", "text"))
        val result = NotesService.edit(1,"title2", "text2")
        assertEquals(NotesService.edit(1,"title2", "text2"),result)
    }

    @Test
    fun editComment() {
        NotesService.add(Note(1,"title", "text"))
        NotesService.createComment(1,"message")
        val result = NotesService.editComment(1,"text2")
        assertEquals(NotesService.editComment(1,"text2"), result)
    }

    @Test
    fun getAll() {
        NotesService.add(Note(1,"title", "text"))
        NotesService.add(Note(2,"title", "text"))
        NotesService.add(Note(3,"title", "text"))
        var result = items()
        assertNotEquals(null,items())
    }

    @Test
    fun getById() {
        NotesService.add(Note(1,"title", "text"))
        val result = NotesService.getById(1)
       assertEquals(1, result)
    }

    @Test
    fun getComments() {
        NotesService.add(Note(1,"title", "text"))
        val result = NotesService.getComments(1)
        assertEquals(1, result)
    }

    @Test
    fun restoreCommentTrue() {
        NotesService.add(Note(1,"title", "text"))
        NotesService.createComment(1,"message")
        NotesService.deleteComment(1)
        assertTrue(NotesService.restoreComment(1))
    }
    @Test
    fun restoreCommentFalse() {
        NotesService.add(Note(1,"title", "text"))
        NotesService.createComment(1,"message")
        NotesService.deleteComment(1)
        assertFalse(NotesService.restoreComment(2))
    }
}

