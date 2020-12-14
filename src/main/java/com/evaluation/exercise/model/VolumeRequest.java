package com.evaluation.exercise.model;

import java.util.Arrays;

public class VolumeRequest {
    public VolumeRequest() {
    }
    private Box[] boxes;
    public Box[] getBoxes() {
        return boxes;
    }
    public void setBoxes(Box[] box) {
        this.boxes = box;
    }
    @Override
    public String toString() {
        return "{\"boxes\": [" + Arrays.toString(boxes) + "]}";
    }
    public static class Box {
        private Double width;
        private Double height;
        private Double depth;
        public Double getWidth() {
            return width;
        }
        public void setWidth(Double width) {
            this.width = width;
        }
        public Double getHeight() {
            return height;
        }
        public void setHeight(Double height) {
            this.height = height;
        }
        public Double getDepth() {
            return depth;
        }
        public void setDepth(Double depth) {
            this.depth = depth;
        }
        @Override
        public String toString() {
            return "{\"width\":" + width + ", \"height\":" + height + ", \"depth\":" + depth
                    + "}";
        }     
    }
}
