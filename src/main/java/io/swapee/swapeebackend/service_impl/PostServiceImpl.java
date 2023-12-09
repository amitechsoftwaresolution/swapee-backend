package io.swapee.swapeebackend.service_impl;

import io.swapee.swapeebackend.common_library.resource.CarouselInterval;
import io.swapee.swapeebackend.common_library.resource.PostResponseResource;
import io.swapee.swapeebackend.common_library.resource.PremiumContentResponse;
import io.swapee.swapeebackend.model.Post;
import io.swapee.swapeebackend.repository.CategoryRepository;
import io.swapee.swapeebackend.repository.ConfigRepository;
import io.swapee.swapeebackend.repository.PostRepository;
import io.swapee.swapeebackend.service.PostService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

@Service
public class PostServiceImpl implements PostService {

    private final Logger logger = Logger.getLogger(PostServiceImpl.class.getName());

    private final PostRepository postRepository;

    private final ConfigRepository configRepository;

    private final CategoryRepository categoryRepository;

    public PostServiceImpl(PostRepository postRepository, ConfigRepository configRepository, CategoryRepository categoryRepository) {
        this.postRepository = postRepository;
        this.configRepository = configRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<PostResponseResource> getAllPosts() {
        logger.info("Get all posts");
        List<Post> postList = postRepository.findAll();
        return getPostResponseResource(postList);
    }

    @Override
    public PremiumContentResponse getPremiumContent() {
        logger.info("Get all premium content");
        List<Post> platinumList = postRepository.findAllActivePostsByType("platinum");
        List<Post> goldList = postRepository.findAllActivePostsByType("gold");
        List<Post> bronzeList = postRepository.findAllActivePostsByType("bronze");

        String platinumMaxTime = configRepository.findByKey("PLATINUM_MAX_TIME");
        String platinumMinTime = configRepository.findByKey("PLATINUM_MIN_TIME");
        CarouselInterval platinumCarouselInterval = new CarouselInterval(Integer.parseInt(platinumMaxTime), Integer.parseInt(platinumMinTime));

        String goldMaxTime = configRepository.findByKey("GOLD_MAX_TIME");
        String goldMinTime = configRepository.findByKey("GOLD_MIN_TIME");
        CarouselInterval goldCarouselInterval = new CarouselInterval(Integer.parseInt(goldMaxTime), Integer.parseInt(goldMinTime));

        String bronzeMaxTime = configRepository.findByKey("BRONZE_MAX_TIME");
        String bronzeMinTime = configRepository.findByKey("BRONZE_MIN_TIME");
        CarouselInterval bronzeCarouselInterval = new CarouselInterval(Integer.parseInt(bronzeMaxTime), Integer.parseInt(bronzeMinTime));

        PremiumContentResponse.PlatinumContent platinumContent = new PremiumContentResponse.PlatinumContent(platinumCarouselInterval, getPostResponseResource(platinumList));
        PremiumContentResponse.GoldContent goldContent = new PremiumContentResponse.GoldContent(goldCarouselInterval, getPostResponseResource(goldList));
        PremiumContentResponse.BronzeContent bronzeContent = new PremiumContentResponse.BronzeContent(bronzeCarouselInterval, getPostResponseResource(bronzeList));

        return new PremiumContentResponse(platinumContent, goldContent, bronzeContent);
    }

    private List<PostResponseResource> getPostResponseResource(List<Post> postList) {
        List<PostResponseResource> postResponseResourceList = new java.util.ArrayList<>(Collections.emptyList());
        for (Post post : postList) {
            PostResponseResource postResponseResource = new PostResponseResource();
            postResponseResource.setTitle(post.getTitle());
            postResponseResource.setDescription(post.getDescription());
            postResponseResource.setCategoryType(categoryRepository.findNameById(post.getCategoryId()));
            String[] imageLinks = post.getImageArray().split(",");
            postResponseResource.setImageLinks(Arrays.asList(imageLinks));
            postResponseResource.setPostDate(post.getPostDate());
            postResponseResourceList.add(postResponseResource);
        }
        return postResponseResourceList;
    }
}
