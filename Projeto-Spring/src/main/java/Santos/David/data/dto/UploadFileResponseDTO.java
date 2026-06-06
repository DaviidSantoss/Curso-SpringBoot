package Santos.David.data.dto;

import java.io.Serializable;
import java.util.Objects;

public class UploadFileResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String FileName;
    private String FileDownloadUri;
    private String FileType;
    private Long Size;

    public UploadFileResponseDTO(String fileName, String fileDownloadUri, String fileType, Long size) {
        FileName = fileName;
        FileDownloadUri = fileDownloadUri;
        FileType = fileType;
        Size = size;
    }

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String fileName) {
        FileName = fileName;
    }

    public String getFileDownloadUri() {
        return FileDownloadUri;
    }

    public void setFileDownloadUri(String fileDownloadUri) {
        FileDownloadUri = fileDownloadUri;
    }

    public String getFileType() {
        return FileType;
    }

    public void setFileType(String fileType) {
        FileType = fileType;
    }

    public Long getSize() {
        return Size;
    }

    public void setSize(Long size) {
        Size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UploadFileResponseDTO that = (UploadFileResponseDTO) o;
        return Objects.equals(FileName, that.FileName) && Objects.equals(FileDownloadUri, that.FileDownloadUri) && Objects.equals(FileType, that.FileType) && Objects.equals(Size, that.Size);
    }

    @Override
    public int hashCode() {
        return Objects.hash(FileName, FileDownloadUri, FileType, Size);
    }
}
