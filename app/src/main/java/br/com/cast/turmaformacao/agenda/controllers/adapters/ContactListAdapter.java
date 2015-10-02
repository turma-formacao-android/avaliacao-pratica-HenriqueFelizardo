package br.com.cast.turmaformacao.agenda.controllers.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fasterxml.jackson.databind.deser.Deserializers;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.agenda.R;
import br.com.cast.turmaformacao.agenda.model.entities.Contact;

public class ContactListAdapter extends BaseAdapter {

    private Activity context;
    private List<Contact> contactList;


    public ContactListAdapter(Activity context, List<Contact> contactList) {
        this.contactList = contactList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return contactList.size();
    }

    @Override
    public Contact getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Contact contact = getItem(position);
        View listViewContacts = context.getLayoutInflater().inflate(R.layout.list_item_contact, parent, false);

        TextView textViewName = (TextView) listViewContacts.findViewById(R.id.txtName);
        textViewName.setText(contact.getName());

        return listViewContacts;
    }
}
