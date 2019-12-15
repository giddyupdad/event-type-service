package com.raminus.ems.event.adapter.datastore;

import java.util.function.Function;

import com.raminus.ems.event.domain.EventType;

public interface EventTypeEntityTransformer {

  default Function<EventType, EventTypeEntity> toEventTypeEntity() {
    return eventType -> {
      EventTypeEntity eventTypeEntity = new EventTypeEntity();
      eventTypeEntity.setTypeCode(eventType.getTypeCode());
      eventTypeEntity.setTypeDescription(eventType.getTypeDescription());
      eventTypeEntity.setCreateBy(eventType.getCreateBy());
      eventTypeEntity.setCreateTime(eventType.getCreateTime());
      return eventTypeEntity;
    };
  }

  default Function<EventTypeEntity, EventType> toEventType() {
    return eventTypeEntity -> {
      EventType eventType = new EventType();
      eventType.setId(eventTypeEntity.getId());
      eventType.setTypeCode(eventTypeEntity.getTypeCode());
      eventType.setTypeDescription(eventTypeEntity.getTypeDescription());
      eventType.setCreateBy(eventTypeEntity.getCreateBy());
      eventType.setCreateTime(eventTypeEntity.getCreateTime());
      return eventType;
    };
  }
}
