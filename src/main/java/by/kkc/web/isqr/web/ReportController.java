package by.kkc.web.isqr.web;

import by.kkc.web.isqr.model.Comment;
import by.kkc.web.isqr.repository.CommentRepository;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/report/")
public class ReportController {


  //  private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    CommentRepository commentRepository;

    @RequestMapping(method = RequestMethod.GET , value = "pdf")
    public ModelAndView generatePdfReport(ModelAndView modelAndView){

       // logger.debug("--------------generate PDF report----------");

        Map<String,Object> parameterMap = new HashMap<String,Object>();

        List<Comment> commentList = (List<Comment>) commentRepository.findAll();

        JRDataSource JRdataSource = new JRBeanCollectionDataSource(commentList);

        parameterMap.put("datasource", JRdataSource);

        //pdfReport bean has ben declared in the jasper-views.xml file
        modelAndView = new ModelAndView("pdfReport", parameterMap);

        return modelAndView;

    }//generatePdfReport



    @RequestMapping(method = RequestMethod.GET , value = "xls")
    public ModelAndView generateXlsReport(ModelAndView modelAndView){

      //  logger.debug("--------------generate XLS report----------");

        Map<String,Object> parameterMap = new HashMap<String,Object>();

        List<Comment> commentList = (List<Comment>) commentRepository.findAll();

        JRDataSource JRdataSource = new JRBeanCollectionDataSource(commentList);

        parameterMap.put("datasource", JRdataSource);

        //xlsReport bean has ben declared in the jasper-views.xml file
        modelAndView = new ModelAndView("xlsReport", parameterMap);

        return modelAndView;

    }//generatePdfReport


    @RequestMapping(method = RequestMethod.GET , value = "csv")
    public ModelAndView generateCsvReport(ModelAndView modelAndView){

  //      logger.debug("--------------generate CSV report----------");

        Map<String,Object> parameterMap = new HashMap<String,Object>();

        List<Comment> commentList = (List<Comment>) commentRepository.findAll();

        JRDataSource JRdataSource = new JRBeanCollectionDataSource(commentList);;

        parameterMap.put("datasource", JRdataSource);

        //xlsReport bean has ben declared in the jasper-views.xml file
        modelAndView = new ModelAndView("csvReport", parameterMap);

        return modelAndView;

    }//generatePdfReport



    @RequestMapping(method = RequestMethod.GET , value = "html")
    public ModelAndView generateHtmlReport(ModelAndView modelAndView){

    //    logger.debug("--------------generate HTML report----------");

        Map<String,Object> parameterMap = new HashMap<String,Object>();

        List<Comment> commentList = (List<Comment>) commentRepository.findAll();

        JRDataSource JRdataSource = new JRBeanCollectionDataSource(commentList);

        parameterMap.put("datasource", JRdataSource);

        //xlsReport bean has ben declared in the jasper-views.xml file
        modelAndView = new ModelAndView("htmlReport", parameterMap);

        return modelAndView;

    }//generatePdfReport


}//ReportController
