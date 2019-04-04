package com.sample.common.service;

import com.sample.common.domain.Sample;
import com.sample.common.repository.CommonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommonService {

    @Autowired
    private CommonRepository commonRepository;

    public String sayWow() {
        return "WOW!!";
    }

    public List<Sample> findAll() {
        return commonRepository.findAll();
    }

    public void create(Sample sample) {
        commonRepository.create(sample);
    }
}
