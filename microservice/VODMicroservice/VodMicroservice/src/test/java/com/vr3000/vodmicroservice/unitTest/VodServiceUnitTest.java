package com.vr3000.vodmicroservice.unitTest;

import com.vr3000.vodmicroservice.VodRepository;
import com.vr3000.vodmicroservice.dto.VodDTO;
import com.vr3000.vodmicroservice.entity.Vod;
import com.vr3000.vodmicroservice.exceptions.VodNotFoundException;
import com.vr3000.vodmicroservice.mapper.VodMapper;
import com.vr3000.vodmicroservice.service.VodService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
public class VodServiceUnitTest {

    @Mock
    private VodRepository vodRepository;

    @InjectMocks
    private VodService vodService;

    private VodDTO vodDTO;
    private Vod vod;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        vodDTO = new VodDTO();
        vodDTO.setSuccessfulOutcomeList(Collections.emptyList());
        vodDTO.setFailedOutcomeList(Collections.emptyList());
        vodDTO.setNoteList(Collections.emptyList());
        vodDTO.setTitle("Test Title");
        vodDTO.setVideoPath("/test/path");
        vodDTO.setUserUid("user123");

        vod = VodMapper.toEntity(vodDTO);
        vod.setId(1L);
    }

    @Test
    void saveVod_success() {
        when(vodRepository.save(any(Vod.class))).thenReturn(vod);

        vodService.saveVod(vodDTO);

        verify(vodRepository, times(1)).save(any(Vod.class));
    }

    @Test
    void getVodById_success() {
        when(vodRepository.findById(1L)).thenReturn(Optional.of(vod));

        Vod foundVod = vodService.getVodById(1L);

        assertNotNull(foundVod);
        assertEquals("Test Title", foundVod.getTitle());
        assertEquals("/test/path", foundVod.getVideoPath());
        assertEquals("user123", foundVod.getUserUid());
    }

    @Test
    void getVodById_notFound() {
        when(vodRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(VodNotFoundException.class, () -> vodService.getVodById(1L));
    }

    @Test
    void deleteVodById_success() {
        doNothing().when(vodRepository).deleteById(1L);

        vodService.deleteVodById(1L);

        verify(vodRepository, times(1)).deleteById(1L);
    }

}
