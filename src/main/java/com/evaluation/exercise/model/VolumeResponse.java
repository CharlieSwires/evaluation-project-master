package com.evaluation.exercise.model;

public class VolumeResponse {

    private Double result;

    public VolumeResponse() {
    }

    public Double getResult() {
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "VolumeResponse [result=" + result + "]";
    }
    
}
