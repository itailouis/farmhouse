package app.com.milipade.talitha_koum.newfarmhouse.Utils;

/**
 * Created by TALITHA_KOUM on 7/3/2017.
 */

public class EndPoint {

    public static final String BASE_URL ="http://%s/farmhouse/api/";

    public static final String LOGIN_URL =BASE_URL+"login";
    public static final String REGISTER_URL =BASE_URL+"register";

    public static final String NOTIFICATIONS_URL =BASE_URL+"getnotifications";
    public static final String CROP_URL=BASE_URL+"getcrops";
   //public static final String CROP_URL=BASE_URL+"crop.json";

    public static final String BUYER_URL=BASE_URL+"getbuyer";

    public static final String DEAL_URL=BASE_URL+"getdeal";

    public static final String AVARAGE_URL=BASE_URL+"getavarage";
    public static final String NEWS_URL = BASE_URL+"getlettestnews";

    public static final String SET_FARM_URL="http://10.0.2.2:8080/basvuapp/v1/addFarm";
    public static final String ADD_FARM_DAIRY_URL="http://10.0.2.2:8080/basvuapp/v1/addDairy";
    public static final String GET_FARM_DAIRY_URL="http://10.0.2.2:8080/basvuapp/v1/getDairy";
    public static final String REQUEST_DEAL_URL="http://10.0.2.2:8080/basvuapp/v1/requestDeal";
    public static final String SET_DEAL_URL="http://10.0.2.2:8080/basvuapp/v1/addDeal";
    public static final String ADD_FIELD_URL="http://10.0.2.2:8080/basvuapp/v1/addField";
    public static final String ADD_CROP_URL="http://10.0.2.2:8080/basvuapp/v1/addCrop";
    public static final String GET_FIELD_URL="http://10.0.2.2:8080/basvuapp/v1/getField";
    public static final String GET_CROP_URL="http://10.0.2.2:8080/basvuapp/v1/getCrop";
    public static final String GET_CROP_MARKET_URL="http://10.0.2.2:8080/basvuapp/v1/getMarket";
    public static final String PREFS = "LoginPrefs";

}
