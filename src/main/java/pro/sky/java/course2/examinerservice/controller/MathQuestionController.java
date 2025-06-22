package pro.sky.java.course2.examinerservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pro.sky.java.course2.examinerservice.domain.Question;
import pro.sky.java.course2.examinerservice.service.QuestionService;

import java.util.Collection;

@RestController
@RequestMapping("/exam/math")
public class MathQuestionController {
    private final QuestionService questionService;

    @Autowired
    public MathQuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping("/add")
    public Question add(@RequestParam String type, @RequestParam String question, @RequestParam String answer) {
        return questionService.add(type, question, answer);
    }

    @DeleteMapping("/remove")
    public Question remove(@RequestParam String type, @RequestParam String question, @RequestParam String answer) {
        Question unnecessaryQuestion = new Question(type, question, answer);
        return questionService.remove(unnecessaryQuestion);
    }

    @GetMapping
    public Collection<Question> getAll() {
        return questionService.getAll();
    }
}
