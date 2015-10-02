package br.com.cast.turmaformacao.agenda.model.services;

import java.util.List;

import br.com.cast.turmaformacao.agenda.model.entities.Contact;
import br.com.cast.turmaformacao.agenda.model.persistence.ContactRepository;

public class ContactBusinessService {
    private ContactBusinessService() {
        super();
    }

    public static List<Contact> findAll() {
        return null;
    }

    public static void save(Contact contact) {
        ContactRepository.save(contact);
    }

    public static void delete(Contact contact) {
        ContactRepository.delete(contact.getId());
    }
}
