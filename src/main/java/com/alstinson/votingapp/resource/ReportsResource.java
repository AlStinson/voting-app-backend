package com.alstinson.votingapp.resource;

import com.alstinson.votingapp.dto.AdminSurveyInfo;
import com.alstinson.votingapp.dto.VoterSurveyInfoLite;
import com.alstinson.votingapp.service.GetSurveyService;
import com.alstinson.votingapp.service.ReportsService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReportsResource {

    ReportsService reportsService;

    @GET
    @Path("/reports")
    public List<AdminSurveyInfo> getReports() {
        return reportsService.getReports();
    }
}
