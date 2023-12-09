package io.swapee.swapeebackend.service_impl;

import io.swapee.swapeebackend.common_library.resource.AdvertisementCarouselResponseResource;
import io.swapee.swapeebackend.common_library.resource.CarouselInterval;
import io.swapee.swapeebackend.model.Advertisement;
import io.swapee.swapeebackend.repository.AdvertisementRepository;
import io.swapee.swapeebackend.repository.ConfigRepository;
import io.swapee.swapeebackend.service.AdvertisementService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {

    private final Logger logger = Logger.getLogger(AdvertisementServiceImpl.class.getName());

    private final AdvertisementRepository advertisementRepository;

    private final ConfigRepository configRepository;

    public AdvertisementServiceImpl(AdvertisementRepository advertisementRepository, ConfigRepository configRepository) {
        this.advertisementRepository = advertisementRepository;
        this.configRepository = configRepository;
    }

    @Override
    public AdvertisementCarouselResponseResource getAllActiveCarouselAdvertisement() {
        logger.info("Get all advertisement");
        List<Advertisement> advertisementList = advertisementRepository.getAllActiveAdvertisement();
        List<String> imageList = getImageListFromAdvertisements(advertisementList);
        System.out.println(advertisementList);
        if(!advertisementList.isEmpty()){
            String maxTime = configRepository.findByKey("AD_MAX_TIME");
            String minTime = configRepository.findByKey("AD_MIN_TIME");
            CarouselInterval carouselInterval = new CarouselInterval(Integer.parseInt(minTime), Integer.parseInt(maxTime));
            return new AdvertisementCarouselResponseResource(carouselInterval, imageList);
        }
        return null;
    }

    public List<String> getImageListFromAdvertisements(List<Advertisement> advertisementList) {
        return advertisementList.stream()
                .map(Advertisement::getImage)
                .collect(Collectors.toList());
    }
}
