package io.swapee.swapeebackend.adapter.inbound;

import io.swapee.swapeebackend.common_library.controller.AbstractController;
import io.swapee.swapeebackend.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/post")
public class PostController extends AbstractController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllPosts() {
        return sendSuccessResponse(postService.getAllPosts());
    }
    @GetMapping("/premium")
    public ResponseEntity<Object> getPremiumContent() {
        return sendSuccessResponse(postService.getPremiumContent());
    }
}
