package pro.sky.java.course2.examinerservice.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.java.course2.examinerservice.domain.Question;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

public class JavaQuestionRepositoryTest {
    private JavaQuestionRepository javaQuestionRepositoryTest;

    @BeforeEach
    public void setUp() {
        javaQuestionRepositoryTest = new JavaQuestionRepository();
        javaQuestionRepositoryTest.add("java", "Question1", "Answer1");
        javaQuestionRepositoryTest.add("math", "Question1", "Answer1");
        javaQuestionRepositoryTest.add("java", "Question2", "Answer2");
    }

    @Test
    public void testAddQuestion() {
        Question testObject = new Question("java", "Question1", "Answer1");
        javaQuestionRepositoryTest.add(testObject);
        assertTrue(javaQuestionRepositoryTest.getAll().contains(testObject));
    }

    @Test
    public void testAddQuestionWithParameters() {
        String type = "math";
        String question = "Question1";
        String answer = "Answer1";
        Question testObject = javaQuestionRepositoryTest.add(type, question, answer);
        assertTrue(javaQuestionRepositoryTest.getAll().contains(testObject));
    }

    @Test
    public void testRemove() {
        Question objectToRemove = null;
        for (Question question : javaQuestionRepositoryTest.getAll()) {
            if (question.getQuestion().equals("Question2")) {
                objectToRemove = question;
                break;
            }
        }
        assertNotNull(objectToRemove);
        Question removedObject = javaQuestionRepositoryTest.remove(objectToRemove);
        assertEquals(removedObject, objectToRemove);
        assertFalse(javaQuestionRepositoryTest.getAll().contains(removedObject));
    }

    @Test
    public void getAll() {
        Collection<Question> questions = javaQuestionRepositoryTest.getAll();
        assertNotNull(questions);
        assertEquals(3, questions.size());
    }
}
