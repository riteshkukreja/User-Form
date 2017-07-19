/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mettl.learning.userform.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mettl.learning.userform.models.User;
import java.io.File;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Ritesh
 */
@Component
public class JSONFileImporter implements FileImporterInterface {
    
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public Boolean isFileExists(File file) {
        return (file.exists() && !file.isDirectory() && (file.getName().endsWith(".json")));
    }

    @Override
    public List<User> readContent(File file) throws Exception {
        if(!isFileExists(file))
            throw new Exception("File not found!");
        
        List<User> users = mapper.readValue(file, new TypeReference<List<User>>(){});
        return users;
    }
    
}
