package com.codegym.cms.service;

import com.codegym.cms.model.Group;

public interface GroupService {
    Iterable<Group> findAll();
    Group findById(Long id);
    void save(Group group);
    void remove(Long id);
}
