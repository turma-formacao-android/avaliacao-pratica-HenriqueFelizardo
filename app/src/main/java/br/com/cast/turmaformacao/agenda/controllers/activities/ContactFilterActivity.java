package br.com.cast.turmaformacao.agenda.controllers.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

import br.com.cast.turmaformacao.agenda.R;
import br.com.cast.turmaformacao.agenda.controllers.adapters.ContactListAdapter;
import br.com.cast.turmaformacao.agenda.model.entities.Contact;
import br.com.cast.turmaformacao.agenda.model.persistence.ContactRepository;
import br.com.cast.turmaformacao.agenda.model.services.ContactBusinessService;

public class ContactFilterActivity extends AppCompatActivity{
    private EditText editTextNameFilter;
    private Button buttonFilter;
    private ListView listViewFilter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_filter);

        bindButtonFilter();
        bindEditTextNameFilter();
        bindListViewFilter();
    }

    private void bindListViewFilter() {
        listViewFilter = (ListView) findViewById(R.id.listViewFilter);
    }

    private void bindEditTextNameFilter() {
        editTextNameFilter = (EditText) findViewById(R.id.editTextNameFilter);
    }

    private void bindButtonFilter() {
        buttonFilter = (Button) findViewById(R.id.buttonFilter);
        buttonFilter.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                updateFilterList();
            }
        });
    }

    private void updateFilterList() {
        Contact contact = new Contact();

        contact.setName(editTextNameFilter.getText().toString());
        List<Contact> values = ContactRepository.filter(contact);

        listViewFilter.setAdapter(new ContactListAdapter(this, values));
        ContactListAdapter adapter = (ContactListAdapter) listViewFilter.getAdapter();
        adapter.notifyDataSetChanged();
    }

}
