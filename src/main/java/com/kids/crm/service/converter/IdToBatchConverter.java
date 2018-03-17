package com.kids.crm.service.converter;

import com.kids.crm.model.Batch;
import com.kids.crm.repository.BatchRepository;
import com.kids.crm.service.exception.BatchNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class IdToBatchConverter implements Converter<Long, Batch> {

    @Autowired private BatchRepository batchRepository;

    @Override
    public Batch convert(Long source) {
        return batchRepository.findById(source).orElseThrow(BatchNotFoundException::new);
    }
}
