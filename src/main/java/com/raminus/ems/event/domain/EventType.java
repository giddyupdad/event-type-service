package com.raminus.ems.event.domain;

import java.time.Instant;
import java.util.function.Function;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "event type model value", description = "event type model description")
public class EventType implements Transformer<EventType>{

  @ApiModelProperty(hidden = true)
  private Long id;
  @ApiModelProperty(value = " code value of event", required = true)
  private String typeCode;
  @ApiModelProperty(value = " description value of event")
  private String typeDescription;
  @ApiModelProperty(value = " event created by", required = true)
  private String createBy;
  @ApiModelProperty(value = " event created time", required = true)
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
  public <R> R transform(Function<EventType, R> t) {
    return t.apply(this);
  }
}
