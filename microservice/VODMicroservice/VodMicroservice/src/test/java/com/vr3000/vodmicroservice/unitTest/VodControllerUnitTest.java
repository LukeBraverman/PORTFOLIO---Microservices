package com.vr3000.vodmicroservice.unitTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vr3000.vodmicroservice.controller.VodController;
import com.vr3000.vodmicroservice.dto.VodDTO;
import com.vr3000.vodmicroservice.entity.Vod;
import com.vr3000.vodmicroservice.exceptions.VodNotFoundException;
import com.vr3000.vodmicroservice.service.VodService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(VodController.class)
public class VodControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VodService vodService;

    @Autowired
    private ObjectMapper objectMapper;

    private VodDTO vodDTO;

    private Vod vod;

    @BeforeEach
    void setUp() {
        vodDTO = new VodDTO();
        vodDTO.setSuccessfulOutcomeList(Collections.emptyList());
        vodDTO.setFailedOutcomeList(Collections.emptyList());
        vodDTO.setNoteList(Collections.emptyList());
        vodDTO.setTitle("Test Title");
        vodDTO.setVideoPath("/test/path");
        vodDTO.setUserUid("user123");

        vod = new Vod();
        vod.setId(1L);
        vod.setSuccessfulOutcomeList(Collections.emptyList());
        vod.setFailedOutcomeList(Collections.emptyList());
        vod.setNoteList(Collections.emptyList());
        vod.setTitle("Test Title");
        vod.setVideoPath("/test/path");
        vod.setUserUid("user123");
    }

    @Test
    void getVodById_success() throws Exception {
        when(vodService.getVodById(1L)).thenReturn(vod);

        mockMvc.perform(get("/vod/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("Test Title"))
                .andExpect(jsonPath("$.videoPath").value("/test/path"))
                .andExpect(jsonPath("$.userUid").value("user123"));
    }

    @Test
    void getVodById_notFound() throws Exception {
        when(vodService.getVodById(1L)).thenThrow(new VodNotFoundException("Vod not found"));

        mockMvc.perform(get("/vod/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void saveVod_success() throws Exception {
        doNothing().when(vodService).saveVod(any(VodDTO.class));

        mockMvc.perform(post("/vod")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(vodDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    void deleteVodById_success() throws Exception {
        doNothing().when(vodService).deleteVodById(1L);

        mockMvc.perform(delete("/vod/1"))
                .andExpect(status().isNoContent());
    }
}
