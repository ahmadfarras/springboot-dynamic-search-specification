package com.farplayground.searchspecification.domain.persistence;

import com.farplayground.searchspecification.domain.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author farras
 * @since 0.0.1
 */
public interface ProfileDao extends JpaRepository<Profile, Integer>, JpaSpecificationExecutor<Profile> {
}
