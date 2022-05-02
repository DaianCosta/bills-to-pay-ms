package br.com.dcsolution.billstopay.modules.group.service;

import br.com.dcsolution.billstopay.modules.group.dto.GroupDto;
import br.com.dcsolution.billstopay.modules.group.entity.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

public final class GroupServiceStub {
    public static final String NAME = "free-card";
    public static final Integer ID = 1;
    public static final Integer PAGE= 1;
    public static final Integer SIZE = 10;

    public static GroupDto generateDto() {
        final GroupDto groupDto = new GroupDto();
        groupDto.setId(ID);
        groupDto.setName(NAME);
        return groupDto;
    }

    public static Group generateEntity() {
        final Group group = new Group();
        group.setId(ID);
        group.setName(NAME);
        return group;
    }

    public static Page<Group> generatePageEntity() {
        final List<Group> groups = new ArrayList<>();
        groups.add(new Group(1, "free"));

        return new PageImpl<>(groups);
    }

    public static Page<GroupDto> generatePageDto() {
        final List<GroupDto> groups = new ArrayList<>();
        groups.add(new GroupDto(1, "free"));

        return new PageImpl<>(groups);
    }

    public static PageRequest generatePageRequest() {
        return PageRequest.of(0, 10);
    }

}
