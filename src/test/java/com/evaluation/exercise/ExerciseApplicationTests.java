package com.evaluation.exercise;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import com.evaluation.exercise.model.VolumeRequest;
import com.evaluation.exercise.model.VolumeRequest.Box;
import com.evaluation.exercise.model.VolumeResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
class ExerciseApplicationTests {
    private static ExerciseServer server = null;
    private static final Double delta = 0.00001;

    @BeforeAll
    static void startUp() {
        VolumeCalculatorImpl service = new VolumeCalculatorImpl();
        server = new ExerciseServer(service);
    }
    @Test
    void contextLoads() {
        // This test is assertionless. It just makes sure the Spring context loads.
    }

    /*
		TODO Use the Java 11 HTTP Client, provided by the JDK, to test the volume calculator rest API
     */
    private HttpResponse<String> sendVolumeRequest(VolumeRequest value){
        String sampleData = value.toString();

        HttpRequest request = null;
        try {
            request = HttpRequest.newBuilder()
                    .uri(new URI("https://localhost:8080/DAI/volume"))
                    .headers("Content-Type", "application/json;charset=UTF-8")
                    .POST(HttpRequest.BodyPublishers.ofString(sampleData))
                    .build();
        } catch (URISyntaxException e) {
            throw new RuntimeException("URISyntaxException");
        }
        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient()
                    .send(request, BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("IOException | InterruptedException");
        }
        return response;
    }

    @Test
    void zeroBoxesTotalVolumeIsZero() {
        Box[] box = new Box[0];
        VolumeRequest request = new VolumeRequest();
        request.setBoxes(box);
        Assertions.assertEquals(0.0, server.calculateVolume(request).getBody().getResult());

//        HttpResponse<String> response = sendVolumeRequest(request);
//        VolumeResponse resp = null;
//        ObjectMapper mapper = new ObjectMapper();
//        if(response.statusCode() ==200) {
//            try {
//                resp = mapper.readValue(response.toString(), VolumeResponse.class);
//                Assertions.assertEquals(0.0, resp.getResult());
//
//            } catch (JsonProcessingException e) {
//                throw new RuntimeException("JsonProcessingException");
//
//            }
//        }
    }

    @Test
    void oneBox() {
        Box[] boxes = new Box[1];
        Box box = new Box();
        box.setWidth(123.0);
        box.setHeight(234.0);
        box.setDepth(345.0);
        boxes[0] = box;
        VolumeRequest request = new VolumeRequest();
        request.setBoxes(boxes);
        Assertions.assertEquals(123.0*234.0*345.0, server.calculateVolume(request).getBody().getResult());
    }

    @Test
    void manyBoxes() {
        Box[] boxes = new Box[2];
        Box box = new Box();
        box.setWidth(190.1);
        box.setHeight(100.2);
        box.setDepth(250.3);
        boxes[0] = box;
        box = new Box();
        box.setWidth(50.0);
        box.setHeight(60.0);
        box.setDepth(70.0);
        boxes[1] = box;
        VolumeRequest request = new VolumeRequest();
        request.setBoxes(boxes);
        Assertions.assertEquals(190.1*100.2*250.3+50.0*60.0*70.0, server.calculateVolume(request).getBody().getResult());
    }

    @Test
    void nullBoxesResultsInHttp400() {
        Box[] boxes = new Box[2];
        Box box = new Box();
        boxes[0] = box;
        box.setWidth(190.1);
        box.setHeight(100.2);
        box.setDepth(250.3);
        boxes[1]=null;
        VolumeRequest request = new VolumeRequest();
        request.setBoxes(boxes);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, server.calculateVolume(request).getStatusCode());
    }

    //	@Test conflicts with zero boxes test above.
    //	void zeroDimensionResultsInHttp400() {
    //        Box[] box = new Box[0];
    //        VolumeRequest request = new VolumeRequest();
    //        request.setBoxes(box);
    //        Assertions.assertEquals(HttpStatus.BAD_REQUEST, server.calculateVolume(request).getStatusCode());
    //	}

    @Test
    void negativeDimensionResultsInHttp400() {
        Box[] boxes = new Box[1];
        Box box = new Box();
        boxes[0] = box;
        box.setWidth(-123.0);
        box.setHeight(234.0);
        box.setDepth(345.0);
        VolumeRequest request = new VolumeRequest();
        request.setBoxes(boxes);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, server.calculateVolume(request).getStatusCode());
    }
}
