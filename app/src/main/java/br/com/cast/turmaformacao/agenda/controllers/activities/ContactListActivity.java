package br.com.cast.turmaformacao.agenda.controllers.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import br.com.cast.turmaformacao.agenda.R;

public class ContactListActivity extends AppCompatActivity{

    private ListView listViewContacts;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_list);

        bindListContacts();
    }

    private void bindListContacts() {
        listViewContacts = (ListView) findViewById(R.id.listViewContacts);


    }
}
