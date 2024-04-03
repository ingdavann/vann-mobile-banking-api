package co.istad.vannbankingapi.features.media;

import co.istad.vannbankingapi.features.media.dto.MediaResponse;
import lombok.RequiredArgsConstructor;
import org.aspectj.bridge.MessageUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
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
}
