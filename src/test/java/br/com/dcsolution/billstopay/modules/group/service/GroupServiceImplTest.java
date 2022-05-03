package br.com.dcsolution.billstopay.modules.group.service;

import br.com.dcsolution.billstopay.common.exception.BusinessException;
import br.com.dcsolution.billstopay.modules.group.converter.GroupConverter;
import br.com.dcsolution.billstopay.modules.group.dto.GroupDto;
import br.com.dcsolution.billstopay.modules.group.entity.Group;
import br.com.dcsolution.billstopay.modules.group.repository.GroupRepository;
import br.com.dcsolution.billstopay.modules.group.stub.GroupServiceStub;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class GroupServiceImplTest {

    @Mock
    private GroupRepository groupRepository;

    @Mock
    private GroupConverter groupConverter;

    @InjectMocks
    private GroupServiceImpl groupService;

    @Test
    void create() {
        final Group group = GroupServiceStub.generateEntity();
        final GroupDto groupDto = GroupServiceStub.generateDto();

        Mockito.when(groupConverter.dtoToEntity(groupDto)).thenReturn(group);
        Mockito.when(groupRepository.save(group)).thenReturn(group);
        groupService.create(groupDto);
        Mockito.verify(groupRepository, Mockito.times(1)).save(group);
    }

    @Test
    void update() {
        final Group group = GroupServiceStub.generateEntity();
        final GroupDto groupDto = GroupServiceStub.generateDto();

        Mockito.when(groupRepository.findById(1)).thenReturn(java.util.Optional.of(group));
        Mockito.when(groupConverter.dtoToEntity(groupDto)).thenReturn(group);
        Mockito.when(groupRepository.save(group)).thenReturn(group);
        groupService.update(groupDto);
        Mockito.verify(groupRepository, Mockito.times(1)).save(group);
    }

    @Test
    void updateException() {
        final GroupDto groupDto = GroupServiceStub.generateDto();

        Mockito.when(groupRepository.findById(1)).thenReturn(Optional.empty());
        Assertions.assertThrows(BusinessException.class, () -> groupService.update(groupDto));
    }

    @Test
    void findAll() {

        final Page<Group> pageGroup = GroupServiceStub.generatePageEntity();

        Mockito.when(groupConverter.generatePageRequest(GroupServiceStub.PAGE, GroupServiceStub.SIZE))
                .thenReturn(GroupServiceStub.generatePageRequest());
        Mockito.when(groupConverter.pageEntityToPageDto(pageGroup))
                .thenReturn(GroupServiceStub.generatePageDto());
        Mockito.when(groupRepository.search(GroupServiceStub.NAME, GroupServiceStub.generatePageRequest()))
                .thenReturn(pageGroup);

        final Page<GroupDto> result = groupService.findAll(GroupServiceStub.PAGE,
                GroupServiceStub.SIZE, GroupServiceStub.NAME);
        Assertions.assertEquals(1, result.getTotalPages());
        Assertions.assertEquals(1, result.getTotalElements());
    }

    @Test
    void findByIdException() {
        Mockito.when(groupRepository.findById(1)).thenReturn(Optional.empty());
        Assertions.assertThrows(BusinessException.class, () -> groupService.findById(1));
    }

    @Test
    void findById() {
        final Group group = GroupServiceStub.generateEntity();
        final GroupDto groupDto = GroupServiceStub.generateDto();

        Mockito.when(groupRepository.findById(1)).thenReturn(java.util.Optional.of(group));
        Mockito.when(groupConverter.entityToDto(group)).thenReturn(groupDto);

        final GroupDto result = groupService.findById(1);

        Assertions.assertEquals(1, result.getId());
        Mockito.verify(groupRepository, Mockito.times(1)).findById(1);
    }

    @Test
    void deleteException() {
        Mockito.when(groupRepository.findById(1)).thenReturn(Optional.empty());
        Assertions.assertThrows(BusinessException.class, () -> groupService.delete(1));
    }

    @Test
    void delete() {
        final Group group = GroupServiceStub.generateEntity();

        Mockito.when(groupRepository.findById(1)).thenReturn(java.util.Optional.of(group));
        Mockito.doNothing().when(groupRepository).delete(group);

        groupService.delete(1);

        Mockito.verify(groupRepository, Mockito.times(1)).delete(group);
    }
}