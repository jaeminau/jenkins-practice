package student;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

@TestMethodOrder(OrderAnnotation.class)
class StudentManagerTest {

    private static StudentManager studentManager;

    @BeforeAll
    static void setUpAll() {
        studentManager = new StudentManager();
    }

    @Test
    @Order(1)
    void testRemoveNonExistingStudent() {
        assertThrows(IllegalArgumentException.class, () -> {
            studentManager.removeStudent("김철수");
        });
    }

    @Test
    @Order(2)
    void testAddStudent() {
        studentManager.addStudent("홍길동");
        assertTrue(studentManager.hasStudent("홍길동"));
    }

    @Test
    @Order(3)
    void testAddDuplicateStudent() {
        assertThrows(IllegalArgumentException.class, () -> {
            studentManager.addStudent("홍길동");
        });
    }

    @Test
    @Order(4)
    void testRemoveStudent() {
        studentManager.removeStudent("홍길동");
        assertFalse(studentManager.hasStudent("홍길동"));
    }
    
    @Test
    @Order(5)
    void testRemoveOneStudentDoesNotAffectOtherStudents() {
        studentManager.addStudent("성춘향");
        studentManager.addStudent("이몽룡");

        studentManager.removeStudent("성춘향");

        assertFalse(studentManager.hasStudent("성춘향"));
        assertTrue(studentManager.hasStudent("이몽룡"));
    }
    
    @Test
    @Order(6)
    void testCanReAddStudentAfterRemoval() {
        studentManager.addStudent("임꺽정");
        assertTrue(studentManager.hasStudent("임꺽정"));

        studentManager.removeStudent("임꺽정");
        assertFalse(studentManager.hasStudent("임꺽정"));

        studentManager.addStudent("임꺽정");
        assertTrue(studentManager.hasStudent("임꺽정"));
    }
}

