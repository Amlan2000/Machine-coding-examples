package Services.Impl;

import Entities.Template;
import Enums.ChannelEnum;
import Enums.TemplateNameEnum;
import Exceptions.NotificationException;
import Services.TemplateResolutionService;

import java.util.HashMap;
import java.util.Map;

public class TemplateResolutionServiceImpl implements TemplateResolutionService {

    private final Map<String, Template> templateMap = new HashMap<>();

    @Override
    public void setTemplate(Template template) {

        String key = getKey(
                template.getTemplateName(),
                template.getChannel()
        );

        templateMap.put(key, template);
    }

    @Override
    public String resolveTemplate(TemplateNameEnum templateName, ChannelEnum channel, Map<String, String> parameters) {

        String key = getKey(templateName, channel);

        Template template = templateMap.get(key);

        if(template == null) {
            throw new NotificationException("Template not found");
        }

        String resolvedMessage = template.getTemplateBody();

        for(Map.Entry<String, String> entry : parameters.entrySet()) {

            String placeholder = "{" + entry.getKey() + "}";

            resolvedMessage = resolvedMessage.replace(placeholder, entry.getValue());
        }

        return resolvedMessage;
    }

    private String getKey(
            TemplateNameEnum templateName,
            ChannelEnum channel
    ) {
        return templateName + "_" + channel;
    }
}