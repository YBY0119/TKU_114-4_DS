// MediaProcessingSystem.java
interface Playable {
    void play();
}

interface Compressible {
    void compress();
}

abstract class MediaFile {
    protected String fileName;
    protected double sizeInMB;

    public MediaFile(String fileName, double sizeInMB) {
        this.fileName = fileName;
        this.sizeInMB = sizeInMB;
    }

    public abstract void displayDetails();
}

class ImageFile extends MediaFile implements Compressible {
    public ImageFile(String fileName, double sizeInMB) {
        super(fileName, sizeInMB);
    }

    @Override
    public void displayDetails() {
        System.out.println("🖼️ 圖片檔案: " + fileName + " (" + sizeInMB + " MB)");
    }

    @Override
    public void compress() {
        System.out.println("-> 執行圖片無損壓縮，壓縮率達 40%");
    }
}

class AudioFile extends MediaFile implements Playable, Compressible {
    public AudioFile(String fileName, double sizeInMB) {
        super(fileName, sizeInMB);
    }

    @Override
    public void displayDetails() {
        System.out.println("🎵 音訊檔案: " + fileName + " (" + sizeInMB + " MB)");
    }

    @Override
    public void play() {
        System.out.println("-> 正在以 320kbps 高音質播放音樂...");
    }

    @Override
    public void compress() {
        System.out.println("-> 轉碼為 AAC 音訊壓縮格式");
    }
}

class VideoFile extends MediaFile implements Playable, Compressible {
    public VideoFile(String fileName, double sizeInMB) {
        super(fileName, sizeInMB);
    }

    @Override
    public void displayDetails() {
        System.out.println("🎬 影片檔案: " + fileName + " (" + sizeInMB + " MB)");
    }

    @Override
    public void play() {
        System.out.println("-> 正在以 4K 60FPS 串流解碼播放影片...");
    }

    @Override
    public void compress() {
        System.out.println("-> 啟用 H.265 硬體編碼壓縮影片體積");
    }
}

public class MediaProcessingSystem {
    public static void main(String[] args) {
        MediaFile[] mediaLibrary = new MediaFile[] {
            new ImageFile("vacation_photo.jpg", 12.4),
            new AudioFile("podcast_ep01.mp3", 85.0),
            new VideoFile("lecture_recording.mp4", 1024.5)
        };

        System.out.println("=== 媒體庫支援操作檢測與執行 ===");
        for (MediaFile file : mediaLibrary) {
            file.displayDetails();

            // 判斷是否支援 Playable
            if (file instanceof Playable p) {
                p.play();
            } else {
                System.out.println("-> [不支援播放功能]");
            }

            // 判斷是否支援 Compressible
            if (file instanceof Compressible c) {
                c.compress();
            }

            System.out.println("------------------------------------------");
        }
    }
}