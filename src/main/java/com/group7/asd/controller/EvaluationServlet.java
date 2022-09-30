package com.group7.asd.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.group7.asd.dao.EvaluationDao;
import com.group7.asd.model.Evaluation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "Evaluation", value = "/evaluation")
public class EvaluationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EvaluationDao evaluationDao = new EvaluationDao();
        ArrayList<Evaluation> evaluationArrayList = evaluationDao.getAllEvaluations();
        JSONArray JSONObj = (JSONArray) JSON.toJSON(evaluationArrayList);
        String JsonStr = JSON.toJSONString(JSONObj, SerializerFeature.PrettyFormat);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().write(JsonStr);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Evaluation evaluation = new Evaluation();
        evaluation.setId(request.getParameter("id"));
        evaluation.setPlatformRating(Integer.parseInt(request.getParameter("nps")));
        evaluation.setFeedback(request.getParameter("textarea"));
        evaluation.setDeliveryPersonFeedback(request.getParameter("selected"));
        evaluation.setAttraction(JSON.parseArray(request.getParameter("checkList"),String.class));
        evaluation.setFoodOverallRating(Integer.parseInt(request.getParameter("value1")));
        evaluation.setFoodPackingRating(Integer.parseInt(request.getParameter("value2")));
        evaluation.setFoodTasteRating(Integer.parseInt(request.getParameter("value3")));
        EvaluationDao evaluationDao = new EvaluationDao();
        evaluationDao.createEvaluation(evaluation);
    }
}
