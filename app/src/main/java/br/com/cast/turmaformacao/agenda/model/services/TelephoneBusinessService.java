package br.com.cast.turmaformacao.agenda.model.services;

import java.util.List;

import br.com.cast.turmaformacao.agenda.model.entities.Telephone;
import br.com.cast.turmaformacao.agenda.model.persistence.ContactRepository;
import br.com.cast.turmaformacao.agenda.model.persistence.TelephoneRepository;

public class TelephoneBusinessService {

    private TelephoneBusinessService() {
        super();
    }

    public static List<Telephone> findAll() {
        List<Telephone> telephones = TelephoneRepository.getAll();

        for (Telephone t : telephones) {
            t.setContact(ContactRepository.getId(t.getContact().getId()));
        }
        return telephones;
    }

    public static void save(Telephone telephone) {
        TelephoneRepository.save(telephone);
    }

    public static void delete(Telephone telephone) {
        TelephoneRepository.delete(telephone.getId());
    }
}
