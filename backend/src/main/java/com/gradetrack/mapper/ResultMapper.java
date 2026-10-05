package com.gradetrack.mapper;

import com.gradetrack.dto.MarksResponse;
import com.gradetrack.dto.ResultResponse;
import com.gradetrack.entity.Marks;
import com.gradetrack.entity.Result;
import org.springframework.stereotype.Component;

@Component
public class ResultMapper {

    public ResultResponse toResponse(Result result) {

        ResultResponse response = new ResultResponse();

        response.setId(result.getId());
        response.setStudentId(result.getStudent().getId());
        response.setTotal(result.getTotal());
        response.setPercentage(result.getPercentage());
        response.setGrade(result.getGrade());
        response.setStatus(result.getStatus());
        response.setRank(result.getRank());
        response.setCreatedAt(result.getCreatedAt());
        response.setUpdatedAt(result.getUpdatedAt());

        if (result.getMarks() != null) {
            response.setMarks(toMarksResponse(result.getMarks()));
        }

        return response;
    }

    private MarksResponse toMarksResponse(Marks marks) {

        MarksResponse response = new MarksResponse();

        response.setId(marks.getId());
        response.setEnglish(marks.getEnglish());
        response.setMathematics(marks.getMathematics());
        response.setPhysics(marks.getPhysics());
        response.setChemistry(marks.getChemistry());
        response.setComputer(marks.getComputer());
        response.setHindi(marks.getHindi());

        return response;
    }
}