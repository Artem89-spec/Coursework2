package pro.sky.java.course2.examinerservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;
import pro.sky.java.course2.examinerservice.domain.Question;
import pro.sky.java.course2.examinerservice.repository.JavaQuestionRepository;


import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

public class ExaminerServiceImplTest {
    private JavaQuestionService javaQuestionServiceTest;
    private MathQuestionService mathQuestionServiceTest;
    private ExaminerServiceImpl examinerServiceTest;


    @BeforeEach
    public void setUp() {
        JavaQuestionRepository javaQuestionRepositoryTest = new JavaQuestionRepository();
        javaQuestionServiceTest = new JavaQuestionService(javaQuestionRepositoryTest);
        javaQuestionServiceTest.add("java", "Question1", "Answer1");
        javaQuestionServiceTest.add("java", "Question2", "Answer2");
        javaQuestionServiceTest.add("java", "Question3", "Answer3");
        mathQuestionServiceTest = new MathQuestionService(javaQuestionRepositoryTest);
        mathQuestionServiceTest.add("math", "Question1", "Answer1");
        mathQuestionServiceTest.add("math", "Question2", "Answer2");
        mathQuestionServiceTest.add("math", "Question3", "Answer3");
        examinerServiceTest = new ExaminerServiceImpl(javaQuestionServiceTest, mathQuestionServiceTest);
    }

    @Test
    public void testGetQuestionsValidAmount() {
        int amount = 2;
        Collection<Question> questions = examinerServiceTest.getQuestions(amount);
        assertEquals(2, questions.size());
    }

    @Test
    public void testGetQuestionsMoreThanPerhaps() {
        int amount = 10;
        try {
            Collection<Question> questions = examinerServiceTest.getQuestions(amount);
            fail("Ожидается выброс исключения типа ResponseStatusException");
        } catch (ResponseStatusException e) {
            HttpStatusCode statusCode = e.getStatusCode();
            assertEquals(HttpStatus.BAD_REQUEST, statusCode);
        }
    }

    @Test
    public void testGetQuestionsByTypeValidAmountJava() {
        int amount = 2;
        Collection<Question> questions = examinerServiceTest.getQuestionsByType("java", amount);
        assertEquals(questions.size(), amount);
        for (Question question : questions) {
            assertTrue(question.getType().contains("java"));
        }
    }

    @Test
    public void testGetQuestionsByTypeValidAmountMath() {
        int amount = 2;
        Collection<Question> questions = examinerServiceTest.getQuestionsByType("math", amount);
        assertEquals(questions.size(), amount);
        for (Question question : questions) {
            assertTrue(question.getType().contains("math"));
        }
    }

    @Test
    public void testGetQuestionsByTypeMoreThanPerhaps() {
        String type = "java";
        int amount = 15;
        try {
            Collection<Question> questions = examinerServiceTest.getQuestionsByType(type, amount);
            fail("Ожидается выброс исключения типа ResponseStatusException");
        } catch (ResponseStatusException e) {
            HttpStatusCode statusCode = e.getStatusCode();
            assertEquals(HttpStatus.BAD_REQUEST, statusCode);
        }
    }

    @Test
    public void testGetQuestionsByTypeUnknownType() {
        String type = "unknown";
        int amount = 3;
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            examinerServiceTest.getQuestionsByType(type, amount);
        });
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }
}
