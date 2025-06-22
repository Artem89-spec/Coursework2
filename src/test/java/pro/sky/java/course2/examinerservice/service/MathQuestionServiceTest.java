package pro.sky.java.course2.examinerservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.java.course2.examinerservice.domain.Question;
import pro.sky.java.course2.examinerservice.repository.JavaQuestionRepository;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MathQuestionServiceTest {
    private MathQuestionService serviceTest;

    @BeforeEach
    public void setUp() {
        JavaQuestionRepository javaQuestionRepositoryTest = new JavaQuestionRepository();
        serviceTest = new MathQuestionService(javaQuestionRepositoryTest);
        serviceTest.add("math", "Question1", "Answer1");
        serviceTest.add("math", "Question2", "Answer2");
    }

    @Test
    public void testAddQuestion() {
        Question testObject = new Question("math", "Question3", "Answer3");
        serviceTest.add(testObject);
        assertTrue(serviceTest.getAll().contains(testObject));
    }

    @Test
    public void testAddQuestionWithParameters() {
        String type = "math";
        String question = "Question3";
        String answer = "Answer3";
        Question objectTest = serviceTest.add(type, question, answer);
        assertTrue(serviceTest.getAll().contains(objectTest));
    }

    @Test
    public void testRemove() {
        Question objectToRemove = null;
        for (Question question : serviceTest.getAll()) {
            if (question.getQuestion().equals("Question2")) {
                objectToRemove = question;
                break;
            }
        }
        assertNotNull(objectToRemove);
        Question removedObject = serviceTest.remove(objectToRemove);
        assertEquals(removedObject, objectToRemove);
        assertFalse(serviceTest.getAll().contains(removedObject));
    }

    @Test
    public void testGetAll() {
        Collection<Question> questions = serviceTest.getAll();
        assertNotNull(questions);
        assertEquals(2, questions.size());
    }

    @Test
    public void testRandomEquals() {
        Question testObject = serviceTest.getRandomQuestion();
        assertNotNull(testObject);
        assertTrue(serviceTest.getAll().contains(testObject));
    }
}
