package com.eazybank.service.impl;

import com.eazybank.entity.Notice;
import com.eazybank.repository.NoticeRepository;
import com.eazybank.service.NoticeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class NoticeServiceImpl implements NoticeService {

    private NoticeRepository noticeRepository;

    @Override
    public List<Notice> getAllNotices() {
        return noticeRepository.findAllActiveNotices();
    }
}
