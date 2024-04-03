package co.istad.vannbankingapi.features.media;

import co.istad.vannbankingapi.features.media.dto.MediaResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MediaService {
    MediaResponse uploadSingle(MultipartFile file, String folderName);

    List<MediaResponse> uploadMultiple(List<MultipartFile> files, String folderName);

    List<MediaResponse> getAllListMedia(String folderName);

    ResponseEntity downloadMediaByName(String mediaName, String folderName);
}
