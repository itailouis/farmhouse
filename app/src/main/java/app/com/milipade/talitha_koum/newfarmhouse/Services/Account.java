package app.com.milipade.talitha_koum.newfarmhouse.Services;


import android.net.Uri;
import android.util.Log;

import app.com.milipade.talitha_koum.newfarmhouse.Core.ServiceResponse;
import app.com.milipade.talitha_koum.newfarmhouse.Core.User;


public class Account {
private static final String TAG = Account.class.getName();
    private Account() {

    }


    public static class UserResponse extends ServiceResponse {

        public int id;
        public String avatarUri;
        public String displayName;
        public String userName;
        public String email;
        public String authToken;
        public boolean hasPassword;
    }


    public static class LoginWithUsernameRequest {
        public String userName;
        public String password;

        public LoginWithUsernameRequest(String userName, String password) {
            this.userName = userName;
            this.password = password;
            Log.i(TAG,""+userName+ " "+password);
        }
    }

    public static class LoginWithUsernameResponse extends ServiceResponse {

    }


    public static class LoginWithLocalTokenRequest {
        public String AuthToken;

        public LoginWithLocalTokenRequest(String authToken) {
            AuthToken = authToken;
        }
    }


    public static class LoginWithLocalTokenResponse extends UserResponse {
    }




    public static class RegisterRequest {
        public String Username;
        public String Email;
        public String Password;
        public String CliendId;

        public RegisterRequest(String username, String email, String password) {
            Username = username;
            Email = email;
            Password = password;
            CliendId = "android";
        }
    }

    public static class RegisterResponse extends UserResponse {

    }






    public static class ChangeAvatarRequest {
        public Uri newAvatarUri;


        public ChangeAvatarRequest(Uri newAvatarUri) {
            this.newAvatarUri = newAvatarUri;
        }
    }

    public static class ChangeAvatarResponse extends ServiceResponse {
        public String AvatarUrl;
    }


    public static class UpdateProfileRequest {

        public String displayName;
        public String email;

        public UpdateProfileRequest(String displayName, String email) {
            this.displayName = displayName;
            this.email = email;
        }
    }

    public static class UpdateProfileResponse extends ServiceResponse {
        public String DisplayName;
        public String Email;

    }


    public static class ChangePasswordRequest {
        public String newPassword;
        public String confirmNewPassword;
        public String currentPassword;

        public ChangePasswordRequest(String newPassword, String confirmNewPassword, String currentPassword) {
            this.newPassword = newPassword;
            this.confirmNewPassword = confirmNewPassword;
            this.currentPassword = currentPassword;
        }
    }


    public static class ChangePasswordResponse extends ServiceResponse {
    }


    public static class UserDetailUpdatesEvent {
        public User user;

        public UserDetailUpdatesEvent(User user) {
            this.user = user;
        }
    }

}
