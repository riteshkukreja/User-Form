/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mettl.learning.userform.services;

import com.mettl.learning.userform.models.User;
import java.io.File;
import java.util.List;

/**
 *
 * @author admin
 */
public interface FileImporterInterface {
    
    public Boolean isFileExists(File file);
    public List<User> readContent(File file) throws Exception;
    
}
