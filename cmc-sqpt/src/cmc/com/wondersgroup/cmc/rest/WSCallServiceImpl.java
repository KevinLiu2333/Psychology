package com.wondersgroup.cmc.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.wondersgroup.cmc.dispatch.message.service.MessageRouterService;
import com.wondersgroup.cmc.rest.dto.MessageDTO;

@Component
@Scope("prototype")
@Path("/message")
public class WSCallServiceImpl {
	@Autowired
	@Qualifier("messageRouterService")
    public MessageRouterService messageRouterService;

	public MessageRouterService getMessageRouterService() {
		return messageRouterService;
	}

	public void setMessageRouterService(MessageRouterService messageRouterService) {
		this.messageRouterService = messageRouterService;
	}

	@POST
	@Path("/jaxb")
	@Produces({"text/plain"})
	@Consumes({"application/xml", "application/json"})
	public Response doJAXBMessage(JAXBElement<MessageDTO> element){
		MessageDTO messageDTO = (MessageDTO)element.getValue();
		Object handler = messageRouterService.route(messageDTO.getWsName(), messageDTO.getMethodName(), messageDTO.getParams());
		return Response.status(Response.Status.OK).entity(handler).build();
	}
}
