package br.com.dcsolution.billstopay.modules.launch.service;

import br.com.dcsolution.billstopay.common.exception.BusinessException;
import br.com.dcsolution.billstopay.common.exception.MessageBusiness;
import br.com.dcsolution.billstopay.modules.launch.converter.LaunchConverter;
import br.com.dcsolution.billstopay.modules.launch.dto.LaunchDto;
import br.com.dcsolution.billstopay.modules.launch.entity.Launch;
import br.com.dcsolution.billstopay.modules.launch.repository.LaunchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
class LaunchServiceImpl implements LaunchService {

    private final LaunchRepository launchRepository;
    private final LaunchConverter launchConverter;

    @Autowired
    LaunchServiceImpl(final LaunchRepository launchRepository,
                      final LaunchConverter launchConverter) {
        this.launchRepository = launchRepository;
        this.launchConverter = launchConverter;
    }

    @Override
    public void create(final LaunchDto categoryDto) {
        validateExistingDescription(categoryDto);
        launchRepository.save(launchConverter.dtoToEntity(categoryDto));
    }

    @Override
    public void update(final LaunchDto categoryDto) {

        validateNoRecord(categoryDto);
        validateExistingDescription(categoryDto);
        launchRepository.save(launchConverter.dtoToEntity(categoryDto));
    }

    @Override
    public Page<LaunchDto> findAll(final Integer page,
                                   final Integer size,
                                   final String searchTerm) {
        final Page<Launch> categories = launchRepository.search(searchTerm, launchConverter.generatePageRequest(page, size));
        return launchConverter.pageEntityToPageDto(categories);
    }

    @Override
    public LaunchDto findById(final Integer id) {
        final Launch category = launchRepository.findById(id)
                .orElseThrow(() -> new BusinessException(MessageBusiness.NO_RECORD));

        return launchConverter.entityToDto(category);
    }

    @Override
    public void delete(final Integer id) {
        final Launch category = launchRepository.findById(id)
                .orElseThrow(() -> new BusinessException(MessageBusiness.NO_RECORD));

        launchRepository.delete(category);
    }

    void validateNoRecord(final LaunchDto launchDto) {
        final Optional<Launch> launch = launchRepository.findById(launchDto.getId());

        if (launch.isEmpty()) {
            throw new BusinessException(MessageBusiness.NO_RECORD);
        }
    }

    void validateExistingDescription(final LaunchDto launchDto) {
        final Optional<Launch> launch = launchRepository.isExistDescription(launchDto.getDescription(),
                launchDto.getId());

        if (launch.isPresent()) {
            throw new BusinessException(MessageBusiness.EXISTING_RECORD);
        }
    }
}
