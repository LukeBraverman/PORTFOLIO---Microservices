package com.vr3000.vodmicroservice.controller;

import com.vr3000.vodmicroservice.dto.VodDTO;
import com.vr3000.vodmicroservice.entity.Vod;
import com.vr3000.vodmicroservice.exceptions.VodNotFoundException;
import com.vr3000.vodmicroservice.service.VodService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/vod")
public class VodController {

    private final VodService vodService;

    @GetMapping("/{vodId}")
    public ResponseEntity<Vod> getVodById(@PathVariable Long vodId) {
        try {
            Vod vod = vodService.getVodById(vodId);
            return ResponseEntity.ok(vod);
        } catch (VodNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<Void> saveVod(@RequestBody VodDTO vodDTO) {
        vodService.saveVod(vodDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{vodId}")
    public ResponseEntity<Void> deleteVodById(@PathVariable Long vodId) {
        vodService.deleteVodById(vodId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
