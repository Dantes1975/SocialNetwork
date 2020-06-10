package eu.senla.socialnetwork.dto;

import eu.senla.socialnetwork.model.Photo;
import lombok.Data;

@Data
public class PhotoDto {
    private Long id;
    private String fileName;

    public Photo toPhoto() {
        Photo photo = new Photo();
        photo.setId(id);
        photo.setFileName(fileName);
        return photo;
    }

    public static PhotoDto fromPhoto(Photo photo) {
        PhotoDto photoDto = new PhotoDto();
        photoDto.setId(photo.getId());
        photoDto.setFileName(photo.getFileName());
        return photoDto;
    }
}
