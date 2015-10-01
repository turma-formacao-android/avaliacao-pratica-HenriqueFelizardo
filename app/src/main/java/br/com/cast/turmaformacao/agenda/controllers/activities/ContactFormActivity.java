package br.com.cast.turmaformacao.agenda.controllers.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.cast.turmaformacao.agenda.R;

public class ContactFormActivity extends AppCompatActivity {
    private EditText editTextName;
    private EditText editTextZipCode;
    private EditText editTextCity;
    private EditText editTextState;
    private EditText editTextStreet;
    private EditText editTextNeighborhood;
    private Button buttonTest;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_form);

        bindTextName();
        bindTextZipCode();
        bindTextCity();
        bindTextState();
        bindTextStreet();
        bindTextNeighborhood();
        bindButtonTest();
    }

    private void bindButtonTest() {
        buttonTest = (Button) findViewById(R.id.buttonTest);
        buttonTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_contact_save, menu);
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

    private void bindTextZipCode() {
        editTextZipCode = (EditText) findViewById(R.id.editTextZipCode);
    }

    private void bindTextName() {
        editTextName = (EditText) findViewById(R.id.editTextName);
    }
}
