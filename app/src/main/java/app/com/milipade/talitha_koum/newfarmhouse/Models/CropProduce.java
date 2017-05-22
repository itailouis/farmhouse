package app.com.milipade.talitha_koum.newfarmhouse.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by TALITHA_KOUM on 21/1/2017.
 */
public class CropProduce implements Parcelable {

    protected CropProduce(Parcel in) {
        id = in.readInt();
        pic = in.readInt();
        name = in.readString();
        season = in.readString();
        cropType = in.readString();
        avatearImage = in.readString();
        fieldCoverage = in.readDouble();
        fieldType = in.readString();
        seed = in.readString();
        Fertilizer = in.readString();
        lobor = in.readString();
        output = in.readString();
        CropReturn = in.readString();
        price = in.readDouble();
        avarage = in.readDouble();
    }

    public static final Creator<CropProduce> CREATOR = new Creator<CropProduce>() {
        @Override
        public CropProduce createFromParcel(Parcel in) {
            return new CropProduce(in);
        }

        @Override
        public CropProduce[] newArray(int size) {
            return new CropProduce[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(pic);
        dest.writeString(name);
        dest.writeString(season);
        dest.writeString(cropType);
        dest.writeString(avatearImage);
        dest.writeDouble(fieldCoverage);
        dest.writeString(fieldType);
        dest.writeString(seed);
        dest.writeString(Fertilizer);
        dest.writeString(lobor);
        dest.writeString(output);
        dest.writeString(CropReturn);
        dest.writeDouble(price);
        dest.writeDouble(avarage);

    }

    private int id;
    private int pic;
    private String name;
    private String season;
    private String cropType;
    private String avatearImage;
    private double fieldCoverage;
    private String fieldType;
    private String seed;
    private  String Fertilizer;
    private String lobor;
    private String output;
    private String CropReturn;
    private double price;
    private double avarage;

    public double getAvarage() {
        return avarage;
    }

    public void setAvarage(double avarage) {
        this.avarage = avarage;
    }

    public String getFertilizer() {
        return Fertilizer;
    }

    public void setFertilizer(String fertilizer) {
        Fertilizer = fertilizer;
    }



    public String getLobor() {
        return lobor;
    }

    public void setLobor(String lobor) {
        this.lobor = lobor;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getCropReturn() {
        return CropReturn;
    }

    public void setCropReturn(String cropReturn) {
        CropReturn = cropReturn;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }



    private List<Fertilizer> Fertilizers;
    private List<Infection> infections;

    public int getPic() {
        return pic;
    }

    public CropProduce(String name) {
        this.name = name;
    }


    public CropProduce() {
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public String getSeed() {
        return seed;
    }

    public void setSeed(String seed) {
        this.seed = seed;
    }

    public List<Fertilizer> getFertilizers() {
        return Fertilizers;
    }

    public void setFertilizers(List<Fertilizer> fertilizers) {
        Fertilizers = fertilizers;
    }

    public List<Infection> getInfections() {
        return infections;
    }

    public void setInfections(List<Infection> infections) {
        this.infections = infections;
    }

    public Soil getSoilType() {
        return soilType;
    }

    public void setSoilType(Soil soilType) {
        this.soilType = soilType;
    }

    private Soil soilType ;

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCropType() {
        return cropType;
    }

    public void setCropType(String cropType) {
        this.cropType = cropType;
    }

    public String getAvatearImage() {
        return avatearImage;
    }

    public void setAvatearImage(String avatearImage) {
        this.avatearImage = avatearImage;
    }

    public double getFieldCoverage() {
        return fieldCoverage;
    }

    public void setFieldCoverage(double fieldCoverage) {
        this.fieldCoverage = fieldCoverage;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    private class Fertilizer{
        String Name;
        String Ratio;

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public String getRatio() {
            return Ratio;
        }

        public void setRatio(String ratio) {
            Ratio = ratio;
        }
    }
    private class Infection{
        String name;
        String infect;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getInfect() {
            return infect;
        }

        public void setInfect(String infect) {
            this.infect = infect;
        }
    }
}
