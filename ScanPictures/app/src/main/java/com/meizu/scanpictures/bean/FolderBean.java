package com.meizu.scanpictures.bean;

/**
 * Created by hmh on 2015/11/2.
 */
public class FolderBean {
    private String folderPath;//图片的文件夹路径
    private String firstImgPath;//文件夹下第一张图片路径
    private String folderName;//文件夹名字
    private int ImgNumber;//图片数量



    public String getFolderPath() {
        return folderPath;
    }

    public String getFirstImgPath() {
        return firstImgPath;
    }

    public String getFolderName() {
        return folderName;
    }

    public int getImgNumber() {
        return ImgNumber;
    }

    public void setFolderPath(String folderPath) {
        this.folderPath = folderPath;
    }

    public void setFirstImgPath(String firstImgPath) {
        this.firstImgPath = firstImgPath;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public void setImgNumber(int imgNumber) {
        ImgNumber = imgNumber;
    }
}
