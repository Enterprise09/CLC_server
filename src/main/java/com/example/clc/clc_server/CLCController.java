package com.example.clc.clc_server;

import java.util.Map;

import com.example.clc.clc_server.dao.CLCDAO;
import com.example.clc.clc_server.dto.CLCReviewDTO;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Controller
@RequestMapping("/api")
public class CLCController {
    
    @PostMapping("/write_review")
    public String WriteReview(@RequestBody Map<String, Object> data) {
        System.out.println(data);
        String title = data.get("title").toString();
        String content = data.get("content").toString();
        String id = data.get("id").toString();
        String pw = data.get("pw").toString();
        String movieId = data.get("movieId").toString();

        CLCReviewDTO dto = new CLCReviewDTO(title, content, id, pw, movieId);
        CLCDAO dao = new CLCDAO();
        dao.WriteReview(dto);

        return "ok";
    }
    
    @PostMapping("delete_review")
    public String DeleteReivew(@RequestBody Map<String, Object> data) {
        System.out.println(data);
        CLCDAO dao = new CLCDAO();
        dao.DeleteReview(data.get("docId").toString());

        return "ok";
    }

    @PostMapping("update_review")
    public String UpdateReview(@RequestBody Map<String, Object> data) {
        System.out.println(data);
        String title = data.get("title").toString();
        String content = data.get("content").toString();
        String id = data.get("id").toString();
        String pw = data.get("pw").toString();
        String movieId = data.get("movieId").toString();

        CLCReviewDTO dto = new CLCReviewDTO(title, content, id, pw, movieId);
        CLCDAO dao = new CLCDAO();
        dao.UpdateReview(data.get("docId").toString(), dto);

        return "ok";
    }
    
}
