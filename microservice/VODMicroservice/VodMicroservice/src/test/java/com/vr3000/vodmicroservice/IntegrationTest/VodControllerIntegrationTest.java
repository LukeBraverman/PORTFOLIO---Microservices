package com.vr3000.vodmicroservice.IntegrationTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vr3000.vodmicroservice.VodRepository;
import com.vr3000.vodmicroservice.dto.VodDTO;
import com.vr3000.vodmicroservice.entity.Vod;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class VodControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private VodRepository vodRepository;

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
        vod.setSuccessfulOutcomeList(Collections.emptyList());
        vod.setFailedOutcomeList(Collections.emptyList());
        vod.setNoteList(Collections.emptyList());
        vod.setTitle("Test Title");
        vod.setVideoPath("/test/path");
        vod.setUserUid("user123");
    }

    @Test
    void getVodById_success() throws Exception {
        Vod savedVod = vodRepository.save(vod);

        mockMvc.perform(get("/vod/" + savedVod.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(savedVod.getId()))
                .andExpect(jsonPath("$.title").value("Test Title"))
                .andExpect(jsonPath("$.videoPath").value("/test/path"))
                .andExpect(jsonPath("$.userUid").value("user123"));
    }

    @Test
    void getVodById_notFound() throws Exception {
        mockMvc.perform(get("/vod/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void saveVod_success() throws Exception {
        mockMvc.perform(post("/vod")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(vodDTO)))
                .andExpect(status().isCreated());

        Optional<Vod> savedVod = vodRepository.findAll().stream().findFirst();
        assertThat(savedVod).isPresent();
        assertThat(savedVod.get().getTitle()).isEqualTo("Test Title");
    }

    @Test
    void deleteVodById_success() throws Exception {
        Vod savedVod = vodRepository.save(vod);

        mockMvc.perform(delete("/vod/" + savedVod.getId()))
                .andExpect(status().isNoContent());

        Optional<Vod> deletedVod = vodRepository.findById(savedVod.getId());
        assertThat(deletedVod).isNotPresent();
    }
}
