package com.raminus.ems.event.adapter.datastore;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaEventTypeEntityRepository extends JpaRepository<EventTypeEntity, Long> {

  EventTypeEntity findByTypeCode(String typeCode);
  EventTypeEntity deleteByTypeCode(String typeCode);

}
