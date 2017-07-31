package com.wondersgroup.cmc.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.wondersgroup.cmc.dispatch.message.service.MessageRouterService;
import com.wondersgroup.cmc.dispatch.token.service.TokenService;
import com.wondersgroup.cmc.utils.Base64Utils;
import com.wondersgroup.cmc.utils.StringUtils;
import com.wondersgroup.framework.core.web.vo.VOUtils;

@Component
@Path("/")
@Scope("prototype")
public class RestServiceImpl{
	@Autowired
	@Qualifier("tokenService")
    public TokenService tokenService;
	
	@Autowired
	@Qualifier("jkfwmessageRouterService")
    public MessageRouterService messageRouterService;
	
	@POST
	@Path("/{wsName}")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response doJsonMessage(@PathParam("wsName") String wsName,String message){
		return Response.status(Response.Status.OK).entity(this.dealMessage(wsName, message)).build();
	}
	
	@POST
	@Path("/{wsName}")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	public Response doFormMessage(@PathParam("wsName") String wsName,@FormParam("message") String message){
		return Response.status(Response.Status.OK).entity(this.dealMessage(wsName, message)).build();
	}
	
	@GET
	@Path("/{wsName}")
	@Consumes({MediaType.TEXT_PLAIN})
	@Produces({MediaType.APPLICATION_JSON})
	public Response doUrlMessage(@PathParam("wsName") String wsName,@QueryParam("message") String message){
		return Response.status(Response.Status.OK).entity(this.dealMessage(wsName, message)).build();
	}
	
	/**
	 * 统一处理消息
	 * @param wsName
	 * @param message
	 * @return
	 */
	private Object dealMessage(String wsName,String message){
		Object handler = messageRouterService.route(wsName, null, new String[]{message});
		return Base64Utils.encodeString(StringUtils.createBytes(VOUtils.getJsonData(handler)));
	}
}
