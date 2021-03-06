package com.teamtreehouse.device;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.prepost.PreAuthorize;

public interface DeviceRepository extends PagingAndSortingRepository<Device, Long> {
    @RestResource(rel = "device-name", path = "deviceName")
    Page<Device> findByNameContaining(@Param("name") String name, Pageable page);

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN') or #device?.room?.hasAdministrator(authentication.name)")
    <S extends Device> S save(@Param("device") S entity);
}
