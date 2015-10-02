package br.com.cast.turmaformacao.agenda.controllers.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import br.com.cast.turmaformacao.agenda.R;
import br.com.cast.turmaformacao.agenda.controllers.adapters.ContactListAdapter;
import br.com.cast.turmaformacao.agenda.model.entities.Address;
import br.com.cast.turmaformacao.agenda.model.entities.Contact;
import br.com.cast.turmaformacao.agenda.model.entities.Email;
import br.com.cast.turmaformacao.agenda.model.entities.Social;
import br.com.cast.turmaformacao.agenda.model.entities.Telephone;
import br.com.cast.turmaformacao.agenda.model.http.AddressService;
import br.com.cast.turmaformacao.agenda.model.persistence.SocialRepository;
import br.com.cast.turmaformacao.agenda.model.services.ContactBusinessService;
import br.com.cast.turmaformacao.agenda.model.services.EmailBusinessService;
import br.com.cast.turmaformacao.agenda.model.services.SocialBusinessService;
import br.com.cast.turmaformacao.agenda.model.services.TelephoneBusinessService;
import br.com.cast.turmaformacao.agenda.util.FormHelper;

public class ContactFormActivity extends AppCompatActivity {

    public static final String PARAM_CONTACT = "PARAM_CONTACT";
    public static final String PARAM_SOCIAL = "PARAM_SOCIAL";
    public static final String PARAM_EMAIL = "PARAM_EMAIL";
    public static final String PARAM_TELEPHONE = "PARAM_TELEPHONE";
    private EditText editTextName;
    private EditText editTextZipCode;
    private EditText editTextCity;
    private EditText editTextState;
    private EditText editTextStreet;
    private EditText editTextNeighborhood;
    private EditText editTextSocial;
    private EditText editTextEmail;
    private EditText editTextTelephone;
    private Button buttonIncludeSocial;
    private Button buttonIncludeEmail;
    private Button buttonIncludeTelephone;
    private Button buttonComplete;
    private Contact contact;
    private Social social;
    private Email email;
    private Telephone telephone;

    private class GetAddressWeb extends AsyncTask<String, Void, Address> {

        @Override
        protected Address doInBackground(String... params) {
            return AddressService.getAddresByZip(params[0]);
        }

        @Override
        protected void onPostExecute(Address address) {
            super.onPostExecute(address);
            editTextCity.setText(address.getCity().toString());
            editTextState.setText(address.getState().toString());
            editTextStreet.setText(address.getStreet().toString());
            editTextNeighborhood.setText(address.getNeighborhood().toString());
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_form);

        initContact();
        initSocial();
        initEmail();
        initTelephone();

        bindTextName();
        bindTextZipCode();
        bindTextCity();
        bindTextState();
        bindTextStreet();
        bindTextNeighborhood();
        bindTextSocial();
        bindTextEmail();
        bindTextTelephone();
        bindButtonIncludeSocial();
        bindButtonIncludeEmail();
        bindButtonIncludeTelephone();
        bindButtonComplete();
    }

    private void initTelephone() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.telephone = extras.getParcelable(PARAM_TELEPHONE);
        }
        this.telephone = telephone == null ? new Telephone() : telephone;
    }

    private void initEmail() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.email = extras.getParcelable(PARAM_EMAIL);
        }
        this.email = email == null ? new Email() : email;
    }

    private void initSocial() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.social = extras.getParcelable(PARAM_SOCIAL);
        }
        this.social = social == null ? new Social() : social;
    }

    private void initContact() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.contact = extras.getParcelable(PARAM_CONTACT);
        }
        this.contact = contact == null ? new Contact() : contact;
    }

    private void bindButtonComplete() {
        buttonComplete = (Button) findViewById(R.id.buttonComplete);
        buttonComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new GetAddressWeb().execute(editTextZipCode.getText().toString());
            }
        });
    }

    private void bindButtonIncludeTelephone() {
        buttonIncludeTelephone = (Button) findViewById(R.id.buttonIncludeTelephone);
        buttonIncludeTelephone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binTelephone();
                Toast.makeText(ContactFormActivity.this, R.string.msg_save_success, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void binTelephone() {
        telephone.setNumber(editTextTelephone.getText().toString());
    }

    private void bindButtonIncludeEmail() {
        buttonIncludeEmail = (Button) findViewById(R.id.buttonIncludeEmail);
        buttonIncludeEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binEmail();
                Toast.makeText(ContactFormActivity.this, R.string.msg_save_success, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void binEmail() {
        email.setEndereco_email(editTextEmail.getText().toString());
    }

    private void bindButtonIncludeSocial() {
        buttonIncludeSocial = (Button) findViewById(R.id.buttonIncludeSocial);
        buttonIncludeSocial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binSocial();
                Toast.makeText(ContactFormActivity.this, R.string.msg_save_success, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void binSocial() {
        social.setUser(editTextSocial.getText().toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_contact_form, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_save_contact:
                onMenuSaveContactClick();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void onMenuSaveContactClick() {
        String requiredMessage = ContactFormActivity.this.getString(R.string.msg_required);
        if (!FormHelper.validateRequired(requiredMessage, editTextName, editTextNeighborhood, editTextState, editTextStreet,
                editTextZipCode)) {

            binContact();
            ContactBusinessService.save(contact);
            TelephoneBusinessService.save(telephone);
            EmailBusinessService.save(email);
            SocialBusinessService.save(social);
            Toast.makeText(ContactFormActivity.this, R.string.msg_save_ok, Toast.LENGTH_LONG).show();
            ContactFormActivity.this.finish();
        }
    }

    private void binContact() {
        contact.setName(editTextName.getText().toString());
        contact.getAddress().setCity(editTextCity.getText().toString());
        contact.getAddress().setNeighborhood(editTextNeighborhood.getText().toString());
        contact.getAddress().setState(editTextState.getText().toString());
        contact.getAddress().setStreet(editTextStreet.getText().toString());
        contact.getAddress().setZipCode(editTextZipCode.getText().toString());
    }

    private void bindTextNeighborhood() {
        editTextNeighborhood = (EditText) findViewById(R.id.editTextNeighborhood);
    }

    private void bindTextStreet() {
        editTextStreet = (EditText) findViewById(R.id.editTextStreet);
    }

    private void bindTextState() {
        editTextState = (EditText) findViewById(R.id.editTextState);
    }

    private void bindTextCity() {
        editTextCity = (EditText) findViewById(R.id.editTextCity);
    }

    private void bindTextTelephone() {
        editTextTelephone = (EditText) findViewById(R.id.editTextTelephone);
    }

    private void bindTextEmail() {
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
    }

    private void bindTextSocial() {
        editTextSocial = (EditText) findViewById(R.id.editTextSocial);
    }

    private void bindTextZipCode() {
        editTextZipCode = (EditText) findViewById(R.id.editTextZipCode);
    }

    private void bindTextName() {
        editTextName = (EditText) findViewById(R.id.editTextName);
    }
}
