package com.raminus.ems.event.adapter.service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.raminus.ems.event.adapter.datastore.EventTypeEntity;
import com.raminus.ems.event.adapter.datastore.EventTypeEntityTransformer;
import com.raminus.ems.event.adapter.datastore.JpaEventTypeEntityRepository;
import com.raminus.ems.event.domain.EventType;

@Service
public class EventTypeService implements EventTypeEntityTransformer {

  private final JpaEventTypeEntityRepository jpaEventTypeEntityRepository;

  public EventTypeService(JpaEventTypeEntityRepository jpaEventTypeEntityRepository) {
    this.jpaEventTypeEntityRepository = jpaEventTypeEntityRepository;
  }

  public EventType createEventType(EventType eventType) {
    return this.jpaEventTypeEntityRepository.save(eventType.transform(toEventTypeEntity()))
        .transform(toEventType());
  }

  public EventType updateEventType(EventType eventType) {
    EventType type= this.jpaEventTypeEntityRepository
        .findByTypeCode(eventType.getTypeCode()).transform(toEventType());
    type.setCreateBy(eventType.getCreateBy());
    type.setCreateTime(Instant.now());
    type.setTypeCode(eventType.getTypeDescription());
    return this.jpaEventTypeEntityRepository.save(type.transform(toEventTypeEntity()))
        .transform(toEventType());
  }

  public EventType findEventType(String type) {
    return this.jpaEventTypeEntityRepository.findByTypeCode(type).transform(toEventType());
  }

  public EventType deleteEventType(String type) {
    return this.jpaEventTypeEntityRepository.deleteByTypeCode(type).transform(toEventType());
  }

  public List<EventType> findAllEventTypes() {
    return this.jpaEventTypeEntityRepository.findAll().stream()
        .map(eventTypeEntity -> eventTypeEntity.transform(toEventType()))
        .collect(Collectors.toList());
  }

}
