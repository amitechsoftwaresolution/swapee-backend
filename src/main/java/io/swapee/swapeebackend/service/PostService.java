package io.swapee.swapeebackend.service;

import io.swapee.swapeebackend.common_library.resource.PostResponseResource;
import io.swapee.swapeebackend.common_library.resource.PremiumContentResponse;

import java.util.List;

public interface PostService {
    List<PostResponseResource> getAllPosts();
    PremiumContentResponse getPremiumContent();

    List<PostResponseResource> searchPost(String keyword);
}
