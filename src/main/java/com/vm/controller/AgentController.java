package com.vm.controller;

import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.entity.Agent;
import com.entity.enumeration.AgentLevel;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.system.service.UserBiz;
import com.util.ResultMapUtil;
import com.vm.service.AgentBiz;

@Controller
@RequestMapping("agent.do")
public class AgentController {
	private Logger log = Logger.getLogger(AgentController.class);

    @Autowired
    private AgentBiz agentBiz;
    @Autowired
    private UserBiz userBiz;
    
    @InitBinder    
    protected void initBinder(WebDataBinder binder) {
    	binder.registerCustomEditor(AgentLevel.class, new PropertyEditorSupport(){
    		@Override  
		    public void setAsText(String text) throws IllegalArgumentException {  
    			if(!StringUtils.isEmpty(text))
    				this.setValue(AgentLevel.valueOf(text));  
		    }  
		    @Override  
		    public String getAsText() {  
		    	AgentLevel value = (AgentLevel)this.getValue();  
		        return value.name();  
		    }  
    	});
    }
    
    @RequiresPermissions("agent:query")
    @RequestMapping("agent.view")
    public String agentView(Model model) {
        return "forward:/agent.do/agent.view/0/10";
    }

    @RequiresPermissions("agent:query")
    @RequestMapping("agent.view/{pageNum}/{pageSize}")
    public String agentView(Agent agent,Model m,@PathVariable("pageNum") int pageNum,
    		@PathVariable("pageSize") int pageSize) {
    	PageHelper.startPage(pageNum, pageSize);
    	List<Agent> list =  agentBiz.find(agent);
    	PageInfo<Agent> page = new PageInfo<Agent>(list);
        m.addAttribute("agentList", list);
        m.addAttribute("page", page);
        return "/vm/agent/agent";
    }

    @RequiresPermissions("agent:add")
    @RequestMapping("agent_add.view")
    public String agentAddView(Model m) {
    	m.addAttribute("agent", new Agent());
    	m.addAttribute("agentLevel", AgentLevel.values());
    	m.addAttribute("userList", userBiz.findByRole("agent"));
        return "/vm/agent/agent_add";
    }

    @RequiresPermissions("agent:update")
    @RequestMapping("agent_update.view")
    public String agentUpdateView(Long id,Model m) {
    	m.addAttribute("agentLevel", AgentLevel.values());
    	m.addAttribute("agent",agentBiz.findById(id));
    	m.addAttribute("userList", userBiz.findByRole("agent"));
        return "/vm/agent/agent_update";
    }

    @RequiresPermissions("agent:add")
    @RequestMapping("add")
    public String add(Agent agent) {
        agentBiz.add(agent);
        return "redirect:/agent.do/agent.view";
    }

    @RequiresPermissions("agent:update")
    @RequestMapping("update")
    public String update(Agent agent) {
        agentBiz.update(agent);
        return "redirect:/agent.do/agent.view";
    }

    @RequiresPermissions("agent:delete")
    @RequestMapping("delete")
    public String delete(Long id) {
        agentBiz.delete(id);
        return "redirect:/agent.do/agent.view";
    }
    
    
    @SuppressWarnings("resource")
	@RequiresPermissions("agent:import")
    @RequestMapping("import")
    @ResponseBody
    @Transactional()
    public Map<String,Object> imp(@RequestParam("file") CommonsMultipartFile file){
    	Workbook wb = null;
    	try {
    		boolean isExcel2007 = file.getOriginalFilename().matches("^.+\\.(?i)(xlsx)$");  
    		if(isExcel2007) {
    			wb = new XSSFWorkbook(file.getInputStream());  
    		} else {
    			wb = new HSSFWorkbook(file.getInputStream());  
    		}
			Sheet sheet = wb.getSheetAt(0);   
			int rowNum = sheet.getLastRowNum();
			for(int i=1;i<=rowNum;i++) {
				Row row = sheet.getRow(i);
				String level = row.getCell(0).getStringCellValue();
				AgentLevel agentLevel = AgentLevel.parse(level);
				String agentName = row.getCell(1).getStringCellValue();
				if(StringUtils.isEmpty(agentName)) continue;
				String area = row.getCell(2).getStringCellValue();
				Agent agent = new Agent(agentLevel,agentName,area);
				
				agentBiz.add(agent);
			}
		} catch(NullPointerException e) {
			log.error("上传文件解析失败",e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return ResultMapUtil.getFailMap("字段不允许为空");
		} catch (IOException e) {
			log.error("上传文件解析失败",e);
			return ResultMapUtil.getFailMap();
		}
    	return ResultMapUtil.getSuccessMap();
    }
}
