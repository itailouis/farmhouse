package app.com.milipade.talitha_koum.newfarmhouse.Activities.Base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import app.com.milipade.talitha_koum.newfarmhouse.Activities.Base.AuthenticationActivity;
import app.com.milipade.talitha_koum.newfarmhouse.Activities.Base.BaseActivity;
import app.com.milipade.talitha_koum.newfarmhouse.Activities.LoginActivity;


public abstract class BaseAuthenticatedActivity extends BaseActivity {

    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!application.getAuth().getUser().isLoggedIn()) {

            if (application.getAuth().hasAuthToken()) {
                Intent intent = new Intent(this, AuthenticationActivity.class);
                intent.putExtra(AuthenticationActivity.EXTRA_RETURN_TO_ACTIVITY, getClass().getName());
                Log.e("Current Activity", getClass().getName());
                startActivity(intent);
            } else {
                startActivity(new Intent(this, LoginActivity.class));
            }

            finish();
            return;
        }

        onSocialCreate(savedInstanceState);
    }

    protected abstract void onSocialCreate(Bundle savedState);

}
