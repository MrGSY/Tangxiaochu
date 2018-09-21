package com.example.asus.tangtang;

import java.util.ArrayList;

public class Question {
    //题目id
    private String questionId;
    //单选多选标识
    private String type;
    //题目
    private String content;
    //选项
    private ArrayList<Answer> answers;
    //是否解答
    private int que_state;
    //填空输入
    private String que_input;


    public int getQue_state() {
        return que_state;
    }
    public void setQue_state(int que_state) {
        this.que_state = que_state;
    }

    public String getQuestionId() {
        return questionId;
    }
    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public ArrayList<Answer> getAnswers() {
        return answers;
    }
    public void setAnswers(ArrayList<Answer> answers) {
        this.answers = answers;
    }
    public String getQue_input() { return que_input; }
    public void setQue_input(String que_input) { this.que_input = que_input; }
}
