package co.istad.vannbankingapi.features.media;

import co.istad.vannbankingapi.features.media.dto.MediaResponse;
import lombok.RequiredArgsConstructor;
import org.aspectj.bridge.MessageUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/v1/medias")
@RequiredArgsConstructor
public class MediaController {
    private final MediaService mediaService;
    @PostMapping("/upload-single")
    MediaResponse uploadSingle(@RequestPart MultipartFile file){
        return mediaService.uploadSingle(file, "VANN-BANKING");
    }

    @PostMapping("/upload-multiple")
    List<MediaResponse> uploadMultiple(@RequestPart List<MultipartFile> files){
        return mediaService.uploadMultiple(files, "VANN-BANKING");
    }

    @GetMapping
    List<MediaResponse> findAllListMedia(){
        return mediaService.getAllListMedia("VANN-BANKING");
    }

    @GetMapping("/download/{name}")
    ResponseEntity<?> downloadByName(@PathVariable String name) {
        return mediaService.downloadMediaByName(name , "VANN-BANKING");
    }
}
