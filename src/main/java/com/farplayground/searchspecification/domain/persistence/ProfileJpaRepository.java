package com.farplayground.searchspecification.domain.persistence;

import com.farplayground.searchspecification.domain.Profile;
import com.farplayground.searchspecification.repository.ProfileRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
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

    @Override
    public Page<Profile> findAllProfiles(String searchKeyword, Pageable pageable) {
        List<String> searchColumn = Arrays.asList("name");

        SearchSpecification<Profile> searchSpecification = new SearchSpecification<>(searchColumn, searchKeyword);

        return dao.findAll(searchSpecification, pageable);
    }
}
