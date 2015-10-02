package br.com.cast.turmaformacao.agenda.model.services;

import java.util.List;

import br.com.cast.turmaformacao.agenda.model.entities.Social;
import br.com.cast.turmaformacao.agenda.model.persistence.ContactRepository;
import br.com.cast.turmaformacao.agenda.model.persistence.SocialRepository;

public class SocialBusinessService {

    private SocialBusinessService() {
        super();
    }

    public static List<Social> findAll() {
        List<Social> socials = SocialRepository.getAll();

        for (Social s : socials) {
            s.setContact(ContactRepository.getId(s.getContact().getId()));
        }
        return socials;
    }

    public static void save(Social social) {
        SocialRepository.save(social);
    }

    public static void delete(Social social) {
        SocialRepository.delete(social.getId());
    }
}
