package com.eazybank.controller;

import com.eazybank.entity.Notice;
import com.eazybank.service.NoticeService;
import lombok.AllArgsConstructor;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

@AllArgsConstructor
@RestController
@RequestMapping("/notices")
public class NoticeController {

    private NoticeService noticeService;

    @GetMapping
    public ResponseEntity<List<Notice>> getAllActiveNotices(){
        List<Notice> notices = noticeService.getAllNotices();
        if(notices!=null){
            return ResponseEntity.ok()
                    .cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS))
                    .body(notices);
        }
        return null;
    }
}
