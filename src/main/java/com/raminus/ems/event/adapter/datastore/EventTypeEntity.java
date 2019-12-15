package com.raminus.ems.event.adapter.datastore;

import java.time.Instant;
import java.util.function.Function;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.raminus.ems.event.domain.Transformer;

@Entity
@Table(name = "event_type")
public class EventTypeEntity implements Transformer<EventTypeEntity> {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "type_code")
  private String typeCode;

  @Column(name = "type_description")
  private String typeDescription;

  @Column(name = "create_by")
  private String createBy;

  @Column(name = "create_time")
  private Instant createTime;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTypeCode() {
    return typeCode;
  }

  public void setTypeCode(String typeCode) {
    this.typeCode = typeCode;
  }

  public String getTypeDescription() {
    return typeDescription;
  }

  public void setTypeDescription(String typeDescription) {
    this.typeDescription = typeDescription;
  }

  public String getCreateBy() {
    return createBy;
  }

  public void setCreateBy(String createBy) {
    this.createBy = createBy;
  }

  public Instant getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Instant createTime) {
    this.createTime = createTime;
  }

  @Override
  public <R> R transform(Function<EventTypeEntity, R> t) {
    return t.apply(this);
  }
}
