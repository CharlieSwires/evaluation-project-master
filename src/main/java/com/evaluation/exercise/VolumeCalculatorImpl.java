package com.evaluation.exercise;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.evaluation.exercise.api.VolumeApiDelegate;
import com.evaluation.exercise.model.VolumeRequest;
import com.evaluation.exercise.model.VolumeRequest.Box;
import com.evaluation.exercise.model.VolumeResponse;

@Service
public class VolumeCalculatorImpl implements VolumeApiDelegate {

    @Override
    public ResponseEntity<VolumeResponse> calculateVolume(VolumeRequest volumeRequest) {

        Double sum = 0.0;
        for(Box item : volumeRequest.getBoxes()) {
            if(item == null) return new ResponseEntity<VolumeResponse>((VolumeResponse)null, HttpStatus.BAD_REQUEST);
            if(item.getWidth() < 0 || item.getHeight() < 0 || item.getDepth() < 0) 
                return new ResponseEntity<VolumeResponse>((VolumeResponse)null, HttpStatus.BAD_REQUEST);
            Double volume = item.getWidth() * item.getHeight() *item.getDepth();
            sum += volume;
        }
        // TODO Advanced: How can we map exceptions to the ErrorResponse class, so that the error is returned as specified?

        VolumeResponse response = new VolumeResponse();
        response.setResult(sum);
        return new ResponseEntity<VolumeResponse>(response, HttpStatus.OK);
    }
}
