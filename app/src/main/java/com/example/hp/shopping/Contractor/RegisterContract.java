package com.example.hp.shopping.Contractor;

import android.app.Activity;

public interface RegisterContract {
    interface View {
        void onRegistrationSuccess(String message);

        void onRegistrationFailure(String message);
    }

    interface Presenter {
        void register(Activity activity, String email, String password);
    }

    interface Interactor {
        void performRegistration(Activity activity, String email, String password,String company_id,String user_type,String firstname,String lastname);
    }

    interface OnRegistrationListener {
        void onSuccess(String message);

        void onFailure(String message);
    }
}
