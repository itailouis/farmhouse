package app.com.milipade.talitha_koum.newfarmhouse.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by TALITHA_KOUM on 11/3/2017.
 */

public class Buyer implements Parcelable {

    private String buyerName;
    private CropProduce crop;
    private  double quantity;
    private String timestamp;
    private String brefMassage;
    private int id;
    private String cropName;

    protected Buyer(Parcel in) {
        buyerName = in.readString();
        crop = in.readParcelable(CropProduce.class.getClassLoader());
        quantity = in.readDouble();
        timestamp = in.readString();
        brefMassage = in.readString();
        id = in.readInt();
        cropName = in.readString();
    }

    public static final Creator<Buyer> CREATOR = new Creator<Buyer>() {
        @Override
        public Buyer createFromParcel(Parcel in) {
            return new Buyer(in);
        }

        @Override
        public Buyer[] newArray(int size) {
            return new Buyer[size];
        }
    };

    public String getBrefMassage() {
        return brefMassage;
    }

    public Buyer() {
    }

    public void setBrefMassage(String brefMassage) {
        this.brefMassage = brefMassage;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public CropProduce getCrop() {
        return crop;
    }

    public void setCrop(CropProduce crop) {
        this.crop = crop;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    public String getCropName() {
        return cropName;
    }

    public void setCropName(String cropName) {
        this.cropName = cropName;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(buyerName);
        dest.writeParcelable(crop, flags);
        dest.writeDouble(quantity);
        dest.writeString(timestamp);
        dest.writeString(brefMassage);
        dest.writeInt(id);
        dest.writeString(cropName);
    }
}
