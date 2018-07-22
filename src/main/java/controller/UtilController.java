package controller;

import Utils.VerifyCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
public class UtilController {

    @Autowired
    private VerifyCode verifyCode;

    @RequestMapping("/VerifyCodeCreate")
    public void VerifyCodeCreate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedImage image = verifyCode.getImage();
        VerifyCode.output(image, response.getOutputStream());
        request.getSession().setAttribute("vCode", verifyCode.getText());

    }



}
