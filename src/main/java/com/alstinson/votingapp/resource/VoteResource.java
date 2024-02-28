package com.alstinson.votingapp.resource;

import com.alstinson.votingapp.dto.VoterSurveyInfo;
import com.alstinson.votingapp.dto.AdminSurveyInfoLite;
import com.alstinson.votingapp.dto.VoterSurveyInfoLite;
import com.alstinson.votingapp.model.Survey;
import com.alstinson.votingapp.model.SurveyOption;
import com.alstinson.votingapp.service.GetSurveyService;
import com.alstinson.votingapp.service.VoteService;
import jakarta.ws.rs.Consumes;
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
import java.util.UUID;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Path("/{code}/survey")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VoteResource {

    VoteService voteService;
    GetSurveyService getSurveyService;

    @GET
    public List<VoterSurveyInfoLite> getAllSurveys(@PathParam("code") String code) {
        return getSurveyService.getAll(code);
    }

    @GET
    @Path("{survey}")
    public VoterSurveyInfo getSurvey(@PathParam("survey") String survey, @PathParam("code") String code) {
        return getSurveyService.get(survey, code);
    }

    @POST
    @Path("{survey}/{option}")
    public void vote(@PathParam("survey") String survey, @PathParam("option") int option,@PathParam("code") String code) {
        voteService.vote(survey, option, code);
    }
}
