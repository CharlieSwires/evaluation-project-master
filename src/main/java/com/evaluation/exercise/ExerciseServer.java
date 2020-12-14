package com.evaluation.exercise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evaluation.exercise.model.VolumeRequest;
import com.evaluation.exercise.model.VolumeResponse;

@RestController
@RequestMapping(path = "/DAI")
public class ExerciseServer {

    @Autowired
    private VolumeCalculatorImpl service;
    
    public ExerciseServer(VolumeCalculatorImpl service2) {
        service = service2;
    }

    @PostMapping(path="/volume", consumes="application/json", produces="application/json")
    public ResponseEntity<VolumeResponse> calculateVolume(@RequestBody VolumeRequest request) {

        return service.calculateVolume(request);
    }

}
