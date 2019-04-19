package com.codegym.cms.service.impl;

import com.codegym.cms.model.Group;
import com.codegym.cms.repository.GroupRepository;
import com.codegym.cms.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;

public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Override
    public Iterable<Group> findAll() {
        return groupRepository.findAll();
    }

    @Override
    public Group findById(Long id) {
        return groupRepository.findOne(id);
    }

    @Override
    public void save(Group group) {
        groupRepository.save(group);
    }

    @Override
    public void remove(Long id) {
        groupRepository.delete(id);
    }
}
