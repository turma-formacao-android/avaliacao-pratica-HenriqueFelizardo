package br.com.cast.turmaformacao.agenda.controllers.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.melnykov.fab.FloatingActionButton;

import java.util.List;

import br.com.cast.turmaformacao.agenda.R;
import br.com.cast.turmaformacao.agenda.controllers.adapters.ContactListAdapter;
import br.com.cast.turmaformacao.agenda.model.entities.Contact;
import br.com.cast.turmaformacao.agenda.model.persistence.ContactRepository;
import br.com.cast.turmaformacao.agenda.model.services.ContactBusinessService;

public class ContactListActivity extends AppCompatActivity {

    private ListView listViewContacts;
    private Contact contact;
    private FloatingActionButton fab;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_list);

        bindListContacts();
        bindFloatActionButton();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_context_contact_list, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_context_edit:
                onMenuEditClick();
                break;
            case R.id.menu_context_delete:
                onMenuDeleteClick();
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void onMenuDeleteClick() {
        new AlertDialog.Builder(ContactListActivity.this)
                .setTitle(R.string.lbl_confirm)
                .setMessage(R.string.msg_delete)
                .setPositiveButton(R.string.lbl_yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ContactBusinessService.delete(contact);
                        contact = null;
                        String msg = getString(R.string.msg_delete_successfull);
                        Toast.makeText(ContactListActivity.this, msg, Toast.LENGTH_LONG).show();
                        updateContactList();
                    }
                })
                .setNeutralButton(R.string.lbl_no, null)
                .create()
                .show();
    }

    @Override
    protected void onResume() {
       // updateContactList();
        super.onResume();
    }

    private void updateContactList() {
        List<Contact> values = ContactBusinessService.findAll();
        listViewContacts.setAdapter(new ContactListAdapter(this, values));
        ContactListAdapter adapter = (ContactListAdapter) listViewContacts.getAdapter();
        adapter.notifyDataSetChanged();
    }

    private void onMenuEditClick() {
        Intent goToContactForm = new Intent(ContactListActivity.this, ContactFormActivity.class);
        goToContactForm.putExtra(ContactFormActivity.PARAM_CONTACT, contact);
        startActivity(goToContactForm);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_contact_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_filter_contact:
                onMenuFilterClick();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private void onMenuFilterClick() {
        Intent goToFilterActivity = new Intent(ContactListActivity.this, ContactFilterActivity.class);
        startActivity(goToFilterActivity);
    }

    private void bindFloatActionButton() {
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.attachToListView(listViewContacts);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToFormActivity = new Intent(ContactListActivity.this, ContactFormActivity.class);
                startActivity(goToFormActivity);
            }
        });
    }


    private void bindListContacts() {
        listViewContacts = (ListView) findViewById(R.id.listViewContacts);
        registerForContextMenu(listViewContacts);
        listViewContacts.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ContactListAdapter adapter = (ContactListAdapter) listViewContacts.getAdapter();
                contact = adapter.getItem(position);
                return false;
            }
        });

    }
}
