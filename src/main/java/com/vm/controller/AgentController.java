package com.vm.controller;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.Agent;
import com.entity.Standard;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.util.ResultMapUtil;
import com.vm.service.AgentBiz;
import com.vm.service.StandardBiz;

@Controller
@RequestMapping("agent.do")
public class AgentController {
	private Logger log = Logger.getLogger(AgentController.class);

    @Autowired
    private AgentBiz agentBiz;
    @Autowired
    private StandardBiz standardBiz;

    @RequiresPermissions("agent:query")
    @RequestMapping("agent.view/{pageNum}/{pageSize}")
    public String agentView(Agent agent,Model m,@PathVariable("pageNum") int pageNum,
    		@PathVariable("pageNum") int pageSize) {
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
    	m.addAttribute("standardList", standardBiz.find(new Standard()));
        return "/vm/agent/agent_add";
    }

    @RequiresPermissions("agent:update")
    @RequestMapping("agent_update.view")
    public String agentUpdateView(Long id,Model m) {
    	m.addAttribute("standardList", standardBiz.find(new Standard()));
    	m.addAttribute("agent",agentBiz.findById(id));
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
    
    @RequiresPermissions("agent:delete")
    @RequestMapping("test")
    @ResponseBody
    public Map<String,Object> test(@RequestBody String json) {
        log.info(json.toString());
        return ResultMapUtil.getSuccessMap("123");
    }
}
