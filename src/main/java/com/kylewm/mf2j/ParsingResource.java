package com.kylewm.mf2j;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableMap;

@Path("/")
@Produces(MediaType.APPLICATION_JSON + "; charset=utf-8")
public class ParsingResource {

    public ParsingResource() {
        
    }
    
    @GET
    public String parse(@QueryParam("url") Optional<String> url) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter printer = mapper.writerWithDefaultPrettyPrinter();
        try {
            if (url.isPresent()) {
                Parser parser = new com.kylewm.mf2j.Parser();
                try {
                    return printer.writeValueAsString(parser.parse(new URI(url.get())));
                } catch (IOException e) {
                    e.printStackTrace();
                    return printer.writeValueAsString(ImmutableMap.of("error", (Object) e.getMessage()));
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                    return printer.writeValueAsString(ImmutableMap.of("error", (Object) e.getMessage()));
                }
            }
            else {
                return printer.writeValueAsString(Collections.emptyMap());
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return String.format("{\"error\": \"%s\"}", e.getMessage().replace("\"", "\\\""));
        }
    }
    
}
