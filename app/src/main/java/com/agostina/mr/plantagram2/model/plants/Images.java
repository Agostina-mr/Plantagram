package com.agostina.mr.plantagram2.model.plants;

public class Images {
    private String file_name;
    private String url;

    public Images(String file_name, String url) {
        this.file_name = file_name;
        this.url = url;
    }

    public String getFile_name() {
        return file_name;
    }

    public String getUrl() {
        return url;
    }
}
