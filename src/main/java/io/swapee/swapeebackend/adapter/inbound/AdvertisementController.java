package io.swapee.swapeebackend.adapter.inbound;

import io.swapee.swapeebackend.common_library.controller.AbstractController;
import io.swapee.swapeebackend.service.AdvertisementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/advertisement")
public class AdvertisementController extends AbstractController {

    private final AdvertisementService advertisementService;

    public AdvertisementController(AdvertisementService advertisementService) {
        this.advertisementService = advertisementService;
    }

    @GetMapping("/carousel")
    public ResponseEntity<Object> getAllCarouselAdvertisement(){
        return sendSuccessResponse(advertisementService.getAllActiveCarouselAdvertisement());
    }
}
