package com.jafar.pockesoz.controller;

import com.jafar.pockesoz.entity.Pocket;
import com.jafar.pockesoz.model.request.PocketRequest;
import com.jafar.pockesoz.model.response.CommonResponse;
import com.jafar.pockesoz.model.response.PocketResponse;
import com.jafar.pockesoz.service.PocketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/pockets")
public class PocketController {

    private final PocketService pocketService;

    @PostMapping
    public ResponseEntity<?> createPocket(@RequestBody PocketRequest request) {
        PocketResponse pocketResponse = pocketService.create(request);
        CommonResponse<Object> commonResponse = CommonResponse.builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("success created pocket")
                .data(pocketResponse)
                .build();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(commonResponse);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getPocketById(@PathVariable String id) {
        PocketResponse pocketResponse = pocketService.getById(id);
        CommonResponse<Object> commonResponse = CommonResponse.builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("success get pocket")
                .data(pocketResponse)
                .build();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(commonResponse);
    }

    @GetMapping(path = "/myPockets/{userId}")
    public ResponseEntity<?> getAllPocketByUserId(@PathVariable String userId) {
        List<Pocket> pockets = pocketService.getAllByUserId(userId);
        CommonResponse<Object> commonResponse = CommonResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .message("success get pockets")
                .data(pockets)
                .build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commonResponse);
    }

    @PutMapping
    public ResponseEntity<?> updatePocket(@RequestBody PocketRequest request) {
        PocketResponse pocketResponse = pocketService.update(request);
        CommonResponse<Object> commonResponse = CommonResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .message("success updated pocket")
                .data(pocketResponse)
                .build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commonResponse);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deletePocket(@PathVariable String id) {
        pocketService.deleteById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("success deleted pocket")
                        .build()
                );
    }
}
