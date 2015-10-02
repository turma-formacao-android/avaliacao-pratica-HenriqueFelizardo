package br.com.cast.turmaformacao.agenda.model.services;

import java.util.List;

import br.com.cast.turmaformacao.agenda.model.entities.Email;
import br.com.cast.turmaformacao.agenda.model.persistence.ContactRepository;
import br.com.cast.turmaformacao.agenda.model.persistence.EmailRepository;

public class EmailBusinessService {
    private EmailBusinessService() {
        super();
    }

    public static List<Email> findAll() {
        List<Email> emails = EmailRepository.getAll();

        for (Email e : emails) {
            e.setContact(ContactRepository.getId(e.getContact().getId()));
        }
        return emails;
    }

    public static void save(Email email) {
        EmailRepository.save(email);
    }

    public static void delete(Email email) {
        EmailRepository.delete(email.getId());
    }
}
