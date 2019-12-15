package com.raminus.ems.event.adapter.api;

import static org.springframework.http.ResponseEntity.ok;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raminus.ems.event.adapter.service.EventTypeService;
import com.raminus.ems.event.domain.EventType;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Example;
import io.swagger.annotations.ExampleProperty;

@RestController
@RequestMapping(value = "/private/10001", produces = {MediaType.APPLICATION_JSON_VALUE})
@Api(value = "This is Event Type API")
public class EventTypeController {

  private static final Logger LOG = LoggerFactory.getLogger(EventTypeController.class);
  private final EventTypeService eventTypeService;

  public EventTypeController(EventTypeService eventTypeService) {
    this.eventTypeService = eventTypeService;
    LOG.debug("Autowiring EventTypeService");
  }

  @GetMapping(value = "/events")
  @ApiOperation(value = "ApiOperation-value Pull All Event Types", notes = "ApiOperation-notes findAllEventTypes", tags = {
      "Get Events"},
      response = List.class)
  public ResponseEntity<List<EventType>> findAllEventTypes() {
    return ok().body(eventTypeService.findAllEventTypes());
  }

  @GetMapping(value = "/events/{type}")
  @ApiParam(value = "type")
  @ApiOperation(value = "ApiOperation-value Pull given Event Type", notes = "ApiOperation-notes findEventType", tags = {
      "Get Events"},
      response = EventType.class)
  public ResponseEntity<EventType> findEventType(@PathVariable String type) {
    LOG.info("Finding Event Type {}", type);
    return ok().body(eventTypeService.findEventType(type));
  }

  @ApiParam(value = "type")
  @ApiOperation(value = "ApiOperation-value delete Event Type", notes = "ApiOperation-notes delete event", tags = {
      "Process Events"},
      response = EventType.class)
  @DeleteMapping(value = "/events/{type}")
  public ResponseEntity<EventType> deleteEventType(@PathVariable String type) {
    return ok().body(eventTypeService.deleteEventType(type));
  }

  @PostMapping(value = "/events", consumes = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(value = "value createEventType", notes = "you can create a new EventType using this endpoint", tags = {
      "Process Events"}, response = EventType.class)
  @ApiResponses({
      @ApiResponse(code = 201, message = "event type created"),
      @ApiResponse(code = 500, message = "Internal Server Error")
  })
  public ResponseEntity<EventType> createEventType(
      @ApiParam(name = "Event Type Body", value = "Send Event Type as Json Payload", required = true,
          examples = @Example({@ExampleProperty(
              mediaType = "application/json",
              value = "{\n"
                  + "  \"createBy\": \"mani\",\n"
                  + "  \"createTime\": \"2019-12-15T00:24:09.808Z\",\n"
                  + "  \"typeCode\": \"concert\",\n"
                  + "  \"typeDescription\": \"music events\"\n"
                  + "}"
          )}))
      @RequestBody EventType eventType) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(eventTypeService.createEventType(eventType));
  }

  @ApiOperation(value = "value updateEventType", notes = "notes updateEventType", tags = {
      "Process Events"}, response = EventType.class)
  @PutMapping(value = "/events", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<EventType> updateEventType(@RequestBody EventType eventType) {
    return ok(eventTypeService.updateEventType(eventType));
  }
}
