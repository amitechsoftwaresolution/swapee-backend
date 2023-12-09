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
public class PremiumContentResponse {

    private PlatinumContent platinum;
    private GoldContent gold;
    private BronzeContent bronze;

    @Getter
    @Setter
    @AllArgsConstructor
    public static class PlatinumContent {

        private CarouselInterval carousel_interval;
        private List<PostResponseResource> platinum_list;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class GoldContent {

        private CarouselInterval carousel_interval;
        private List<PostResponseResource> gold_list;

    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class BronzeContent {

        private CarouselInterval carousel_interval;
        private List<PostResponseResource> bronze_list;
    }
}
