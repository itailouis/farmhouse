package app.com.milipade.talitha_koum.newfarmhouse.Models;


import java.util.List;

import app.com.milipade.talitha_koum.newfarmhouse.Core.User;

/**
 * Created by TALITHA_KOUM on 17/2/2017.
 */

public class Investment {
    private User user;
    private String returnrate;
    private List<CropProduce> crop;
    private String conditions;
    private String period;
    private String seoson;

    public Investment() {
    }

    public Investment(User user, String returnrate, List<CropProduce> crop, String conditions, String period, String seoson) {
        this.user = user;
        this.returnrate = returnrate;
        this.crop = crop;
        this.conditions = conditions;
        this.period = period;
        this.seoson = seoson;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getReturnrate() {
        return returnrate;
    }

    public void setReturnrate(String returnrate) {
        this.returnrate = returnrate;
    }

    public List<CropProduce> getCrop() {
        return crop;
    }

    public void setCrop(List<CropProduce> crop) {
        this.crop = crop;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getSeoson() {
        return seoson;
    }

    public void setSeoson(String seoson) {
        this.seoson = seoson;
    }


}
