package controllers;

import play.*;
import play.mvc.*;
import play.mvc.Http.*;
import play.mvc.Http.MultipartFormData.*;

import views.html.*;

import java.io.*;

import models.*;

public class Application extends Controller {
  
    public static Result index() {
        return ok(index.render("OpenShade"));
    }

    public static Result upload() {
 		MultipartFormData body = request().body().asMultipartFormData();
  		FilePart input = body.getFile("input");
  		if (input != null) {
    		String fileName = input.getFilename();
    		String contentType = input.getContentType(); 
    		File file = input.getFile();
    		SeqFile seq = new SeqFile(file);
    		return ok("File uploaded");
  		} else {
    		flash("error", "Missing file");
    		return redirect(routes.Application.index());    
  		}
	}
  
}
