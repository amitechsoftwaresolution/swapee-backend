package io.swapee.swapeebackend.common_library.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdvertisementCarouselResponseResource {
    private CarouselInterval carousel_interval;
    private List<String> image_list;
}

