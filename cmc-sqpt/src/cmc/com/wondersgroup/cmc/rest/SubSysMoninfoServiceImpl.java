package com.wondersgroup.cmc.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.wondersgroup.cmc.dispatch.subsys.service.SubsysService;
import com.wondersgroup.framework.core.web.vo.VOUtils;

@Component
@Path("/subsysinfo")
public class SubSysMoninfoServiceImpl{
	@Autowired
	@Qualifier("subsysService")
    public SubsysService subsysService;

	public void setSubsysService(SubsysService subsysService) {
		this.subsysService = subsysService;
	}

	@POST
	@Path("/getall")
	@Produces({"application/json"})
	@Consumes({"application/json"})
	public Response doGetAllSubsysinfo(String token){
		return Response.status(Response.Status.OK).entity(VOUtils.getJsonDataFromCollection(subsysService.getAllSubsysinfo())).build();
	}
}
