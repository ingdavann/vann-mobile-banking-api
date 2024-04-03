package co.istad.vannbankingapi.features.media;

import co.istad.vannbankingapi.features.media.dto.MediaResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class MediaServiceImpl implements MediaService{
    @Value("${media.server-path}")
    private String serverPath;

    @Value("${media.base-uri}")
    private String baseUri;

    @Override
    public MediaResponse uploadSingle(MultipartFile file, String folderName) {
        // Generate new unique name for file upload
        String newName = UUID.randomUUID().toString();

        // Extract extension from file upload
        // Assume profile.png
        int lastDotIndex = file.getOriginalFilename()
                .lastIndexOf(".");
        String extension = file.getOriginalFilename()
                .substring(lastDotIndex + 1);
        newName = newName + "." + extension;

        // Copy file to server
        Path path = Paths.get(serverPath + folderName + "\\" + newName);
        try {
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    e.getLocalizedMessage());
        }

        return MediaResponse.builder()
                .name(newName)
                .contentType(file.getContentType())
                .extension(extension)
                .size(file.getSize())
                .uri(String.format("%s%s/%s", baseUri, folderName, newName))
                .build();
    }

    @Override
    public List<MediaResponse> uploadMultiple(List<MultipartFile> files, String folderName) {
        List<MediaResponse> mediaResponses = new ArrayList<>();
        files.forEach(file -> {
            MediaResponse mediaResponse = this.uploadSingle(file, folderName);
            mediaResponses.add(mediaResponse);
        });
        return mediaResponses;
    }
}
