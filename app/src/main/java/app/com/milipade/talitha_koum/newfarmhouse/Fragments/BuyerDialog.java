package app.com.milipade.talitha_koum.newfarmhouse.Fragments;


import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import app.com.milipade.talitha_koum.newfarmhouse.Models.Buyer;
import app.com.milipade.talitha_koum.newfarmhouse.R;


/**
 * Created by TALITHA_KOUM on 16/3/2017.
 */

public class BuyerDialog extends DialogFragment implements View.OnClickListener {
    Buyer buyer;

    public BuyerDialog() {
    }
public void setData(Buyer buyer) {
    this.buyer = buyer;
}
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //Bundle b = getArguments();
        //CropProduce crop= b.getParcelable
        LayoutInflater li = LayoutInflater.from(getActivity());
        View view = li.inflate(R.layout.crop_info_dialog, null);
        TextView type = (TextView) view.findViewById(R.id.crop_info_type);

        //TextView type = (TextView) view.findViewById(R.id.crop_info_type);
        Button save =(Button) view.findViewById(R.id.btn_add);
        Button cancel =(Button) view.findViewById(R.id.btn_cancel);
        save.setOnClickListener(this);
        cancel.setOnClickListener(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("hie")
                .setCancelable(true)
                .setView(view);

        return builder.create();
    }
    @Override
    public void onClick(View v) {
       if(v.getId()==R.id.btn_add){

       }
        if(v.getId()==R.id.btn_cancel){}
        dismiss();
    }
}
