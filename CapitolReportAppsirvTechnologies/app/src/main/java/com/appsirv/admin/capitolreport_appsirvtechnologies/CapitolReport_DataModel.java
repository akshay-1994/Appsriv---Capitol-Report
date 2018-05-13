package com.appsirv.admin.capitolreport_appsirvtechnologies;

public class CapitolReport_DataModel {
    //Model Class to populate the adapter of recycler view which will in turn populate the recycler view//
    private String id;
    private String title;
    private String link;
    private String date;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getDate() {
        return date;
    }

    public CapitolReport_DataModel(String id , String title , String link , String date){
        this.id=id;
        this.title=title;
        this.link=link;
        this.date=date;
    }
}
