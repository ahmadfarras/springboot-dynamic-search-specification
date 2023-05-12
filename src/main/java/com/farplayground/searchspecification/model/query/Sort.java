package com.farplayground.searchspecification.model.query;

import java.util.Optional;

/**
 * @author farras
 * @since 0.0.1
 */
public class Sort {

	private final String sort;

	private Sort(String sort) {
		this.sort = sort;
	}

	public Optional<String> getValue() {
		if(sort == null) {
			return Optional.empty();
		}

		return Optional.of(sort);
	}

	public String getProperty() {

		if (sort == null) {

			return "createdAt";
		}

		if (getDirection().equals(org.springframework.data.domain.Sort.Direction.DESC)) {

			return sort.substring(1);
		}

		return sort;
	}

	public org.springframework.data.domain.Sort.Direction getDirection() {

		if (sort == null) {

			return org.springframework.data.domain.Sort.Direction.DESC;
		}

		if (sort.charAt(0) == '-') {

			return org.springframework.data.domain.Sort.Direction.DESC;
		}

		return org.springframework.data.domain.Sort.Direction.ASC;
	}
}