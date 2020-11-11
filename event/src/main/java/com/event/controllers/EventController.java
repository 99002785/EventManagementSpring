package com.event.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.event.commands.EventForm;
import com.event.converters.EventToEventForm;
import com.event.domain.Event;
import com.event.services.EventService;



@Controller
public class EventController {

	
	


	    private EventService eventService;

	    private EventToEventForm eventToEventForm;

	    @Autowired
	    public void setEventToEventForm(EventToEventForm eventToEventForm) {
	        this.eventToEventForm = eventToEventForm;
	    }

	    @Autowired
	    public void setEventService(EventService eventService) {
	        this.eventService = eventService;
	    }

	    @RequestMapping("/")
	    public String redirToList(){
	        return "redirect:/event/list";
	    }

	    @RequestMapping({"/event/list", "/event"})
	    public String listEvents(Model model){
	        model.addAttribute("events", eventService.listAll());
	        return "event/list";
	    }

	    @RequestMapping("/event/show/{id}")
	    public String getEvent(@PathVariable String id, Model model){
	        model.addAttribute("event", eventService.getById(id));
	        return "event/show";
	    }

	    @RequestMapping("event/edit/{id}")
	    public String edit(@PathVariable String id, Model model){
	        Event event = eventService.getById(id);
	        EventForm eventForm = eventToEventForm.convert(event);

	        model.addAttribute("eventForm", eventForm);
	        return "event/eventform";
	    }

	    @RequestMapping("/event/new")
	    public String newEvent(Model model){
	        model.addAttribute("eventForm", new EventForm());
	        return "event/eventform";
	    }

	    @RequestMapping(value = "/event", method = RequestMethod.POST)
	    public String saveOrUpdateEvent(@Valid EventForm eventForm, BindingResult bindingResult){

	        if(bindingResult.hasErrors()){
	            return "event/eventform";
	        }

	        Event savedEvent = eventService.saveOrUpdateEventForm(eventForm);

	        return "redirect:/event/show/" + savedEvent.getId();
	    }

	    @RequestMapping("/event/delete/{id}")
	    public String delete(@PathVariable String id){
	        eventService.delete(id);
	        return "redirect:/event/list";
	    }
	

}
