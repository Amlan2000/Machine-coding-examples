package org.main;


import Entities.ClientRequest;
import Entities.Provider;
import Entities.Template;
import Enums.ChannelEnum;
import Enums.ProviderAvailability;
import Enums.TemplateNameEnum;
import Services.*;
import Services.Impl.*;

import java.util.Arrays;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello, there. Have a great day ahead! \n");


        CustomerService customerService = new CustomerServiceImpl();

        customerService.addCustomer("1","Anisha");
        customerService.addCustomer("2","Sameer");
        customerService.addCustomer("3","Gayathri");


        ProviderService providerService = new ProviderServiceImpl();

        Provider provider1 = Provider.builder().providerID("1").providerName("Gupshup").build();
        HashMap<ChannelEnum, ProviderAvailability> channelProviderMap = new HashMap<>();
        channelProviderMap.put(ChannelEnum.SMS,ProviderAvailability.UP);
        channelProviderMap.put(ChannelEnum.EMAIL,ProviderAvailability.DOWN);
        channelProviderMap.put(ChannelEnum.WHATSAPP,ProviderAvailability.UP);

        provider1.setProviderChannelAvailabilityHashMap(channelProviderMap);
        providerService.addProvider(provider1);


        Provider provider2 = Provider.builder().providerID("2").providerName("Twilio").build();
        HashMap<ChannelEnum, ProviderAvailability> channelProviderMap2 = new HashMap<>();
        channelProviderMap.put(ChannelEnum.SMS,ProviderAvailability.UP);
        channelProviderMap.put(ChannelEnum.WHATSAPP,ProviderAvailability.UP);

        provider2.setProviderChannelAvailabilityHashMap(channelProviderMap2);
        providerService.addProvider(provider2);

        TemplateResolutionService templateResolutionService = new TemplateResolutionServiceImpl();

        Template template1= Template.builder().templateID("1").templateName(TemplateNameEnum.LOGIN_OTP).channel(ChannelEnum.SMS).templateBody("From SMS. Your OTP is: {otp}").build();
        Template template2= Template.builder().templateID("2").templateName(TemplateNameEnum.LOGIN_OTP).channel(ChannelEnum.EMAIL).templateBody("From EMAIL. Subject: OTP for login. Body: Your OTP is: {otp}").build();
        Template template3= Template.builder().templateID("3").templateName(TemplateNameEnum.LOGIN_OTP).channel(ChannelEnum.WHATSAPP).templateBody("From whatsapp. Your OTP is: {otp}").build();

        Template template4= Template.builder().templateID("4").templateName(TemplateNameEnum.GREETING).channel(ChannelEnum.SMS).templateBody("From SMS. Hi {name}").build();
        Template template5= Template.builder().templateID("5").templateName(TemplateNameEnum.GREETING).channel(ChannelEnum.WHATSAPP).templateBody("From whatsapp. Hi {name}").build();

        templateResolutionService.setTemplate(template1);
        templateResolutionService.setTemplate(template2);
        templateResolutionService.setTemplate(template3);
        templateResolutionService.setTemplate(template4);
        templateResolutionService.setTemplate(template5);

        ProviderSelectionStrategy providerSelectionStrategy = new RandomSelectionStrategy();

        NotificationService notificationService = new NotificationServiceImpl(providerService,providerSelectionStrategy);
        HashMap<String, String> templateParamteres = new HashMap<>();
        templateParamteres.put("otp","12345");
        templateParamteres.put("name","Anisha");

        ClientRequest request1 = ClientRequest.builder().recipientInfo(customerService.getCustomer("1")).templateName(TemplateNameEnum.LOGIN_OTP).templateParametersMap(templateParamteres).channel(ChannelEnum.SMS).build();
        ClientRequest request2 = ClientRequest.builder().recipientInfo(customerService.getCustomer("2")).templateName(TemplateNameEnum.GREETING).templateParametersMap(templateParamteres).channel(ChannelEnum.WHATSAPP).build();


        notificationService.sendNotification(request2);
        notificationService.sendNotification(request1);






    }
}
