package co.istad.vannbankingapi.features.media.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
public record MediaResponse(
        String name, // unique
        String contentType, // PNG, JPEG, MP4
        String extension,
        String uri, // https://api.istad.co/media/image/899bac49-e47c-406c-abb2-30ad0b498f88.png
        @JsonInclude(JsonInclude.Include.NON_NULL)
        Long size

) {

}
