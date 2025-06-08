package pro.sky.java.course2.examinerservice.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pro.sky.java.course2.examinerservice.domain.Question;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private final QuestionService javaQuestionService;
    private final QuestionService mathQuestionService;
    private final Random random = new Random();

    public ExaminerServiceImpl(QuestionService javaQuestionService, QuestionService mathQuestionService) {
        this.javaQuestionService = javaQuestionService;
        this.mathQuestionService = mathQuestionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        List<Question> allQuestions = new ArrayList<>();
        allQuestions.addAll(javaQuestionService.getAll());
        allQuestions.addAll(mathQuestionService.getAll());
        if (amount > allQuestions.size()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Количество запрашиваемых вопросов превысило существующий лимит");
        }

        Set<Question> selectedQuestions = new HashSet<>();
        while (selectedQuestions.size() < amount) {
            int index = random.nextInt(allQuestions.size());
            selectedQuestions.add(allQuestions.get(index));

        }
        return selectedQuestions;
    }

    @Override
    public Collection<Question> getQuestionsByType(String type, int amount) {
        List<Question> filteredQuestions;
        switch (type.toLowerCase()) {
            case "java":
                filteredQuestions = new ArrayList<>(javaQuestionService.getAll());
                break;
            case "math":
                filteredQuestions = new ArrayList<>(mathQuestionService.getAll());
                break;
            default:
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Неизвестный тип вопроса");
        }
        if (amount > filteredQuestions.size()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Количество запрашиваемых вопросов превысило существующий лимит");
        }
        Set<Question> selectedQuestions = new HashSet<>();
        while (selectedQuestions.size() < amount) {
            int index = random.nextInt(filteredQuestions.size());
            selectedQuestions.add(filteredQuestions.get(index));
        }
        return selectedQuestions;
    }
}

