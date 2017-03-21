package com.vm.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.entity.Car;
import com.entity.Procurement;
import com.entity.Standard;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.upload.FileUpload;
import com.upload.Message;
import com.util.ResultMapUtil;
import com.vm.service.ProcurementBiz;

@Controller
@RequestMapping("procurement.do")
public class ProcurementController {
	private Logger log = Logger.getLogger(ProcurementController.class);

    @Autowired
    private ProcurementBiz procBiz;
    @Autowired
	private FileUpload fileUpload;
    
    @RequiresPermissions("procurement:query")
    @RequestMapping("procurement.view")
    public String ProcurementViewForword(Model model) {
        return "forward:/procurement.do/procurement.view/0/10";
    }
    
    @RequiresPermissions("procurement:query")
    @RequestMapping("procurement.view/{pageNum}/{pageSize}")
    public String ProcurementView(Procurement procurement,Model m,@PathVariable("pageNum") int pageNum,
    		@PathVariable("pageSize") int pageSize) {
    	
    	PageHelper.startPage(pageNum, pageSize);
    	List<Procurement> list =  procBiz.find(procurement);
    	PageInfo<Procurement> page = new PageInfo<Procurement>(list);
        m.addAttribute("procurementList", list);
        m.addAttribute("page", page);
        return "/vm/procurement/procurement";
    }
    
    @RequiresPermissions("procurement:add")
    @RequestMapping("procurement_add.view")
    public String procurementAddView(Model m) {
    	
        return "/vm/procurement/procurement_add";
    }
    @RequiresPermissions("procurement:add")
    @RequestMapping("add")
    public String add(Procurement procurement) {
    	procBiz.add(procurement);
        return "redirect:/procurement.do/procurement.view";
    }
    
    @RequiresPermissions("procurement:update")
    @RequestMapping("procurement_update.view")
    public String procurementUpdateView(Long id,Model m) {
    	m.addAttribute("procurement",procBiz.findById(id));
        return "/vm/procurement/procurement_update";
    }
    
    @RequiresPermissions("procurement:update")
    @RequestMapping("update")
    public String update(Procurement procurement) {
    	procBiz.update(procurement);
        return "redirect:/procurement.do/procurement.view";
    }

    @RequiresPermissions("procurement:delete")
    @RequestMapping("delete")
    public String delete(Long id) {
    	procBiz.delete(id);
        return "redirect:/procurement.do/procurement.view";
    }
    
    @SuppressWarnings("resource")
	@RequiresPermissions("procurement:import")
    @RequestMapping("import")
    @ResponseBody
    @Transactional()
    public Message imp(HttpServletRequest request){
    	
    	return fileUpload.fileUploadFile(request,"procurement");
    }
}
