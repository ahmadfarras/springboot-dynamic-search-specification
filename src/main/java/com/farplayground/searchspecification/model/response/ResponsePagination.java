package com.farplayground.searchspecification.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * @author farras
 * @since 0.0.1
 */
@Data
@SuperBuilder(toBuilder = true)
public class ResponsePagination {

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private final Long totalData;

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private final Integer totalPage;
}
