package com.example.hp.shopping.Intractor;

import android.app.Activity;

import com.example.hp.shopping.Contractor.RegisterContract;

public class RegisterInteractor implements RegisterContract.Interactor {
    public  static  final  String TAG=RegisterInteractor.class.getSimpleName();

    private RegisterContract.OnRegistrationListener mOnRegistrationListener;

    public RegisterInteractor(RegisterContract.OnRegistrationListener onRegistrationListener) {
        this.mOnRegistrationListener=onRegistrationListener;

    }



    @Override
    public void performRegistration(Activity activity, String email, String password, String company_id, String user_type, String firstname, String lastname) {

    }
}
