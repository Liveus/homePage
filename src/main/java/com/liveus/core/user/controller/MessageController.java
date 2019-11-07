package com.liveus.core.user.controller;

import com.liveus.core.user.pojo.entity.Message;
import com.liveus.core.user.service.MessageService;
import com.liveus.common.utils.DateTransfrom;
import com.liveus.common.utils.IpDetective;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RequestMapping("/message")
@RestController
public class MessageController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    MessageService messageService;

    @RequestMapping(value = "/newMessage"/*,method = RequestMethod.POST*/)
    @ResponseBody
    @ApiOperation(value = "增加新留言",httpMethod = "POST",notes = "增加新留言")
    public String newMessage(@RequestParam String context){
        String ip = IpDetective.getIpAddr(request);
        String timestamp = messageService.ipMonitoring(ip);
        if(!timestamp.equals("noRecord")){
            Date date = new Date();
            Long a = DateTransfrom.seconds(date,DateTransfrom.
                    StringToDate(timestamp));
            if(a<10000){
                return "请等待一定时间再留言！";
            }
        }
        Message message = new Message();
        message.setContext(context);
        message.setIp(ip);
        message.setMessagetimestamp(Calendar.getInstance().getTime());
        return  messageService.newMessage(message);
    }

    @RequestMapping(value = "/allMessages",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "获取所有留言",httpMethod = "POST",notes = "获取所有留言")
    public List<Message> getAllMessages(){
        List<Message> messages = messageService.getAllMessagesOrderByTime();
        return messages;
    }
}
