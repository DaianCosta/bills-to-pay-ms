package br.com.dcsolution.billstopay.modules.launch.service;

import br.com.dcsolution.billstopay.common.exception.BusinessException;
import br.com.dcsolution.billstopay.modules.launch.converter.LaunchConverter;
import br.com.dcsolution.billstopay.modules.launch.dto.LaunchDto;
import br.com.dcsolution.billstopay.modules.launch.entity.Launch;
import br.com.dcsolution.billstopay.modules.launch.repository.LaunchRepository;
import br.com.dcsolution.billstopay.modules.launch.stub.LaunchServiceStub;
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
class LaunchServiceImplTest {

    /*
    @Mock
    private LaunchRepository launchRepository;

    @Mock
    private LaunchConverter launchConverter;

    @InjectMocks
    private LaunchServiceImpl launchService;

    @Test
    void create() {
        final Launch launch = LaunchServiceStub.generateEntity();
        final LaunchDto launchDto = LaunchServiceStub.generateDto();

        Mockito.when(launchConverter.dtoToEntity(launchDto)).thenReturn(launch);
        Mockito.when(launchRepository.save(launch)).thenReturn(launch);
        launchService.create(launchDto);
        Mockito.verify(launchRepository, Mockito.times(1)).save(launch);
    }

    @Test
    void update() {
        final Launch launch = LaunchServiceStub.generateEntity();
        final LaunchDto launchDto = LaunchServiceStub.generateDto();

        Mockito.when(launchRepository.findById(1)).thenReturn(Optional.of(launch));
        Mockito.when(launchConverter.dtoToEntity(launchDto)).thenReturn(launch);
        Mockito.when(launchRepository.save(launch)).thenReturn(launch);
        launchService.update(launchDto);
        Mockito.verify(launchRepository, Mockito.times(1)).save(launch);
    }

    @Test
    void updateException() {
        final LaunchDto launchDto = LaunchServiceStub.generateDto();

        Mockito.when(launchRepository.findById(1)).thenReturn(Optional.empty());
        Assertions.assertThrows(BusinessException.class, () -> launchService.update(launchDto));
    }

    @Test
    void findAll() {

        final Page<Launch> pageLaunch = LaunchServiceStub.generatePageEntity();

        Mockito.when(launchConverter.generatePageRequest(LaunchServiceStub.PAGE, LaunchServiceStub.SIZE))
                .thenReturn(LaunchServiceStub.generatePageRequest());
        Mockito.when(launchConverter.pageEntityToPageDto(pageLaunch))
                .thenReturn(LaunchServiceStub.generatePageDto());
        Mockito.when(launchRepository.search(LaunchServiceStub.DESCRIPTION, LaunchServiceStub.generatePageRequest()))
                .thenReturn(pageLaunch);

        final Page<LaunchDto> result = launchService.findAll(LaunchServiceStub.PAGE,
                LaunchServiceStub.SIZE, LaunchServiceStub.DESCRIPTION);
        Assertions.assertEquals(1, result.getTotalPages());
        Assertions.assertEquals(1, result.getTotalElements());
    }

    @Test
    void findByIdException() {
        Mockito.when(launchRepository.findById(1)).thenReturn(Optional.empty());
        Assertions.assertThrows(BusinessException.class, () -> launchService.findById(1));
    }

    @Test
    void findById() {
        final Launch launch = LaunchServiceStub.generateEntity();
        final LaunchDto launchDto = LaunchServiceStub.generateDto();

        Mockito.when(launchRepository.findById(1)).thenReturn(Optional.of(launch));
        Mockito.when(launchConverter.entityToDto(launch)).thenReturn(launchDto);

        final LaunchDto result = launchService.findById(1);

        Assertions.assertEquals(1, result.getId());
        Mockito.verify(launchRepository, Mockito.times(1)).findById(1);
    }

    @Test
    void deleteException() {
        Mockito.when(launchRepository.findById(1)).thenReturn(Optional.empty());
        Assertions.assertThrows(BusinessException.class, () -> launchService.delete(1));
    }

    @Test
    void delete() {
        final Launch launch = LaunchServiceStub.generateEntity();

        Mockito.when(launchRepository.findById(1)).thenReturn(Optional.of(launch));
        Mockito.doNothing().when(launchRepository).delete(launch);

        launchService.delete(1);

        Mockito.verify(launchRepository, Mockito.times(1)).delete(launch);
    }

     */
}