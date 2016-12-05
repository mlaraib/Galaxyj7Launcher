package samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.wallpapar;

/**
 * Created by iamla on 11/29/2016.
 */
import java.io.Serializable;

public class Wallpaper implements Serializable {
    private static final long serialVersionUID = 1;
    private int height;
    private String photoJson;
    private String url;
    private int width;

    public Wallpaper(String photoJson, String url, int width, int height) {
        this.photoJson = photoJson;
        this.url = url;
        this.width = width;
        this.height = height;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPhotoJson() {
        return this.photoJson;
    }

    public void setPhotoJson(String photoJson) {
        this.photoJson = photoJson;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}

