package app.com.milipade.talitha_koum.newfarmhouse.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public class IPPreference {
	
	SharedPreferences prefs;
	
	public IPPreference(Context context){
		prefs = context.getSharedPreferences("ipinfo",context.MODE_PRIVATE);
	}
	
	public String getCity(){
		return prefs.getString("meip", "10.0.2.2:8080");
	}
	
	public void setCity(String city){
		SharedPreferences.Editor ed = prefs.edit();
		ed.putString("meip", city);
		ed.commit();
		//prefs.edit().putString("address", city).commit();
	}
	
}
