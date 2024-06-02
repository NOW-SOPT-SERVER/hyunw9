package org.sopt.sopt.infrastructure.domain;

import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

public interface S3Service {

  String uploadImage(String directoryPath, MultipartFile image) throws IOException;

  void deleteImage(String key) throws IOException;
}
