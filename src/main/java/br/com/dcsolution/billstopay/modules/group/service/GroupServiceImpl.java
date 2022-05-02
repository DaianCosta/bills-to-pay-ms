package br.com.dcsolution.billstopay.modules.group.service;

import br.com.dcsolution.billstopay.common.exception.BusinessException;
import br.com.dcsolution.billstopay.common.exception.MessageBusiness;
import br.com.dcsolution.billstopay.modules.group.converter.GroupConverter;
import br.com.dcsolution.billstopay.modules.group.dto.GroupDto;
import br.com.dcsolution.billstopay.modules.group.entity.Group;
import br.com.dcsolution.billstopay.modules.group.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final GroupConverter groupConverter;

    @Autowired
    GroupServiceImpl(final GroupRepository groupRepository,
                     final GroupConverter groupConverter) {
        this.groupRepository = groupRepository;
        this.groupConverter = groupConverter;
    }

    @Override
    public void create(final GroupDto groupDto) {
        groupRepository.save(groupConverter.dtoToEntity(groupDto));
    }

    @Override
    public void update(final GroupDto groupDto) {

        final Optional<Group> group = groupRepository.findById(groupDto.getId());

        if (group.isEmpty()) {
            throw new BusinessException(MessageBusiness.NO_RECORD);
        }

        groupRepository.save(groupConverter.dtoToEntity(groupDto));
    }

    @Override
    public Page<GroupDto> findAll(final Integer page,
                                  final Integer size,
                                  final String searchTerm) {
        final Page<Group> groups = groupRepository.search(searchTerm, groupConverter.generatePageRequest(page, size));
        return groupConverter.pageEntityToPageDto(groups);
    }

    @Override
    public GroupDto findById(final Integer id) {
        final Group group = groupRepository.findById(id)
                .orElseThrow(() -> new BusinessException(MessageBusiness.NO_RECORD));

        return groupConverter.entityToDto(group);
    }

    @Override
    public void delete(final Integer id) {
        final Group group = groupRepository.findById(id)
                .orElseThrow(() -> new BusinessException(MessageBusiness.NO_RECORD));

        groupRepository.delete(group);
    }
}
