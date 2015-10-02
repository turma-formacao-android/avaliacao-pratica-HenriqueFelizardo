package br.com.cast.turmaformacao.agenda;

import android.app.Application;

import br.com.cast.turmaformacao.agenda.util.ApplicationUtil;

public class ContactManagerApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationUtil.setContext(getApplicationContext());
    }
}
