package com.alstinson.votingapp.resource;

import com.alstinson.votingapp.dto.AdminSurveyInfoLite;
import com.alstinson.votingapp.dto.CreateSurvey;
import com.alstinson.votingapp.dto.AdminSurveyInfo;
import com.alstinson.votingapp.service.ActiveSurveyService;
import com.alstinson.votingapp.service.CreateSurveyService;
import com.alstinson.votingapp.service.DeleteSurveyService;
import com.alstinson.votingapp.service.GetSurveyService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
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
@Path("/admin/survey")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AdminSurveyResource {

    GetSurveyService getSurveyService;
    CreateSurveyService createSurveyService;
    DeleteSurveyService deleteSurveyService;
    ActiveSurveyService activeSurveyService;

    @GET
    public List<AdminSurveyInfoLite> getAllSurveys() {
        return getSurveyService.adminGetAll();
    }

    @GET
    @Path("/{survey}")
    public AdminSurveyInfo getSurvey(@PathParam("survey") String surveyCode) {
        return getSurveyService.adminGet(surveyCode);
    }

    @POST
    public AdminSurveyInfo createSurvey(CreateSurvey survey) {
        return createSurveyService.apply(survey);
    }

    @DELETE
    @Path("/{survey}")
    public void deleteSurvey(@PathParam("survey") String surveyCode) {
        deleteSurveyService.delete(surveyCode);
    }

    @POST
    @Path("/{survey}/enable")
    public void enable(@PathParam("survey") String surveyCode) {
        activeSurveyService.enable(surveyCode);
    }

    @POST
    @Path("/{survey}/disable")
    public void disable(@PathParam("survey") String surveyCode) {
        activeSurveyService.disable(surveyCode);
    }

}
