import java.io.File;

public class BoardReaderFactory {

    public static BoardReader getBoardType(File file) {
        String fileName = file.getName();
        String extension = fileName.substring(fileName.lastIndexOf("."));
        if (extension.equals(".sdk")) {
            return new BoardReaderSDK();
        }
        else if (extension.equals(".ss")) {
            return new BoardReaderSS();
        }
        else {
            return new BoardReaderSDK();
        }
    }


}
