package com.vm.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.entity.Agent;
import com.entity.enumeration.AgentLevel;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vm.service.AgentBiz;

@Controller
@RequestMapping("agent.do")
public class AgentController {
	private Logger log = Logger.getLogger(AgentController.class);

    @Autowired
    private AgentBiz agentBiz;
    
    @RequiresPermissions("agent:query")
    @RequestMapping("agent.view")
    public String agentView(Model model) {
        return "forward:/agent.do/agent.view/0/10";
    }

    @RequiresPermissions("agent:query")
    @RequestMapping("agent.view/{pageNum}/{pageSize}")
    public String agentView(Agent agent,Model m,@PathVariable("pageNum") int pageNum,
    		@PathVariable("pageNum") int pageSize) {
    	PageHelper.startPage(pageNum, pageSize);
    	List<Agent> list =  agentBiz.find(agent);
    	PageInfo<Agent> page = new PageInfo<Agent>(list);
        m.addAttribute("agentList", list);
        m.addAttribute("page", page);
        m.addAttribute("agentLevel", AgentLevel.values());
        return "/vm/agent/agent";
    }

    @RequiresPermissions("agent:add")
    @RequestMapping("agent_add.view")
    public String agentAddView(Model m) {
        return "/vm/agent/agent_add";
    }

    @RequiresPermissions("agent:update")
    @RequestMapping("agent_update.view")
    public String agentUpdateView(Long id,Model m) {
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
}
