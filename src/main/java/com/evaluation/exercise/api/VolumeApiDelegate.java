package com.evaluation.exercise.api;

import org.springframework.http.ResponseEntity;

import com.evaluation.exercise.model.VolumeRequest;
import com.evaluation.exercise.model.VolumeResponse;

public interface VolumeApiDelegate {

    ResponseEntity<VolumeResponse> calculateVolume(VolumeRequest volumeRequest);

}
