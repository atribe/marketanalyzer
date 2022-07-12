package com.ar.marketanalyzer.controllers;

import com.ar.marketanalyzer.backtest.models.models.AbstractModel;
import com.ar.marketanalyzer.backtest.services.AbstractModelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/")
public class RootController {
    private final AbstractModelService abstractModelService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView rootView() throws Exception {
        ModelAndView mav = new ModelAndView();
        //the view name is the name of the jsp

        List<AbstractModel> modelList = abstractModelService.getAll();

        log.info(modelList.toString());

        mav.addObject("modelList", modelList);

        mav.setViewName("index");
        return mav;
    }

    @RequestMapping(value = "dday", method = RequestMethod.GET)
    public ModelAndView ddayView() throws Exception {
        ModelAndView mav = new ModelAndView();
        //the view name is the name of the jsp

        List<AbstractModel> modelList = abstractModelService.getAll();

        log.info(modelList.toString());

        mav.addObject("modelList", modelList);

        mav.setViewName("index");
        return mav;
    }

    @RequestMapping(value = "combined", method = RequestMethod.GET)
    public ModelAndView combinedView() throws Exception {
        ModelAndView mav = new ModelAndView();
        //the view name is the name of the jsp

        List<AbstractModel> modelList = abstractModelService.getAll();

        log.info(modelList.toString());

        mav.addObject("modelList", modelList);

        mav.setViewName("index");
        return mav;
    }
}
