package com.alstinson.votingapp.resource;

import com.alstinson.votingapp.service.CodeService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Path("/admin/code")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AdminCodeResource {

    CodeService codeService;

    @GET
    public List<String> get() {
        return codeService.getAll();
    }

    @POST
    @Path("/generate")
    public List<String> generate(int quantity) {
        return codeService.generate(quantity);
    }

    @DELETE
    public void delete() {
        codeService.deleteAll();
    }
}
