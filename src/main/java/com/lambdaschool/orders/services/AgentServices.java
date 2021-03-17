package com.lambdaschool.orders.services;

import com.lambdaschool.orders.models.Agent;
import com.lambdaschool.orders.models.Customer;

import java.util.List;

public interface AgentServices {


//   http://localhost:2019/agents/agent/9

   Agent getAgentByID(long agentid);



   Agent save(Agent agent);
}

