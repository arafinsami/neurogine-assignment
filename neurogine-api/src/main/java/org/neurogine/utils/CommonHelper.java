package org.neurogine.utils;

import org.neurogine.dto.StoresDTO;
import org.neurogine.entity.Stores;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CommonHelper {

    public static void getData(Pageable pageable, Page<Stores> pages, Map<String, Object> response) {
        List<StoresDTO> storesDTOS = pages.stream().map(StoresDTO::from).collect(Collectors.toList());
        int total = pages.getTotalPages();
        int page = pageable.getPageNumber();
        int size = pageable.getPageSize();
        Integer nextPage = ((page + 1) < total) ? page + 1 : 0;
        Integer previousPage = ((page - 1) >= 0) ? page - 1 : 0;
        response.put("currentPage", page);
        response.put("nextPage", nextPage);
        response.put("previousPage", previousPage);
        response.put("size", size);
        response.put("total", total);
        response.put("lists", storesDTOS);
    }
}
