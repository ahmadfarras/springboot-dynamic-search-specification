package com.farplayground.searchspecification.domain.persistence;

import com.farplayground.searchspecification.domain.Profile;
import com.farplayground.searchspecification.repository.ProfileRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author farras
 * @since 0.0.1
 */
@Repository
public class ProfileJpaRepository implements ProfileRepository {

    private final ProfileDao dao;

    public ProfileJpaRepository(ProfileDao dao) {
        this.dao = dao;
    }

    @Override
    public void save(Profile profile) {
        dao.save(profile);
    }

    @Override
    public List<Profile> getAllProfile() {
        return dao.findAll();
    }
}
