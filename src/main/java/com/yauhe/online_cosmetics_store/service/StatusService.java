package com.yauhe.online_cosmetics_store.service;

import com.yauhe.online_cosmetics_store.entity.Status;
import com.yauhe.online_cosmetics_store.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusService extends CrudImpl<Status> {

    public StatusRepository repository;

    @Autowired
    public StatusService(StatusRepository repository) {
        super(repository);
        this.repository = repository;
    }

}
