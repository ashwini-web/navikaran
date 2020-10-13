package com.capgemini.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/upload")
public class UploadController {

    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "C://Temp//";

    @RequestMapping(method = RequestMethod.GET)
	@ResponseBody
    public ModelAndView index(ModelMap modelMap) {
    	ModelAndView model = new ModelAndView("upload");
		return model;
    }

    @RequestMapping(value="/upload",method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {
    	  ModelAndView model = new ModelAndView("redirect:/uploadStatus");
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return model;
        }

        try {

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");

        } catch (IOException e) {
            e.printStackTrace();
        }
      
		return model;      
    }
    @RequestMapping(method = RequestMethod.GET,value="/uploadStatus")
	@ResponseBody   
    public ModelAndView uploadStatus() {
    	 ModelAndView model = new ModelAndView("uploadStatus");
        return model;
    }

}